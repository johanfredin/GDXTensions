package se.fredin.gdxtensions.object.weapon;

import se.fredin.gdxtensions.input.BaseInput;
import se.fredin.gdxtensions.object.BasicGameObject;


public abstract class SemiAutomaticWeapon extends Weapon {
	
	private boolean isShootButtonPressed;
	private BaseInput input;
	
	public SemiAutomaticWeapon(BaseInput input) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, input);
	}
	
	public SemiAutomaticWeapon(short ammo, BaseInput input) {
		this(0, Integer.MAX_VALUE, SHOOTING_INTERVAL_DEFAULT, input);
		this.ammo = ammo;
	}
	
	public SemiAutomaticWeapon(int maxCapacity, BaseInput input) {
		this(0, maxCapacity, SHOOTING_INTERVAL_DEFAULT, input);
	}
	
	public SemiAutomaticWeapon(int initialCapacity, int maxCapacity, BaseInput input) {
		this(initialCapacity, maxCapacity, SHOOTING_INTERVAL_DEFAULT, input);
	}
	
	public SemiAutomaticWeapon(int initialCapacity, int maxCapacity, float shootingInterval, BaseInput input) {
		super(initialCapacity, maxCapacity, shootingInterval, FiringMode.SEMI);
		this.input = input;
	}
	
	@Override
	public boolean canShoot() {
		return super.canShoot() && !isShootButtonPressed;
	}
	
	@Override
	public void tick(float deltaTime, BasicGameObject gameObject) {
		checkInputStatus();
		
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
	
	@Override
	public void shoot(Projectile projectile) {
		super.shoot(projectile);
		
		if(!isUnlimitedAmmo) {
			ammo--;
		}
	}
	
	public void checkInputStatus() {
		if(input.isShootButtonPressed()) {
			isShootButtonPressed = true;
		} else {
			isShootButtonPressed = false;
		}
	}
	
}
