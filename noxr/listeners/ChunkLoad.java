package noxr.listeners;

import noxr.NOXR;
import noxr.utils.ReplaceValentMaterial;
import noxr.utils.Valent;
import noxr.utils.ValentFinder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkUnloadEvent;

import java.util.Collections;
import java.util.List;

public class ChunkLoad implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void on(ChunkLoadEvent e) {
        new ValentFinder(e.getChunk());
    }


    @EventHandler
    public void on(ChunkUnloadEvent e) {
        final List<Valent> valents = NOXR.valents.getOrDefault(e.getChunk(), Collections.emptyList());
        for (Valent valent : valents) {
            new ReplaceValentMaterial(valent).show();
        }
    }

}
