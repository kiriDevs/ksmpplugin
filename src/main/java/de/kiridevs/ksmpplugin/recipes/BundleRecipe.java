package de.kiridevs.ksmpplugin.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class BundleRecipe extends _RecipeTemplate {
    public BundleRecipe(Plugin nsPlugin) {
        super(nsPlugin, Material.BUNDLE);
    }

    @Override
    protected ArrayList<Recipe> buildRecipes() {
        NamespacedKey nsKey = new NamespacedKey(this.nsPlugin, "bundleRecipe");
        Recipe rec1 = new ShapedRecipe(nsKey, this.outputStack)
                .shape("sls", "lcl", "lll")
                .setIngredient('s', Material.STRING)
                .setIngredient('l', Material.LEATHER)
                .setIngredient('c', Material.CHEST);

        return new ArrayList<>() {{ add(rec1); }};
    }
}
