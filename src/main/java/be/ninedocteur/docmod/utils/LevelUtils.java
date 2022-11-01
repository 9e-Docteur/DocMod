package be.ninedocteur.docmod.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.world.ForgeChunkManager;

public class LevelUtils {
    public static boolean unforcedChunkIfLoaded(ServerLevel serverLevel, ChunkPos chunkPos, BlockPos blockPos){
        if(ForgeChunkManager.forceChunk(serverLevel, "docmod", blockPos, chunkPos.x, chunkPos.z, false, true)){
            unforcedChunkIfLoadedForVanilla(serverLevel, chunkPos);
            markChunkChanged(serverLevel, blockPos);
            return true;
        }
        return false;
    }

    public static boolean forcedChunkIfLoaded(ServerLevel serverLevel, ChunkPos chunkPos, BlockPos blockPos){
        if(ForgeChunkManager.forceChunk(serverLevel, "docmod", blockPos, chunkPos.x, chunkPos.z, true, true)){
            unforcedChunkIfLoadedForVanilla(serverLevel, chunkPos);
            markChunkChanged(serverLevel, blockPos);
            return true;
        }
        return false;
    }

    public static boolean unforcedChunkIfLoadedForVanilla(ServerLevel serverLevel, ChunkPos chunkPos){
        if(serverLevel.getForcedChunks().contains(chunkPos.toLong())){
            serverLevel.setChunkForced(chunkPos.x, chunkPos.z, false);
            return true;
        }
        return false;
    }

    public static void markChunkChanged(Level level, BlockPos blockPos){
        if(isBlockLoaded(level, blockPos)){
            level.getChunkAt(blockPos).setUnsaved(true);
        }
    }

    public static boolean isBlockLoaded(BlockGetter blockGetter, BlockPos blockPos) {
        if(blockGetter == null){
            return false;
        } else if(blockGetter instanceof LevelReader levelReader){
            return levelReader.hasChunkAt(blockPos);
        }
        return true;
    }
}
