package se.fredin.gdxtensions.object.weapon;

import se.fredin.gdxtensions.object.GameObjectBase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Super class for all weapons. Contains an {@link Array} or {@link Projectile} objects
 * and fields for handling interval between fired projectiles, ammo, {@link FiringMode} 
 * for setting semiautomatic or automatic weapons and the ability to set unlimited ammo 
 * and unlimited firing interval. The {@link Weapon} class also uses pooling to save performance
 * and avoid garbage collection. For this pool you can also specify initial and max capacity.
 * The max capacity will also be the max capacity of the {@link Projectile} {@link Array}
 * When creating a new instance a {@link FiringMode} must be specified.
 * @author Johan Fredin
 *
 */
public abstract class Weapon implements GameObjectBase {
	
	/** Default time interval until next projectile can be fired, 
	 * useful mostly for automatic weapons. Value={@value #SHOOTING_INTERVAL_DEFAULT} seconds
	 */
	public static final float SHOOTING_INTERVAL_DEFAULT = .01f;
	
	protected TextureRegion weaponTexture;
	protected float shootingInterval;
	protected float timer;
	protected int maxCapacity;
	protected short ammo;
	protected boolean isUnlimitedFiringInterval;
	protected boolean isUnlimitedAmmo;
	protected FiringMode firingMode;
	protected Array<Projectile> projectiles;
	protected ProjectilesPool projectilesPool;
	
