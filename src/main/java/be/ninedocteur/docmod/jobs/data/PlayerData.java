package be.ninedocteur.docmod.jobs.data;


import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;

public class PlayerData {

	public static Capability<JobsInfo> JOBS;


	public static JobsInfo getPlayerJobs(Player player) {
		Optional<JobsInfo> capability = player.getCapability(JOBS, null).resolve();
		return capability.orElse(null);
	}

	public static class JobsDispatcher implements ICapabilityProvider, INBTSerializable<CompoundTag> {

		public static Capability<JobsInfo> JOBS = CapabilityManager.get(new CapabilityToken<JobsInfo>(){});
		private final JobsInfo jobs = new JobsInfo();

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return LazyOptional.of(() -> (T)jobs);
		}

		/**
		 * Serialize the jobs to NBT
		 * @return the NBT containing the jobs
		 */
		@Override
		public CompoundTag serializeNBT() {
			return this.jobs.toNBT();
		}

		/**
		 * Deserialize the jobs from NBT
		 * @param nbt the nbt from which the jobs are deserialized
		 */
		@Override
		public void deserializeNBT(CompoundTag nbt) {
			this.jobs.fromNBT(nbt);
		}


	}

}
