package noxr.utils;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class ConvertValentToBlock {


    private final Valent valent;
    public ConvertValentToBlock(Valent valent) {
        this.valent = valent;
    }

    public Block convert() {
        final Location loc = new Location(valent.getWorld(), valent.getX(), valent.getY(), valent.getZ());
        return loc.getBlock();
    }

}
