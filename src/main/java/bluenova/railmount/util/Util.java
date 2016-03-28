/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmount.util;

import bluenova.railmount.RailMount;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Collection;

/**
 *
 * @author BoneBoy
 */
public class Util {
    
    /**
     * Checks if Player Has Permission
     * @param pl Player to Check
     * @param perm Permission to Check
     * @return Has Permission or Not
     */
    public static boolean hasPermission(Player pl, String perm) {
        if (RailMount.Permissions != null) {
            if (RailMount.Permissions.has(pl, perm)) {
                return true;
            } else {
                pl.sendMessage(ChatColor.RED + "No Permission!");
                return false;
            }
        } else {
            if (pl.hasPermission(perm) || pl.isOp()) {
                return true;
            } else {
                pl.sendMessage(ChatColor.RED + "No Permission!");
                return false;
            }
        }
    }

    public static Entity closest(Location loc, Collection<Entity> entities) {
        double closest = 100000000000.0;
        Entity closestEntity = null;
        for(Entity entity : entities) {
            if(loc.distance(entity.getLocation()) < closest) {
                closest = loc.distance(entity.getLocation());
                closestEntity = entity;
            }
        }
        return closestEntity;
    }
}
