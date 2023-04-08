package be.ninedocteur.docmod.common.item.space;

import be.ninedocteur.docmod.utils.PlanetUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.checkerframework.checker.units.qual.C;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpaceSuitHelmet extends ArmorItem {
	private float currentTick = 0;
    public static int air;
    
    public SpaceSuitHelmet(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
		super(p_40386_, p_266831_, p_40388_);
		// TODO Auto-generated constructor stub
	}

   
    @Override
    public void appendHoverText(ItemStack p_41421_, @Nullable Level p_41422_, List<Component> p_41423_, TooltipFlag p_41424_) {
        super.appendHoverText(p_41421_, p_41422_, p_41423_, p_41424_);
        if(p_41421_.hasTag() && p_41421_.getTag().contains("air")){
            p_41423_.add(Component.literal("Air: " + p_41421_.getTag().getInt("air") + "%"));
        }
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        super.inventoryTick(p_41404_, p_41405_, p_41406_, p_41407_, p_41408_);
        if(p_41406_ instanceof Player player){
//            if(PlanetUtils.getDimension("moon") && PlanetUtils.isHelmetPresent(player)){
//                if(!p_41404_.hasTag()){
//                    p_41404_.setTag(new CompoundTag());
//                    p_41404_.getTag().putInt("air", 100);
//                }
//            }
        }
        if(air != 0){
            update(p_41404_);
        }
    }

    public void update(ItemStack stack) {
        if(currentTick > 240) {
            air = stack.getTag().getInt("air");
            System.out.println(air);
            air--;
            currentTick = 0;
        }
        currentTick++;
    }
}
