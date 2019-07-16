package bounen057.pveplus;

import bounen057.pveplus.commands.PVEcommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PVEPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginCommand("pve").setExecutor(new PVEcommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
