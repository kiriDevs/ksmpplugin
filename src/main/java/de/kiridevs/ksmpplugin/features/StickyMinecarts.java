package de.kiridevs.ksmpplugin.features;

import java.util.HashMap;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import de.kiridevs.ksmpplugin.main.KiriSmpPlugin;

public class StickyMinecarts implements Listener {
    private static final String STICKY_MARKER_KEY = "isStickyMinecart";
    /**
     * @see <a href="https://jd.papermc.io/paper/1.20/org/bukkit/entity/Minecart.html#setMaxSpeed(double)">Paper JavaDocs</a>
     */
    private static final double CART_DEFAULT_MAX_SPEED = 0.4d;

    private final KiriSmpPlugin plugin;
    private final NamespacedKey dataKey;

    public StickyMinecarts(KiriSmpPlugin plugin) {
        this.plugin = plugin;
        this.dataKey = new NamespacedKey(this.plugin, STICKY_MARKER_KEY);
    }

    private boolean makeSticky(RideableMinecart cart) {
        PersistentDataContainer pdc = cart.getPersistentDataContainer();
        if (pdc.getOrDefault(dataKey, PersistentDataType.BOOLEAN, false)) {
            // Cart is already sticky
            return false;
        }
        pdc.set(this.dataKey, PersistentDataType.BOOLEAN, true);

        // Stop cart from moving
        cart.setVelocity(new Vector(0, 0, 0));
        cart.setMaxSpeed(0);

        // Play waxing effect
        Location cartLoc = cart.getLocation();
        cartLoc.getWorld().playEffect(cartLoc, Effect.COPPER_WAX_ON, 0);

        return true;
    }

    private boolean unsticky(RideableMinecart cart) {
        // Remove flag
        PersistentDataContainer pdc = cart.getPersistentDataContainer();
        boolean sticky = pdc.getOrDefault(this.dataKey, PersistentDataType.BOOLEAN, false);
        if (!sticky) return false;

        cart.setMaxSpeed(CART_DEFAULT_MAX_SPEED);
        pdc.remove(dataKey);

        // Play effect
        Location cartLoc = cart.getLocation();
        cartLoc.getWorld().playEffect(cartLoc, Effect.COPPER_WAX_OFF, 0);

        return true;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEntityEvent event) {
        if (!(event.getRightClicked() instanceof RideableMinecart cart)) return;

        Player player = event.getPlayer();
        PlayerInventory inv = player.getInventory();
        ItemStack main = inv.getItemInMainHand();

        event.setCancelled(true); // Prevent sit-down
        switch (main.getType()) {
            case HONEY_BOTTLE -> {
                boolean stickied = this.makeSticky(cart);
                // Remove glue from inventory
                if (!stickied) return;
                main.subtract(1);
                HashMap<Integer, ItemStack> uninsertables = inv.addItem(new ItemStack(Material.GLASS_BOTTLE, 1));
                for (ItemStack uninsertable : uninsertables.values()) {
                    Location playerLoc = player.getLocation();
                    playerLoc.getWorld().dropItem(playerLoc, uninsertable);
                }
            }
            case SHEARS -> {
                boolean unstickied = this.unsticky(cart);
                // Remove 1 durability from the used shears
                if (!unstickied) return;
                Damageable dmgShears = (Damageable) main.getItemMeta();
                dmgShears.setDamage(dmgShears.getDamage() + 1);
                main.setItemMeta(dmgShears);
            }
            case STICK -> cart.setRotation(90, 0);
            default -> event.setCancelled(false); // Allow sitdown without special item
        }
    }

    @EventHandler
    public void onMinecartMove(VehicleMoveEvent event) {
        if (!(event.getVehicle() instanceof RideableMinecart cart)) return;
        if (
            !cart.getPersistentDataContainer()
            .getOrDefault(this.dataKey, PersistentDataType.BOOLEAN, false)
        ) return;

        double yVelo = cart.getVelocity().getY();
        cart.setVelocity(new Vector(0, yVelo, 0));
        cart.setMaxSpeed(0);
    }

    public void init() {
        Bukkit.getPluginManager().registerEvents(this, this.plugin);
    }
}
