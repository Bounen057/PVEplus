package bounen057.pveplus.listeners;

import bounen057.pveplus.GUI.AreaList;
import bounen057.pveplus.GUI.ShowAreaEnemy;
import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.GetLayer;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickedInventory implements Listener {
    private PVEPlus plugin;

    public ClickedInventory(PVEPlus plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void Click(InventoryClickEvent e){
        String name = e.getInventory().getName();

        switch (name){
            case "エリア一覧":
                new AreaList(plugin).click(e);
        }

        if(name.contains("モンスター一覧 ")){
            new ShowAreaEnemy(plugin).clicked(e);
        }
    }
}
