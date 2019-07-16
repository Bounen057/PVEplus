package bounen057.pveplus.utils;

import bounen057.pveplus.PVEPlus;
import org.bukkit.Location;


/*
 * y: 0 ~ 20:最地下層
 * y: 20 ~ 50:地下層
 * y: 50 ~ 100:地上層
 * y: 100 ~:山岳層
 */

public class GetLayer {
    private PVEPlus plugin;

    public GetLayer(PVEPlus plugin) {
        this.plugin = plugin;
    }

    public int Get(Location l){
        Double y = l.getY();
        int layer = 0;

        if(y > 0){
            layer = 3;
        }
        if(y > 20){
            layer = 2;
        }
        if(y > 50){
            layer = 1;
        }
        if(y > 100){
            layer = 0;
        }

        return layer;
    }

}
