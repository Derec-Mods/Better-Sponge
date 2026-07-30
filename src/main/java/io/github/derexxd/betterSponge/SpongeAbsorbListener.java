package io.github.derexxd.betterSponge;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SpongeAbsorbEvent;

public final class SpongeAbsorbListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onSpongeAbsorb(SpongeAbsorbEvent event) {
        event.setCancelled(true);

        Block sponge = event.getBlock();
        if (BfsFunction.drainWater(sponge) == 0) {
            return;
        }

        sponge.setType(Material.WET_SPONGE);
        sponge.getWorld().playEffect(sponge.getLocation(), Effect.STEP_SOUND, Material.WATER);
    }
}
