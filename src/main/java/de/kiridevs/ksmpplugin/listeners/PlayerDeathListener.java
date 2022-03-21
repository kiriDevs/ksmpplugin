package de.kiridevs.ksmpplugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    @EventHandler
    public static void onPlayerDeathEvent(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location loc = player.getLocation();

        String pref = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "kiriSMP" + ChatColor.DARK_GRAY + "] ";
        String text = ChatColor.AQUA + "You died at ";
        String xStr = "X: " + ChatColor.DARK_AQUA + loc.getBlockX() + ChatColor.AQUA + " ";
        String yStr = "Y: " + ChatColor.DARK_AQUA + loc.getBlockY() + ChatColor.AQUA + " ";
        String zStr = "Z: " + ChatColor.DARK_AQUA + loc.getBlockZ() + ChatColor.AQUA + "!";

        player.sendMessage(pref + text + xStr + yStr + zStr);
    }
}
