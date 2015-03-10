package se.fredin.gdxtensions.object.weapon;

import com.badlogic.gdx.utils.Array;

import se.fredin.gdxtensions.object.BasicGameObject;

/**
 * Abstract super class for all automatic {@link Weapon}s 
 * Makes it easier to create an automatic weapon without having to 
 * pass in unnecessary parameters 
 * @author Johan Fredin
 *
 */
public abstract class AutomaticWeapon extends Weapon {
	
	/**
	 * Construct a new {@link AutomaticWeapon} with default values e.g <br>
	 * initial capacity = 0 <br>
	 * max capacity = {@link Integer#MAX_VALUE} <br>
	 * shooting interval = {@link Weapon#SHOOTING_INTERVAL_DEFAULT}
	 */
	public AutomaticWeapon() {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT);
	}
	
	/**
	 * Construct a new {@link AutomaticWeapon} with default values e.g <br>
	 * initial capacity = 0 <br>
	 * max capacity = {@link Integer#MAX_VALUE} <br>
	 * shooting interval = {@link Weapon#SHOOTING_INTERVAL_DEFAULT}
	 * @param ammo the starting ammunition
	 */
	public AutomaticWeapon(short ammo) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT);
		this.ammo = ammo;
	}
	
	/**
	 * Construct a new {@link AutomaticWeapon}
	 * @param maxCapacity the max amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool} 
	 */
	public AutomaticWeapon(int maxCapacity) {
		this(0, maxCapacity, SHOOTING_INTERVAL_DEFAULT);
	}
	
	/**
	 * Construct a new {@link AutomaticWeapon}
	 * @param initialCapacity the starting amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool}
	 * @param maxCapacity the max amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool} 
	 */
	public AutomaticWeapon(int initialCapacity, int maxCapacity) {
		this(initialCapacity, maxCapacity, SHOOTING_INTERVAL_DEFAULT);
	}
	
	/**
	 * Construct a new {@link AutomaticWeapon}
	 * @param initialCapacity the starting amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool}
	 * @param maxCapacity the max amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool} 
	 * @param shootingInterval the time interval in seconds until next projectile can be fired
	 */
	public AutomaticWeapon(int initialCapacity, int maxCapacity, float shootingInterval) {
		super(initialCapacity, maxCapacity, shootingInterval, FiringMode.AUTOMATIC);
	}
	
	@Override
	public void tick(float deltaTime, BasicGameObject gameObject) {
		if(timer > shootingInterval) {
			timer = 0.0f;
		} 
		timer += deltaTime;
		
		for(Projectile projectile : projectiles) {
			projectile.tick(deltaTime, gameObject);
			if(projectile.isCollidedWith() || isMaxedOut()) {
				projectiles.removeValue(projectile, true);
				projectilesPool.free(projectile);
			}
		}
	}

}
