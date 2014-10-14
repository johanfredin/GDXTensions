package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;



public class AnimationUtils {
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed) {
		TextureRegion[] reg = TextureUtils.split(region);
		return new Animation(animSpeed, reg);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed, int frameWidth, int frameHeight) {
		TextureRegion[] reg = TextureUtils.split(region, frameWidth, frameHeight);
		return new Animation(animSpeed, reg);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed, int frameWidth, int frameHeight, boolean flipX) {
		TextureRegion[] reg = TextureUtils.split(region, frameWidth, frameHeight, flipX);
		return new Animation(animSpeed, reg);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed, int frameWidth, int frameHeight, boolean flipX, boolean flipY) {
		TextureRegion[] reg = TextureUtils.split(region, frameWidth, frameHeight, flipX, flipY);
		return new Animation(animSpeed, reg);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param intervalRegExp lets you specify a certain interval of frames, if you for example want frame 2 to 6 you simply write "2-6", 
	 * the string must be formatted this way!
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed, int frameWidth, int frameHeight, String intervalRexExp) {
		TextureRegion[] reg = TextureUtils.split(region, frameWidth, frameHeight, intervalRexExp);
		return new Animation(animSpeed, reg);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @param intervalRegExp lets you specify a certain interval of frames, if you for example want frame 2 to 6 you simply write "2-6", 
	 * the string must be formatted this way!
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed, int frameWidth, int frameHeight, boolean flipX, boolean flipY, String intervalRexExp) {
		TextureRegion[] reg = TextureUtils.split(region, frameWidth, frameHeight, flipX, flipY, intervalRexExp);
		return new Animation(animSpeed, reg);
	}
	
	/**
	 * Creates a customized animation from from a given sprite sheet. 
	 * @param animSpeed the speed of the animation
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @param desiredFrames the specific frames we want to retrieve, convenient when we don't need the entire sprite sheet
	 * @return a new animation object
	 */
	public static Animation getAnimation(TextureRegion region, float animSpeed, int frameWidth, int frameHeight, boolean flipX, boolean flipY, int... desiredFrames) {
		TextureRegion[] reg = TextureUtils.split(region, frameWidth, frameHeight, flipX, flipY, desiredFrames);
		return new Animation(animSpeed, reg);
	}
}