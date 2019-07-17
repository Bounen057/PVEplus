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
        inv.setItem(1,item("desert"));
        inv.setItem(2,item("mountain"));
        inv.setItem(3,item("forest"));
        inv.setItem(4,item("ice"));

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
        if(str.equals("desert")){
            item.setType(Material.SAND);
            itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("desert");
            lore.add("§e砂漠のモンスター");
        }
        if(str.equals("mountain")){
            item.setType(Material.GRASS);
            itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("mountain");
            lore.add("§e山岳のモンスター");
        }
        if(str.equals("forest")){
            item.setType(Material.LEAVES);
            itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("forest");
            lore.add("§e森のモンスター");
        }
        if(str.equals("ice")){
            item.setType(Material.PACKED_ICE);
            itemMeta = item.getItemMeta();

            itemMeta.setDisplayName("ice");
            lore.add("§e雪原のモンスター");
        }



        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);
        return item;
    }

}
