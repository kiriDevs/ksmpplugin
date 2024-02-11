package de.kiridevs.ksmpplugin;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.boss.DragonBattle;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Util {
    public static void applyDamageModifier(EntityDamageEvent event, double modifier) {
        double rawDamage = event.getDamage();
        double effectiveDamage = rawDamage * modifier;

        if (effectiveDamage == 0) event.setCancelled(true);
        else event.setDamage(effectiveDamage);
    }

    public static boolean entityIsPartOfFirstDragonBattle(Entity entity) {
        Location loc = entity.getLocation();
        World world = loc.getWorld();
        // The dragon battle happens on the end island
        if (!world.getBiome(loc).equals(Biome.THE_END)) return false;

        DragonBattle battle = world.getEnderDragonBattle();
        if (battle == null) return false; // Can't be part of a non-existing dragon fight
        return !battle.hasBeenPreviouslyKilled();
    }

    public static class HealthIndicators {
        public static final Map<Integer, NamedTextColor> HEALTH_COLOR_MAP = Map.ofEntries(
                Map.entry(3, NamedTextColor.GREEN),
                Map.entry(2, NamedTextColor.YELLOW),
                Map.entry(1, NamedTextColor.RED)
        );
        public static final Component HEALTH_SEPERATOR_COMPONENT = Component.text(" / ").color(NamedTextColor.BLUE);

        public static void update(Entity forEntity, NamespacedKey pdcKey, int maxHealth) {
            Component maxHealthComponent = Component.text(maxHealth).color(NamedTextColor.BLUE);
            Component suffixComponent = HEALTH_SEPERATOR_COMPONENT.append(maxHealthComponent);

            PersistentDataContainer pdc = forEntity.getPersistentDataContainer();
            int currentHealth = pdc.getOrDefault(pdcKey, PersistentDataType.INTEGER, maxHealth);
            NamedTextColor currentHealthColor = HEALTH_COLOR_MAP.getOrDefault(currentHealth, NamedTextColor.LIGHT_PURPLE);
            Component currentHealthComponent = Component.text(currentHealth).color(currentHealthColor);

            Component newHealthIndicator = currentHealthComponent.append(suffixComponent);
            forEntity.customName(newHealthIndicator);
            forEntity.setCustomNameVisible(true);
        }
    }
}
