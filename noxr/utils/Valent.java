package noxr.utils;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.World;

public class Valent {

    @Getter
    private final Material type;
    @Getter
    private final World world;
    @Getter
    private final int x;
    @Getter
    private final int y;
    @Getter
    private final int z;

    @Setter @Getter
    private boolean converted = false;


    public Valent(Material type, World world, int x, int y, int z) {
        this.type = type;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
    }

}
