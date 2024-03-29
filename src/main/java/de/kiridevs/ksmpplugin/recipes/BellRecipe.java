package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BellRecipe {

    KiriSmpPlugin plugin;

    public BellRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void register() {
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
