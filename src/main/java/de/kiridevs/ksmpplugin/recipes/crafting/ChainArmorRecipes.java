package de.kiridevs.ksmpplugin.recipes.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class ChainArmorRecipes {

    private final KiriSmpPlugin plugin;
    private final Material inputMaterial;

    public ChainArmorRecipes(KiriSmpPlugin plugin, String config) {
        this.plugin = plugin;
        this.inputMaterial =
            switch (config) {
                case "chains" -> Material.CHAIN;
                case "nugget" -> Material.IRON_NUGGET;
                default -> {
                    plugin.log.warning("recipes: ChainArmorRecipes: Invalid config value!");
                    plugin.log.warning(
                        "recipes: ChainArmorRecipes: Using default value \"chains\"."
                    );
                    yield Material.CHAIN;
                }
            };
    }

    private NamespacedKey key(String armorPart) {
        return new NamespacedKey(this.plugin, "chain_" + armorPart);
    }

    ShapedRecipe helmet() {
        ItemStack result = new ItemStack(Material.CHAINMAIL_HELMET);
        return new ShapedRecipe(this.key("helmet"), result)
            .shape("mmm", "m m")
            .setIngredient('m', this.inputMaterial);
    }

    ShapedRecipe chestplate() {
        ItemStack result = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        return new ShapedRecipe(this.key("chestplate"), result)
            .shape("m m", "mmm", "mmm")
            .setIngredient('m', this.inputMaterial);
    }

    ShapedRecipe leggings() {
        ItemStack result = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        return new ShapedRecipe(this.key("leggings"), result)
            .shape("mmm", "m m", "m m")
            .setIngredient('m', this.inputMaterial);
    }

    ShapedRecipe boots() {
        ItemStack result = new ItemStack(Material.CHAINMAIL_BOOTS);
        return new ShapedRecipe(this.key("boots"), result)
            .shape("m m", "m m")
            .setIngredient('m', this.inputMaterial);
    }

    public void register() {
        this.plugin.log.info("recipes: ChainArmorRecipes: Registering Custom Recipes");
        Bukkit.addRecipe(this.helmet());
        Bukkit.addRecipe(this.chestplate());
        Bukkit.addRecipe(this.leggings());
        Bukkit.addRecipe(this.boots());
    }
}
