package se.fredin.gdxtensions.object;

import se.fredin.gdxtensions.assetmanagement.Assets;
import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.utils.ParticleHelper;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class contains the very basic elements that most game objects require.
 * It holds a {@link Vector2} for position and velocity,
 * a {@link Rectangle} for bounds, a {@link CollisionHandler}, a {@link TextureRegion}
 * for drawing, a speed variable as well as options for cropping / enlarging its bounding box,
 * a {@link ParticleHelper} if user wishes to use particles
 * and at last a boolean for detecting if collision has occurred
 * @author Johan Fredin
 *
 */
public abstract class BasicGameObject implements GameObjectBase {

	protected Vector2 position;
	protected Vector2 velocity;
	protected Rectangle bounds;
	protected TextureRegion gameObjectTexture;
	protected CollisionHandler collisionHandler;
	protected boolean isCollidedWith;
	protected float top, left, bottom, right;
	protected float speed;
	
	/** Used to emit particles for the children of this class that may need to */
	protected ParticleHelper particleHelper;
	
	/**
	 * Construct a new {@link BasicGameObject} without setting anything
	 */
	public BasicGameObject() {}
	
	/**
	 * Construct a new {@link BasicGameObject}
	 * @param position the initial position
	 */
	public BasicGameObject(Vector2 position) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f);
	}

	/**
	 * Construct a new {@link BasicGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public BasicGameObject(Vector2 position, float width, float height) {
		this(position, null, width, height, 0f, 0f, 0f, 0f);
	}

	/**
	 * Construct a new {@link BasicGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public BasicGameObject(Vector2 position, CollisionHandler collisionHandler, float width, float height) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f);
	}

	/**
	 * Construct a new {@link BasicGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 */
	public BasicGameObject(Vector2 position, float width, float height, float right, float bottom, float left, float top) {
		this(position, null, width, height, right, bottom, left, top);
	}

	/**
	 * Construct a new {@link BasicGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 */
	public BasicGameObject(Vector2 position, CollisionHandler collisionHandler, float width, float height, float right, float bottom, float left, float top) {
		this.position = position;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		this.top = top;
		this.bounds = new Rectangle(position.x + left, position.y + top, width - (left + right), height - (bottom + top));
		this.velocity = new Vector2();
		this.collisionHandler = collisionHandler;
	}
	
	/**
	 * @return whether or not collision has occurred
	 */
	public boolean isCollidedWith() {
		return isCollidedWith;
	}
	
	/**
	 * Set what {@link CollisionHandler} to use
	 * @param collisionHandler
	 */
	public void setCollisionHandler(CollisionHandler collisionHandler) {
		this.collisionHandler = collisionHandler;
	}
	
	/**
	 * @return the {@link CollisionHandler} used
	 */
	public CollisionHandler getCollisionHandler() {
		return collisionHandler;
	}
	
	/**
	 * @return the current position we are at
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * set the position
	 * @param position
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
		if(bounds != null) {
			this.bounds.setPosition(position.x + left, position.y + top);
		} 
	}

	/**
	 * Set the current position
	 * @param x the x position
	 * @param y the y position
	 */
	public void setPosition(float x, float y) {
		this.position.set(x, y);
		if(bounds != null) {
			this.bounds.setPosition(position.x + left, position.y + top);
		}
	}
	
	/**
	 * Set the current x position
	 * @param x
	 */
	public void setX(float x) {
		this.position.x = x + left;
	}
	
	/**
	 * Set the current y position
	 * @param y
	 */
	public void setY(float y) {
		this.position.y = y + top;
	}
	
	/**
	 * @return the bounding boxes height
	 */
	public float getBoundsHeight() {
		return bounds.height;
	}

	/**
	 * @return the bounding boxes width
	 */
	public float getBoundsWidth() {
		return this.bounds.width;
	}

	/**
	 * Set the {@link Rectangle} to use as bounding box
	 * @param bounds
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	/**
	 * Set the positions and size of the {@link Rectangle} to use as bounding box
	 * @param x the x position 
	 * @param y the y position
	 * @param width the width
	 * @param height the height
	 */
	public void setBounds(float x, float y, float width, float height) {
		if(this.bounds != null) {
			this.bounds.set(x, y, width, height);
		} else {
			this.bounds = new Rectangle(x, y, width, height);
		}
	}
	
	/**
	 * Set the positions and size of the {@link Rectangle} to use as bounding box
	 * @param x the x position 
	 * @param y the y position
	 * @param width the width
	 * @param height the height
	 * @param right how much to crop or enlarge the bounding box on the right
	 * @param bottom how much to crop or enlarge the bounding box on the bottom
	 * @param left how much to crop or enlarge the bounding box on the left
	 * @param top how much to crop or enlarge the bounding box on the top
	 */
	public void setBounds(float x, float y, float width, float height, float right, float bottom, float left, float top) {
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		this.top = top;
		if(this.bounds != null) {
			this.bounds.set(position.x + left, position.y + top, width - (left + right), height - (bottom + top));
		} else {
			this.bounds = new Rectangle(position.x + left, position.y + top, width - (left + right), height - (bottom + top));
		}
	}
	
	/**
	 * @return the bounding box
	 */
	public Rectangle getBounds() {
		return this.bounds;
	}
	
	/**
	 * Set the {@link Vector2} to use for velocity
	 * @param velocity
	 */
	public void setVelocity(Vector2 velocity) {
		if(this.velocity != null) {
			this.velocity.set(velocity);
		} else {
			this.velocity = new Vector2(velocity);
		}
	}
	
	/**
	 * Set the velocity of our {@link Vector2} velocity
	 * @param xSpeed the speed in the x direction
	 * @param ySpeed the speed in the y direction
	 */
	public void setVelocity(float xSpeed, float ySpeed) {
		if(this.velocity != null) {
			this.velocity.set(xSpeed, ySpeed);
		} else {
			this.velocity = new Vector2(xSpeed, ySpeed);
		}
	}
	
	/**
	 * @return the velocity {@link Vector2}
	 */
	public Vector2 getVelocity() {
		return velocity;
	}
	
	/**
	 * Set our {@link #velocity}'s x value
	 * @param xSpeed
	 */
	public void setXSpeed(float xSpeed) {
		this.velocity.x = xSpeed;
	}
	
	/**
	 * Set the {@link #velocity}'s y value
	 * @param ySpeed
	 */
	public void setYSpeed(float ySpeed) {
		this.velocity.y = ySpeed;
	}
	
	/**
	 * Set the {@link #speed}
	 * @param speed
	 */
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	/**
	 * @return the {@link #speed}
	 */
	public float getSpeed() {
		return speed;
	}
	
	/**
	 * Lets us separate the rendering of particles from the actual game object. Usually we will want
	 * the particles to be rendered in front of game and map objects. 
	 * @param batch 
	 */
	public void renderParticles(SpriteBatch batch) {
		particleHelper.render(batch);
	}

	/**
	 * Lets us separate the rendering of particles from the actual game object. Usually we will want
	 * the particles to be rendered in front of game and map objects. This method renders aswell as updates
	 * the particles
	 * @param batch 
	 * @param deltaTime
	 */
	public void renderParticles(SpriteBatch batch, float deltaTime) {
		particleHelper.render(batch, deltaTime);
	}

	/**
	 * Used to check if the collision made is vertical e.g. from top
	 * @param colliderY the colliding objects y position
	 * @param collisionObjectY the collided objects y position
	 * @return true if the collider objects y value is smaller than the collided objects y value
	 */
	public boolean isVerticalCollision(float colliderY, float collisionObjectY) {
		return colliderY < collisionObjectY;
	}
	
	public float getTop() {
		return top;
	}
	
	public float getBottom() {
		return bottom;
	}
	
	public float getLeft() {
		return left;
	}
	
	public float getRight() {
		return right;
	}
	
	public void setGameObjectTexture(TextureRegion gameObjectTexture) {
		if(this.gameObjectTexture == null) {
			this.gameObjectTexture = gameObjectTexture;
		}
	}
	
	public void setGameObjectTexture(String path) {
		this.setGameObjectTexture(new TextureRegion((Texture) Assets.getInstance().get(path)));
	}
	
	public void setGameObjectTexture(FileHandle fileHandle) {
		this.setGameObjectTexture(new TextureRegion(new Texture(fileHandle)));
	}
	
	public TextureRegion getGameObjectTexture() {
		return gameObjectTexture;
	}
	
	/**
	 * Get the width of the current frame texture region
	 * @return width
	 */
	public int getTextureWidth() {
		return gameObjectTexture == null ? 32 : gameObjectTexture.getRegionWidth();
	}

	/**
	 * Get the height of the current frame texture region
	 * @return height
	 */
	public int getTextureHeight() {
		return gameObjectTexture == null ? 32 : gameObjectTexture.getRegionHeight();
	}

}
