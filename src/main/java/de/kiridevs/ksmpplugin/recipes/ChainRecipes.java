package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class ChainRecipes {

    private final KiriSmpPlugin plugin;
    private final ConfigurationSection config;

    final boolean cheap;
    final int yield;

    public ChainRecipes(KiriSmpPlugin plugin, ConfigurationSection config) {
        this.plugin = plugin;
        this.config = config;

        this.cheap = this.config.getBoolean("cheap");
        if (this.config.getInt("yield") < 1) {
            this.plugin.log.warning("recipes: ChainRecipes: Invalid config value!");
            this.plugin.log.warning("recipes: ChainRecipes: Using default value \"2\".");
            this.yield = 2;
        } else {
            this.yield = this.config.getInt("yield");
        }
    }

    public void register() {
        // Remove vanilla recipe
        this.plugin.log.info("recipes: ChainRecipes: De-Registering Vanilla Recipe");
        Bukkit.removeRecipe(NamespacedKey.minecraft("chain"));

        NamespacedKey key = new NamespacedKey(this.plugin, "chain");
        ItemStack result = new ItemStack(Material.CHAIN, this.yield);

        ShapedRecipe recipe = new ShapedRecipe(key, result);

        if (this.cheap) {
            recipe.shape("n", "n", "n").setIngredient('n', Material.IRON_NUGGET);
        } else {
            recipe
                .shape("n", "i", "n")
                .setIngredient('n', Material.IRON_NUGGET)
                .setIngredient('i', Material.IRON_INGOT);
        }

        this.plugin.log.info("recipes: ChainRecipes: Registering Custom Recipe");
        Bukkit.addRecipe(recipe);
    }
}
