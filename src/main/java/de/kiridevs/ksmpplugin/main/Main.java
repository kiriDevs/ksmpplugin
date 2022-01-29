package de.kiridevs.ksmpplugin.main;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static Logger log;

    public void initRecipes() {
    }

    @Override
    public void onEnable() {
        log = this.getLogger();

        log.info("Registering custom recipes...");
        this.initRecipes();
    }

}
