package noxr.utils;

import noxr.NOXR;
import org.bukkit.Chunk;

import java.util.Collections;
import java.util.List;

public class ValentHider {

    private final Chunk chunk;
    public ValentHider(Chunk chunk) {
        this.chunk = chunk;
    }

    public void hide() {
        final List<Valent> valents = NOXR.valents.getOrDefault(this.chunk, Collections.emptyList());
        for (Valent valent : valents) {
            new ReplaceValentMaterial(valent).hide();
        }

    }

}
