package de.kiridevs.ksmpplugin.recipes.crafting;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class ChainRecipes {
    private static final int DEFAULT_YIELD = 2;
    private static final boolean DEFAULT_CHEAP = true;

    private static final String WARN_PREFIX = "recipes: ChainRecipes: ";
    private static final String INVALID_CONFIG_WARNING = WARN_PREFIX + "Invalid config value for '%s'; using default: %s";

    private final KiriSmpPlugin plugin;

    public ChainRecipes(KiriSmpPlugin plugin) { this.plugin = plugin; }

    private void configWarn(String key, Object defaultValue) {
        this.plugin.getLogger().warning(INVALID_CONFIG_WARNING.formatted(key, defaultValue));
    }

    public void register(ConfigurationSection config) {
        if (!config.isConfigurationSection("chain")) return;
        ConfigurationSection chainConfig = config.getConfigurationSection("chain");
        assert chainConfig != null;

        if (!chainConfig.isInt("yield")) this.configWarn("yield", String.valueOf(DEFAULT_YIELD));
        int yield = chainConfig.getInt("yield", DEFAULT_YIELD);

        if (!chainConfig.isBoolean("cheap")) this.configWarn("cheap", DEFAULT_CHEAP);
        boolean cheap = chainConfig.getBoolean("cheap", DEFAULT_CHEAP);

        // Remove vanilla recipe
        this.plugin.log.info("recipes: ChainRecipes: De-Registering Vanilla Recipe");
        Bukkit.removeRecipe(NamespacedKey.minecraft("chain"));

        NamespacedKey key = new NamespacedKey(this.plugin, "chain");
        ItemStack result = new ItemStack(Material.CHAIN, yield);

        ShapedRecipe recipe = new ShapedRecipe(key, result);
        recipe.shape("n", "_", "n")
            .setIngredient('n', Material.IRON_NUGGET)
            .setIngredient('_', cheap ? Material.IRON_NUGGET : Material.IRON_INGOT);

        this.plugin.log.info("recipes: ChainRecipes: Registering Custom Recipe");
        Bukkit.addRecipe(recipe);
    }
}
