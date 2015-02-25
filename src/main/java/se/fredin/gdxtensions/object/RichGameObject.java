
package se.fredin.gdxtensions.object;

import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.collision.CollisionHandler.Filter;
import se.fredin.gdxtensions.input.BaseInput;
import se.fredin.gdxtensions.utils.Settings;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * This class is a more advanced extension of {@link BasicGameObject}, besides all the features
 * of its superclass {@link RichGameObject} also contains methods and variables for gravity, animation,
 * teleportation etc..
 * @author Johan Fredin
 *
 */
public abstract class RichGameObject extends BasicGameObject {

	public static final byte MAX_HEALTH = 100;
	protected static final float JUMP = 306.0f * Settings.GAMESPEED;
	
	public final byte DIRECTION_LEFT = 0;
	public final byte DIRECTION_RIGHT = 1;
	public final byte DIRECTION_UP = 2;
	public final byte DIRECTION_DOWN = 4;
	public final byte DIRECTION_NONE = 8;

	protected final float TERMINAL_VELOCITY = 1200.0f * Settings.GAMESPEED;
	protected final float ACCELERATION = 760.0f * Settings.GAMESPEED;

	protected boolean isJumping;
	protected boolean isTeleporting;
	protected boolean isVisible;
	protected boolean down;
	protected boolean onGround;
	protected float gravity;
	protected byte direction = DIRECTION_NONE;
	protected float health;
	protected Vector2 teleportLandingPosition;
	
	
	protected float stateTime;

	//TODO: For now only fixed keyboard, fix later to map to any keys or gamepad or touchpad
	protected BaseInput input;
	
	/**
	 * Construct a new {@link RichGameObject} without setting anything
	 */
	public RichGameObject() {}
	
	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 */
	public RichGameObject(Vector2 position) {
		this(position, null, 0f, 0f, 0f, 0f, 0f, 0f);
	}

	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public RichGameObject(Vector2 position, float width, float height) {
		this(position, null, width, height, 0f, 0f, 0f, 0f);
	}

	/**
	 * Construct a new {@link RichGameObject}
	 * @param position the initial position
	 * @param collisionHandler the {@link CollisionHandler} that will handle collision for this game object
	 * @param width the width of the bounding box
	 * @param height the height of the bounding box
	 */
	public RichGameObject(Vector2 position, CollisionHandler collisionHandler, float width, float height) {
		this(position, collisionHandler, width, height, 0f, 0f, 0f, 0f);
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
	public RichGameObject(Vector2 position, float width, float height, float right, float bottom, float left, float top) {
		this(position, null, width, height, right, bottom, left, top);
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
	public RichGameObject(Vector2 position, CollisionHandler collisionHandler, float width, float height, float right, float bottom, float left, float top) {
		super(position, collisionHandler, width, height, right, bottom, left, top);
		this.health = MAX_HEALTH;
	}

	/**
	 * @return the gravity used
	 */
	public float getGravity() {
		return gravity;
	}

	/**
	 * Set the amount of gravity
	 * @param gravity
	 */
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	/**
	 * @return whether or not we are teleporting
	 */
	public boolean isTeleporting(){
		return isTeleporting;
	}
	
	public void setOnGround(boolean onGround) {
		this.onGround = onGround;
	}

	public Vector2 getTeleportLandingPosition() {
		return teleportLandingPosition;
	}

	public void setTeleportLandingPosition(Vector2 teleportLandingPosition) {
		this.teleportLandingPosition = teleportLandingPosition;
	}
	
	/**
	 * Set whether or not we are teleporting
	 * @param isTeleporting
	 */
	public void setTeleporting(boolean isTeleporting) {
		this.isTeleporting = isTeleporting;
	}
	
	public InputProcessor getInput() {
		return input;
	}
	
	/**
	 * Transforms the given vector by this transform
	 * @param v
	 * @return
	 */
	public Vector2 mul(Vector2 v){
		return v;
	}
	
	/**
	 * @return whether or not we are falling e.g {@link #onGround}==false && {@link #gravity}>=0
	 */
	public boolean isFalling() {
		return !onGround && gravity >= 0f;
	}
	
	/**
	 * Switch the x direction
	 */
	public void switchXDirection() {
		direction = direction == DIRECTION_RIGHT ? DIRECTION_LEFT : DIRECTION_RIGHT;
	}
	
	/**
	 * Swith the y direction
	 */
	public void switchYDirection() {
		direction = direction == DIRECTION_UP ? DIRECTION_DOWN : DIRECTION_UP;
	}

	/**
	 * @return whether or not we are moving e.g {@link #direction} != {@link #DIRECTION_NONE}
	 */
	public boolean isMoving() {
		return direction != DIRECTION_NONE;
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
	
	/**
	 * @return the current direction we are heading
	 */
	public byte getDirection() {
		return direction;
	}
	
	/**
	 * Adds health to the player
	 * @param amount the amount of health to add
	 */
	public void increaseHealth(float amount) {
		if(health < MAX_HEALTH) {
			this.health += amount;
		}
	}

	public float getHealth() {
		return health;
	}

	/**
	 * Sets the players health to zero
	 */
	public void kill() {
		this.health = 0.0f;
	}
	
	/**
	 * Set the player to visible or not
	 * @param isVisible
	 */
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	/**
	 * Check if the player is visible
	 * @return
	 */
	public boolean isVisible() {
		return isVisible;
	}
	
	/**
	 * Set the direction we should be heading in
	 * @param direction the direction we want to set
	 * must be one of the following:<br>
	 * {@link #DIRECTION_DOWN}<br>
	 * {@link #DIRECTION_LEFT}<br>
	 * {@link #DIRECTION_RIGHT}<br>
	 * {@link #DIRECTION_UP}<br>
	 * {@link #DIRECTION_NONE}
	 */
	public void setDirection(byte direction) {
		this.direction = direction;
	}
	
	public void handleInput() {
		if(input.isLeftButtonPressed()) {
			direction = DIRECTION_LEFT;
		} if(input.isRightButtonPressed()) {
			direction = DIRECTION_RIGHT;
		} else {
			if(input.noMovementKeysPressed()) {
				direction = DIRECTION_NONE;
			}
		}
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
