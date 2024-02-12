package de.kiridevs.ksmpplugin.recipes.crafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class BundleRecipe {
    private static final String CONFIG_NAME = "bundle";
    private static final String INVALID_SHELL_WARNING = "Invalid config value for crafting." + CONFIG_NAME + ".shell";

    private final KiriSmpPlugin plugin;

    public BundleRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    public void register(ConfigurationSection config) {
        // If config explicitly specifies "false", don't register
        if (!config.getBoolean(CONFIG_NAME, true)) return;

        List<Material> shellMaterials = new ArrayList<>();
        boolean useChest = true;

        if (config.getBoolean(CONFIG_NAME, false)) {
            shellMaterials.add(Material.LEATHER);
            shellMaterials.add(Material.RABBIT_HIDE);
        }

        if (config.isConfigurationSection(CONFIG_NAME)) {
            ConfigurationSection bundleConfig = config.getConfigurationSection(CONFIG_NAME);
            assert bundleConfig != null;

            String shellChoice = bundleConfig.getString("shell");
            if (shellChoice != null) {
                if (shellChoice.equals("leather") || shellChoice.equals("either"))
                    shellMaterials.add(Material.LEATHER);
                if (shellChoice.equals("hide") || shellChoice.equals("either"))
                    shellMaterials.add(Material.RABBIT_HIDE);
            }
            useChest = bundleConfig.getBoolean("chest", true);
        }

        if (shellMaterials.isEmpty()) {
            this.plugin.getLogger().warning(INVALID_SHELL_WARNING);
            return;
        }
        RecipeChoice shellChoice = new RecipeChoice.MaterialChoice(shellMaterials);

        ItemStack outputStack = new ItemStack(Material.BUNDLE);
        NamespacedKey key = new NamespacedKey(this.plugin, "bundle");
        ShapedRecipe recipe = new ShapedRecipe(key, outputStack)
            .shape("sxs", "xcx", "xxx")
            .setIngredient('s', Material.STRING)
            .setIngredient('x', shellChoice);
        if (useChest) recipe.setIngredient('c', Material.CHEST);
        Bukkit.addRecipe(recipe);

        this.plugin.log.info("recipes: BundleRecipe: Registered Custom Recipe");
    }
}
