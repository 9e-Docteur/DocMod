package be.ninedocteur.docmod.client.gui.overlay;

import com.mojang.blaze3d.vertex.PoseStack;

import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.item.sonic.NinedocteurSonic;
import be.ninedocteur.docmod.common.item.sonic.SonicItem;
import be.ninedocteur.docmod.utils.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;

@OnlyIn(Dist.CLIENT)
public class DocModSonicChargeOverlay {

    public static void render(RenderGuiOverlayEvent.Pre event){
            int posX = 22;
            int posY = 10;
            ItemStack itemIcon = new ItemStack(DMItems.GENERIC_SONIC_SCREWDRIVER.get());
            if (Minecraft.getInstance().player.getItemInHand(InteractionHand.MAIN_HAND.MAIN_HAND).getItem() instanceof SonicItem sonicItem) {
                Minecraft.getInstance().font.draw(event.getPoseStack(), "§6Sonic charge: §r" + sonicItem.getCharge(), posX, posY, ColorUtils.getWhite());
                Minecraft.getInstance().font.draw(event.getPoseStack(), "§Level: §r" + sonicItem.getSonicLevel(), posX, posY + 10, ColorUtils.getWhite());
                Minecraft.getInstance().font.draw(event.getPoseStack(), "§6XP: §r" + sonicItem.getXp(), posX, posY + 20, ColorUtils.getWhite());

                Minecraft.getInstance().getItemRenderer().renderGuiItem(new PoseStack(), itemIcon, 4, 6);
            }
    }
}
