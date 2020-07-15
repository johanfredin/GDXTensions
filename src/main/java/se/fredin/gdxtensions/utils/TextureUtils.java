package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Utility class for splitting up sprite sheets and use for animation.
 * Instead of LibGDX split method all methods in this class splits up the
 * sprite sheets into single arrays. This approach is more convenient when
 * working with Animation objects.
 * @author Johan Fredin
 *
 */
public class TextureUtils {
	
	/** The starting default pixel width for each frame */
	public static int defaultFrameWidth = 32;
	/** The starting default pixel height for each frame */
	public static int defaultFrameHeight = 32;
	
	/** Default empty index array */
	private static int[] blank = {};
	
	/**
	 * Splits up a Texture object into a TextureRegion array with the default width and height.
	 * @param textureToSplit the Texture object to split up
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit) {
		return split(textureToSplit, defaultFrameWidth, defaultFrameHeight, false, false, blank);
	}
	
	/**
	 * Splits up a Texture object into a TextureRegion array with the default width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param flipX whether or not to flip each frame horizontally
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, boolean flipX) {
		return split(textureToSplit, defaultFrameWidth, defaultFrameHeight, flipX, false, blank);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with the default width and height.
	 * @param textureRegionToSplit the Texture object to split up
	 * @param flipX whether or not to flip each frame horizontally
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, boolean flipX) {
		return split(textureRegionToSplit, defaultFrameWidth, defaultFrameHeight, flipX, false, blank);
	}
	
	/**
	 * Splits up a Texture object into a TextureRegion array with the default width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, boolean flipX, boolean flipY) {
		return split(textureToSplit, defaultFrameWidth, defaultFrameHeight, flipX, flipY, blank);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with the default width and height.
	 * @param textureRegionToSplit the TextureRegion object to split up
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, boolean flipX, boolean flipY) {
		return split(textureRegionToSplit, defaultFrameWidth, defaultFrameHeight, flipX, flipY, blank);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureRegionToSplit the Texture object to split up
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit) {
		return split(textureRegionToSplit, defaultFrameWidth, defaultFrameHeight, false, false, blank);
	}
	
	/**
	 * Splits up a Texture object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, int frameWidth, int frameHeight) {
		return split(textureToSplit, frameWidth, frameHeight, false, false, blank);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureRegionToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight) {
		return split(textureRegionToSplit, frameWidth, frameHeight, false, false, blank);
	}
	
	/**
	 * Splits up a Texture object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, int frameWidth, int frameHeight, boolean flipX) {
		return split(textureToSplit, frameWidth, frameHeight, flipX, false, blank);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureRegionToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight, boolean flipX) {
		return split(textureRegionToSplit, frameWidth, frameHeight, flipX, false);
	}
	
	/**
	 * Splits up a Texture object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, int frameWidth, int frameHeight, boolean flipX, boolean flipY) {
		return split(textureToSplit, frameWidth, frameHeight, flipX, flipY, blank);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureRegionToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight, boolean flipX, boolean flipY) {
		return split(textureRegionToSplit, frameWidth, frameHeight, flipX, flipY, blank);
	}
	
	/**
	 * Splits up a Texture object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @param desiredFrames the specific frames we want to retrieve, convenient when we don't need the entire sprite sheet
	 * @throws IndexOutOfBoundsException if you try to acquire a frame that is outside the bounds of the Textures
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, int frameWidth, int frameHeight, boolean flipX, boolean flipY, int... desiredFrames) {
		int xSlices = textureToSplit.getWidth() / frameWidth;
		int ySlices = textureToSplit.getHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int y = 0; y < ySlices; y++) {
			for (int x = 0; x < xSlices; x++) {
				tmp[x][y] = new TextureRegion(textureToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				res[index].flip(flipX, flipY);
				index++;
			}
		}
		
		if(isNotEmpty(desiredFrames)) {
			return getCustomizedRegion(res, desiredFrames);
		}
		return res;
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureRegionToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @param desiredFrames the specific frames we want to retrieve, convenient when we don't need the entire sprite sheet
	 * @throws IndexOutOfBoundsException if you try to acquire a frame that is outside the bounds of the TextureRegion
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight, boolean flipX, boolean flipY, int... desiredFrames) {
		int xSlices = textureRegionToSplit.getRegionWidth() / frameWidth;
		int ySlices = textureRegionToSplit.getRegionHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int y = 0; y < ySlices; y++) {
			for (int x = 0; x < xSlices; x++) {
				tmp[x][y] = new TextureRegion(textureRegionToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				res[index].flip(flipX, flipY);
				index++;
			}
		}
		
		if(isNotEmpty(desiredFrames)) {
			return getCustomizedRegion(res, desiredFrames);
		}
		return res;
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param intervalRegExp lets you specify a certain interval of frames, if you for example want frame 2 to 6 you simply write "2-6", 
	 * the string must be formatted this way!
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split(TextureRegion textureToSplit, int frameWidth, int frameHeight, String intervalRegExp) {
		return split(textureToSplit, frameWidth, frameHeight, false, false, intervalRegExp);
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param region the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @param intervalRegExp lets you specify a certain interval of frames, if you for example want frame 2 to 6 you simply write "2-6", 
	 * the string must be formatted this way!
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split(TextureRegion region, int frameWidth, int frameHeight, boolean flipX, boolean flipY, String intervalRegExp) {
		if(intervalRegExp == null) {
			return split(region, frameWidth, frameHeight, flipX, flipY);
		}
		
		if(!intervalRegExp.contains("-")) {
			throw new RuntimeException("the inteval string passed must be in the form n-n");
		}
		
		int startIndex = Integer.parseInt(intervalRegExp.substring(0, intervalRegExp.indexOf('-')));
		int endIndex = Integer.parseInt(intervalRegExp.substring(intervalRegExp.indexOf('-') + 1));
		int totalFrames = (endIndex - startIndex) + 1;
		int[] desiredFrames = new int[totalFrames];
		int counter = 0;
		for(int i = startIndex; i <= endIndex; i++) {
			desiredFrames[counter] = i;
			counter++;
		}
		
		return split(region, frameWidth, frameHeight, flipX, flipY, desiredFrames);
	}
	
	private static TextureRegion[] getCustomizedRegion(TextureRegion[] originalRegion, int... desiredFrames) {
		TextureRegion[] customizedRes = new TextureRegion[desiredFrames.length];
		for(int i = 0; i < originalRegion.length; i++) {
			for(int j = 0; j < desiredFrames.length; j++) {
				if(i == desiredFrames[j]) {
					customizedRes[j] = new TextureRegion(originalRegion[i]);
				}
			}
		}
		return customizedRes;
	}
	
	private static boolean isNotEmpty(int... frames) {
		return frames.length > 0;
	}
	
}
