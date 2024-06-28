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

    private static final MaterialChoice SHROOM_CHOICE = new MaterialChoice(
            Material.BROWN_MUSHROOM,
            Material.CRIMSON_FUNGUS,
            Material.RED_MUSHROOM,
            Material.WARPED_FUNGUS
    );

    public ShroomlightRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(ConfigurationSection config) {
        if (!config.getBoolean("shroomlight")) return;

        NamespacedKey key = new NamespacedKey(this.plugin, "shroomlight");
        ItemStack result = new ItemStack(Material.SHROOMLIGHT);

        ShapedRecipe recipe = new ShapedRecipe(key, result)
            .shape(" s ", "sgs", " s ")
            .setIngredient('s', ShroomlightRecipe.SHROOM_CHOICE)
            .setIngredient('g', Material.GLOWSTONE_DUST);

        this.plugin.log.info("recipes: ShroomlightRecipe: Registering Custom Recipe");
        Bukkit.addRecipe(recipe);
    }
}
