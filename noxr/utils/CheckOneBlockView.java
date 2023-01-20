package noxr.utils;

import noxr.NOXR;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Collections;
import java.util.List;

public class CheckOneBlockView {


    private final Location blockLoc;
    private final Valent valent;
    public CheckOneBlockView(Location blockLoc, Valent valent) {
        this.blockLoc = blockLoc;
        this.valent = valent;
    }

    public boolean check() {
        boolean noView = true;
        for (int var = -1 ; var < 1 ; var++) {
            for (int var1 = -1 ; var1 < 1 ; var1++) {
                for (int var2 = -1 ; var2 < 1 ; var2++) {
                    final Location var3 = blockLoc.clone();
                    var3.add(var, var1, var2);
                    // var3.getWorld(), var3.getBlockX(), var3.getBlockY(), var3.getBlockZ()

                    final Location var4 = new Location(valent.getWorld(), valent.getX(), valent.getY(), valent.getZ());

                    final Chunk var5 = var4.getChunk();
                    final List<Valent> valents = NOXR.valents.getOrDefault(var5, Collections.emptyList());
             /*


                    boolean noStone = false;
                    for (Valent var6 : valents) {
                        if (var6.getWorld() == var3.getWorld() && var6.getX() == var3.getBlockX() && var6.getY() == var3.getY() && var6.getZ() == var3.getBlockZ()) noStone = true;
                    }

                    if (noStone) continue;
              */

                    if (var3.getBlock().getType().equals(Material.AIR)) noView = false;
                }
            }
        }

        return !noView;
    }

}
