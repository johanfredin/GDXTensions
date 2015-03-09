package se.fredin.gdxtensions.object.weapon;

import se.fredin.gdxtensions.object.BasicGameObject;

public abstract class AutomaticWeapon extends Weapon {
	
	public AutomaticWeapon() {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT);
	}
	
	public AutomaticWeapon(short ammo) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT);
		this.ammo = ammo;
	}
	
	public AutomaticWeapon(int maxCapacity) {
		this(0, maxCapacity, SHOOTING_INTERVAL_DEFAULT);
	}
	
	public AutomaticWeapon(int initialCapacity, int maxCapacity) {
		this(initialCapacity, maxCapacity, SHOOTING_INTERVAL_DEFAULT);
	}
	
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
