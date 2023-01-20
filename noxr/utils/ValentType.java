package noxr.utils;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ValentType {


    private final Material material;
    public ValentType(Material material) {
        this.material = material;
    }


    List<Material> types = Arrays.asList(
            Material.DIAMOND_ORE,
            Material.EMERALD_ORE,
            Material.IRON_ORE,
            Material.GOLD_ORE,
            Material.LAPIS_ORE,
            Material.COAL_ORE
    );
    public boolean check() {
        return types.contains(material);
    }

}
