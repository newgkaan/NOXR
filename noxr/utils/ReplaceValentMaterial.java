package noxr.utils;

import noxr.NOXR;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitRunnable;

public class ReplaceValentMaterial {

    private final Valent valent;
    public ReplaceValentMaterial(Valent valent) {
        this.valent = valent;
    }

    public void hide() {
        if (this.valent.isConverted()) return;

        try {
            new ConvertValentToBlock(valent).convert().setType(Material.STONE);
        } catch (IllegalStateException ignored) {
            try {
                new BukkitRunnable() {
                    public void run() {
                        new ConvertValentToBlock(ReplaceValentMaterial.this.valent).convert().setType(Material.STONE);
                    }
                }.runTask(NOXR.noxr);
            } catch (Exception ignored1) {}

        }


        this.valent.setConverted(true);

    }

    public void show() {
        if (!this.valent.isConverted()) return;


        try {
            new ConvertValentToBlock(valent).convert().setType(valent.getType());
        } catch (IllegalStateException ignored) {
            try {
                new BukkitRunnable() {
                    public void run() {
                        new ConvertValentToBlock(ReplaceValentMaterial.this.valent).convert().setType(ReplaceValentMaterial.this.valent.getType());
                    }
                }.runTask(NOXR.noxr);
            } catch (Exception ignored1) {
            }


            this.valent.setConverted(false);
        }
    }

}
