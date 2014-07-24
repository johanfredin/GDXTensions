package se.fredin.gdxtensions.scene2d;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

/**
 * Lets user  create an animated spritesheet that will move repeatedly from right to left respawning at a random
 * y position when hitting screen boundaries. Useful when you want to create some flashing retro effects a'la MegaMan
 * @author Johan Fredin
 *
 */
public class MovingActor extends AnimatedActor {

	private float speed;
	private short worldWidth;
	private short worldHeight;
	private float frameHeight;
	
	/**
	 * Create a new MovingStar
	 * @param region the sprite sheet
	 * @param ANIM_SPEED the speed of the animation
	 * @param xPos 
	 * @param yPos
	 * @param worldWidth the width of the area you want the moving actor to operate on
	 * @param worldHeight the height of the are you want the moving actor to operate on
	 * @param frameWidth the width of each frame in the sprite sheet
	 * @param frameHeight the height of each frame in the sprite sheet
	 * @param speed the moving speed
	 */
	public MovingActor(TextureRegion region, float ANIM_SPEED, float xPos, float yPos, short worldWidth, short worldHeight, int frameWidth, int frameHeight, float speed) {
		super(region, ANIM_SPEED, xPos, yPos, frameWidth, frameHeight, true);
		this.speed = speed;
		this.frameHeight = frameHeight;
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
	}
		
	@Override
	public void act(float delta) {
		super.act(delta);
		setX(getX() - (speed * delta));
		if(getX() < 0) {
			setPosition(MathUtils.random(worldWidth, worldWidth * 1.5f), newRandomPos());
		}
	}
	
	/**
	 * @return a new random y position within the worldheight limit
	 */
	private float newRandomPos() {
		return MathUtils.random(0, worldHeight - frameHeight);
	}
	
}
