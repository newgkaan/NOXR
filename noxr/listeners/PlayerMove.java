package noxr.listeners;

import noxr.NOXR;
import noxr.utils.CheckCanSee;
import noxr.utils.ReplaceValentMaterial;
import noxr.utils.Valent;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public class PlayerMove implements Listener {



    private final Set<UUID> cooldown = new HashSet<>();


    @EventHandler(ignoreCancelled = true)
    public void onMove(PlayerMoveEvent e) {
        if (cooldown.contains(e.getPlayer().getUniqueId())) return;

        if (e.getTo() == null) return;

        final Chunk to = e.getTo().getChunk();
        final List<Valent> valents = NOXR.valents.getOrDefault(to, Collections.emptyList());
        for (Valent valent : valents) {

            boolean var = new CheckCanSee(valent, e.getPlayer().getUniqueId()).can();
            if (!var) continue;

            new ReplaceValentMaterial(valent).show();
        }


        cooldown.add(e.getPlayer().getUniqueId());
        new BukkitRunnable() {
            public void run() {
                cooldown.remove(e.getPlayer().getUniqueId());
            }
        }.runTaskLater(NOXR.noxr, 22L);


    }

}
