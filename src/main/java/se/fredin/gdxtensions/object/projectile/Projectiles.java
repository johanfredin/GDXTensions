package se.fredin.gdxtensions.object.projectile;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Projectiles {
	
	private float damage;
	private byte initialCapacity;
	private byte maxCapacity;
	private Array<Projectile> projectiles;
	private ProjectilesPool projectilesPool;
	
	public Projectiles(Projectile projectile, byte initialCapacity, byte maxCapacity) {
		this.projectilesPool = new ProjectilesPool(initialCapacity, maxCapacity);
		this.projectiles = new Array<Projectile>();
	}
	
	public void initiateProjectilesArray(Projectile projectile, byte initialCapacity) {
		for(byte i = 0; i < initialCapacity; i++) {
			projectiles.add(new Projectile(projectile));
			projectilesPool.newObject();
		}
	}
	
	private class ProjectilesPool extends Pool<Projectile> {

		public ProjectilesPool(int initialCapacity, int max) {
			super(initialCapacity, max);
		}
		
		@Override
		protected Projectile newObject() {
			return null;
		}
		
	}

}
