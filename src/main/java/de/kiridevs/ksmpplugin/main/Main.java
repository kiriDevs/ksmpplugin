package de.kiridevs.ksmpplugin.main;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin {
    private static Logger log;

    @Override
    public void onEnable() {
        this.log = this.getLogger();
    }

}
