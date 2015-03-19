package se.fredin.gdxtensions.object.weapon;


public class Shotgun extends Weapon {
	
	protected byte amountOfShellsPerRound;
	protected float spread;
	
	public Shotgun(FiringMode firingMode) {
		super(firingMode);
	}
	
	@Override
	public void shoot(Projectile projectile) {
		if(canShoot()) {
			for(byte i = 0; i < amountOfShellsPerRound; i++) {
				Projectile obtainedProjectile = projectilesPool.obtain();
				this.transferValuesFrom(obtainedProjectile, projectile);
				projectiles.add(obtainedProjectile);
			}
		}
	}
	
	@Override
	public void tick(float deltaTime) {
		
	}


}
