package de.kiridevs.ksmpplugin.features;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPlaceEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import de.kiridevs.ksmpplugin.Util;
import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

// This class groups all functionality responsible for making EndCrystals stronger for the first dragon fight
public class EndCrystalBuff implements Listener {
    public static final int END_CRYSTAL_MAX_HEALTH = 3;

    KiriSmpPlugin plugin;
    NamespacedKey crystalHealthNsKey;

    public EndCrystalBuff(KiriSmpPlugin plugin) {
        this.plugin = plugin;
        this.crystalHealthNsKey = new NamespacedKey(this.plugin, "endcrystal_health");
    }

    @EventHandler
    public void onDimensionSwitch(PlayerChangedWorldEvent event) {
        Player player = event.getPlayer();
        if (player.getWorld().getPlayerCount() > 1) return; // There are other players already, don't touch anything
        if (!Util.entityIsPartOfFirstDragonBattle(player)) return;

        // Update (Initialize) health indicators for all end crystals
        World endWorld = player.getWorld();
        Collection<EnderCrystal> crystals = endWorld.getEntitiesByClass(EnderCrystal.class);
        for (EnderCrystal crystal : crystals) {
            Util.HealthIndicators.update(crystal, this.crystalHealthNsKey, END_CRYSTAL_MAX_HEALTH);
        }
    }

    // This handler creates health indicators for crystals placed during the fight
    @EventHandler
    public void onEntityPlace(EntityPlaceEvent event) {
        if (!(event.getEntity() instanceof EnderCrystal crystal)) return;
        if (!Util.entityIsPartOfFirstDragonBattle(crystal)) return;
        Util.HealthIndicators.update(crystal, this.crystalHealthNsKey, END_CRYSTAL_MAX_HEALTH);
    }

    // Disallow any damage coming from blocks
    @EventHandler
    public void onCrystalDamageByBlock(EntityDamageByBlockEvent event) {
        Entity damagee = event.getEntity();
        if (!(damagee instanceof EnderCrystal)) return; // No EnderCrystal => No action
        if (Util.entityIsPartOfFirstDragonBattle(damagee)) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        // Only act on EndCrystals in the first dragon fight
        Entity damagee = event.getEntity();
        if (!(damagee instanceof EnderCrystal crystal)) return;
        if (!Util.entityIsPartOfFirstDragonBattle(crystal)) return;

        // Disallow any non-player damage
        Entity damager = event.getDamager();
        if (!(damager instanceof Player player)) {
            event.setCancelled(true);
            return;
        }

        // Disallow any non-melee damage
        EntityDamageEvent.DamageCause cause = event.getCause();
        if (!cause.equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)) {
            event.setCancelled(true);
            return;
        }

        // Calculate custom crystal knockback
        Location crystalLocation = crystal.getLocation();
        Location playerLocation = player.getLocation();

        Vector delta = (playerLocation.subtract(crystalLocation)).toVector();
        double lengthSquared = delta.lengthSquared();
        Vector knockback = delta.multiply(1 / lengthSquared).multiply(10);

        // Create a small explosion, then apply customKnockback, schedule big boom for later
        World crystalWorld = crystal.getWorld();
        crystalWorld.createExplosion(crystalLocation, 3);
        player.setVelocity(knockback);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {
            crystalWorld.createExplosion(crystalLocation, 12);
        }, 6); // 4t * 20t/s = 0.25s

        // Reduce crystal health
        PersistentDataContainer pdc = crystal.getPersistentDataContainer();
        int previousHealth = pdc.getOrDefault(
                this.crystalHealthNsKey,
                PersistentDataType.INTEGER,
                END_CRYSTAL_MAX_HEALTH
        );
        int currentHealth = previousHealth - 1;
        if (currentHealth > 0) {
            pdc.set(this.crystalHealthNsKey, PersistentDataType.INTEGER, currentHealth);
            Util.HealthIndicators.update(crystal, crystalHealthNsKey, END_CRYSTAL_MAX_HEALTH);
            event.setCancelled(true); // Preserve the crystal entity
        }
    }

    public void init() {
        this.plugin.log.info("features: EndCrystalBuff: Initializing");
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }
}
