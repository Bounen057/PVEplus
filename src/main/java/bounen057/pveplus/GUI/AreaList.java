package bounen057.pveplus.GUI;

import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.SubFunctions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class AreaList {
    private PVEPlus plugin;
    SubFunctions subFunctions = new SubFunctions(plugin);

    public AreaList(PVEPlus plugin) {
        this.plugin = plugin;
    }

    String inv_name = "エリア一覧";


    public void show(Player p){
        System.out.println("inv");
        Inventory inv = Bukkit.createInventory(null,9 * 4,inv_name);

        inv.setItem(0,item("layer"));

        p.openInventory(inv);
    }


    public void click(InventoryClickEvent e){
        String name = e.getCurrentItem().getItemMeta().getDisplayName();
        new ShowAreaEnemy(plugin).show((Player) e.getWhoClicked(),name);
        e.setCancelled(true);
    }

    public ItemStack item(String str){
        ItemStack item = new ItemStack(Material.AIR);
        ItemMeta itemMeta = null;
        List<String> lore = new ArrayList<>();

        if(str.equals("layer")){
            item.setType(Material.STONE);
            itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("layer");
            lore.add("§e-0 天空      y100~");
            lore.add("§e-1 地上      y50~");
            lore.add("§e-2 地下      y20~");
            lore.add("§e-3 地下第二層 y0~");
        }


        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

}
