package de.kiridevs.ksmpplugin.main;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.PluginDescriptionFile;

public class Main extends JavaPlugin {
    final Logger log;

    public Main() {
        super();
        this.log = this.getLogger();
    }

    @Override
    public void onEnable() {
        final PluginDescriptionFile pluginYml = this.getDescription();
        this.log.info("Mom says I'm version " + pluginYml.getVersion() + "!");
    }
}
