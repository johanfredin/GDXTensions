package se.fredin.gdxtensions.object.weapon;

import com.badlogic.gdx.utils.Pool;

/**
 * Simple extension of the LibGDX {@link Pool} class, used here for pooling
 * {@link Projectile} objects.
 * @author Johan Fredin
 *
 */
public class ProjectilesPool extends Pool<Projectile> {
	
	/**
	 * Construct a new {@link ProjectilesPool}
	 * @param initialCapacity the initial capacity of the {@link Pool}
	 * @param max the max amount of {@link Projectile} objects that the {@link Pool} can contain
	 */
	public ProjectilesPool(int initialCapacity, int max) {
		super(initialCapacity, max);
	}
	
	/**
	 * Create a new {@link Projectile} without any properties set.
	 * This method gets called (duh) when the pool does not have any 
	 * reusable objects and needs to create a new one
	 */
	@Override
	protected Projectile newObject() {
		return new Projectile();
	}
	
}