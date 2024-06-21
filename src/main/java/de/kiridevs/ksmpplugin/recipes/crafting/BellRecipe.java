package de.kiridevs.ksmpplugin.recipes.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class BellRecipe {
    private final KiriSmpPlugin plugin;

    public BellRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(ConfigurationSection config) {
        if (!config.getBoolean("bell")) return;

        // Remove vanilla recipe
        this.plugin.log.info("recipes: BellRecipe: De-Registering Vanilla Recipe");
        Bukkit.removeRecipe(NamespacedKey.minecraft("bell"));

        NamespacedKey key = new NamespacedKey(this.plugin, "bell");
        ItemStack result = new ItemStack(Material.BELL);

        ShapedRecipe recipe = new ShapedRecipe(key, result)
            .shape("rsr", "ggg", "gig")
            .setIngredient('r', Material.STONE)
            .setIngredient('s', Material.STICK)
            .setIngredient('g', Material.GOLD_INGOT)
            .setIngredient('i', Material.IRON_INGOT);

        this.plugin.log.info("recipes: BellRecipe: Registering Custom Recipe");
        Bukkit.addRecipe(recipe);
    }
}
