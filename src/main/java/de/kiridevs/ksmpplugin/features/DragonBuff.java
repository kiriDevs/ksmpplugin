package de.kiridevs.ksmpplugin.features;

import de.kiridevs.ksmpplugin.Util;
import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import io.papermc.paper.event.block.DragonEggFormEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.projectiles.ProjectileSource;

import java.util.Map;

// This class groups all functionality responsible for making the EnderDragon stronger for the first dragon fight
public class DragonBuff implements Listener {
    // Damage is reduced across the board to adjust for player amount
    // This modifier is applied after cause-specific modifiers
    public static final Float INCOMING_MODIFIER = 0.5f;

    // This modifier is applied to all damage coming *from* the dragon,
    // or AreaEffectCloud|s of its breath
    public static final Float OUTGOING_MODIFIER = 2f;
    public static final int END_WORLDBORDER_SIZE = 59_999_968;

    static final Map<EntityDamageEvent.DamageCause, Float> incomingDamageModifiers = Map.ofEntries(
            // Explosions don't do any damage
            Map.entry(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION, 0f),
            Map.entry(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION, 0f),

            // Melee Attacks only deal 35% of the damage
            Map.entry(EntityDamageEvent.DamageCause.ENTITY_ATTACK, 0.35f),
            Map.entry(EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK, 0.35f)
    );

    final KiriSmpPlugin plugin;

    public DragonBuff(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    // This limits the world border during the fight
    @EventHandler
    public void onDimensionSwitch(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        World world = player.getWorld();
        if (world.getPlayerCount() > 1) return; // There are other players already, don't touch anything

        if (!Util.entityIsPartOfFirstDragonBattle(player)) return;
        DragonBattle battle = world.getEnderDragonBattle();
        assert battle != null;

        // Rename the first dragon due to its higher strength
        // Dragon might take a little while to spawn, so let's wait for that
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            EnderDragon dragon = battle.getEnderDragon();
            if (dragon == null) return; // The dragon somehow already died
            this.plugin.log.info("Changed dragon name");
            dragon.customName(Component.text("Elder Dragon").color(NamedTextColor.AQUA));
        }, 40); // 5t * 20t/s = 0.25s

        // Limit world border for the first dragon fight
        WorldBorder border = player.getWorld().getWorldBorder();
        border.setCenter(0, 0);
        border.setSize(500);
        border.setWarningDistance(50);
        border.setDamageAmount(2);
    }

    // This EventHandler is responsible for modifying any incoming damage (AGAINST the dragon)
    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        Entity target = event.getEntity();
        if (!(target instanceof EnderDragon)) return;
        if (!Util.entityIsPartOfFirstDragonBattle(target)) return;

        // Apply whatever causeModifier applies
        EntityDamageEvent.DamageCause dmgCause = event.getCause();
        float effectiveModifer = INCOMING_MODIFIER;
        for (EntityDamageEvent.DamageCause testCause : incomingDamageModifiers.keySet()) {
            if (!testCause.equals(dmgCause)) continue;

            Float causeModifier = incomingDamageModifiers.get(testCause);
            effectiveModifer = effectiveModifer * causeModifier;
        }

        Util.applyDamageModifier(event, effectiveModifer);
    }

    // This EventHandler is responsible for modifying any outgoing damage (FROM the dragon)
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        if (damager instanceof EnderDragon) {
            Util.applyDamageModifier(event, OUTGOING_MODIFIER);
        } else if (damager instanceof AreaEffectCloud) {
            AreaEffectCloud cloud = (AreaEffectCloud) event.getDamager();
            ProjectileSource source = cloud.getSource();
            if (source instanceof EnderDragon) Util.applyDamageModifier(event, OUTGOING_MODIFIER);
        }
    }

    // This resets the world border when the first dragon fight concludes
    @EventHandler
    public void onFirstDragonDeath(DragonEggFormEvent event) {
        WorldBorder border = event.getBlock().getWorld().getWorldBorder();
        border.setCenter(0, 0);
        border.setSize(END_WORLDBORDER_SIZE);
        border.setWarningDistance(5);
    }

    public void init() {
        this.plugin.log.info("features: DragonBuff: Initializing");
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }
}
