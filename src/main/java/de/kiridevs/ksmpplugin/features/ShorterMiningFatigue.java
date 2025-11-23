package de.kiridevs.ksmpplugin.features;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class ShorterMiningFatigue implements Listener {
    public static final PotionEffectType GUARDIAN_FATIGUE_TYPE = PotionEffectType.SLOW_DIGGING;
    private static final int GUARDIAN_FATIGUE_AMPLIFIER = 2;
    private static final int GUARDIAN_FATIGUE_TICK_DURATION = 20 * 60 * 5;
    private static final int SHORTENED_DURATION = 20 * 60 * 2;

    private final KiriSmpPlugin plugin;

    public ShorterMiningFatigue(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void init() {
        this.plugin.log.info("features: ShorterMiningFatigue: Initializing");
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onMiningFatigue(EntityPotionEffectEvent event) {
        // Check if this is Mining Fatigue being added by an Elder Guardian
        if (!(event.getEntity() instanceof Player player)) return;
        if (!event.getAction().equals(EntityPotionEffectEvent.Action.ADDED)) return;
        if (!event.getCause().equals(EntityPotionEffectEvent.Cause.ATTACK)) return;

        PotionEffect effect = event.getNewEffect();
        if (effect == null) {
            plugin.log.warning("Added potion effect is null?");
            return;
        }

        if (!effect.getType().equals(ShorterMiningFatigue.GUARDIAN_FATIGUE_TYPE)) return;
        if (effect.getAmplifier() != GUARDIAN_FATIGUE_AMPLIFIER) return;
        if (effect.getDuration() != GUARDIAN_FATIGUE_TICK_DURATION) return;

        // Apply a shorter copy ...
        player.addPotionEffect(new PotionEffect(
            GUARDIAN_FATIGUE_TYPE, SHORTENED_DURATION,
            effect.getAmplifier(), effect.isAmbient(), effect.hasParticles()
        ));
        // ... and cancel adding the original effect
        event.setCancelled(true);
    }
}
