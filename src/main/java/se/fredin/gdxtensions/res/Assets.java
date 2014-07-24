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
 * @authors Johan Fredin, Niklas Istenes 
 *
 */
public class Assets implements Disposable {

	public static boolean LOAD_SYNCHRONOUSLY = false;
	
	private AssetManager manager;
	private static Assets assets;

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
	 * Loads all the assets required for the menu screens
	 */
	@SuppressWarnings("unchecked")
	public void loadMenuAssets(LoadParams... params) {
		// Set the manager for the textures
		for(LoadParams param : params) {
			manager.load(param.path, param.clazz);	
		}
		Texture.setAssetManager(manager);
	}

	/**
	 * Unloads all the menu screen assets
	 */
	public void unloadMenuAssets(String... assets) {
		try {
			for(String asset : assets) {
				manager.unload(asset);
			}
		} catch(GdxRuntimeException ex) {
			ex.printStackTrace();
		}
	}

	
	/**
	 * Loads a tile map with the given index
	 * @param worldType the type of map to load (beach, grass etc)
	 * @param index the index of the map we want to load
	 * @param isTestLevel whether to retrieve the map from the test levels path or not
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
	 * Unloads a tile map with the given index if the map is loaded
	 * @param tmxFilePath the path to the tmxMap to load
	 */
	public void unloadTileMap(String tmxFilePath) {
		manager.unload(tmxFilePath);
	}
	
	/**
	 * Unload the in game assets
	 */
	public void unloadInGameAssets() {
	}
	

	private Assets() {
		manager = new AssetManager();
		if(LOAD_SYNCHRONOUSLY) {
			loadMenuAssets();
			Texture.setAssetManager(manager);
			manager.finishLoading();
		}
	}
	
	public int getProgress() {
		return (int) (manager.getProgress() * 100);
	}

	public boolean isLoading() {
		return !manager.update();
	}
	
	public boolean isLoaded(String path) {
		return manager.isLoaded(path);
	}
	
	public void finishLoading() {
		manager.finishLoading();
		Texture.setAssetManager(manager);
	}
	
}