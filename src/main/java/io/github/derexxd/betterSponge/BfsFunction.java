package io.github.derexxd.betterSponge;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import java.util.ArrayDeque;
import java.util.Deque;

public final class BfsFunction {

    private static final BlockFace[] FACES = {
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.NORTH,
            BlockFace.SOUTH,
            BlockFace.EAST,
            BlockFace.WEST
    };

    private record Node(Block block, int depth) {
    }

    private BfsFunction() {
    }

    public static int drainWater(Block sponge) {
        Deque<Node> queue = new ArrayDeque<>();
        queue.add(new Node(sponge, 0));

        int drained = 0;

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (BlockFace face : FACES) {
                Block neighbour = current.block().getRelative(face);

                Material type = neighbour.getType();

                if (Tag.CORAL_PLANTS.isTagged(type)
                        || type == Material.SEAGRASS
                        || type == Material.TALL_SEAGRASS
                        || type == Material.KELP
                        || type == Material.KELP_PLANT) {
                    neighbour.breakNaturally();
                    continue;
                }

                if (neighbour.getType() != Material.WATER) {
                    continue;
                }

                neighbour.setType(Material.AIR);
                drained++;

                if (current.depth() < SpongeSettings.MAX_DEPTH) {
                    queue.add(new Node(neighbour, current.depth() + 1));
                }
            }

            if (drained >= SpongeSettings.MAX_BLOCKS) {
                break;
            }
        }

        return drained;
    }
}
