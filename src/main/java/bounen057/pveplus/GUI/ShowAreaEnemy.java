package bounen057.pveplus.GUI;

import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.SubFunctions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MonsterEggs;

import java.util.ArrayList;
import java.util.List;

public class ShowAreaEnemy {
    private PVEPlus plugin;
    SubFunctions subFunctions = new SubFunctions(plugin);
    public ShowAreaEnemy(PVEPlus plugin) {
        this.plugin = plugin;
    }

    String inv_name = "モンスター一覧 ";

    public void show(Player p,String area){
        plugin.enemy.reloadConfig();

        inv_name += area;
        FileConfiguration file = plugin.enemy.getConfig();

        Inventory inv = Bukkit.createInventory(null,36,inv_name);
        ItemStack item = new ItemStack(Material.MONSTER_EGG);
        ItemMeta itemMeta = item.getItemMeta();
        List<String> lore = new ArrayList<>();

        for(int i=0;i < 4;i++) {
            // 設定されているKeyを取得する
            if (plugin.enemy.getConfig().get("area." + area + "." + i) != null) {
                for (String mobName : file.getConfigurationSection("area." + area + "." + i).getKeys(false)) {
                    switch (i){
                        case 0:
                            item.setDurability((short) 6);
                        case 1:
                            item.setDurability((short) 54);
                        case 2:
                            item.setDurability((short) 60);
                        case 3:
                            item.setDurability((short) 58);
                    }

                    lore.clear();
                    int raito = file.getInt("area." + area + "." + i + "." + mobName);
                    itemMeta.setDisplayName("§7§l" + mobName);
                    lore.add("§e-階層> " + i);
                    lore.add("§e-比重> " + raito);
                    lore.add("§cクリックで消去!");


                    itemMeta.setLore(lore);
                    item.setItemMeta(itemMeta);
                    inv.addItem(item);
                }
            }
        }

        p.openInventory(inv);
    }


    public void clicked(InventoryClickEvent e){
        List<String> lore = new ArrayList<>();
        lore = e.getCurrentItem().getItemMeta().getLore();

        String area = e.getInventory().getName().replace("モンスター一覧 ","");
        String mobname = e.getCurrentItem().getItemMeta().getDisplayName().replace("§7§l","");
        String layer = lore.get(0).replace("§e-階層> ","");

        plugin.enemy.getConfig().set("area."+area+"."+layer+"."+mobname,null);
        plugin.enemy.saveConfig();

        show((Player) e.getWhoClicked(),area);

        e.setCancelled(true);
    }
}
