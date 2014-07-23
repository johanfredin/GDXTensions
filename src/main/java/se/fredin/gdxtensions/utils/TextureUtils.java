package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Utility class for splitting up sprite sheets and use for animation.
 * Instead of LibGDX split method all methods in this class splits up the
 * sprite sheets into single arrays. This approach is more convenient when
 * working with Animation objects.
 * @author Johan Fredin
 *
 */
public class TextureUtils {
	
	/**
	 * Splits up a Texture object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (Texture textureToSplit, int frameWidth, int frameHeight) {
		int xSlices = textureToSplit.getWidth() / frameWidth;
		int ySlices = textureToSplit.getHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int y = 0; y < ySlices; y++) {
			for (int x = 0; x < xSlices; x++) {
				tmp[x][y] = new TextureRegion(textureToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				index++;
			}
		}
		return res;
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
		int xSlices = textureToSplit.getWidth() / frameWidth;
		int ySlices = textureToSplit.getHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int y = 0; y < ySlices; y++) {
			for (int x = 0; x < xSlices; x++) {
				tmp[x][y] = new TextureRegion(textureToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				res[index].flip(flipX, false);
				index++;
			}
		}
		return res;
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
		return res;
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
		
		Array<TextureRegion> customizedRes = new Array<TextureRegion>(desiredFrames.length);
		for(int j = 0; j < desiredFrames.length; j++) {
			if(j >= res.length) {
				throw new IndexOutOfBoundsException();
			}
			customizedRes.add(new TextureRegion(res[j]));
		}
		return customizedRes.toArray();
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight) {
		int xSlices = textureRegionToSplit.getRegionWidth() / frameWidth;
		int ySlices = textureRegionToSplit.getRegionHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				tmp[x][y] = new TextureRegion(textureRegionToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				index++;
			}
		}

		return res;
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight, boolean flipX) {
		int xSlices = textureRegionToSplit.getRegionWidth() / frameWidth;
		int ySlices = textureRegionToSplit.getRegionHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				tmp[x][y] = new TextureRegion(textureRegionToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				res[index].flip(flipX, false);
				index++;
			}
		}

		return res;
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
	 * @param frameWidth the desired frame width
	 * @param frameHeight the desired frame height
	 * @param flipX whether or not to flip each frame horizontally
	 * @param flipY whether or not to flip each frame vertically
	 * @return a new TextureRegion sprite sheet array
	 */
	public static TextureRegion[] split (TextureRegion textureRegionToSplit, int frameWidth, int frameHeight, boolean flipX, boolean flipY) {
		int xSlices = textureRegionToSplit.getRegionWidth() / frameWidth;
		int ySlices = textureRegionToSplit.getRegionHeight() / frameHeight;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				tmp[x][y] = new TextureRegion(textureRegionToSplit, x * frameWidth, y * frameHeight, frameWidth, frameHeight);
				res[index] = tmp[x][y];
				res[index].flip(flipX, flipY);
				index++;
			}
		}

		return res;
	}
	
	/**
	 * Splits up a TextureRegion object into a TextureRegion array with given width and height.
	 * @param textureToSplit the Texture object to split up
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
		
		Array<TextureRegion> customizedRes = new Array<TextureRegion>(desiredFrames.length);
		for(int i = 0; i < desiredFrames.length; i++) {
			if(i >= res.length) {
				throw new IndexOutOfBoundsException();
			}
			customizedRes.add(new TextureRegion(res[i]));
		}
		return customizedRes.toArray();
	}


}
