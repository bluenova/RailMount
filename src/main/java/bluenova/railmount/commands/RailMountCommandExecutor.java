/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bluenova.railmount.commands;

import bluenova.railmount.RailMount;
import bluenova.railmount.util.Util;
import org.bukkit.Location;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.libs.jline.internal.Log;
import org.bukkit.craftbukkit.v1_9_R1.command.CraftBlockCommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.RideableMinecart;
import org.bukkit.util.Vector;

import java.util.Collection;

/**
 *
 * @author BoneBoy
 */
public class RailMountCommandExecutor implements CommandExecutor {

    private RailMount plugin;

    public RailMountCommandExecutor(RailMount plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            sender.sendMessage("Don't run this Command as a Player!");
            return true;
        } else if (sender instanceof CraftBlockCommandSender) {
            CraftBlockCommandSender send = (CraftBlockCommandSender) sender;
            Location loc = send.getBlock().getLocation();
            Collection<Entity> minecarts = loc.getWorld().getEntitiesByClasses(RideableMinecart.class);
            RideableMinecart minecart = (RideableMinecart) Util.closest(loc, minecarts);
            if(minecart == null)
                return true;

            if(args.length < 2) {
                sender.sendMessage("Missing Params!");
                return true;
            } else if(args[0].equals("mount") && args.length == 2) {
                Player player = minecart.getServer().getPlayer(args[1]);
                minecart.setPassenger(player);
            } else if(args[0].equals("speed") && args.length == 2) {
                double spd = Double.parseDouble(args[1]);
                minecart.setMaxSpeed(1000.0);
                Vector vio = minecart.getVelocity();
                if(vio.getX() != 0.0) {
                    vio.setX(vio.getX() > 0 ? spd * 1 : spd * -1 );
                }
                if(vio.getY() != 0.0) {
                    vio.setY(vio.getY() > 0 ? spd * 1 : spd * -1 );
                }
                if(vio.getZ() != 0.0) {
                    vio.setZ(vio.getZ() > 0 ? spd * 1 : spd * -1);
                }
                minecart.setVelocity(vio);
            }

            return true;
        } else {
            sender.sendMessage("Don't run this Command from Console!");
            return true;
        }
    }
}
