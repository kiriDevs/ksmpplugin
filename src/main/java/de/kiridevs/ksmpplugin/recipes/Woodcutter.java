package de.kiridevs.ksmpplugin.recipes;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;
import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.StonecuttingRecipe;

public class Woodcutter {

    public static final Map<Material, Map<String, Material>> materials = Map.ofEntries(
        Map.entry(
            Material.ACACIA_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.ACACIA_SLAB),
                Map.entry("stairs", Material.ACACIA_STAIRS)
            )
        ),
        Map.entry(
            Material.BIRCH_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.BIRCH_SLAB),
                Map.entry("stairs", Material.BIRCH_STAIRS)
            )
        ),
        Map.entry(
            Material.CRIMSON_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.CRIMSON_SLAB),
                Map.entry("stairs", Material.CRIMSON_STAIRS)
            )
        ),
        Map.entry(
            Material.DARK_OAK_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.DARK_OAK_SLAB),
                Map.entry("stairs", Material.DARK_OAK_STAIRS)
            )
        ),
        Map.entry(
            Material.JUNGLE_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.JUNGLE_SLAB),
                Map.entry("stairs", Material.JUNGLE_STAIRS)
            )
        ),
        Map.entry(
            Material.OAK_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.OAK_SLAB),
                Map.entry("stairs", Material.OAK_STAIRS)
            )
        ),
        Map.entry(
            Material.SPRUCE_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.SPRUCE_SLAB),
                Map.entry("stairs", Material.SPRUCE_STAIRS)
            )
        ),
        Map.entry(
            Material.WARPED_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.WARPED_SLAB),
                Map.entry("stairs", Material.WARPED_STAIRS)
            )
        ),
        Map.entry(
            Material.CHERRY_PLANKS,
            Map.ofEntries(
                Map.entry("slab", Material.CHERRY_SLAB),
                Map.entry("stairs", Material.CHERRY_STAIRS)
            )
        )
    );

    KiriSmpPlugin plugin;
    ConfigurationSection config;

    private NamespacedKey key(String wood, String type) {
        return new NamespacedKey(this.plugin, "woodcutter_" + wood + "_" + type);
    }

    public Woodcutter(KiriSmpPlugin plugin, ConfigurationSection config) {
        this.plugin = plugin;
        this.config = config;
    }

    public void woodenSlabs() {
        this.plugin.log.info("recipes: Woodcutter(slabs): Registering Recipes");
        for (Material planks : materials.keySet()) {
            Material slabMaterial = materials.get(planks).get("slab");
            if (slabMaterial == null) continue;

            String woodName = planks.toString().split("_")[0];

            NamespacedKey key = this.key(woodName, "slab");
            ItemStack result = new ItemStack(slabMaterial, 2);
            StonecuttingRecipe recipe = new StonecuttingRecipe(key, result, planks);
            Bukkit.addRecipe(recipe);
        }
    }

    public void woodenStairs() {
        this.plugin.log.info("recipes: Woodcutter(stairs): Registering Recipes");
        for (Material planks : materials.keySet()) {
            Material stairsMaterial = materials.get(planks).get("stairs");
            if (stairsMaterial == null) continue;

            String woodName = planks.toString().split("_")[0];

            NamespacedKey key = this.key(woodName, "stairs");
            ItemStack result = new ItemStack(stairsMaterial, 1);
            StonecuttingRecipe recipe = new StonecuttingRecipe(key, result, planks);
            Bukkit.addRecipe(recipe);
        }
    }

    public void register() {
        if (this.config.getBoolean("slabs")) this.woodenSlabs();
        if (this.config.getBoolean("stairs")) this.woodenStairs();
    }
}
