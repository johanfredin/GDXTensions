package se.fredin.gdxtensions.texture;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Utility class for splitting up sprite sheets and use for animation.
 * @author Johan Fredin
 *
 */
public class TextureUtils {

	/**
	 * Split the textures into a single array
	 * @param texture
	 * @param width
	 * @param height
	 * @param flipX
	 * @return
	 */
	public static TextureRegion[] split (Texture texture, int width, int height, boolean flipX) {
		int xSlices = texture.getWidth() / width;
		int ySlices = texture.getHeight() / height;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int y = 0; y < ySlices; y++) {
			for (int x = 0; x < xSlices; x++) {
				tmp[x][y] = new TextureRegion(texture, x * width, y * height, width, height);
				res[index] = tmp[x][y];
				res[index].flip(flipX, false);
				index++;
			}
		}
		return res;
	}	



	/**
	 * Does the same as split but into a single array
	 * @param textureRegion
	 * @param width
	 * @param height
	 * @param flipX
	 * @return
	 */
	public static TextureRegion[] split (TextureRegion textureRegion, int width, int height, boolean flipX) {
		int xSlices = textureRegion.getRegionWidth() / width;
		int ySlices = textureRegion.getRegionHeight() / height;
		TextureRegion[][] tmp = new TextureRegion[xSlices][ySlices];
		TextureRegion[] res = new TextureRegion[xSlices * ySlices];

		int index = 0;
		for (int x = 0; x < xSlices; x++) {
			for (int y = 0; y < ySlices; y++) {
				tmp[x][y] = new TextureRegion(textureRegion, x * width, y * height, width, height);
				res[index] = tmp[x][y];
				res[index].flip(flipX, false);
				index++;
			}
		}

		return res;
	}


}
