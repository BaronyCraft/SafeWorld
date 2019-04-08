package de.guntram.bukkit.SafeWorld;

import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    
    public static Main instance;
    private List<String> safeWorlds;
    
    @Override 
    public void onEnable() {
        if (instance==null)
            instance=this;
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        getServer().getPluginManager().registerEvents(this, this);
        safeWorlds = config.getStringList("worlds");
    }
    
    @EventHandler(ignoreCancelled = true, priority=EventPriority.NORMAL)
    public void onPlayerDamageEvent(EntityDamageEvent event) {
        if (!(event.getEntityType() == EntityType.PLAYER))
            return;
        Player player=(Player)event.getEntity();
        if (safeWorlds.contains(player.getLocation().getWorld().getName())) {
            event.setCancelled(true);
        }
    }
}
