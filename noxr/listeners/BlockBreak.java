package noxr.listeners;

import noxr.NOXR;
import noxr.utils.ReplaceValentMaterial;
import noxr.utils.Valent;
import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Collections;
import java.util.List;

public class BlockBreak implements Listener {


    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void on(BlockBreakEvent e) {

        final Block block = e.getBlock();
        final Chunk chunk = block.getChunk();


        final List<Valent> valents = NOXR.valents.getOrDefault(chunk, Collections.emptyList());


        boolean updated = false;
        for (Valent valent : valents) {

            if (valent.getWorld().equals(block.getWorld()) &&
            valent.getX() == block.getX() && valent.getY() == block.getY() &&
            valent.getZ() == block.getZ()) {
                valents.remove(valent);
                updated = true;

                e.setCancelled(true);
                new ReplaceValentMaterial(valent).show();

                break;
            }


        }

        if (updated) {
            NOXR.valents.put(chunk, valents);
        }


    }

}
