package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class ElytraRecipe extends _RecipeTemplate {
    public ElytraRecipe(Plugin nsPlugin) {
        super(nsPlugin, Material.ELYTRA);
    }

    @Override
    protected ArrayList<Recipe> buildRecipes() {
        NamespacedKey nsKey = new NamespacedKey(this.nsPlugin, "elytraRecipe");
        Recipe rec1 = new ShapedRecipe(nsKey, this.outputStack)
                .shape("ppp", "plp", "ftf")
                .setIngredient('p', Material.PHANTOM_MEMBRANE)
                .setIngredient('l', Material.LEATHER)
                .setIngredient('t', Material.TOTEM_OF_UNDYING)
                .setIngredient('f', Material.FEATHER);

        return new ArrayList<>() {{ add(rec1); }};
    }

    @Override
    protected boolean shouldRegister() {
        return Main.config.doAllowCrafting("elytra");
    }
}
