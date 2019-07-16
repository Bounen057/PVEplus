package bounen057.pveplus.commands;

import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.SubFunctions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PVEcommand implements CommandExecutor {


    private PVEPlus plugin;
    SubFunctions subFunctions = new SubFunctions(plugin);
    public PVEcommand(PVEPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if(args[0].equals("help")){
            p.sendMessage("§a/pve enemy list layer <数字>");
            p.sendMessage("§a/pve enemy list baiome <id>");
            p.sendMessage("§a/pve layer add <地層> <名前> <比重>");
        }

        if(args[0].equals("layer")){
            if(args[1].equals("add")){
                String layer = args[2];
                String name = args[3];
                String raito = args[4];

                plugin.enemy.getConfig().set("area.layer."+layer+"."+name,raito);
            }
        }

        plugin.enemy.saveConfig();
        return false;
    }
}
