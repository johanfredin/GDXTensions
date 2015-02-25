package se.fredin.gdxtensions.object.projectile;

import se.fredin.gdxtensions.object.GameObjectBase;
import se.fredin.gdxtensions.object.RichGameObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class Projectiles implements GameObjectBase {
	
	private int maxCapacity;
	private Array<Projectile> projectiles;
	private ProjectilesPool projectilesPool;
	
	public Projectiles(Projectile template, int initialCapacity, int maxCapacity) {
		this.projectilesPool = new ProjectilesPool(template, initialCapacity, maxCapacity);
		this.projectiles = new Array<Projectile>(initialCapacity);
		this.maxCapacity = maxCapacity;
	}
	
	public void shoot() {
		projectiles.add(projectilesPool.obtain());
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
		for(Projectile projectile : projectiles) {
			projectile.tick(deltaTime, gameObject);
			if(projectile.isCollidedWith()) {
				projectiles.removeValue(projectile, true);
				projectilesPool.free(projectile);
			}
		}
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

		private Projectile template;
		
		public ProjectilesPool(Projectile template, int initialCapacity, int max) {
			super(initialCapacity, max);
			this.template = template;
		}
		
		@Override
		protected Projectile newObject() {
			return new Projectile(template);
		}
		
	}

}
