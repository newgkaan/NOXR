package noxr.utils;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class GetClosestFace {


    private final Player player;
    private final Block block;
    public GetClosestFace(Player player, Block block) {
        this.player = player;
        this.block = block;
    }

    public BlockFace get() {
        float dir = (float)Math.toDegrees(Math.atan2(player.getLocation().getBlockX() - block.getX(), block.getZ() - player.getLocation().getBlockZ()));

        dir = dir % 360;

        if(dir < 0)
            dir += 360;

        dir = Math.round(dir / 45);

        switch((int)dir){
            case 1:
            case 2:
            case 3:
                return BlockFace.NORTH;
            case 4:
            case 5:
                return BlockFace.EAST;
            case 6:
            case 7:
                return BlockFace.SOUTH;
            default:
                return BlockFace.WEST;

        }
    }


}
