package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public abstract class _RecipeTemplate {
    private final List<Recipe> recipes;
    protected abstract ArrayList<Recipe> buildRecipes();

    protected final Plugin nsPlugin;

    protected final Material outputMaterial;
    protected final ItemStack outputStack;

    protected ItemStack makeOutputStack() {
        return new ItemStack(this.outputMaterial);
    }

    public _RecipeTemplate(Plugin nsPlugin, Material outputMaterial) {
        this.nsPlugin = nsPlugin;

        this.outputMaterial = outputMaterial;
        this.outputStack = this.makeOutputStack();

        this.recipes = this.buildRecipes();
    }

    public void register() {
        Main.log.info("Registering " + this.recipes.toArray().length + " recipes " + "for " + this.outputStack);
        for (Recipe recipe : this.recipes) {
            if (recipe instanceof ShapedRecipe) {
                Main.log.info("  Registering " + ((ShapedRecipe) recipe).getKey());
            } else {
                Main.log.info("  Registering #" + recipe.hashCode());
            }
            Bukkit.addRecipe(recipe);
        }
    }
}
