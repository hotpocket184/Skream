package us.happypockets.skream.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Citizens {
    public static boolean hasCitizens(){
        PluginManager pm = Bukkit.getServer().getPluginManager();
        Plugin citizens = pm.getPlugin("Citizens");
        if(citizens == null){
            return false;
        }
        else{
            if(citizens.isEnabled()){
                return true;
            }
            else{
                return false;
            }
        }
    }

}
