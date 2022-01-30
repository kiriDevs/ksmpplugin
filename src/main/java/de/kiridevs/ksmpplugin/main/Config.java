package de.kiridevs.ksmpplugin.main;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    FileConfiguration config;
    ConfigurationSection craftingSection;

    private static void initialize(Plugin plugin) {
        // Copy the default config.yml from the plugin JAR
        // This will not override existing configuration files.
        plugin.saveDefaultConfig();
    }

    public Config(Plugin plugin) {
        this.config = plugin.getConfig();
        initialize(plugin);

        this.craftingSection = this.config.getConfigurationSection("craftingRecipes");
    }

    public boolean doAllowCrafting(String craftingKey) {
        return this.craftingSection.getBoolean(craftingKey);
    }

}
