package se.fredin.gdxtensions.object.weapon;


public class Shotgun extends Weapon {
	
	private SpreadMode spreadMode;
	
	public Shotgun(FiringMode firingMode) {
		this(firingMode, SpreadMode.TRIPPLE);
	}
	
	public Shotgun(FiringMode firingMode, SpreadMode spreadMode) {
		super(firingMode);
	}
	
	public void setSpreadMode(SpreadMode spreadMode) {
		this.spreadMode = spreadMode;
	}
	
	public SpreadMode getSpreadMode() {
		return spreadMode;
	}
	
	@Override
	public void shoot(Projectile projectile) {
		if(canShoot()) {
			
			Projectile obtainedProjectile = projectilesPool.obtain();
			this.transferValuesFrom(obtainedProjectile, projectile);
			projectiles.add(obtainedProjectile);
			for(byte i = 0; i < spreadMode.amount; i++) {
				
			}
		}		
	}
	
	@Override
	public void tick(float deltaTime) {
	
	}

	public static enum SpreadMode {
		
		TRIPPLE((byte)3),
		FIVE_SPREAD((byte)5),
		SEVEN_SPREAD((byte)7);
		
		private byte amount;
		
		private SpreadMode(byte amount) {
			this.amount = amount;
		}
		
		public byte getAmount() {
			return amount;
		}
		
	}
	
}
