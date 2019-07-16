package bounen057.pveplus.listeners;

import bounen057.pveplus.PVEPlus;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class SpawnEnemy implements Listener{
    private PVEPlus plugin;

    public SpawnEnemy(PVEPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Spawn(CreatureSpawnEvent e){

    }
}
