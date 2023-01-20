package noxr.utils;

import org.bukkit.World;

public class Height {

    private final World world;
    public Height(World world) {
        this.world = world;
    }

    public int maxY() {
        return 256;
        //return world.getMaxHeight();
    }

    public int minY() {
        return 1;
        //return world.getMinHeight();
    }

}
