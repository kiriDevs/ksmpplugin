package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.*;

import static java.util.Map.entry;

public class MusicDiscRecipes extends _RecipeTemplate {
    public static final Set<Material> DiscMaterial = Set.of(
        Material.MUSIC_DISC_13,
        Material.MUSIC_DISC_CAT,
        Material.MUSIC_DISC_BLOCKS,
        Material.MUSIC_DISC_CHIRP,
        Material.MUSIC_DISC_FAR,
        Material.MUSIC_DISC_MALL,
        Material.MUSIC_DISC_MELLOHI,
        Material.MUSIC_DISC_STAL,
        Material.MUSIC_DISC_STRAD,
        Material.MUSIC_DISC_WARD,
        Material.MUSIC_DISC_11,
        Material.MUSIC_DISC_WAIT,
        Material.MUSIC_DISC_OTHERSIDE,
        Material.MUSIC_DISC_PIGSTEP
    );

    public static final Map<Material, Material> discExtraMap = Map.ofEntries(
        entry(Material.MUSIC_DISC_13, Material.YELLOW_DYE),
        entry(Material.MUSIC_DISC_CAT, Material.LIME_DYE),
        entry(Material.MUSIC_DISC_BLOCKS, Material.ORANGE_DYE),
        entry(Material.MUSIC_DISC_CHIRP, Material.RED_DYE),
        entry(Material.MUSIC_DISC_FAR, Material.LIME_DYE),
        entry(Material.MUSIC_DISC_MALL, Material.PURPLE_DYE),
        entry(Material.MUSIC_DISC_MELLOHI, Material.PINK_DYE),
        entry(Material.MUSIC_DISC_STAL, Material.BLACK_DYE),
        entry(Material.MUSIC_DISC_STRAD, Material.WHITE_DYE),
        entry(Material.MUSIC_DISC_WARD, Material.GREEN_DYE),
        entry(Material.MUSIC_DISC_11, Material.IRON_PICKAXE),
        entry(Material.MUSIC_DISC_WAIT, Material.LIGHT_BLUE_DYE),
        entry(Material.MUSIC_DISC_OTHERSIDE, Material.CYAN_DYE),
        entry(Material.MUSIC_DISC_PIGSTEP, Material.NETHER_GOLD_ORE)
    );

    public MusicDiscRecipes(Plugin nsPlugin, Material outputMaterial) {
        super(nsPlugin, outputMaterial);
    }

    @Override
    protected ItemStack makeOutputStack() {
        ItemStack outputStack = new ItemStack(this.outputMaterial);
        outputStack.setAmount(2);
        return outputStack;
    }

    @Override
    protected ArrayList<Recipe> buildRecipes() {
        final String outname = this.outputMaterial.name();

        NamespacedKey nsk1 = new NamespacedKey(this.nsPlugin, outname + "_recipe1");
        ShapedRecipe rec1 = new ShapedRecipe(nsk1, this.outputStack)
                .shape("aX", "Di", "nl");

        NamespacedKey nsk2 = new NamespacedKey(this.nsPlugin, outname + "_recipe2");
        ShapedRecipe rec2 = new ShapedRecipe(nsk2, this.outputStack)
                .shape("a X", "D i", "n l");

        ArrayList<Recipe> builtRecipes = new ArrayList<>();
        // Infuse the raw, shape-only recipes with ingredients
        for (ShapedRecipe rec : new ShapedRecipe[] { rec1, rec2 } ) {
            ShapedRecipe modified = rec
                    .setIngredient('a', Material.ANVIL)
                    .setIngredient('X', discExtraMap.get(this.outputMaterial))
                    .setIngredient('D', this.outputMaterial)
                    .setIngredient('i', Material.IRON_BLOCK)
                    .setIngredient('n', Material.NETHERITE_BLOCK)
                    .setIngredient('l', Material.LAVA_BUCKET);
            builtRecipes.add(modified);
        }
        return builtRecipes;
    }

    @Override
    protected boolean shouldRegister() {
        String discName = this.outputMaterial.name() // "MUSIC_DISC_PIGSTEP"
                .replaceFirst("MUSIC_DISC_", "") // "PIGSTEP"
                .toLowerCase(); // "pigstep"

        return Main.config.doAllowCraftingDisc(discName);
    }
}
