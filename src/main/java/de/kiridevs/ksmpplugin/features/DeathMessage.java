package de.kiridevs.ksmpplugin.features;

import org.bukkit.event.Listener;
import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.Bukkit;

public class DeathMessage implements Listener {
    private static final String MESSAGE_FORMAT = "You died at X=%d, Y=%d, Z=%d";

    private final KiriSmpPlugin plugin;

    public DeathMessage(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent deathEvent) {
        Player player = deathEvent.getPlayer();
        Location loc = player.getLocation();

        player.sendMessage(MESSAGE_FORMAT.formatted(
            (int) loc.getX(),
            (int) loc.getY(),
            (int) loc.getZ()
        ));
    }

    public void init(KiriSmpPlugin plugin) {
        this.plugin.log.info("features: DragonBuff: Initializing");
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }
}
