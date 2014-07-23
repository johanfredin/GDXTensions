package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimationUtils {

	/**
	 * Creates a customized animation from from a given sprite sheet. We need
	 * further control here compared to the methods we already have in the
	 * GameObject super class.
	 * 
	 * @param animSpeed the speed of the animation
	 * @param flipX whether to flip the x position
	 * @param flipY whether to flip the y position
	 * @param positions the frames from the sprite sheet we want to take out
	 * @return a new animation object
	 */
	public static Animation getFramesFromRegion(TextureRegion region, float animSpeed, boolean flipX, int... positions) {
		TextureRegion[] res = new TextureRegion[positions.length];
		for (int i = 0; i < positions.length; i++) {
			res[i] = new TextureRegion(region, positions[i] * 32, 0, 32, 32);
			res[i].flip(flipX, false);
		}
		return new Animation(animSpeed, region);
	}
	
	/**
	 * Get an animation from a sprite sheet with 32x32 frames.
	 * @param spriteSheet the sprite sheet we want to split up
	 * @param animSpeed the animation speed
	 * @return a new animation
	 */
	public Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed) {
		TextureRegion[] region = TextureUtils.split(spriteSheet, 32, 32, false);
		return new Animation(animSpeed, region);
	}

	/**
	 * Get an animation from a sprite sheet with 32x32 frames.
	 * @param spriteSheet the sprite sheet we want to split up
	 * @param animSpeed the animation speed
	 * @param flipX whether or not to flip the xPosition of each frame
	 * @return a new animation
	 */
	public Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed, boolean flipX) {
		TextureRegion[] region = TextureUtils.split(spriteSheet, 32, 32, flipX);
		return new Animation(animSpeed, region);
	}

	/**
	 * Get an animation from a sprite sheet were we customize width and height of each frame.
	 * @param spriteSheet the sprite sheet we want to split up
	 * @param animSpeed the animation speed
	 * @param frameWidth the width of each frame
	 * @param frameHeight the height of each frame
	 * @return a new animation
	 */
	public Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed, int frameWidth, int frameHeight) {
		TextureRegion[] region = TextureUtils.split(spriteSheet, frameWidth, frameHeight, false);
		return new Animation(animSpeed, region);
	}

	/**
	 * 
	 * @param spriteSheet
	 * @param animSpeed
	 * @param frameWidth
	 * @param frameHeight
	 * @param flipX
	 * @return
	 */
	public Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed, int frameWidth, int frameHeight, boolean flipX) {
		TextureRegion[] region = TextureUtils.split(spriteSheet, frameWidth, frameHeight, flipX);
		return new Animation(animSpeed, region);
	}
}
