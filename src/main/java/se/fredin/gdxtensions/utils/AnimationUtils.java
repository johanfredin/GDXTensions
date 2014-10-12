package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class AnimationUtils {

	/** The starting default pixel width for each frame */
	public static short defaultWidth = 32;
	/** The starting default pixel height for each frame */
	public static short defaultHeight = 32;
	
	/**
	 * Creates a customized animation from from a given sprite sheet. We need
	 * further control here compared to the methods we already have in the
	 * GameObject super class.
	 * 
	 * @param animSpeed the speed of the animation
	 * @param flipX whether to flip the x position
	 * @param positions the frames from the sprite sheet we want to take out
	 * @return a new animation object
	 */
	public static Animation getFramesFromRegion(TextureRegion region, float animSpeed, boolean flipX, int... positions) {
		TextureRegion[] res = new TextureRegion[positions.length];
		for (int i = 0; i < positions.length; i++) {
			res[i] = new TextureRegion(region, positions[i] * defaultWidth, 0, defaultWidth, defaultHeight);
			res[i].flip(flipX, false);
		}
		return new Animation(animSpeed, region);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. We need
	 * further control here compared to the methods we already have in the
	 * GameObject super class.
	 * 
	 * @param animSpeed the speed of the animation
	 * @param flipX whether to flip the x position
	 * @param startIndex the starting index in the spritesheet
	 * @param endIndex the last index in the spritesheet
	 * @return a new animation object
	 */
	public static Animation getFramesFromRegion(TextureRegion region, float animSpeed, boolean flipX, short startIndex, short endIndex) {
		int totalFrames = endIndex - startIndex;
		int[] positions = new int[totalFrames];
		TextureRegion[] res = new TextureRegion[totalFrames];
		for (int i = startIndex; i < endIndex; i++) {
			res[i] = new TextureRegion(region, positions[i] * defaultWidth, 0, defaultWidth, defaultHeight);
			res[i].flip(flipX, false);
		}
		return new Animation(animSpeed, region);
	}
	
	/**
	 * Get an animation from a sprite sheet with defaultWidth * defaultHeight frames.
	 * @param spriteSheet the sprite sheet we want to split up
	 * @param animSpeed the animation speed
	 * @return a new animation
	 */
	public static Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed) {
		return getFramesFromRegion(spriteSheet, animSpeed, false);
	}

	/**
	 * Get an animation from a sprite sheet with defaultWidth * defaultHeight frames.
	 * @param spriteSheet the sprite sheet we want to split up
	 * @param animSpeed the animation speed
	 * @param flipX whether or not to flip the xPosition of each frame
	 * @return a new animation
	 */
	public static Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed, boolean flipX) {
		TextureRegion[] region = TextureUtils.split(spriteSheet, defaultWidth, defaultHeight, flipX);
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
	public static Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed, int frameWidth, int frameHeight) {
		return getFramesFromRegion(spriteSheet, animSpeed, frameWidth, frameHeight, false);
	}

	/**
	 * Get an animation from a sprite sheet were we customize width and height of each frame.
	 * @param spriteSheet the sprite sheet we want to split up
	 * @param animSpeed the animation speed
	 * @param frameWidth the width of each frame
	 * @param frameHeight the height of each frame
	 * @param flipX whether or not to flip the frames horizontally
	 * @return a new animation
	 */
	public static Animation getFramesFromRegion(TextureRegion spriteSheet, float animSpeed, int frameWidth, int frameHeight, boolean flipX) {
		TextureRegion[] region = TextureUtils.split(spriteSheet, frameWidth, frameHeight, flipX);
		return new Animation(animSpeed, region);
	}
}
