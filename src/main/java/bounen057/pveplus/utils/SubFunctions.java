package bounen057.pveplus.utils;

import bounen057.pveplus.PVEPlus;

public class SubFunctions {
    private PVEPlus plugin;

    public SubFunctions(PVEPlus plugin) {
        this.plugin = plugin;
    }

    public boolean isInt(String str){
        int i = 0;

        try {
            i = Integer.parseInt(str);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
