package be.ninedocteur.docmod.jobs;

import be.ninedocteur.docmod.client.gui.overlay.DocModJobOverlay;
import be.ninedocteur.docmod.common.init.DMBlocks;
import be.ninedocteur.docmod.jobs.commands.CommandAdd;
import be.ninedocteur.docmod.jobs.data.ClientInfos;
import be.ninedocteur.docmod.jobs.data.PlayerData;
import be.ninedocteur.docmod.jobs.gui.GuiGainXP;
import be.ninedocteur.docmod.jobs.network.PacketSendChatMessage;
import be.ninedocteur.docmod.jobs.network.PacketUpdateClientJob;
import be.ninedocteur.docmod.jobs.util.Constants;
import be.ninedocteur.docmod.jobs.util.handler.PacketHandler;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkDirection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JobFactory {
    public static Map<Object, IJobFactory> JOBS_XP_FACTORY = new HashMap<>();


    //TODO: SHOW ALL OF THIS ON GUI HOW XP SCREEN
    public static void init(){
        Factory STONE = new Factory(Blocks.STONE, 1, Constants.Job.MINER, 0, 7, Factory.Action.GIVE);
        Factory COPPER_ORE = new Factory(Blocks.COPPER_ORE, 2, Constants.Job.MINER, 0, 20, Factory.Action.GIVE);
        Factory ZINC_ORE = new Factory(DMBlocks.ZINC_ORE.get(), 25, Constants.Job.MINER, 0, 20, Factory.Action.GIVE);
        Factory XP_ORE = new Factory(DMBlocks.XP_ORE.get(), 25, Constants.Job.MINER, 0, 20, Factory.Action.GIVE);
        Factory HALFINUM_ORE_1 = new Factory(DMBlocks.HALFINUM_ORE.get(), 250, Constants.Job.MINER, 0, 4, Factory.Action.GIVE);
        Factory HALFINUM_ORE_2 = new Factory(DMBlocks.HALFINUM_ORE.get(), 150, Constants.Job.MINER, 4, 10, Factory.Action.GIVE);
        Factory HALFINUM_ORE_3 = new Factory(DMBlocks.HALFINUM_ORE.get(), 100, Constants.Job.MINER, 10, 20, Factory.Action.GIVE);
        registerToFactory(DMBlocks.XP_ORE.get(), XP_ORE);
        registerToFactory(Blocks.STONE, STONE);
        registerToFactory(Blocks.COPPER_ORE, COPPER_ORE);
        registerToFactory(DMBlocks.ZINC_ORE.get(), ZINC_ORE);
        registerToFactory(DMBlocks.HALFINUM_ORE.get(), HALFINUM_ORE_1);
        registerToFactory(DMBlocks.HALFINUM_ORE.get(), HALFINUM_ORE_2);
        registerToFactory(DMBlocks.HALFINUM_ORE.get(), HALFINUM_ORE_3);
        for(Object factory : JOBS_XP_FACTORY.keySet()){
            Factory factory1 = (Factory) factory;
            if(factory1.object instanceof Block block){
                switch (factory1.fromJob){
                    case MINER:
                        ClientInfos.addBreakBlockXp(block, new long[]{factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive, factory1.xpToGive});
                }
            }
        }
    }

    public static void updateJobs(ServerPlayer serverPlayer){
        PacketHandler.INSTANCE.sendTo(new PacketUpdateClientJob(PlayerData.getPlayerJobs(serverPlayer).toTotalXPs()), serverPlayer.connection.getConnection(), NetworkDirection.PLAY_TO_CLIENT);
    }

    public static int getLevelByJob(Constants.Job job){
        return ClientInfos.job.getLevelByJob(job);
    }

    //TODO: TAG ON OBJECT TO AVOID XP GLITCH WHEN GIVING ITEM
    public static void giveXP(Integer xpToGive, Constants.Job fromJob, Integer fromLevel, Integer toLevel){
        int currentLevel = ClientInfos.job.getLevelByJob(fromJob);
        if(currentLevel >= fromLevel && currentLevel <= toLevel){
            ClientInfos.job.addXP(fromJob, xpToGive);
            Player player = Minecraft.getInstance().player;

        }
    }

    //TODO: TAG ON OBJECT TO AVOID XP GLITCH WHEN GIVING ITEM
    public static void removeXP(Integer xpToRemove, Constants.Job fromJob, Integer fromLevel, Integer toLevel){
        int currentLevel = ClientInfos.job.getLevelByJob(fromJob);
        if(currentLevel >= fromLevel && currentLevel <= toLevel){
            ClientInfos.job.addXP(fromJob, -xpToRemove);
        }
    }

    public static void registerToFactory(Object object, Factory factory){
        JOBS_XP_FACTORY.put(object, factory);
    }

    public static class Factory implements IJobFactory{
        private Object object;
        private int xpToGive;
        private int fromLevel;
        private int toLevel;
        private int xpToRemove;
        private Constants.Job fromJob;
        private Action action;

        public Factory(Object object, Integer xpToGiveOrRemove, Constants.Job fromJob, Integer fromLevel, Integer toLevel, Action action) {
            this.object = object;
            this.action = action;
            if(action == Action.GIVE){
                this.xpToGive = xpToGiveOrRemove;
                this.giveXP(object, xpToGiveOrRemove, fromJob, fromLevel, toLevel);
            }
            if(action == Action.REMOVE){
                this.xpToRemove = xpToGiveOrRemove;
                this.removeXP(object, xpToGiveOrRemove, fromJob, fromLevel, toLevel);
            }
            this.fromJob = fromJob;
            this.fromLevel = fromLevel;
            this.toLevel = toLevel;
        }

        public Object getObject() {
            return object;
        }

        public int getFromLevel() {
            return fromLevel;
        }

        public int getToLevel() {
            return toLevel;
        }

        public int getXpToGive() {
            return xpToGive;
        }

        public int getXpToRemove() {
            return xpToRemove;
        }

        public Constants.Job getFromJob() {
            return fromJob;
        }

        public Action getAction() {
            return action;
        }

        @Override
        public void giveXP(Object object, Integer xpToGive, Constants.Job fromJob, Integer fromLevel, Integer toLevel) {
            int currentLevel = ClientInfos.job.getLevelByJob(fromJob);
            if(currentLevel >= fromLevel && currentLevel <= toLevel){
                //TODO: TAG ON BLOCK TO AVOID XP GLITCH WHEN GIVING ITEM
                if(object instanceof Block block){
                    Player player = Minecraft.getInstance().player;
                    Level level = Minecraft.getInstance().level;
                    BlockState blockState = block.defaultBlockState();
                    BlockHitResult hitResult = (BlockHitResult) Minecraft.getInstance().hitResult;
                    BlockPos pos = hitResult.getBlockPos();
                   if(block.onDestroyedByPlayer(blockState, level, pos, player, false, null)){
                       ClientInfos.job.addXP(fromJob, xpToGive);
                       CompoundTag tag = new CompoundTag();
                   }
                }
                //TODO: TAG ON ITEM TO AVOID XP GLITCH WHEN GIVING ITEM
                if(object instanceof Item item){
                    ItemStack itemStack = item.getDefaultInstance();
                    Player player = Minecraft.getInstance().player;
                    Level level = Minecraft.getInstance().level;
                }
            }
        }

        @Override
        public void removeXP(Object object, Integer xpToRemove, Constants.Job fromJob, Integer fromLevel, Integer toLevel) {
            int currentLevel = ClientInfos.job.getLevelByJob(fromJob);
            if(currentLevel >= fromLevel && currentLevel <= toLevel){
                if(object instanceof Block block){
                    Player player = Minecraft.getInstance().player;
                    Level level = Minecraft.getInstance().level;
                    BlockState blockState = block.defaultBlockState();
                    BlockHitResult hitResult = (BlockHitResult) Minecraft.getInstance().hitResult;
                    BlockPos pos = hitResult.getBlockPos();
                    if(block.onDestroyedByPlayer(blockState, level, pos, player, false, null)){
                        ClientInfos.job.addXP(fromJob, -xpToRemove);
                    }
                }
                if(object instanceof Item item){

                }
            }
        }

        @Override
        public void executeActionFromBlockstate(BlockState blockState) {
            Factory jobFactory = (Factory) JOBS_XP_FACTORY.get(blockState);
            if(jobFactory.getAction() == Action.GIVE){
                this.giveXP(getObject(), getXpToGive(), getFromJob(), getFromLevel(), getToLevel());
            }
            if(jobFactory.getAction() == Action.REMOVE){
                this.removeXP(getObject(), getXpToRemove(), getFromJob(), getFromLevel(), getToLevel());
            }
        }

        public enum Action{
            GIVE,
            REMOVE
        }
    }

}
