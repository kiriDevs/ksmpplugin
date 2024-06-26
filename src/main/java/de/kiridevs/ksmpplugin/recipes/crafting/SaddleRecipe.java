package de.kiridevs.ksmpplugin.recipes.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class SaddleRecipe {
    private static final String KEY_NAME = "saddle";

    KiriSmpPlugin plugin;

    public SaddleRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(ConfigurationSection config) {
        if (!config.getBoolean(KEY_NAME, false)) return;

        NamespacedKey key = new NamespacedKey(this.plugin, KEY_NAME);
        ItemStack result = new ItemStack(Material.SADDLE);

        ShapedRecipe recipe = new ShapedRecipe(key, result)
            .shape("lll", "s s", "h h")
            .setIngredient('l', Material.LEATHER)
            .setIngredient('s', Material.STICK)
            .setIngredient('h', Material.TRIPWIRE_HOOK);

        this.plugin.log.info("recipes: SaddleRecipe: Registering Custom Recipe");
        Bukkit.addRecipe(recipe);
    }
}
