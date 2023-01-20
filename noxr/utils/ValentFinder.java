package noxr.utils;

import noxr.NOXR;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class ValentFinder {


    private final Chunk chunk;
    public ValentFinder(Chunk chunk) {
        this.chunk = chunk;
        this.load();
    }

    public void load() {


        Height height = new Height(chunk.getWorld());
        final Location minLoc = chunk.getBlock(0, 0, 0).getLocation();

        final int minY = height.minY(), maxY = height.maxY();
        final int minX = minLoc.getBlockX(), minZ = minLoc.getBlockZ();
        final int maxX = minX + 15, maxZ = minZ + 15;



        final List<Valent> finded = new ArrayList<>();


        new Thread(() -> {

            for (int varX = minX ; varX <= maxX ; varX++) {
                for (int varZ = minZ ; varZ <= maxZ ; varZ++) {
                    for (int varY = minY ; varY <= maxY ; varY++) {
                        try {


                            final Location varLoc = new Location(chunk.getWorld(), varX, varY, varZ);
                            final Block block = varLoc.getBlock();

                            if (!new ValentType(block.getType()).check()) continue;
                            final Valent valent = new Valent(block.getType(), block.getWorld(), block.getX(), block.getY(), block.getZ());
                            finded.add(valent);

                            Thread.sleep(10);
                        } catch (InterruptedException ignored) {}
                    }
                }
            }

            add(finded);

        }).start();

    }

    private void add(List<Valent> finded) {
        List<Valent> valents = NOXR.valents.getOrDefault(this.chunk, null);
        if (valents == null) {
            valents = new ArrayList<>(finded);
        } else {
            valents.addAll(finded);
        }


        try {
            NOXR.valents.put(chunk, valents);
            new ValentHider(chunk).hide();

        } catch (ConcurrentModificationException ignored) {
            new BukkitRunnable() {
                public void run() {
                    add(finded);
                }
            }.runTaskLater(NOXR.noxr, 1L);
        }
    }

}
