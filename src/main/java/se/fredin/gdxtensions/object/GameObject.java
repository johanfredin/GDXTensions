
package se.fredin.gdxtensions.object;

import se.fredin.gdxtensions.level.LevelBase;
import se.fredin.gdxtensions.utils.ParticleHelper;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;

/**
 * Super class for all Entities in the game (except for AICow)
 * @author Johan Fredin, Niklas Istenes
 *
 */
public abstract class GameObject implements GameObjectBase, Disposable {

	public final byte DIRECTION_LEFT = 0;
	public final byte DIRECTION_RIGHT = 1;
	public final byte DIRECTION_UP = 2;
	public final byte DIRECTION_DOWN = 4;
	public final byte DIRECTION_NONE = 8;
 
	protected static final float GAMESPEED = 1f;
	protected static final float JUMP = 216.0f * GAMESPEED;

	protected final float TERMINAL_VELOCITY = 600.0f * GAMESPEED;
	protected final float ACCELERATION = 360.0f * GAMESPEED;

	protected Vector2 position;
	protected Vector2 velocity;
	protected Rectangle bounds;
	protected TextureRegion currentFrame;
	protected LevelBase levelBase;

	protected boolean isJumping;
	protected boolean isCollidedWith;
	protected boolean isTeleporting;
	protected boolean down;
	protected boolean onGround;
	protected float gravity;
	protected byte direction = DIRECTION_NONE;
	
	//TODO:Remove once collision controls work1
	public Rectangle movingBounds = new Rectangle();
	
	protected float top, left, bottom, right;
	protected float speed;
	protected float stateTime;

	/** Used to emit particles for the children of this class that may need to */
	protected ParticleHelper particleHelper;
	

	public GameObject(Vector2 position) {
		this.position = position;
		this.bounds = new Rectangle();
		this.velocity = new Vector2();
	}

	public GameObject(Vector2 position, float width, float height) {
		this.position = position;
		this.bounds = new Rectangle(position.x, position.y, width, height);
		this.velocity = new Vector2();
	}

	public GameObject(Vector2 position, LevelBase levelBase, float width, float height) {
		this(position, width, height);
		this.levelBase = levelBase;
	}

	public GameObject(Vector2 position, float width, float height, float right, float bottom, float left, float top) {
		this.position = position;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		this.top = top;
		this.bounds = new Rectangle(position.x + left, position.y + top, width - (left + right), height - (bottom + top));
		this.velocity = new Vector2();
	}

	public GameObject(Vector2 position, LevelBase levelBase, float width, float height, float right, float bottom, float left, float top) {
		this(position, width, height, right, bottom, left, top);
		this.levelBase = levelBase;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public boolean isCollidedWith() {
		return isCollidedWith;
	}
	
	public boolean isTeleporting(){
		return isTeleporting;
	}
	
	public void setTeleporting(boolean isTeleporting) {
		this.isTeleporting = isTeleporting;
	}
	
	public void setLevel(LevelBase levelBase) {
		this.levelBase = levelBase;
	}

	//TODO  Transforms the given vector by this transform
	public Vector2 mul(Vector2 v){
		return v;
	}
	
	public Vector2 getPosition() {
		return position;
	}

	public boolean isFalling() {
		return !onGround && gravity >= 0f;
	}
	
	public void setPosition(Vector2 position) {
		this.position = position;
		this.bounds.setPosition(position.x + left, position.y + top);
	}

	public void setPosition(float x, float y) {
		this.position.set(x, y);
		this.bounds.setPosition(position.x + left, position.y + top);
	}

	public float getHeight() {
		return bounds.height;
	}

	public float getWidth() {
		return this.bounds.width;
	}

	public Rectangle getBounds() {
		return this.bounds;
	}

	public void switchXDirection() {
		direction = direction == DIRECTION_RIGHT ? DIRECTION_LEFT : DIRECTION_RIGHT;
	}
	
	public void switchYDirection() {
		direction = direction == DIRECTION_UP ? DIRECTION_DOWN : DIRECTION_UP;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public boolean isMoving() {
		return direction != DIRECTION_NONE;
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

	/**
	 * direction == DIRECTION_RIGHT ? 
	 */
	public boolean isHeadingRight() {
		return direction == DIRECTION_RIGHT;
	}

	/**
	 * direction == DIRECTION_LEFT ?
	 * @return
	 */
	public boolean isHeadingLeft() {
		return direction == DIRECTION_LEFT;
	}
	
	/**
	 * direction == DIRECTION_UP ?
	 * @return
	 */
	public boolean isHeadingUp() {
		return direction == DIRECTION_UP;
	}
	
	/**
	 * direction == DIRECTION_DOWN ?
	 * @return
	 */
	public boolean isHeadingDown() {
		return direction == DIRECTION_DOWN;
	}
	
	public byte getDirection() {
		return direction;
	}
	
	public void setDirection(byte direction) {
		this.direction = direction;
	}

	/**
	 * Animates the object.
	 * @param deltaTime
	 */
	protected void animate(float deltaTime) {
		stateTime += deltaTime;
	}

}
