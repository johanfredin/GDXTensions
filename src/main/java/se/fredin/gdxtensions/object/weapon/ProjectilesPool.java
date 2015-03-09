package se.fredin.gdxtensions.object.weapon;

import com.badlogic.gdx.utils.Pool;

public class ProjectilesPool extends Pool<Projectile> {
	
	public ProjectilesPool(int initialCapacity, int max) {
		super(initialCapacity, max);
	}
	
	@Override
	protected Projectile newObject() {
		return new Projectile();
	}
	
}