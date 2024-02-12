package de.kiridevs.ksmpplugin.recipes.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class ChainArmorRecipes {
    private static final String CONFIG_NAME = "chainArmor";
    private static final String LOG_PREFIX = "recipes: ChainArmorRecipes: ";
    private static final String CONFIG_WARNING = LOG_PREFIX + "Invalid config; use 'chain' or 'nugget'.";

    private final KiriSmpPlugin plugin;

    public ChainArmorRecipes(KiriSmpPlugin plugin) { this.plugin = plugin; }

    private NamespacedKey key(String armorPart) {
        return new NamespacedKey(this.plugin, "chain_" + armorPart);
    }

    private ShapedRecipe helmet(Material inputMaterial) {
        ItemStack result = new ItemStack(Material.CHAINMAIL_HELMET);
        return new ShapedRecipe(this.key("helmet"), result)
            .shape("mmm", "m m")
            .setIngredient('m', inputMaterial);
    }

    private ShapedRecipe chestplate(Material inputMaterial) {
        ItemStack result = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
        return new ShapedRecipe(this.key("chestplate"), result)
            .shape("m m", "mmm", "mmm")
            .setIngredient('m', inputMaterial);
    }

    private ShapedRecipe leggings(Material inputMaterial) {
        ItemStack result = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        return new ShapedRecipe(this.key("leggings"), result)
            .shape("mmm", "m m", "m m")
            .setIngredient('m', inputMaterial);
    }

    private ShapedRecipe boots(Material inputMaterial) {
        ItemStack result = new ItemStack(Material.CHAINMAIL_BOOTS);
        return new ShapedRecipe(this.key("boots"), result)
            .shape("m m", "m m")
            .setIngredient('m', inputMaterial);
    }

    private void configWarning() {
        this.plugin.getLogger().warning(CONFIG_WARNING);
    }

    private void registerWithMaterial(Material material) {
        this.plugin.log.info("recipes: ChainArmorRecipes: Registering Custom Recipes");
        Bukkit.addRecipe(this.helmet(material));
        Bukkit.addRecipe(this.chestplate(material));
        Bukkit.addRecipe(this.leggings(material));
        Bukkit.addRecipe(this.boots(material));
    }

    public void register(ConfigurationSection config) {
        if (!config.isString(CONFIG_NAME)) {
            this.configWarning();
            return;
        }

        String materialConfig = config.getString("chainArmor");
        assert materialConfig != null;

        switch (materialConfig) {
            case "chain" -> this.registerWithMaterial(Material.CHAIN);
            case "nugget" -> this.registerWithMaterial(Material.IRON_NUGGET);
        }
    }
}
