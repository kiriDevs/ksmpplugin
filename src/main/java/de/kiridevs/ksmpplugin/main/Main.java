package de.kiridevs.ksmpplugin.main;

import de.kiridevs.ksmpplugin.recipes.BundleRecipe;
import de.kiridevs.ksmpplugin.recipes.ElytraRecipe;
import de.kiridevs.ksmpplugin.recipes.SculkSensorRecipes;
import de.kiridevs.ksmpplugin.recipes.MusicDiscRecipes;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;


public class Main extends JavaPlugin {
    public static Logger log;

    public void initRecipes() {
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

        log.info("Registering custom recipes...");
        this.initRecipes();
    }

}
