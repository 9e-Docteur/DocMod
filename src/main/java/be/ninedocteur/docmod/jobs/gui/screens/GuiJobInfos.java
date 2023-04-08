package be.ninedocteur.docmod.jobs.gui.screens;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import be.ninedocteur.docmod.DocMod;
import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.gui.buttons.ButtonBack;
import be.ninedocteur.docmod.jobs.gui.buttons.ButtonXPCategory;
import be.ninedocteur.docmod.jobs.gui.buttons.SlideBarUnlock;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.jobs.util.JobsMath;
import com.mojang.blaze3d.systems.RenderSystem;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class GuiJobInfos extends Screen {

    private static final ResourceLocation BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_job_infos.png");
    private static final ResourceLocation ICONES = new ResourceLocation(DocMod.MOD_ID, "textures/gui/jobs_icons.png");
    private static final ResourceLocation UNLOCK_BACKGROUND = new ResourceLocation(DocMod.MOD_ID, "textures/gui/gui_unlocked_items.png");
    public final Constants.Job job;
    public int left;
    public int top;
    public int offsetUnlock;
    private final ItemRenderer renderItem;
    public int page;
    public final int pageNumber;
    Button back, xp1, xp2, slide;
    private List<Item> unlocked_items = new ArrayList<>();
    public GuiJobInfos(Constants.Job job)
    {
    	super(Component.literal(""));
        this.left = this.width/2 - 110;
        this.top = this.height/2 - 90;
        this.job = job;
        this.offsetUnlock = 0;
        this.renderItem = Minecraft.getInstance().getItemRenderer();
        this.page = 0;
        this.unlocked_items = ClientInfos.getClassedUnlockedItems(this.job);
        this.pageNumber = unlocked_items.size() <= 7 ? 1 : unlocked_items.size() - 6;
    }

    @Override
    public void init()
    {

        back = new ButtonBack(this.width/2 - 105 + offsetUnlock, this.height/2-85, this);
        xp1 = new ButtonXPCategory(this.width/2 - 84 + offsetUnlock, this.height/2 + 40, Constants.XPCategories.XP, this);
        xp2 = new ButtonXPCategory(this.width/2 + 4 + offsetUnlock, this.height/2 + 40, Constants.XPCategories.UNLOCK, this);
        this.addRenderableWidget(back);
        this.addRenderableWidget(xp1);
        this.addRenderableWidget(xp2);

        if(offsetUnlock == -70)
        {
            slide = new SlideBarUnlock(3, this.width/2 + offsetUnlock + 115 + 48, this.top + 14 + 16, this);
            this.addRenderableWidget(slide);
        }
        super.init();
    }
    
    public void update()
    {
        int backX = back.getX();
        int xp1 = back.getX();
        int xp2 = back.getX();
    	backX = this.width/2 - 105 + offsetUnlock;
    	xp1 = this.width/2 - 84 + offsetUnlock;
    	xp2 = this.width/2 + 4 + offsetUnlock;
    	if(offsetUnlock == -70)
    		this.addRenderableWidget(new SlideBarUnlock(3, this.width/2 + offsetUnlock + 115 + 48, this.top + 14 + 16, this));
    	else
    		this.renderables.remove(this.renderables.size()-1);
    }
    
    @Override
    public boolean isPauseScreen() 
    {
    	return false;
    }

    
    @Override
    public void render(PoseStack mStack, int mouseX, int mouseY, float partialTicks)
    {
    	super.render(mStack, mouseX, mouseY, partialTicks);
    	this.left = this.width/2 - 110 + offsetUnlock;
        this.top = this.height/2 - 90;
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, BACKGROUND);
        this.blit(mStack, this.left, this.top, 0, 0, 220, 180);//background
        this.drawGradients(mStack);

        if(offsetUnlock != 0)
        {
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderTexture(0, UNLOCK_BACKGROUND);
            this.blit(mStack, this.left + 225, this.top + 14, 0, 0, 70, 152);
        }
        super.render(mStack, mouseX, mouseY, partialTicks);
        if(offsetUnlock != 0)
            this.drawUnlockedItems(mStack, mouseX, mouseY);
    }

    private void drawUnlockedItems(PoseStack mStack, int mouseX, int mouseY)
    {
        //RenderHelper.setupForFlatItems();
        int renderIndex = -1;
        for(int i = 0; i < (unlocked_items.size() >= 7 ? 7 : unlocked_items.size() - page); i++)
        {
            renderItem.renderGuiItem(new PoseStack(), new ItemStack(unlocked_items.get(i + page)), this.left + 242, this.top + 27 + i*18);
            if(mouseX >= this.left + 242      && mouseX < this.left + 258 &&
               mouseY >= this.top + 27 + i*18 && mouseY < this.top + 27 + 16 + i*18)
                renderIndex = i;
        }
        if(renderIndex != -1) renderComponentTooltip(mStack, getItemToolTip(new ItemStack(unlocked_items.get(renderIndex + page))), mouseX, mouseY);
        //RenderHelper.setupFor3DItems();
    }

    private void drawGradients(PoseStack mStack)
    {
        int lvl = ClientInfos.job.getLevelByJob(job);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderTexture(0, ICONES);
        this.blit(mStack, this.width/2 - 20 + offsetUnlock, this.top + 7, 40*this.job.index, 0, 40, 40);//icon
        this.blit(mStack, this.width/2 - 20 + offsetUnlock, this.top + 7, 0, 40, 40, 40);//icon
        this.blit(mStack, this.width/2 - 75 + offsetUnlock, this.top + 65, 0, 80, 150, 12); //background1
        if(lvl < 25)
            this.blit(mStack, this.width/2 - 75 + offsetUnlock, this.top + 90, 0, 80, 150, 12);//background2

        int y = lvl < 25 ? 92 : 104;
        int size1 = lvl < 25 ? (int) (((double)ClientInfos.job.getXPByJob(job)/ (double)Constants.XP_BY_LEVEL[lvl + 1]) * 150) : 150;
        int size2 = lvl < 25 ? (int) (((double)ClientInfos.job.toTotalXPs()[job.index]/ (double)Constants.TOTAL_XP_BY_LEVEL[25]) * 150) : 0;
        String text1 = lvl < 25 ? ClientInfos.job.getXPByJob(job) + "/" + Constants.XP_BY_LEVEL[lvl + 1] : I18n.get("text.level.max");
        String text2 = lvl < 25 ? ClientInfos.job.toTotalXPs()[job.index] + "/" + Constants.TOTAL_XP_BY_LEVEL[25] : "";
        String title1 = I18n.get("text.level") + " " + lvl;
        String title2 = lvl < 25 ? I18n.get("text.total_progression") : "";

        this.blit(mStack, this.width/2 - 75 + offsetUnlock, this.top + 65, 0, y, size1, 12);//gradient1
        this.blit(mStack, this.width/2 - 75 + offsetUnlock, this.top + 90, 0, y, size2, 12);//gradient2
        this.font.draw(mStack, text1, this.width/2 + offsetUnlock - this.font.width(text1)/2, this.top + 67, Color.WHITE.getRGB());
        this.font.draw(mStack, text2, this.width/2 + offsetUnlock - this.font.width(text2)/2, this.top + 92, Color.WHITE.getRGB());
        this.font.draw(mStack, title1, this.width/2 + offsetUnlock - this.font.width(title1)/2, this.top + 56, Color.BLACK.getRGB());
        this.font.draw(mStack, title2, this.width/2 + offsetUnlock - this.font.width(title2)/2, this.top + 81, Color.BLACK.getRGB());

    }

    public static void drawEntityOnScreen(PoseStack poseStack, int xOffset, int yOffset, int scale, float mouseX, float mouseY, LivingEntity entity) {
        float size = scale*1.1f/entity.getBbHeight();
        float f = (float)Math.atan((double)(mouseX / 40.0F));
        float f1 = (float)Math.atan((double)(mouseY / 40.0F));
        poseStack.pushPose();
        poseStack.translate((float)xOffset, (float)yOffset, 1050.0F);
        poseStack.scale(1.0F, 1.0F, -1.0F);
        PoseStack PoseStack = new PoseStack();
        PoseStack.translate(0.0D, 0.0D, 1000.0D);
        PoseStack.scale(size, size, size);
        Axis quaternion = (Axis) Axis.ZP.rotationDegrees(180.0F);
        Axis quaternion1 = (Axis) Axis.XP.rotationDegrees(f1 * 20.0F);
        PoseStack.mulPose((Quaternionf) quaternion1);
        PoseStack.mulPose((Quaternionf) quaternion);
        float f2 = entity.yBodyRot;
        float f3 = entity.yRotO;
        float f4 = entity.xRotO;
        float f5 = entity.yHeadRotO;
        float f6 = entity.yHeadRot;
        entity.yBodyRot = 180.0F + f * 20.0F;
        entity.yRotO = 180.0F + f * 40.0F;
        entity.xRotO = -f1 * 20.0F;
        entity.yHeadRot = entity.yRotO;
        entity.yHeadRotO = entity.yRotO;
        EntityRenderDispatcher entityrenderermanager = Minecraft.getInstance().getEntityRenderDispatcher();
        entityrenderermanager.overrideCameraOrientation((Quaternionf) quaternion1);
        entityrenderermanager.setRenderShadow(false);
        MultiBufferSource irendertypebuffer$impl = Minecraft.getInstance().renderBuffers().bufferSource();
        RenderSystem.runAsFancy(() -> {
            entityrenderermanager.render(entity, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, PoseStack, irendertypebuffer$impl, 15728880);
        });
        irendertypebuffer$impl.getBuffer(RenderType.LINES);
        entityrenderermanager.setRenderShadow(true);
        entity.yBodyRot = f2;
        entity.yRotO = f3;
        entity.xRotO = f4;
        entity.yHeadRotO = f5;
        entity.yHeadRot = f6;
        poseStack.popPose();
     }


    public List<Component> getItemToolTip(ItemStack stack)
    {
        List<Component> tooltip = new ArrayList<>();
        tooltip.add(Component.literal(stack.getDisplayName().getString().replace("[", "").replace("]", "")));
        if(ClientInfos.CRAFT_UNLOCK_LVL.get(stack.getItem()) > ClientInfos.job.getLevelByJob(this.job))
        {
            tooltip.add(Component.literal(ChatFormatting.RED  + I18n.get("text.unlock_lvl") + " " +
            									ClientInfos.CRAFT_UNLOCK_LVL.get(stack.getItem())));
        }
        else tooltip.add(Component.literal(ChatFormatting.GREEN  + I18n.get("text.unlock_craft")));

        return tooltip;
    }
    
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double direction) 
    {
    	int x;
        if (direction != 0 && offsetUnlock == -70)
        {
            x = -1 * Integer.signum((int)direction);
            this.page = JobsMath.clamp(this.page + x, 0, this.pageNumber-1);
            int slideY = slide.getY();
            slideY = this.top + 30 + (int)(((double)this.page/(double) (this.pageNumber - 1))*105);
        }
    	return true;
    }
}
