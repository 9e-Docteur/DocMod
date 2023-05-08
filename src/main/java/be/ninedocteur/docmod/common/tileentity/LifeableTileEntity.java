package be.ninedocteur.docmod.common.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LifeableTileEntity extends BlockEntity{
	public int maxHealth;
	public int health;
	public int lifeTime;
	public int ticks;
	public boolean dead;

	public LifeableTileEntity(BlockEntityType<?> p_155228_, BlockPos p_155229_, BlockState p_155230_) {
		super(p_155228_, p_155229_, p_155230_);
		// TODO Auto-generated constructor stub
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getLifeTime() {
		return lifeTime;
	}
	
	public void setLifeTime(int lifeTime) {
		this.lifeTime = lifeTime;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public void hurt(int damage) {
		health = health - damage;
		if(health < -1) {
			health = 0;
		}
	}
	
	public void heal(int healValue) {
		health = health + healValue;
		if(health > maxHealth) {
			health = maxHealth;;
		}
	}
	
	public void regenerateTile() {
		this.health = this.maxHealth;
	}
	
	public void tick() {
		if(ticks != lifeTime) {
			ticks++;
		} else {
			if(health != 0) {
				health--;
			}
		}
		
		if(health == 0) {
			dead = true;
		} else {
			dead = false;
		}
	}

}
