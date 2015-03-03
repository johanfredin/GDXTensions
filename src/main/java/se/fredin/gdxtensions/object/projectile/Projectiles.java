package se.fredin.gdxtensions.object.projectile;

import se.fredin.gdxtensions.input.BaseInput;
import se.fredin.gdxtensions.object.GameObjectBase;
import se.fredin.gdxtensions.object.RichGameObject;
import se.fredin.gdxtensions.utils.LogUtils;
import se.fredin.gdxtensions.utils.Settings;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Projectiles implements GameObjectBase {
	
	private int maxCapacity;
	private float shootingInterval;
	private float timer;
	private boolean isUnlimitedInterval;
	private Array<Projectile> projectiles;
	private ProjectilesPool projectilesPool;
	private BaseInput input;
	
	public Projectiles(int initialCapacity, int maxCapacity) {
		this(initialCapacity, maxCapacity, Settings.defaultProjectileSpeed, null);
	}
	
	public Projectiles(int initialCapacity, int maxCapacity, BaseInput input) {
		this(initialCapacity, maxCapacity, Settings.defaultProjectileSpeed, input);
	}
	
	public Projectiles(int initialCapacity, int maxCapacity, float shootingInterval) {
		this(initialCapacity, maxCapacity, shootingInterval, null);
	}
	
	public Projectiles(int initialCapacity, int maxCapacity, float shootingInterval, BaseInput input) {
		this.projectilesPool = new ProjectilesPool(initialCapacity, maxCapacity);
		this.projectiles = new Array<Projectile>(initialCapacity);
		this.maxCapacity = maxCapacity;
		this.shootingInterval = shootingInterval;
		this.isUnlimitedInterval = false;
		this.input = input;
	}
	
	public void shoot(Projectile projectile) {
		if(canShoot()) {
			Projectile obtainedProjectile = projectilesPool.obtain();
			this.transferValuesFrom(obtainedProjectile, projectile);
			projectiles.add(obtainedProjectile);
			LogUtils.log("size=" + projectiles.size + " pool size=" + projectilesPool.getFree());
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

	@Override
	public void tick(float deltaTime, RichGameObject gameObject) {
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
	
	public boolean isMaxedOut() {
		return projectiles.size >= maxCapacity;
	}
	
	public boolean canShoot() {
		return timer >= shootingInterval && !isUnlimitedInterval && projectiles.size <= maxCapacity;
	}
	
	public void setUnlimitedInterval(boolean isUnlimitedInterval) {
		this.isUnlimitedInterval = isUnlimitedInterval;
	}
	
	public boolean isUnlimitedInterval() {
		return isUnlimitedInterval;
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
	
	public void setInput(BaseInput input) {
		this.input = input;
	}
	
	public BaseInput getInput() {
		return input;
	}
	
	@Override
	public void dispose() {
		for(Projectile projectile : projectiles) {
			projectile.dispose();
		}
		projectilesPool.freeAll(projectiles);
		projectiles.clear();
	}
	
	public static class ProjectilesPool extends Pool<Projectile> {
		
		public ProjectilesPool(int initialCapacity, int max) {
			super(initialCapacity, max);
		}
		
		@Override
		public Projectile obtain() {
			LogUtils.log("Obtain() called");
			return super.obtain();
		}
		
		@Override
		protected Projectile newObject() {
			LogUtils.log("New object() called");
			return new Projectile();
		}
		
	}

}