	/**
	 * Construct a new {@link Weapon}
	 * @param firingMode what type of {@link FiringMode} to use 
	 */
	public Weapon(FiringMode firingMode) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, firingMode);
	}
	
	/**
	 * Construct a new {@link Weapon}
	 * @param ammo ammunition to start with
	 * @param firingMode what type of {@link FiringMode} to use 
	 */
	public Weapon(short ammo, FiringMode firingMode) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, firingMode);
		this.ammo = ammo;
	}
	
	/**
	 * Construct a new {@link Weapon}
	 * @param maxCapacity the max amount of {@link Projectile} objects that the projectile {@link Array} and {@link ProjectilesPool} can hold 
	 * @param firingMode what type of {@link FiringMode} to use 
	 */
	public Weapon(int maxCapacity, FiringMode firingMode) {
		this(0, maxCapacity, SHOOTING_INTERVAL_DEFAULT, firingMode);
	}
	
	/**
	 * Construct a new {@link Weapon}
	 * @param initialCapacity the initial amounts of {@link Projectile} objects for the projectile {@link Array} and {@link ProjectilesPool}
	 * @param maxCapacity the max amount of {@link Projectile} objects that the projectile {@link Array} and {@link ProjectilesPool} can hold 
	 * @param firingMode what type of {@link FiringMode} to use 
	 */
	public Weapon(int initialCapacity, int maxCapacity, FiringMode firingMode) {
		this(initialCapacity, maxCapacity, SHOOTING_INTERVAL_DEFAULT, firingMode);
	}
	
	/**
	 * Construct a new {@link Weapon}
	 * @param initialCapacity the initial amounts of {@link Projectile} objects for the projectile {@link Array} and {@link ProjectilesPool}
	 * @param maxCapacity the max amount of {@link Projectile} objects that the projectile {@link Array} and {@link ProjectilesPool} can hold 
	 * @param shootingInterval the time interval in seconds until next projectile can be fired
	 * @param firingMode what type of {@link FiringMode} to use 
	 */
	public Weapon(int initialCapacity, int maxCapacity, float shootingInterval, FiringMode firingMode) {
		this.projectilesPool = new ProjectilesPool(initialCapacity, maxCapacity);
		this.projectiles = new Array<Projectile>(initialCapacity);
		this.maxCapacity = maxCapacity;
		this.shootingInterval = shootingInterval;
	}
	
	/**
	 * Adds a {@link Projectile} to the projectiles {@link Array} by obtaining one from the {@link ProjectilesPool}
	 * This method (if not overriden of course) will call {@link #transferValuesFrom(Projectile, Projectile)} method to
	 * decide logic for the projectile to be added.
	 * @param projectile the {@link Projectile} to add to the projectiles array
	 */
	public void shoot(Projectile projectile) {
		if(canShoot()) {
			Projectile obtainedProjectile = projectilesPool.obtain();
			this.transferValuesFrom(obtainedProjectile, projectile);
			projectiles.add(obtainedProjectile);
		}
	}
	
	/**
	 * Copies the values from one {@link Projectile} to another
	 * @param projectileToAddTo the {@link Projectile} we want to populate
	 * @param populatedProjectile the {@link Projectile} containing the values we want
	 */
	public void transferValuesFrom(Projectile projectileToAddTo, Projectile populatedProjectile) {
		projectileToAddTo.setPosition(populatedProjectile.getPosition());
		projectileToAddTo.setSpeed(populatedProjectile.getSpeed());
		projectileToAddTo.setVelocity(populatedProjectile.getVelocity());
		projectileToAddTo.setDamage(populatedProjectile.getDamage());
		projectileToAddTo.setBounds(populatedProjectile.getBounds());
		projectileToAddTo.setCollisionHandler(populatedProjectile.getCollisionHandler());
		projectileToAddTo.setGameObjectTexture(populatedProjectile.getGameObjectTexture());
	}
	
	/**
	 * Lets us add a specified amount of projectiles to the array at once.
	 * @param amount the amount of {@link Projectile} objects to add
	 */
	public void populateProjectilesArray(int amount) {
		for(int i = 0; i < amount; i++) {
			projectiles.add(projectilesPool.obtain());
		}
	}
	
	/**
	 * Fills the projectiles array with the max amount given previously
	 */
	public void fillProjectilesArray() {
		for(int i = 0; i < maxCapacity; i++) {
			projectiles.add(projectilesPool.obtain());
		}
	}
	
	@Override
	public void render(SpriteBatch batch) {
		for(Projectile projectile : projectiles) {
			projectile.render(batch);
		}
	}
	
	/**
	 * @return wheather or not the size of the projectiles array is bigger than or equal to given max capacity
	 */
	public boolean isMaxedOut() {
		return projectiles.size >= maxCapacity;
	}
	
	/**
	 * @return whether or not a projectile can be fired. 
	 * Standard behavior: if the firing interval has not been reached if we dont have unlimited firing interval and we are not {@link #isMaxedOut()}
	 */
	public boolean canShoot() {
		return (timer >= shootingInterval && !isUnlimitedFiringInterval) && !isMaxedOut();
	}
	
	/**
	 * Set whether or not to have unlimited firing interval
	 * @param isUnlimitedInterval
	 */
	public void setUnlimitedFiringInterval(boolean isUnlimitedInterval) {
		this.isUnlimitedFiringInterval = isUnlimitedInterval;
	}
	
	/**
	 * @return whether or not we have unlimited firing interval set
	 */
	public boolean isUnlimitedFiringInterval() {
		return isUnlimitedFiringInterval;
	}
	
	/**
	 * Set the interval in seconds until next {@link Projectile} can be fired
	 * @param shootingInterval
	 */
	public void setShootingInterval(float shootingInterval) {
		this.shootingInterval = shootingInterval;
	}
	
	/**
	 * @return the interval in seconds until the next {@link Projectile} can be fired
	 */
	public float getShootingInterval() {
		return shootingInterval;
	}
	
	/**
	 * Set the timer that is responsible for checking wheather or not we can fire.
	 * Only if the timer is bigger than or equal to the {@link #shootingInterval} can we fire
	 * (if of course, unlimited interval has not been set). Not really many use cases to call this
	 * but hey, it's supposed to be a helper API after all!
	 * @param timer
	 */
	public void setTimer(float timer) {
		this.timer = timer;
	}
	
	/**
	 * @return what the timer is right now
	 */
	public float getTimer() {
		return timer;
	}
	
	/**
	 * Set whether or not to use unlimited ammo
	 * @param isUnlimitedAmmo
	 */
	public void setUnlimitedAmmo(boolean isUnlimitedAmmo) {
		this.isUnlimitedAmmo = isUnlimitedAmmo;
	}
	
	/**
	 * @return whether or not unlimited ammo is set
	 */
	public boolean isUnlimitedAmmo() {
		return isUnlimitedAmmo;
	}
	
	/**
	 * Set the current ammo
	 * @param ammo
	 */
	public void setAmmo(short ammo) {
		this.ammo = ammo;
	}
	
	/**
	 * @return the current ammo
	 */
	public short getAmmo() {
		return ammo;
	}
	
	/**
	 * Set the {@link FiringMode} to use for this weapon
	 * @param firingMode
	 */
	public void setFiringMode(FiringMode firingMode) {
		this.firingMode = firingMode;
	}
	
	/**
	 * @return the {@link FiringMode} used for this weapon
	 */
	public FiringMode getFiringMode() {
		return firingMode;
	}
	
	/**
	 * Set a separate {@link TextureRegion} for the weapon. Not mandatory!
	 * @param weaponTexture
	 */
	public void setWeaponTexture(TextureRegion weaponTexture) {
		this.weaponTexture = weaponTexture;
	}
	
	/**
	 * @return the {@link TextureRegion} used for this weapon, if any
	 */
	public TextureRegion getWeaponTexture() {
		return weaponTexture;
	}
	
	/**
	 * @return whether or not the {@link FiringMode} is set to {@link FiringMode#AUTOMATIC}
	 */
	public boolean isAutomaticMode() {
		return firingMode == FiringMode.AUTOMATIC;
	}
	
	/**
	 * @return whether or not the {@link FiringMode} is set to {@link FiringMode#SEMI}
	 */
	public boolean isSemiAutomaticMode() {
		return firingMode == FiringMode.SEMI;
	}
	
	@Override
	public void dispose() {
		for(Projectile projectile : projectiles) {
			projectile.dispose();
		}
		projectilesPool.freeAll(projectiles);
		projectiles.clear();
	}
}
