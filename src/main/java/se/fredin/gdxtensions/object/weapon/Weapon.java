package se.fredin.gdxtensions.object.weapon;

import se.fredin.gdxtensions.object.GameObjectBase;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public abstract class Weapon implements GameObjectBase {
	
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
	
	public Weapon(FiringMode firingMode) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, firingMode);
	}
	
	public Weapon(short ammo, FiringMode firingMode) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, firingMode);
		this.ammo = ammo;
	}
	
	public Weapon(int maxCapacity, FiringMode firingMode) {
		this(0, maxCapacity, SHOOTING_INTERVAL_DEFAULT, firingMode);
	}
	
	public Weapon(int initialCapacity, int maxCapacity, FiringMode firingMode) {
		this(initialCapacity, maxCapacity, SHOOTING_INTERVAL_DEFAULT, firingMode);
	}
	
	public Weapon(int initialCapacity, int maxCapacity, float shootingInterval, FiringMode firingMode) {
		this.projectilesPool = new ProjectilesPool(initialCapacity, maxCapacity);
		this.projectiles = new Array<Projectile>(initialCapacity);
		this.maxCapacity = maxCapacity;
		this.shootingInterval = shootingInterval;
	}
	
	public void shoot(Projectile projectile) {
		if(canShoot()) {
			Projectile obtainedProjectile = projectilesPool.obtain();
			this.transferValuesFrom(obtainedProjectile, projectile);
			projectiles.add(obtainedProjectile);
		}
	}
	
	public void transferValuesFrom(Projectile projectileToAddTo, Projectile populatedProjectile) {
		projectileToAddTo.setPosition(populatedProjectile.getPosition());
		projectileToAddTo.setSpeed(populatedProjectile.getSpeed());
		projectileToAddTo.setVelocity(populatedProjectile.getVelocity());
		projectileToAddTo.setDamage(populatedProjectile.getDamage());
		projectileToAddTo.setBounds(populatedProjectile.getBounds());
		projectileToAddTo.setCollisionHandler(populatedProjectile.getCollisionHandler());
		projectileToAddTo.setGameObjectTexture(populatedProjectile.getGameObjectTexture());
	}
	
	public void populateProjectilesArray(int amount) {
		for(int i = 0; i < amount; i++) {
			projectiles.add(projectilesPool.obtain());
		}
	}
	
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
	
	public boolean isMaxedOut() {
		return projectiles.size >= maxCapacity;
	}
	
	public boolean canShoot() {
		return timer >= shootingInterval && !isUnlimitedFiringInterval && projectiles.size <= maxCapacity;
	}
	
	public void setUnlimitedFiringInterval(boolean isUnlimitedInterval) {
		this.isUnlimitedFiringInterval = isUnlimitedInterval;
	}
	
	public boolean isUnlimitedFiringInterval() {
		return isUnlimitedFiringInterval;
	}
	
	public void setShootingInterval(float shootingInterval) {
		this.shootingInterval = shootingInterval;
	}
	
	public float getShootingInterval() {
		return shootingInterval;
	}
	
	public void setTimer(float timer) {
		this.timer = timer;
	}
	
	public float getTimer() {
		return timer;
	}
	
	public void setUnlimitedAmmo(boolean isUnlimitedAmmo) {
		this.isUnlimitedAmmo = isUnlimitedAmmo;
	}
	
	public boolean isUnlimitedAmmo() {
		return isUnlimitedAmmo;
	}
	
	public void setAmmo(short ammo) {
		this.ammo = ammo;
	}
	
	public short getAmmo() {
		return ammo;
	}
	
	public void setFiringMode(FiringMode firingMode) {
		this.firingMode = firingMode;
	}
	
	public FiringMode getFiringMode() {
		return firingMode;
	}
	
	public void setWeaponTexture(TextureRegion weaponTexture) {
		this.weaponTexture = weaponTexture;
	}
	
	public TextureRegion getWeaponTexture() {
		return weaponTexture;
	}
	
	public boolean isAutomaticMode() {
		return firingMode == FiringMode.AUTOMATIC;
	}
	
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
