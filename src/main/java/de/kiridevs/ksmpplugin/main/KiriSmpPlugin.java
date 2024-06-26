package de.kiridevs.ksmpplugin.main;

import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;
import io.papermc.paper.plugin.configuration.PluginMeta;

import de.kiridevs.ksmpplugin.features.DeathMessage;
import de.kiridevs.ksmpplugin.features.DragonBuff;
import de.kiridevs.ksmpplugin.features.EndCrystalBuff;
import de.kiridevs.ksmpplugin.features.StickyMinecarts;
import de.kiridevs.ksmpplugin.recipes.*;

public class KiriSmpPlugin extends JavaPlugin {

    public final Logger log;

    public KiriSmpPlugin() {
        super();
        this.log = this.getLogger();
    }

    private void registerRecipes() {
        CraftingRecipes.register(this);

        ConfigurationSection stonecuttingConfig =
            this.getConfig().getConfigurationSection("recipes.stonecutting");
        new Woodcutter(this, stonecuttingConfig).register();
    }

    public void initFeatures() {
        new DragonBuff(this).init();
        new EndCrystalBuff(this).init();
        new DeathMessage(this).init();
        new StickyMinecarts(this).init();
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
