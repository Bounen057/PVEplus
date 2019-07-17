package bounen057.pveplus.utils;

import bounen057.pveplus.PVEPlus;
import org.bukkit.block.Biome;

import java.util.Arrays;

public class GetBiome {
    private PVEPlus plugin;

    public GetBiome(PVEPlus plugin) {
        this.plugin = plugin;
    }


    Biome[] Desert = {Biome.DESERT,Biome.DESERT_HILLS,Biome.MUTATED_DESERT};
    Biome[] Mountain = {Biome.EXTREME_HILLS,Biome.EXTREME_HILLS_WITH_TREES,Biome.MUTATED_EXTREME_HILLS};
    Biome[] Forest = {Biome.FOREST,Biome.FOREST_HILLS,Biome.MUTATED_BIRCH_FOREST,Biome.MUTATED_FOREST,Biome.ROOFED_FOREST,Biome.BIRCH_FOREST_HILLS};
    Biome[] Ice = {Biome.ICE_FLATS,Biome.ICE_MOUNTAINS,Biome.MUTATED_ICE_FLATS};

    public String BiomeCategory(Biome b){
        String name = null;

        // 砂漠系
        if(Arrays.asList(Desert).contains(b)){
            name = "desert";
        }
        // 山岳系
        if(Arrays.asList(Mountain).contains(b)){
            name = "mountain";
        }
        // 砂漠系
        if(Arrays.asList(Forest).contains(b)){
            name = "forest";
        }
        // 雪原系
        if(Arrays.asList(Ice).contains(b)){
            name = "ice";
        }
        return name;
    }
}
