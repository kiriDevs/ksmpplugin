package de.kiridevs.ksmpplugin.recipes;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class SculkSensorRecipes extends _RecipeTemplate {
    public SculkSensorRecipes(Plugin nsPlugin) {
        super(nsPlugin, Material.SCULK_SENSOR);
    }

    @Override
    protected ArrayList<Recipe> buildRecipes() {
        NamespacedKey nsk1 = new NamespacedKey(nsPlugin, "sculkSensorRecipe1");
        Recipe rec1 = new ShapedRecipe(nsk1, this.outputStack)
                .shape("i g", "ere", "sss")
                .setIngredient('e', Material.ENDER_PEARL)
                .setIngredient('r', Material.REPEATER)
                .setIngredient('g', Material.GLOW_INK_SAC)
                .setIngredient('i', Material.INK_SAC)
                .setIngredient('s', Material.STONE);

        NamespacedKey nsk2 = new NamespacedKey(nsPlugin, "sculkSensorRecipe2");
        Recipe rec2 = new ShapedRecipe(nsk2, this.outputStack)
                .shape("g i", "ere", "sss")
                .setIngredient('e', Material.ENDER_PEARL)
                .setIngredient('r', Material.REPEATER)
                .setIngredient('g', Material.GLOW_INK_SAC)
                .setIngredient('i', Material.INK_SAC)
                .setIngredient('s', Material.STONE);

        return new ArrayList<>() {{ add(rec1); add(rec2); }};
    }
}
