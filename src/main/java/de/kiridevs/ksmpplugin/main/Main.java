package de.kiridevs.ksmpplugin.main;

import de.kiridevs.ksmpplugin.recipes.BundleRecipes;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static Logger log;

    public void initRecipes() {
        new BundleRecipes(this).register();
    }

    @Override
    public void onEnable() {
        log = this.getLogger();

        log.info("Registering custom recipes...");
        this.initRecipes();
    }

}
