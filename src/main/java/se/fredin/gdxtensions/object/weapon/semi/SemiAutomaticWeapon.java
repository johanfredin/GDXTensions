package se.fredin.gdxtensions.object.weapon.semi;

import se.fredin.gdxtensions.input.BaseInput;
import se.fredin.gdxtensions.object.weapon.FiringMode;
import se.fredin.gdxtensions.object.weapon.Projectile;
import se.fredin.gdxtensions.object.weapon.ProjectilesPool;
import se.fredin.gdxtensions.object.weapon.Weapon;
import se.fredin.gdxtensions.object.weapon.automatic.AutomaticWeapon;

import com.badlogic.gdx.utils.Array;

/**
 * Abstract super class for all semi-automatic {@link Weapon}s 
 * Makes it easier to create an automatic weapon without having to 
 * pass in unnecessary parameters. This class, unlike {@link AutomaticWeapon} 
 * also holds a {@link BaseInput} instance that must be passed into all of its constructors. 
 * This was necessary to be able to detect when the shoot button has been pressed. The {@link SemiAutomaticWeapon} class
 * allows only {@link Projectile}s to be emitted per button press, we can not hold the 
 * button down, but semi automatic in the name should be clear enought right?
 * @author Johan Fredin
 */
public abstract class SemiAutomaticWeapon extends Weapon {
	
	private boolean isShootButtonPressed;
	private BaseInput input;
	
	/**
	 * Construct a new {@link SemiAutomaticWeapon}
	 * @param input the {@link BaseInput} object to use for detecting when the shoot button has been pressed
	 */
	public SemiAutomaticWeapon(BaseInput input) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, input);
	}
	
	/**
	 * Construct a new {@link SemiAutomaticWeapon}
	 * @param ammo the amount of ammo to start with
	 * @param input the {@link BaseInput} object to use for detecting when the shoot button has been pressed
	 */
	public SemiAutomaticWeapon(short ammo, BaseInput input) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, input);
		this.ammo = ammo;
	}
	
	/**
	 * Construct a new {@link SemiAutomaticWeapon}
	 * @param maxCapacity the max amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool} 
	 * @param input the {@link BaseInput} object to use for detecting when the shoot button has been pressed
	 */
	public SemiAutomaticWeapon(int maxCapacity, BaseInput input) {
		this(0, maxCapacity, SHOOTING_INTERVAL_DEFAULT, input);
	}
	
	/**
	 * Construct a new {@link SemiAutomaticWeapon}
	 * @param initialCapacity the starting amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool}
	 * @param maxCapacity the max amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool} 
	 * @param input the {@link BaseInput} object to use for detecting when the shoot button has been pressed
	 */
	public SemiAutomaticWeapon(int initialCapacity, int maxCapacity, BaseInput input) {
		this(initialCapacity, maxCapacity, SHOOTING_INTERVAL_DEFAULT, input);
	}
	
	/**
	 * Construct a new {@link SemiAutomaticWeapon}
	 * @param initialCapacity the starting amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool}
	 * @param maxCapacity the max amount of {@link Projectile} objects for the {@link Projectile} {@link Array} and {@link ProjectilesPool} 
	 * @param shootingInterval the time interval in seconds until next projectile can be fired
	 * @param input the {@link BaseInput} object to use for detecting when the shoot button has been pressed
	 */
	public SemiAutomaticWeapon(int initialCapacity, int maxCapacity, float shootingInterval, BaseInput input) {
		super(initialCapacity, maxCapacity, shootingInterval, FiringMode.SEMI);
		this.input = input;
	}
	
	@Override
	public boolean canShoot() {
		return super.canShoot() && !isShootButtonPressed;
	}
	
	@Override
	public void tick(float deltaTime) {
		checkInputStatus();
		
		if(timer > shootingInterval) {
			timer = 0.0f;
		} 
		timer += deltaTime;
		
		for(Projectile projectile : projectiles) {
			projectile.tick(deltaTime);
			if(projectile.isCollidedWith() || isMaxedOut()) {
				projectiles.removeValue(projectile, true);
				projectilesPool.free(projectile);
			}
		}
	}
	
	@Override
	public void shoot(Projectile projectile) {
		super.shoot(projectile);
		
		if(!isUnlimitedAmmo) {
			ammo--;
		}
	}
	
	/**
	 * Checks whether or not the shoot button has been pressed
	 */
	public void checkInputStatus() {
		if(input.isShootButtonPressed()) {
			isShootButtonPressed = true;
		} else {
			isShootButtonPressed = false;
		}
	}
	
}
