package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class BellRecipe extends _RecipeTemplate {
    public BellRecipe(Plugin plugin) {
        super(plugin, Material.BELL);
    }

    @Override
    protected ArrayList<Recipe> buildRecipes() {
        NamespacedKey nsKey = new NamespacedKey(this.nsPlugin, "bellRecipe");
        Recipe rec1 = new ShapedRecipe(nsKey, this.outputStack)
                .shape("rsr", "ggg", "gig")
                .setIngredient('r', Material.STONE)
                .setIngredient('s', Material.STICK)
                .setIngredient('g', Material.GOLD_INGOT)
                .setIngredient('i', Material.IRON_INGOT);

        return new ArrayList<>() {{ add(rec1); }};
    }

    @Override
    protected boolean shouldRegister() {
        return Main.config.doAllowCrafting("bell");
    }
}
