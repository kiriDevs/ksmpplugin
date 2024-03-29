package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

public class BundleRecipe {

    KiriSmpPlugin plugin;

    public BundleRecipe(KiriSmpPlugin plugin) {
        this.plugin = plugin;
    }

    private NamespacedKey key(String name) {
        return new NamespacedKey(this.plugin, "bundle_" + name);
    }

    public void register() {
        ItemStack outputStack = new ItemStack(Material.BUNDLE);
        ShapedRecipe leatherRecipe = new ShapedRecipe(this.key("leather"), outputStack)
            .shape("sls", "l l", "lll")
            .setIngredient('s', Material.STRING)
            .setIngredient('l', Material.LEATHER);

        ShapedRecipe rabbitRecipe = new ShapedRecipe(this.key("rabbit"), outputStack)
            .shape("sls", "l l", "lll")
            .setIngredient('s', Material.STRING)
            .setIngredient('l', Material.RABBIT_HIDE);

        this.plugin.log.info("recipes: BundleRecipe: Registering Custom Recipe");
        Bukkit.addRecipe(leatherRecipe);
        Bukkit.addRecipe(rabbitRecipe);
    }
}
