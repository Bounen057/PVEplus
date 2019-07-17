package bounen057.pveplus.listeners;

import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.GetBiome;
import bounen057.pveplus.utils.GetLayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*

 <通常>
 area:
   layer:
     0:
       mobname: <確率>

 */
public class SpawnEnemy implements Listener{
    private PVEPlus plugin;
    private GetLayer getLayer = new GetLayer(plugin);
        private Location miningSpawnPoint = null;

    public SpawnEnemy(PVEPlus plugin) {
            this.plugin = plugin;

        World mining = Bukkit.getWorld("mining");
        if ( mining != null ) {
            miningSpawnPoint = mining.getSpawnLocation();
        }
    }


    /**
     * 敵Mobがスポーンしたときに呼ばれるメソッド
     */

    List<String> enemies = new ArrayList<>();
    FileConfiguration conf = plugin.enemy.getConfig();

    @EventHandler
    public void OnSpawn(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();
        Location entityLoc = entity.getLocation();

        boolean isSpawn = false;

        // ワールド名が違う場合return
        if ( entityLoc.getWorld() != miningSpawnPoint.getWorld() ) {
            return;
        }

        // スポーン理由が NATURAL もしくは SPAWNER_EGG ではない場合return
        if ( e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL) {
            isSpawn =true;
        }
        if ( e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.SPAWNER_EGG ){
            isSpawn =true;
        }

        if(!isSpawn){
            return;
        }

        // 変数
        int layer = getLayer.Get(entityLoc);
        String biome = new GetBiome(plugin).BiomeCategory(entityLoc.getBlock().getBiome());

        // 各Mobを入れてランダムに抽選するList
        enemies.clear();

        // 通常のモンスター
        layer_enemy(layer);
        if(biome != null){
            biome_enemy(layer,biome);
        }


        // サイズが0以下ならreturn
        if ( enemies.size() <= 0 ) {
            plugin.getLogger().warning("enemies list のサイズが0です");
            return;
        }

        // Listの中からランダムに選んで抽選
        Collections.shuffle(enemies);
        String mobName = enemies.get(0);

        // 敵Mobをスポーン
        spawn(mobName, entityLoc);

        // Entityのスポーンをキャンセルする
        e.setCancelled(true);
    }

    private void spawn(String name, Location l) {
        String locationStr = l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ();
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm mobs spawn " + name + " 1 " + locationStr);
    }

    private void layer_enemy(int layer){
        if(conf.get("area.layer." + layer) == null){
            return;
        }

        // 設定されているKeyを取得する
        for ( String mobName : conf.getConfigurationSection("area.layer." + layer).getKeys(false) ) {

            // 比率を取得
            int raito = conf.getInt("area.layer."+layer+"."+mobName);


            // enemiesRaitoにその数だけ追加
            for ( ; raito > 0; raito-- ) {
                enemies.add(mobName);
            }
        }
    }

    private void biome_enemy(int layer,String biome){
        if(conf.get("area."+biome+"." + layer) == null){
            return;
        }

        // 設定されているKeyを取得する
        for ( String mobName : conf.getConfigurationSection("area."+biome+"." + layer).getKeys(false) ) {

            // 比率を取得
            int raito = conf.getInt("area."+biome+"."+layer+"."+mobName);


            // enemiesRaitoにその数だけ追加
            for ( ; raito > 0; raito-- ) {
                enemies.add(mobName);
            }
        }
    }
}
