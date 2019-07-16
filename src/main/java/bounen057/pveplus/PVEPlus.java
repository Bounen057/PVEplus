package bounen057.pveplus;

import bounen057.pveplus.Data.CustomConfig;
import bounen057.pveplus.commands.PVEcommand;
import bounen057.pveplus.listeners.SpawnEnemy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PVEPlus extends JavaPlugin {


    CustomConfig config,enemy;
    @Override
    public void onEnable() {

        config = new CustomConfig(this);
        config.saveDefaultConfig();

        enemy = new CustomConfig(this,"enemy.yml");
        enemy.saveDefaultConfig();


        Bukkit.getPluginManager().registerEvents(new SpawnEnemy(this),this);

        Bukkit.getPluginCommand("pve").setExecutor(new PVEcommand(this));
    }

    @Override
    public void onDisable() {
    }
}
