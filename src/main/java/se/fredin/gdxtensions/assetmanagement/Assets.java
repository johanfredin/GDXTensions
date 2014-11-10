package se.fredin.gdxtensions.assetmanagement;

import se.fredin.gdxtensions.utils.WorldType;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader.Parameters;
import com.badlogic.gdx.utils.Disposable;

/**
 * Handles all the game assets
 * @authors Johan Fredin 
 */
public class Assets implements Disposable {

	public static boolean LOAD_SYNCHRONOUSLY = false;
	public static Assets assets;
	
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
	 */
	public void load() {
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void load(String name, Class clazz) {
		manager.load(name, clazz);
	}
	
	/**
	 * Unloads all the given assets
	 */
	public void unload(String... assets) {
		for(String asset : assets) {
			manager.unload(asset);
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
	 * Loads a tile map with the given index
	 * @param worldType the type of map to load (beach, grass etc)
	 * @param index the index of the map we want to load
	 * @param isTestLevel whether to retrieve the map from the test levels path or not
	 */
	public void loadTileMap(WorldType worldType, byte index, boolean isTestLevel) {
		manager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
	   
		Parameters p = new Parameters();
		p.textureMagFilter = TextureFilter.Nearest;
		p.textureMinFilter = TextureFilter.Nearest;
		
		String path = isTestLevel ? "levels/test/level-" : "levels/" + worldType.getDisplayName().toLowerCase() + "/level-";
		String finalPath = path + index + ".tmx";
		manager.load(finalPath, TiledMap.class, p);
		manager.finishLoading();
	}
	
	/**
	 * Unloads a tile map with the given index if the map is loaded
	 * @param worldType the type of map to load (beach, grass etc)
	 * @param index the index of the map we want to load
	 * @param isTestLevel whether to retrieve the map from the test levels path or not
	 */
	public void unloadTileMap(WorldType worldType, byte index, boolean isTestLevel) {
		String path = isTestLevel ? "levels/test/level-" : "levels/" + worldType.getDisplayName().toLowerCase() + "/level-";
		manager.unload(path + index + ".tmx");
	}
	
	
	
	/**
	 * Unloads a tile map loaded
	 * @param tmxFilePath the path to the tmxMap to unload
	 */
	public void unloadTileMap(String tmxFilePath) {
		manager.unload(tmxFilePath);
	}
	
	protected Assets() {
		manager = new AssetManager();
		if(LOAD_SYNCHRONOUSLY) {
			load();
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
	}
	
	
}