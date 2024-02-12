package de.kiridevs.ksmpplugin.recipes;

import org.bukkit.configuration.ConfigurationSection;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import de.kiridevs.ksmpplugin.recipes.crafting.*;

public final class CraftingRecipes {
    private CraftingRecipes() {}

    public static void register(KiriSmpPlugin plugin) {
        ConfigurationSection config = plugin
            .getConfig()
            .getConfigurationSection("recipes.crafting");
        if (config == null) return;

        new BellRecipe(plugin).register(config);
        new BundleRecipe(plugin).register(config);

        // Copied from KiriSmpPlugin.java
        new ChainRecipes(plugin, config.getConfigurationSection("chain")).register();

        if (!config.getString("chainArmor").equals("false")) {
            new ChainArmorRecipes(plugin, config.getString("chainArmor")).register();
        }

        if (config.getBoolean("saddle")) new SaddleRecipe(plugin).register();
    }
}
