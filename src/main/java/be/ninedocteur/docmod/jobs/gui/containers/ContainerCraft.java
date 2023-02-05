package be.ninedocteur.docmod.jobs.gui.containers;

import be.ninedocteur.docmod.jobs.data.ServerJobsData;
import be.ninedocteur.docmod.jobs.data.capabilities.PlayerData;
import net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Optional;

public class ContainerCraft extends RecipeBookMenu<CraftingContainer> {
	private final CraftingContainer craftSlots = new CraftingContainer(this, 3, 3);
	private final ResultContainer resultSlots = new ResultContainer();
	private final ContainerLevelAccess access;
	private final Player player;

	/**
	 * Creates a modified crafting container
	 * @param id the contained id
	 * @param playerInventory the player who opened the crafting table
	 */
	public ContainerCraft(int id, Inventory playerInventory) {
		this(id, playerInventory, ContainerLevelAccess.NULL);
	}

	/**
	 * Creates a modified crafting container
	 * @param id the contained id
	 * @param playerInventory the player who opened the crafting table
	 * @param pos the position of the crafting container
	 */
	public ContainerCraft(int id, Inventory playerInventory, ContainerLevelAccess pos) {
		super(MenuType.CRAFTING, id);
		this.access = pos;
		this.player = playerInventory.player;
		this.addSlot(new ResultSlot(playerInventory.player, this.craftSlots, this.resultSlots, 0, 124, 35));

		for(int i = 0; i < 3; ++i) {
			for(int j = 0; j < 3; ++j) {
				this.addSlot(new Slot(this.craftSlots, j + i * 3, 30 + j * 18, 17 + i * 18));
			}
		}

		for(int k = 0; k < 3; ++k) {
			for(int i1 = 0; i1 < 9; ++i1) {
				this.addSlot(new Slot(playerInventory, i1 + k * 9 + 9, 8 + i1 * 18, 84 + k * 18));
			}
		}

		for(int l = 0; l < 9; ++l) {
			this.addSlot(new Slot(playerInventory, l, 8 + l * 18, 142));
		}

	}

	/**
	 * Updates the crafting result after a slot has been modified in the crafting grid
	 * @param index the index of the modified slot
	 * @param world the world the player is in
	 * @param player the player using the crafting table
	 * @param craftingInventory the crafting grid
	 * @param resultInventory the result slot
	 */
	protected static void slotChangedCraftingGrid(int index, Level world, Player player, CraftingContainer craftingInventory, ResultContainer resultInventory) {
		if (!world.isClientSide) {
			ServerPlayer ServerPlayer = (ServerPlayer)player;
			ItemStack itemstack = ItemStack.EMPTY;
			Optional<CraftingRecipe> optional = world.getServer().getRecipeManager().getRecipeFor(RecipeType.CRAFTING, craftingInventory, world);
			if (optional.isPresent()) {
				CraftingRecipe icraftingrecipe = optional.get();
				if (resultInventory.setRecipeUsed(world, ServerPlayer, icraftingrecipe)) {
					itemstack = icraftingrecipe.assemble(craftingInventory);
					if(!ServerJobsData.BLOCKED_CRAFTS.canCraft(itemstack, PlayerData.getPlayerJobs(ServerPlayer)))
						itemstack = ItemStack.EMPTY;
				}
			}

			resultInventory.setItem(0, itemstack);
			ServerPlayer.connection.send(new ClientboundContainerSetSlotPacket(index, 0, 0, itemstack));
		}
	}

	/**
	 * Updates the crafting grid and result slot
	 * @param inventory the inventory modified
	 */
	public void slotsChanged(Container inventory) {
		this.access.execute((world, pos) -> {
			slotChangedCraftingGrid(this.containerId, world, this.player, this.craftSlots, this.resultSlots);
		});
	}

	/**
	 * Fills the craft slot with a stack
	 * @param helper the item helper
	 */
	public void fillCraftSlotsStackedContents(StackedContents helper) {
		this.craftSlots.fillStackedContents(helper);
	}

	/**
	 * Empties the crafting grid and result slot
	 */
	public void clearCraftingContent() {
		this.craftSlots.clearContent();
		this.resultSlots.clearContent();
	}

	/**
	 * Checks if a recipe matches the content of the crafting grid and result slot
	 * @param recipe the recipe to check
	 * @return true if the recipe matches the content of the crafting grid
	 */
	public boolean recipeMatches(Recipe<? super CraftingContainer> recipe) {
		return recipe.matches(this.craftSlots, this.player.level);
	}

	/**
	 * Empties the container when it should be removed
	 * @param player the player that was using the crafting container
	 */
	public void removed(Player player) {
		super.removed(player);
		this.access.execute((p_217068_2_, p_217068_3_) -> {
			this.clearContainer(player, p_217068_2_, this.craftSlots);
		});
	}

	/**
	 * @param player the player using the crafting table
	 * @return true if the player can still use the crafting table, false otherwise
	 */
	public boolean stillValid(Player player) {
		return stillValid(this.access, player, Blocks.CRAFTING_TABLE);
	}

	/**
	 * Moves a stack when the player shift-clicks it
	 * @param player the player using the crafting table
	 * @param slotIndex the slot that is shift-clicked
	 * @return the remaining stack after the stack has been moved
	 */
	public ItemStack quickMoveStack(Player player, int slotIndex) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(slotIndex);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (slotIndex == 0) {
				this.access.execute((p_217067_2_, p_217067_3_) -> {
					itemstack1.getItem().onCraftedBy(itemstack1, p_217067_2_, player);
				});
				if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
					return ItemStack.EMPTY;
				}

				slot.onQuickCraft(itemstack1, itemstack);
			} else if (slotIndex >= 10 && slotIndex < 46) {
				if (!this.moveItemStackTo(itemstack1, 1, 10, false)) {
					if (slotIndex < 37) {
						if (!this.moveItemStackTo(itemstack1, 37, 46, false)) {
							return ItemStack.EMPTY;
						}
					} else if (!this.moveItemStackTo(itemstack1, 10, 37, false)) {
						return ItemStack.EMPTY;
					}
				}
			} else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(player, itemstack1);
			if (slotIndex == 0) {
				player.drop(itemstack2, false);
			}
		}

		return itemstack;
	}

	/**
	 * Checks if the player can take all the item of the same type as the stack
	 * @param stack the item to stack up
	 * @param slot the slot where the stack should end up
	 * @return
	 */
	public boolean canTakeItemForPickAll(ItemStack stack, Slot slot) {
		return slot.container != this.resultSlots && super.canTakeItemForPickAll(stack, slot);
	}

	/**
	 * @return the index of the result slot
	 */
	public int getResultSlotIndex() {
		return 0;
	}

	/**
	 * @return the crafting grid width (3)
	 */
	public int getGridWidth() {
		return this.craftSlots.getWidth();
	}

	/**
	 * @return the crafting grid height (3)
	 */
	public int getGridHeight() {
		return this.craftSlots.getHeight();
	}

	/**
	 * @return the amount of slots (10)
	 */
	@OnlyIn(Dist.CLIENT)
	public int getSize() {
		return 10;
	}

	/**
	 * @return the Recipe Book category of CRAFTING
	 */
	@OnlyIn(Dist.CLIENT)
	public RecipeBookType getRecipeBookType() {
		return RecipeBookType.CRAFTING;
	}

	@Override
	public boolean shouldMoveToInventory(int p_150635_) {
		return false;
	}
}
