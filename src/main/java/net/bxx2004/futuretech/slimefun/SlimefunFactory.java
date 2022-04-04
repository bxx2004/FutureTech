package net.bxx2004.futuretech.slimefun;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.FutureTech;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import org.bukkit.Material;

public class SlimefunFactory {
    //分类注册
    public static final NestedItemGroup MAIN = new NestedItemGroup(
            Tools.key("main"),
            new PItemStack(Material.BLUE_STAINED_GLASS_PANE,
                    ConfigManager.color() + ConfigManager.itemGroup("main")).clone()
    );
    public static final SubItemGroup MACHINE = new SubItemGroup(
            Tools.key("machine"),
            MAIN,
            new PItemStack(Material.BEACON,
                    ConfigManager.color() + ConfigManager.itemGroup("machine")).clone()
    );
    public static final SubItemGroup ROBOT = new SubItemGroup(
            Tools.key("robot"),
            MAIN,
            new PItemStack("robot",
                    ConfigManager.color() + ConfigManager.itemGroup("robot")).clone()
    );
    public static final SubItemGroup MATERIALS = new SubItemGroup(
            Tools.key("materials"),
            MAIN,
            new PItemStack(Material.IRON_INGOT,
                    ConfigManager.color() + ConfigManager.itemGroup("materials")).clone()
    );
    public static Research CPU;
    public static void init(){
        MAIN.register(FutureTech.instance());
        ROBOT.register(FutureTech.instance());
        MACHINE.register(FutureTech.instance());
        MATERIALS.register(FutureTech.instance());
        if (ConfigManager.enableResearch()){
            CPU = new Research(Tools.key("CPU"),1,"CPU",10);
            CPU.register();
        }
    }
}
