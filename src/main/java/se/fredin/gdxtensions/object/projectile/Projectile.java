package se.fredin.gdxtensions.object.projectile;

import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.object.BasicGameObject;
import se.fredin.gdxtensions.object.RichGameObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * This class handles projectiles like bullets etc.
 * It already comes equipped with all the features from {@link BasicGameObject} but
 * contains settings for damage as well
 * @author Johan Fredin
 *
 */
public class Projectile extends BasicGameObject {
	
	private float damage;
	
	/**
	 * Construct a new {@link Projectile} without setting anything
	 */
	public Projectile() {}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 */
	public Projectile(Vector2 position) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f, 0f);
	}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, float damage) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f, damage);
	}

	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public Projectile(Vector2 position, float width, float height) {
		this(position, null, width, height, 0f, 0f, 0f, 0f, 0f);
	}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, float width, float height, float damage) {
		this(position, null, width, height, 0f, 0f, 0f, 0f, damage);
	}

	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f, 0f);
	}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height, float damage) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f, damage);
	}

	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 */
	public Projectile(Vector2 position, float width, float height, float right, float bottom, float left, float top) {
		this(position, null, width, height, right, bottom, left, top, 0f);
	}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, float width, float height, float right, float bottom, float left, float top, float damage) {
		this(position, null, width, height, right, bottom, left, top, damage);
	}

	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height, float right, float bottom, float left, float top) {
		this(position, collisionHandler, width, height, right, bottom, left, top, 0f);
	}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height, float right, float bottom, float left, float top, float damage) {
		super(position, collisionHandler, width, height, right, bottom, left, top);
		this.damage = damage;
	}
	
	public Projectile(Projectile projectile) {
		super(projectile.getPosition(), projectile.getCollisionHandler(), projectile.getBoundsWidth(), projectile.getBoundsHeight(), 
			  projectile.getRight(), projectile.getBottom(), projectile.getLeft(), projectile.getTop());
		this.damage = projectile.getDamage();
	}
	
	/**
	 * Set the amount of damage this projectile will deliver
	 * @param damage
	 */
	public void setDamage(float damage) {
		this.damage = damage;
	}
	
	/**
	 * @return how much damage this projectile will deliver
	 */
	public float getDamage() {
		return damage;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(currentFrame, position.x, position.y);
	}

	@Override
	public void tick(float deltaTime, RichGameObject gameObject) {
		//TODO: Do something here?
	}

	@Override
	public void dispose() {
		
	}
	

}