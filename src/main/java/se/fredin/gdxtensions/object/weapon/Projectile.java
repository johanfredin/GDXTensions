package se.fredin.gdxtensions.object.weapon;

import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.object.BasicGameObject;
import se.fredin.gdxtensions.utils.Settings;
import se.fredin.gdxtensions.utils.logging.LogUtils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

/**
 * This class handles projectiles like bullets etc.
 * It already comes equipped with all the features from {@link BasicGameObject} but
 * contains settings for damage as well
 * @author Johan Fredin
 *
 */
public class Projectile extends BasicGameObject implements Poolable {
	
	private boolean isShootASAP = true;
	private float angle;
	
	/**
	 * Construct a new {@link Projectile} without setting anything
	 */
	public Projectile() {}
	
	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 */
	public Projectile(Vector2 position) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f);
	}
	
	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, float damage) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f);
	}

	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public Projectile(Vector2 position, float width, float height) {
		this(position, null, width, height, 0f, 0f, 0f, 0f);
	}
	
	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this projectile 
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f);
	}
	
	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this projectile
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height, float damage, TextureRegion gameObjectTexture) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f);
		this.gameObjectTexture = gameObjectTexture;
	}

	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 * @param damage how much damage this bullet will do
	 */
	public Projectile(Vector2 position, float width, float height, float right, float bottom, float left, float top) {
		this(position, null, width, height, right, bottom, left, top);
	}

	/**
	 * Construct a new {@link Projectile}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this projectile
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 */
	public Projectile(Vector2 position, CollisionHandler collisionHandler, float width, float height, float right, float bottom, float left, float top) {
		super(position, collisionHandler, width, height, right, bottom, left, top);
	}
	
	/**
	 * Constructs a new {@link Projectile} populating it with values from 
	 * a passed in {@link Projectile}
	 * @param projectile the {@link Projectile} we want to transfer the values from
	 */
	public Projectile(Projectile projectile) {
		super(projectile.position, projectile.collisionHandler, projectile.getBoundsWidth(), 
			  projectile.getBoundsHeight(), projectile.right, projectile.bottom, 
			  projectile.left, projectile.top);
		this.isShootASAP = projectile.isShootASAP;
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(gameObjectTexture, position.x, position.y);
	}
	
	@Override
	public void tick(float deltaTime) {
		this.tick(deltaTime, 1.0f);
	}
	
	public void tick(float deltaTime, float angle) {
		if(isShootASAP) {
			this.position.add((velocity.x * angle) * deltaTime, (velocity.y * angle) * deltaTime);
			this.bounds.setPosition(position);
			this.isCollidedWith = collisionHandler.isCollisionWithHardBlock(bounds);
		}
	}
	
	/**
	 * @return whether or not this projectile can be fired
	 */
	public boolean isShootASAP() {
		return isShootASAP;
	}
	
	/**
	 * Set whether or not this projectile can be fired
	 * @param canMove
	 */
	public void setShootASAP(boolean canMove) {
		this.isShootASAP = canMove;
	}
	
	/**
	 * If you want the bullet to sort of bend a bit you can angle it to stray from its default path.
	 * @param angle
	 */
	public void setAngle(float angle) {
		this.angle = angle;
	}
	
	/**
	 * @return the angle this bullet strays from its default path.
	 */
	public float getAngle() {
		return angle;
	}

	@Override
	public void dispose() {
		LogUtils.log("dispose called");
	}

	@Override
	public void reset() {
		this.position.set(0, 0);
		this.velocity.set(Settings.defaultProjectileSpeed, 0);
		this.top = this.bottom = this.left = this.right = this.speed = 0;
		this.bounds.setPosition(this.position);
	}
	

}
