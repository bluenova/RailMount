package bluenova.railmount;

import bluenova.railmount.commands.RailMountCommandExecutor;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;


/**
 * RailMount for Bukkit
 * 
 * @author BoneBoy
 */
public class RailMount extends JavaPlugin {
    
    public static Plugin plugin;
    
    /**
     * The Plugin Manager of the Bukkit Server
     */
    public static PluginManager pm;
    
    /**
     * The Bukkit Server
     */
    public static Server server;
       
    /**
     * The Permision Manager
     */
    public static PermissionManager Permissions;

    /**
     * Listener Object For commands
     */
    private RailMountCommandExecutor myExecutor;
    
    /**
     * System
     */
    public static System system;
    

    /**
    *The Recorded Player to RailIndex
    */
    public static HashMap<Player, Integer> recordedPlayer = new HashMap<Player, Integer>();
    
    @Override
    public void onEnable() { 
        RailMount.plugin = this;
        RailMount.pm = getServer().getPluginManager();
        RailMount.server = getServer();

        myExecutor = new RailMountCommandExecutor(this);
	    getCommand("mct").setExecutor(myExecutor);
        getCommand("minecart").setExecutor(myExecutor);

        //Setting up PermissionEx if plugin is installed
        this.setupPermissions();
    }
    
    
    
    private void setupPermissions() {
        
        Plugin test = this.getServer().getPluginManager().getPlugin("Permissions");
        
        if(Bukkit.getServer().getPluginManager().isPluginEnabled("PermissionsEx")){
            RailMount.Permissions = PermissionsEx.getPermissionManager();
            System.out.println("[RailMount] Permissions Enabled");
        } else {
            System.out.println("[RailMount] Permission System not found! Using native Permissions or OP!");
        }
    }
}
