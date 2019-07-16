package bounen057.pveplus.listeners;

import bounen057.pveplus.PVEPlus;
import bounen057.pveplus.utils.GetLayer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
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
    @EventHandler
    public void OnSpawn(CreatureSpawnEvent e) {
        LivingEntity entity = e.getEntity();
        Location entityLoc = entity.getLocation();

        // ワールド名が違う場合return
        if ( entityLoc.getWorld() != miningSpawnPoint.getWorld() ) {
            return;
        }

        // スポーン理由が NATURAL ではない場合return
        if ( e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.NATURAL ) {
            return;
        }
        FileConfiguration conf = plugin.enemy.getConfig();
        int layer = getLayer.Get(entityLoc);


        // 各Mobを入れてランダムに抽選するList
        List<String> enemies = new ArrayList<>();

        // 設定されているKeyを取得する
        for ( String mobName : conf.getConfigurationSection("area.layer." + layer).getKeys(false) ) {
            // 比率を取得
            int raito = plugin.enemy.getConfig().getInt("area.layer."+layer+"."+mobName);
            System.out.println("area.layer."+layer+"."+mobName+": "+raito);

            // enemiesRaitoにその数だけ追加
            for ( ; raito > 0; raito-- ) {
                enemies.add(mobName);
            }
        }

        // サイズが0以下ならreturn
        if ( enemies.size() <= 0 ) {
            plugin.getLogger().warning("enemies list のサイズが0です");
            return;
        }

        // Listの中からランダムに選んで抽選
        Collections.shuffle(enemies);
        String mobName = enemies.get(0);

        /**
         * この抽選方法は数が大きすぎるとその分だけサーバーが停止してしまう。なにかもっと良い方法があるはず。でもこれが一番読みやすい
         */

        // 敵Mobをスポーン
        spawn(mobName, entityLoc);

        // Entityのスポーンをキャンセルする
        e.setCancelled(true);
    }

    private void spawn(String name, Location l) {
        String locationStr = l.getWorld().getName() + "," + l.getX() + "," + l.getY() + "," + l.getZ();
        Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "mm mobs spawn " + name + " 1 " + locationStr);
    }
}
