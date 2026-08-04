package io.github.derexxd.betterSponge;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.EnumSet;
import java.util.Set;

public class SpongeDryListener implements Listener {
    private static final Set<Biome> DRY_BIOMES = EnumSet.of(
            Biome.DESERT,
            Biome.BADLANDS,
            Biome.ERODED_BADLANDS,
            Biome.WOODED_BADLANDS,
            Biome.SAVANNA,
            Biome.SAVANNA_PLATEAU,
            Biome.WINDSWEPT_SAVANNA
    );

    public boolean isDryBiome(Block block) {
        return block.getHumidity() == 0.0;
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onWetSpongePlace(BlockPlaceEvent event) {
        Block block = event.getBlockPlaced();

        if (block.getType() != Material.WET_SPONGE) {
            return;
        }

        if (!isDryBiome(block.getBiome())) {
            return;
        }

        block.setType(Material.SPONGE);

        Location centerLoc = block.getLocation().add(0.5, 0.5, 0.5);
        Location particleLoc = block.getLocation().add(0.5, 1.0, 0.5);

        block.getWorld().playSound(
                centerLoc,
                Sound.BLOCK_FIRE_EXTINGUISH,
                SoundCategory.BLOCKS,
                1.0f,
                1.0f
        );

        block.getWorld().spawnParticle(
                Particle.CLOUD,
                particleLoc,
                8,
                0.25,
                0.1,
                0.25,
                0.02
        );
    }

    private boolean isDryBiome(Biome biome) {
        return DRY_BIOMES.contains(biome);
    }
}