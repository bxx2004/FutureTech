package net.bxx2004.futuretech.slimefun.main.items.materials.basic;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import net.bxx2004.futuretech.core.data.ConfigManager;
import net.bxx2004.futuretech.core.utils.RegisterItem;
import net.bxx2004.futuretech.slimefun.SlimefunFactory;
import net.bxx2004.futuretech.slimefun.main.Item;
import net.bxx2004.pandalib.bukkit.pentity.PEntity;
import net.bxx2004.pandalib.bukkit.pentity.PEntityMeta;
import net.bxx2004.pandalib.bukkit.pitem.PItemStack;
import net.bxx2004.pandalib.bukkit.plistener.PListener;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Vindicator;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.ItemStack;

@RegisterItem
public class FT_ROBOTARM extends Item<SlimefunItemStack> {
    public FT_ROBOTARM(){
        super();
    }
    @Override
    public SlimefunItemStack itemStack() {
        return new SlimefunItemStack(getID(),"fb1847c0a6ed488fa22a8acc3694fe31c355e6399c3201306555485e9bc4a56",
                ConfigManager.itemName(getID()),
                ConfigManager.itemLore(getID()));
    }

    @Override
    public ItemGroup group() {
        return SlimefunFactory.MATERIALS;
    }

    @Override
    public RecipeType type() {
        return RecipeType.MOB_DROP;
    }
    @Override
    public ItemStack[] recipe() {
        return new ItemStack[]{
                null, null, null,
                null, new PItemStack(Material.IRON_INGOT,"VINDICATOR" ,ConfigManager.mobName("FT_SIRI")), null,
                null, null, null
        };
    }
    @Override
    public PListener listener() {
        return new PListener(){
          @EventHandler
          public void onDeath(EntityDeathEvent event){
              if (event.getEntity() instanceof Vindicator){
                  LivingEntity entity = event.getEntity();
                  if (entity.getCustomName() != null){
                      if (entity.getCustomName().equals(ConfigManager.mobName("FT_SIRI"))){
                          event.getDrops().add(getItem().getItem());
                      }
                  }
              }
          }
          @EventHandler
          public void onSpawn(EntitySpawnEvent event){
              if (event.getLocation().getWorld().getName().equals("ft_world")){
                  if (event.getEntity() instanceof Vindicator){
                      event.setCancelled(true);
                      PEntity entity = new PEntity(EntityType.VINDICATOR);
                      PEntityMeta meta = entity.getMeta();
                      meta.setDisplayName(ConfigManager.mobName("FT_SIRI"));
                      meta.setHealth(200);
                      meta.addAttribute(Attribute.GENERIC_ATTACK_DAMAGE,7);
                      entity.setMeta(meta);
                      entity.spawn(event.getLocation());
                  }
              }
          }
        };
    }

    @Override
    public Research research() {
        return null;
    }
}