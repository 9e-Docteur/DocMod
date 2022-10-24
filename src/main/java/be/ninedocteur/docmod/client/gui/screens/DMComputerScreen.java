package be.ninedocteur.docmod.client.gui.screens;

import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.common.block.ComputerBlock;
import be.ninedocteur.docmod.common.computer.*;
import be.ninedocteur.docmod.common.computer.OS.MotherboardOS;
import be.ninedocteur.docmod.common.computer.command.BaseCommand;
import be.ninedocteur.docmod.common.init.DMItems;
import be.ninedocteur.docmod.common.tileentity.ComputerTileEntity;
import be.ninedocteur.docmod.utils.ColorUtils;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.checkerframework.checker.units.qual.C;
@OnlyIn(Dist.CLIENT)
public class DMComputerScreen extends Screen {
    public static ResourceLocation COMPUTER_GUI = new ResourceLocation(DocMod.MOD_ID, "textures/gui/computer_screen.png");

    private static EditBox terminal;

    private boolean isEnterPressed;
    private boolean isBooted = false;

    private ComputerTileEntity computerTileEntity;

    private BaseOS currentOS;
   // @OnlyIn(Dist.CLIENT)
    public DMComputerScreen(ComputerTileEntity computerTileEntity) {
        super(Component.literal("none"));
        this.computerTileEntity = computerTileEntity;
        init();
    }

    @Override
    protected void init() {
        terminal = new EditBox(this.font, this.width / 2 - 110, this.height / 2 + 60, 220, 20, Component.literal(""));
        addRenderableWidget(terminal);
        this.isEnterPressed = false;
        setInitialFocus(terminal);
        //if(!checkComposant()){
            //return;
        //}
        for(BaseOS baseOS : OSRegistry.OSes){
            for(ItemStack itemStack : computerTileEntity.getItems()){
                if(itemStack.is(DMItems.DISK.get())){
                    if(baseOS.getName().equals(itemStack.getTag().getString("OS"))){
                        currentOS = baseOS;
                        break;
                    }
                    currentOS = OSRegistry.MOTHERBOARD;
                }
            }
            break;
        }
        if(currentOS == null){
            currentOS = OSRegistry.MOTHERBOARD;
        }
        currentOS.init(computerTileEntity);
        if(!isBooted) {
            currentOS.boot();
            isBooted = true;
        }
        isBooted = true;
        super.init();
    }

    private boolean checkComposant(){
        if(computerTileEntity.getItems().contains(DMItems.CPU.get()) && computerTileEntity.getItems().contains(DMItems.ALIMENTATION.get()) &&
                computerTileEntity.getItems().contains(DMItems.MOTHERBOARD.get()) && computerTileEntity.getItems().contains(DMItems.RAM.get()) &&
                computerTileEntity.getItems().contains(DMItems.DISK.get())){
            return true;
        }
        return false;
    }

    @Override
    public boolean keyPressed(int p_96552_, int p_96553_, int p_96554_) {
        if(p_96552_ == 257 || p_96552_ == 335){
            this.isEnterPressed = true;
        }
        return super.keyPressed(p_96552_, p_96553_, p_96554_);
    }

    @Override
    public void render(PoseStack p_96562_, int p_96563_, int p_96564_, float p_96565_) {
        int i2 = (this.width - 229) / 2; //x
        int j = (this.height - 156) / 2; //y
        RenderSystem.setShaderTexture(0, COMPUTER_GUI);
        blit(p_96562_, terminal.x - 4, terminal.y - 140, 0, 0, 256, 198);
        if(isEnterPressed){
            currentOS.execute(terminal.getValue());
            isEnterPressed = false;
            terminal.setValue("");
        }
        for(int i = computerTileEntity.TERMINAL_HISTORY.size() - 15; i < computerTileEntity.TERMINAL_HISTORY.size(); i++){
            if(i >= 0){
                drawString(p_96562_, this.font, computerTileEntity.TERMINAL_HISTORY.get(i),this.width / 2 - 100, this.height / 2 - 43 + (i*9 - (int)(computerTileEntity.TERMINAL_HISTORY.size() / 14F - i*18)), ColorUtils.getWhite());
            }
        }
        super.render(p_96562_, p_96563_, p_96564_, p_96565_);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
