package de.kiridevs.ksmpplugin.features;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.Listener;
import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Bukkit;

import static net.kyori.adventure.text.Component.text;

public class DeathMessage implements Listener {
    private static final String MESSAGE_TEXT = "You died at";

    private final KiriSmpPlugin plugin;

    public DeathMessage(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    private static Component coordComponent(char dimension, double value) {
        Component comp = text("%s: ".formatted(dimension), NamedTextColor.GRAY)
            .append(text(Math.round(value), NamedTextColor.AQUA));
        return comp;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent) {
        Player player = deathEvent.getPlayer();
        Location loc = player.getLocation();

        Component prefix = text("[", NamedTextColor.DARK_GRAY)
                .append(text("kiriSMP", NamedTextColor.GREEN, TextDecoration.BOLD))
                .append(text("]", NamedTextColor.DARK_GRAY));

        player.sendMessage(
                prefix
                .appendSpace().append(text(MESSAGE_TEXT, NamedTextColor.WHITE))
                .appendSpace().append(coordComponent('X', loc.getX()))
                .appendSpace().append(coordComponent('Y', loc.getY()))
                .appendSpace().append(coordComponent('Z', loc.getZ()))
        );
    }

    public void init() {
        this.plugin.log.info("features: DragonBuff: Initializing");
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }
}
