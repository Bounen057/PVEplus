package bounen057.pveplus.commands;

import bounen057.pveplus.GUI.AreaList;
import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.SubFunctions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class PVEcommand implements CommandExecutor {


    private PVEPlus plugin;
    SubFunctions subFunctions = new SubFunctions(plugin);

    public PVEcommand(PVEPlus plugin) {
        this.plugin = plugin;
    }


    String[] arealist = {"layer","desert","mountain","forest","ice"};

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args[0].equals("help")) {
            p.sendMessage("§a/pve list enemy");
            p.sendMessage("§a/pve list area");
            p.sendMessage("§a/pve add <エリア> <地層> <名前> <比重>");
        }

        if (args[0].equals("list")) {
            if (args[1].equals("enemy")) {
                new AreaList(plugin).show(p);
            }

            if(args[1].equals("area")){
                listarea(p);
            }
        }

        if (args[0].equals("add")) {
            String area = args[1]; // バイオーム
            String layer = args[2]; // 地層
            String name = args[3]; // mobの名前
            String raito = args[4];  // 比重

            //
            // エラーチェック
            //
            // int型かどうか
            if(!subFunctions.isInt(layer) || !subFunctions.isInt(raito)){
                p.sendMessage(plugin.logo+"§c数値ではないものがあります!");
                return false;
            }

            if(!Arrays.asList(arealist).contains(area)){
                p.sendMessage(plugin.logo+"§cそのようなエリア名はありません!");
                return false;
            }

            if(Integer.parseInt(layer) < 0 && Integer.parseInt(layer) > 3){
                p.sendMessage(plugin.logo+"§c数値が大きすぎます!!");
                return false;
            }

            plugin.enemy.getConfig().set("area." + area + "." + layer + "." + name, Integer.parseInt(raito));
            p.sendMessage(plugin.logo+"§aSuccees! "+area+"の地層"+layer+"に"+name+"を"+raito+"で登録しました!");
        }

        plugin.enemy.saveConfig();
        return false;
    }


    private void listarea(Player p){
        p.sendMessage("§e§l[§a§l§k===" + "§6§l[エリア(バイオーム)一覧]" + "§a§l§k===§e§l]");
        p.sendMessage("§a-layer §7全てのバイオームで出現するモンスター");
        p.sendMessage("§a-desert §7砂漠のバイオーム");
        p.sendMessage("§a-mountain §7山岳のバイオーム");
        p.sendMessage("§a-forest §7森のバイオーム");
        p.sendMessage("§a-ice §7雪のバイオーム");
    }
}
