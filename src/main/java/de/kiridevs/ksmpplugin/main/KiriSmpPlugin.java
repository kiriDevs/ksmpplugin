package de.kiridevs.ksmpplugin.main;

import de.kiridevs.ksmpplugin.features.DragonBuff;
import de.kiridevs.ksmpplugin.features.EndCrystalBuff;
import de.kiridevs.ksmpplugin.recipes.*;
import io.papermc.paper.plugin.configuration.PluginMeta;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class KiriSmpPlugin extends JavaPlugin {

    public final Logger log;

    public KiriSmpPlugin() {
        super();
        this.log = this.getLogger();
    }

    private void registerRecipes() {
        ConfigurationSection craftingConfig =
            this.getConfig().getConfigurationSection("recipes.crafting");

        new ChainRecipes(this, craftingConfig.getConfigurationSection("chain")).register();

        if (!craftingConfig.getString("chainArmor").equals("false")) {
            new ChainArmorRecipes(this, craftingConfig.getString("chainArmor")).register();
        }

        if (craftingConfig.getBoolean("bell")) new BellRecipe(this).register();
        if (craftingConfig.getBoolean("saddle")) new SaddleRecipe(this).register();
        if (craftingConfig.getBoolean("bundle")) new BundleRecipe(this).register();

        ConfigurationSection stonecuttingConfig =
            this.getConfig().getConfigurationSection("recipes.stonecutting");
        new Woodcutter(this, stonecuttingConfig).register();
    }

    public void initFeatures() {
        new DragonBuff(this).init();
        new EndCrystalBuff(this).init();
    }

    @Override
    public void onEnable() {
        final PluginMeta pluginYml = this.getPluginMeta();
        this.log.info("Mom says I'm version " + pluginYml.getVersion() + "!");
        this.saveDefaultConfig();

        registerRecipes();
        initFeatures();
    }
}
