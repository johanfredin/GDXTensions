
package se.fredin.gdxtensions.object;

import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.collision.CollisionHandler.Filter;
import se.fredin.gdxtensions.utils.ParticleHelper;
import se.fredin.gdxtensions.utils.Settings;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Super class for all Entities
 * @author Johan Fredin
 *
 */
public abstract class GameObject implements GameObjectBase {

	public final byte DIRECTION_LEFT = 0;
	public final byte DIRECTION_RIGHT = 1;
	public final byte DIRECTION_UP = 2;
	public final byte DIRECTION_DOWN = 4;
	public final byte DIRECTION_NONE = 8;
 
	protected static final float JUMP = 306.0f * Settings.GAMESPEED;

	protected final float TERMINAL_VELOCITY = 1200.0f * Settings.GAMESPEED;
	protected final float ACCELERATION = 760.0f * Settings.GAMESPEED;

	protected Vector2 position;
	protected Vector2 velocity;
	protected Rectangle bounds;
	protected TextureRegion currentFrame;
	protected CollisionHandler collisionHandler;
	
	protected boolean isJumping;
	protected boolean isCollidedWith;
	protected boolean isTeleporting;
	protected boolean down;
	protected boolean onGround;
	protected float gravity;
	protected byte direction = DIRECTION_NONE;
	
	protected float top, left, bottom, right;
	protected float speed;
	protected float stateTime;

	/** Used to emit particles for the children of this class that may need to */
	protected ParticleHelper particleHelper;
	

	public GameObject(Vector2 position) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f);
	}

	public GameObject(Vector2 position, float width, float height) {
		this(position, null, width, height, 0f, 0f, 0f, 0f);
	}

	public GameObject(Vector2 position, CollisionHandler collisionHandler, float width, float height) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f);
	}

	public GameObject(Vector2 position, float width, float height, float right, float bottom, float left, float top) {
		this(position, null, width, height, right, bottom, left, top);
	}

	public GameObject(Vector2 position, CollisionHandler collisionHandler, float width, float height, float right, float bottom, float left, float top) {
		this.position = position;
		this.right = right;
		this.bottom = bottom;
		this.left = left;
		this.top = top;
		this.bounds = new Rectangle(position.x + left, position.y + top, width - (left + right), height - (bottom + top));
		this.velocity = new Vector2();
		this.collisionHandler = collisionHandler;
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
	
	public void setCollisionHandler(CollisionHandler collisionHandler) {
		this.collisionHandler = collisionHandler;
	}
	
	public CollisionHandler getCollisionHandler() {
		return collisionHandler;
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
	
	public void setX(float x) {
		this.position.x = x + left;
	}
	
	public void setY(float y) {
		this.position.y = y + top;
	}
	
	public float getHeight() {
		return bounds.height;
	}

	public float getWidth() {
		return this.bounds.width;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	public void setBounds(float x, float y, float width, float height) {
		if(this.bounds != null) {
			this.bounds.set(x, y, width, height);
		} else {
			this.bounds = new Rectangle(x, y, width, height);
		}
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
	
	public void setVelocity(Vector2 velocity) {
		this.velocity.set(velocity);
	}
	
	public void setVelocity(float xSpeed, float ySpeed) {
		this.velocity.set(xSpeed, ySpeed);
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public void setXSpeed(float xSpeed) {
		this.velocity.x = xSpeed;
	}
	
	public void setYSpeed(float ySpeed) {
		this.velocity.y = ySpeed;
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
	
	/**
	 * Checks for collision with walls and doors in all directions
	 * @param newPosition where we want to go
	 * @return 
	 */
	protected boolean tryMove(Vector2 newPosition) {
		onGround = false;

		if(newPosition.y < position.y) {
			handleVerticalCollisionFromAbove(newPosition);
		} else {
			setPosition(position.x, newPosition.y);
		}

		handleVerticalCollisionFromBelow(newPosition);
		handleHorizontalCollision(newPosition);
		return false;
	}

	protected void handleVerticalCollisionFromAbove(Vector2 newPosition) {
		Rectangle tmpBounds = new Rectangle(bounds);
		tmpBounds.y = newPosition.y;
		tmpBounds.height = (newPosition.y - bounds.y) * -1;
		Rectangle downBounds = collisionHandler.getBoundsAt(tmpBounds, (byte)(Filter.HARD|Filter.SOFT), this);
		if(downBounds!=null) {
			onGround = true;
			Vector2 groundedPosition = new Vector2(position.x, downBounds.y + downBounds.height);
			setPosition(groundedPosition);
		} else {
			onGround = false;
			setPosition(position.x, newPosition.y);
		}
	}

	protected void handleVerticalCollisionFromBelow(Vector2 newPosition) {
		Rectangle headBounds = new Rectangle(bounds);
		headBounds.y += headBounds.height;
		headBounds.height = 1;
		Rectangle topBounds = collisionHandler.getBoundsAt(headBounds, (byte)(Filter.HARD), this);
		if(topBounds != null) {
			isJumping = false;
			gravity *= -.1f;
			setPosition(position.x, topBounds.y - bounds.height - 1f);
		} 
	}

	protected void handleHorizontalCollision(Vector2 newPosition) {
		Rectangle tmpHorzBounds = new Rectangle(bounds);
		tmpHorzBounds.x = newPosition.x + left;
		Rectangle horzBounds = collisionHandler.getBoundsAt(tmpHorzBounds, (byte)(Filter.HARD), this);
		if(horzBounds!=null) {
			if(onGround) {
//				switchDirection();
			}
			setPosition(position.x, position.y);
		} else {
			setPosition(newPosition.x, position.y);
		}
	}

}
