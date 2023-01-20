package noxr;

import noxr.listeners.BlockBreak;
import noxr.listeners.ChunkLoad;
import noxr.listeners.PlayerMove;
import noxr.utils.ReplaceValentMaterial;
import noxr.utils.Valent;
import org.bukkit.Chunk;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NOXR extends JavaPlugin {

    public static NOXR noxr;
    public static Map<Chunk, List<Valent>> valents = new HashMap<>();


    public void onEnable() {
        noxr = this;

        getServer().getPluginManager().registerEvents(new BlockBreak(), this);
        getServer().getPluginManager().registerEvents(new ChunkLoad(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
    }


    public void onDisable() {
        for (Chunk chunk : valents.keySet()) {
            final List<Valent> valents = NOXR.valents.getOrDefault(chunk, Collections.emptyList());
            for (Valent valent : valents) {
                new ReplaceValentMaterial(valent).show();
            }
        }
    }


}
