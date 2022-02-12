package de.kiridevs.ksmpplugin.main;

import de.kiridevs.ksmpplugin.recipes.*;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static Logger log;
    public static Config config;

    public void initRecipes() {
        new BellRecipe(this).register();
        new BundleRecipe(this).register();
        new ElytraRecipe(this).register();
        new SculkSensorRecipes(this).register();

        for (Material discMaterial : MusicDiscRecipes.DiscMaterial) {
            new MusicDiscRecipes(this, discMaterial).register();
        }
    }

    @Override
    public void onEnable() {
        log = this.getLogger();

        log.info("Reading configuration file...");
        config = new Config(this);

        log.info("Registering custom recipes...");
        this.initRecipes();
    }
}
