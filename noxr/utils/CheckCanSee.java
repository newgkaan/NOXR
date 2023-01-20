package noxr.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

public class CheckCanSee {


    private final Valent valent;
    private final UUID uuid;
    public CheckCanSee(Valent valent, UUID uuid) {
        this.valent = valent;
        this.uuid = uuid;
    }




    public boolean can() {

        final Player player = toPlayer();

        if (player == null) return false;


        final Block block = new ConvertValentToBlock(valent).convert();

        final Location blockLoc = block.getLocation();



        if (!new CheckOneBlockView(blockLoc, valent).check()) return false;



        final Location loc = player.getLocation();
        @SuppressWarnings("unused") final BlockFace face = new GetClosestFace(player, block).get();



        if (loc.getY() < 18) {


            int diffX = blockLoc.getBlockX() - loc.getBlockX();
            if (diffX < 0) diffX *= -1;
            if (diffX > 14) {
                return false;
            }

            int diffZ = blockLoc.getBlockZ() - loc.getBlockZ();
            if (diffZ < 0) diffZ *= -1;
            if (diffZ > 14) {
                return false;
            }

        }

        if (checkIfBlocksOnVector(loc, blockLoc)) {
            return false;
        }
        if (checkIfBlocksOnVector(blockLoc, loc)) {
            return false;
        }
        if (!hasSight(player, blockLoc)) return false;


        /*
        if (face.equals(BlockFace.NORTH)) {
            return north(loc, blockLoc);
        }
        if (face.equals(BlockFace.SOUTH)) {
            return south(loc, blockLoc);
        }

        if (face.equals(BlockFace.WEST)) {
            return west(loc, blockLoc);
        }
        if (face.equals(BlockFace.EAST)) {
            return east(loc, blockLoc);
        }


         */

        return true;
    }



    private boolean hasSight(Player var, Location blockLoc) {

        assert blockLoc.getWorld() != null;
        Entity entity = blockLoc.getWorld().spawnEntity(blockLoc, EntityType.BAT);


        boolean has = var.hasLineOfSight(entity);
        entity.remove();
        return has;
    }


    private boolean checkIfBlocksOnVector(Location a, Location b) {
        //A to B
        Vector v = b.toVector().subtract(a.toVector());
        double j = Math.floor(v.length());
        v.multiply(1/v.length()); //Converting v to a unit vector
        for (int i = 0; i<=j; i++) {
            v = b.toVector().subtract(a.toVector());
            v.multiply(1/v.length());
            @SuppressWarnings("ConstantConditions") Block block = a.getWorld().getBlockAt((a.toVector().add(v.multiply(i))).toLocation(a.getWorld()));
            if (!block.getType().equals(Material.AIR) && !block.getType().equals(Material.WATER)) { //Here you can set your own "transparent" blocks
                return true;
            }
        }
        return false;
    }




    @SuppressWarnings("unused")
    private boolean north(Location player, Location block) {

        boolean view = true;

        int dis = block.getBlockZ() - player.getBlockZ();
        if (dis == 0) return true;
        if (dis < 0) dis *= -1;



        while (!block.getBlock().getType().equals(Material.AIR)) {

            boolean faceAir = false;

            for (int var = -1 ; var < 1 ; var++) {
                for (int var1 = -1; var1 < 1; var1++) {
                    Location aw = block.clone();
                    aw.add(var, var1, 0);
                    if (aw.getBlock().getType().equals(Material.AIR)) {
                        faceAir = true;
                    }
                }
            }


            block.add(0, 0, -1);
            dis--;
            if (dis < 0) break;

            if (!faceAir && !block.getBlock().getType().equals(Material.AIR)) return false;
        }


        return view;
    }

    @SuppressWarnings("unused")
    private boolean south(Location player, Location block) {

        boolean view = true;

        int dis = block.getBlockZ() - player.getBlockZ();
        if (dis == 0) return true;
        if (dis < 0) dis *= -1;



        while (!block.getBlock().getType().equals(Material.AIR)) {

            boolean faceAir = false;

            for (int var = -1 ; var < 1 ; var++) {
                for (int var1 = -1; var1 < 1; var1++) {
                    Location aw = block.clone();
                    aw.add(var, var1, 0);
                    if (aw.getBlock().getType().equals(Material.AIR)) {
                        faceAir = true;
                    }
                }
            }


            block.add(0, 0, +1);
            dis--;
            if (dis < 0) break;

            if (!faceAir && !block.getBlock().getType().equals(Material.AIR)) return false;
        }

        return view;
    }

    @SuppressWarnings("unused")
    private boolean west(Location player, Location block) {

        boolean view = true;

        int dis = block.getBlockX() - player.getBlockX();
        if (dis == 0) return true;
        if (dis < 0) dis *= -1;



        while (!block.getBlock().getType().equals(Material.AIR)) {

            boolean faceAir = false;

            for (int var = -1 ; var < 1 ; var++) {
                for (int var1 = -1; var1 < 1; var1++) {
                    Location aw = block.clone();
                    aw.add(0, var, var1);
                    if (aw.getBlock().getType().equals(Material.AIR)) {
                        faceAir = true;
                    }
                }
            }


            block.add(-1, 0, 0);
            dis--;
            if (dis < 0) break;

            if (!faceAir && !block.getBlock().getType().equals(Material.AIR)) return false;
        }


        return view;
    }

    @SuppressWarnings("unused")
    private boolean east(Location player, Location block) {

        boolean view = true;

        int dis = block.getBlockX() - player.getBlockX();
        if (dis == 0) return true;
        if (dis < 0) dis *= -1;



        while (!block.getBlock().getType().equals(Material.AIR)) {

            boolean faceAir = false;

            for (int var = -1 ; var < 1 ; var++) {
                for (int var1 = -1; var1 < 1; var1++) {
                    Location aw = block.clone();
                    aw.add(0, var, var1);
                    if (aw.getBlock().getType().equals(Material.AIR)) {
                        faceAir = true;
                    }
                }
            }


            block.add(+1, 0, 0);
            dis--;
            if (dis < 0) break;

            if (!faceAir && !block.getBlock().getType().equals(Material.AIR)) return false;
        }


        return view;
    }









    public Player toPlayer() {
        return Bukkit.getPlayer(uuid);
    }


}
