package bounen057.pveplus.commands;

import bounen057.pveplus.PVEPlus;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PVEcommand implements CommandExecutor {

    private PVEPlus plugin;

    public PVEcommand(PVEPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        p.sendMessage("hello world!");
        return false;
    }
}
