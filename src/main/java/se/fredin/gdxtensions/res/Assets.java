package se.fredin.gdxtensions.res;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Handles all the game assets
 * @authors Johan Fredin 
 */
public class Assets implements Disposable {

	public static boolean LOAD_SYNCHRONOUSLY = false;
	private static Assets assets;
	private AssetManager manager;

	public static Assets getInstance() {
		if(assets == null) {
			assets = new Assets();
		}
		return assets;
	}

	/**
	 * Lets us retrieve an object from the loaded assets.
	 * Usually the object needs to be cast to the type 
	 * we want (like a Texture for example)
	 * @param fileName
	 * @return
	 */
	public Object get(String fileName) {
		return manager.get(fileName);
	}

	/**
	* Returns a new sprite and sets yDown to true.
	* @param path the path for the texture.
	* @return
	*/
	public Sprite getSprite(String path) {
		Sprite sprite = new Sprite((Texture) get(path));
		return sprite;
	}

	/**
	 * Returns a sprite and lets us flip the values we want
	 * @param path
	 * @param flipX whether to flip the x value
	 * @param flipY whether to flip the y value
	 * @return
	 */
	public Sprite getSprite(String path, boolean flipX, boolean flipY) {
		Sprite sprite = new Sprite((Texture) get(path));
		sprite.flip(flipX, flipY);
		return sprite;
	}

	/**
	 * Unloads all the assets and disposes of the asset manager.
	 * When this method is called the manager should not be used anymore.
	 */
	@Override
	public void dispose() {
		manager.dispose();
	}

	/**
	 * Unloads all assets currently handled by the manager
	 */
	public void clear() {
		manager.clear();
	}

	/**
	 * Load all the assets given
	 * @param params the assets to load
	 */
	@SuppressWarnings("unchecked")
	public void load(LoadParams... params) {
		// Set the manager for the textures
		for(LoadParams param : params) {
			manager.load(param.path, param.clazz);	
		}
		Texture.setAssetManager(manager);
	}

	/**
	 * Unloads all the given assets
	 */
	public void unload(String... assets) {
		try {
			for(String asset : assets) {
				manager.unload(asset);
			}
		} catch(GdxRuntimeException ex) {
			ex.printStackTrace();
		}
	}

	
	/**
	 * Load a tiled map
	 * @param tmxFilePath path to the map
	 */
	public void loadTileMap(String tmxFilePath) {
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		manager.load(tmxFilePath, TiledMap.class);
		manager.finishLoading();
	}
	
	/**
	 * Load a tiled map
	 * @param tmxFilePath the path to the map
	 * @param textureFilter texture filter to use for the map
	 */
	public void loadTileMap(String tmxFilePath, TextureFilter textureFilter) {
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		Parameters p = new Parameters();
		p.textureMagFilter = textureFilter;
		p.textureMinFilter = textureFilter;
		manager.load(tmxFilePath, TiledMap.class, p);
		manager.finishLoading();
	}
	
	/**
	 * Unloads a tile map loaded
	 * @param tmxFilePath the path to the tmxMap to unload
	 */
	public void unloadTileMap(String tmxFilePath) {
		manager.unload(tmxFilePath);
	}
	
	private Assets() {
		manager = new AssetManager();
		if(LOAD_SYNCHRONOUSLY) {
			load();
			Texture.setAssetManager(manager);
			manager.finishLoading();
		}
	}
	
	/**
	 * Useful when you wish to use a loading screen
	 * @return the progress (0 = nothing loaded, 100 = fully loaded)
	 */
	public int getProgress() {
		return (int) (manager.getProgress() * 100);
	}

	/**
	 * @return whether or not assets are still being loaded
	 */
	public boolean isLoading() {
		return !manager.update();
	}
	
	/**
	 * Lets us find out if an asset is loaded or not
	 * @param path
	 * @return
	 */
	public boolean isLoaded(String path) {
		return manager.isLoaded(path);
	}
	
	/**
	 * Makes the application freeze until all the assets are loaded
	 */
	public void finishLoading() {
		manager.finishLoading();
		Texture.setAssetManager(manager);
	}
	
}