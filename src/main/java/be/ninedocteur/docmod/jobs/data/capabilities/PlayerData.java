package be.ninedocteur.docmod.jobs.data.capabilities;


import be.ninedocteur.docmod.jobs.data.registry.LevelData;
import net.minecraft.core.Direction;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import java.util.Optional;
import java.util.function.Consumer;

public class PlayerData {
	
	//@CapabilityInject(PlayerJobs.class)
	public static Capability<PlayerJobs> JOBS;

	/**
	 * Gets the Jobs from a player
	 * @param player
	 * @return the jobs of the player
	 */
	public static PlayerJobs getPlayerJobs(Player player) {
		Optional<PlayerJobs> capability = player.getCapability(JOBS, null).resolve();
		return capability.orElse(null);
	}

	/**
	 * Registers the capability
	 */
	public static void register() {
		CapabilityManager.INSTANCE.injectCapabilities(PlayerJobs.class, new Capability<PlayerJobs>() {

			@Override
			public Tag writeNBT(Capability<PlayerJobs> capability, PlayerJobs instance, Direction side) {
				return instance.toNBT();
			}

			@Override
			public synchronized Capability<PlayerJobs> addListener(Consumer<Capability<PlayerJobs>> listener) {
				return super.addListener(listener);
			}

			@Override
			public void readNBT(Capability<PlayerJobs> capability, PlayerJobs instance, Direction side, Tag nbt) {
				instance.fromNBT((Tag) nbt);
			}
			
		}, () -> null);
	}
	
	public static class JobsDispatcher implements ICapabilitySerializable<Tag>{

		private final PlayerJobs jobs;

		/**
		 * Initialise jobs with levels data
		 * @param levels the levels data
		 */
		public JobsDispatcher(LevelData levels){
			this.jobs = new PlayerJobs(levels);
		}

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return LazyOptional.of(() -> (T)jobs);
		}

		/**
		 * Serialize the jobs to NBT
		 * @return the NBT containing the jobs
		 */
		@Override
		public Tag serializeNBT() {
			return this.jobs.toNBT();
		}

		/**
		 * Deserialize the jobs from NBT
		 * @param nbt the nbt from which the jobs are deserialized
		 */
		@Override
		public void deserializeNBT(Tag nbt) {
			this.jobs.fromNBT(nbt);
		}
		
		
	}

}
