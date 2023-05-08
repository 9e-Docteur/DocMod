package be.ninedocteur.docmod.common.tileentity.system;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class LifeEngine {
	private int maxHealth;
	private int health;
	private int lifeTime;
	private int ticks;
	private boolean dead;
	private boolean healthSystemEnabled;
	private boolean tick;
	
	public boolean isHealthSystemEnabled() {
		return healthSystemEnabled;
	}
	
	public void setHealthSystemEnabled(boolean healthSystemEnabled) {
		this.healthSystemEnabled = healthSystemEnabled;
	}
	
	public boolean isTick() {
		return tick;
	}
	
	public void setTick(boolean tick) {
		this.tick = tick;
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
		if(isHealthSystemEnabled()) {
			if(isTick()) {
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
	}
}
