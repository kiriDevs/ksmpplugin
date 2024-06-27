package de.kiridevs.ksmpplugin.recipes.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.RecipeChoice.MaterialChoice;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class ShroomlightRecipe {
    private final KiriSmpPlugin plugin;

    private static final MaterialChoice SHROOM_BLOCKS = new MaterialChoice(
            Material.BROWN_MUSHROOM_BLOCK,
            Material.RED_MUSHROOM_BLOCK
    );

    public ShroomlightRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(ConfigurationSection config) {
        if (!config.getBoolean("shroomlight")) return;

        NamespacedKey key = new NamespacedKey(this.plugin, "shroomlight");
        ItemStack result = new ItemStack(Material.SHROOMLIGHT);

        ShapedRecipe recipe = new ShapedRecipe(key, result)
            .shape(" g ", "gsg", " g ")
            .setIngredient('g', Material.GLOWSTONE_DUST)
            .setIngredient('s', ShroomlightRecipe.SHROOM_BLOCKS);

        this.plugin.log.info("recipes: ShroomlightRecipe: Registering Custom Recipe");
        Bukkit.addRecipe(recipe);
    }
}
