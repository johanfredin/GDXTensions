package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.assetmanagement.Assets;
import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.screen.BaseScreen;
import se.fredin.gdxtensions.utils.TiledMapUtils;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/***
 * Used when we want to create a {@link Level} that works with a tiled map
 * created in Tiled or any other editor supporting the .TMX format. Contains a {@link TiledMap}, 
 * {@link OrthogonalTiledMapRenderer} and a {@link CollisionHandler}. Also comes equipped with
 * an instance of {@link TiledMapUtils} that comes with a bunch of 
 * helper methods for quickly retrieving map objects, properties and layers.
 * @author Johan Fredin
 *
 * @param <T> any class extending {@link BaseScreen}
 */
@SuppressWarnings("rawtypes")
public abstract class TiledMapLevel<T extends BaseScreen> extends Level {
	
	protected OrthogonalTiledMapRenderer mapRenderer;
	protected TiledMap map;
	protected TiledMapUtils tiledMapUtils;
	protected CollisionHandler collisionHandler;
	protected int mapWidth;
	protected int mapHeight;
	protected int tileWidth;
	protected int tileHeight;
	
	/**
	 * Construct a new {@link TiledMapLevel}
	 * @param levelName the path and name of the .tmx map
	 * @param screen the {@link Screen} that will render this level
	 */
	@SuppressWarnings("unchecked")
	public TiledMapLevel(String levelName, T screen) {
		super(screen);
		this.map = (TiledMap) Assets.getInstance().get(levelName);
		this.tiledMapUtils = new TiledMapUtils(this.map);
		this.tileWidth = this.tiledMapUtils.getTileWidth();
		this.tileHeight = this.tiledMapUtils.getTileHeight();
		this.mapWidth = this.tiledMapUtils.getMapWidth();
		this.mapHeight = this.tiledMapUtils.getMapHeight();
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.map, screen.getSpriteBatch());
	}
	
	/**
	 * Set the {@link OrthogonalTiledMapRenderer} to use
	 * @param mapRenderer the renderer to use
	 */
	public void setMapRenderer(OrthogonalTiledMapRenderer mapRenderer) {
		this.mapRenderer = mapRenderer;
	}
	
	/**
	 * @return the maprenderer
	 */
	public OrthogonalTiledMapRenderer getMapRenderer() {
		return mapRenderer;
	}

	/**
	 * @return the tiled map used
	 */
	public TiledMap getMap() {
		return map;
	}
	
	/**
	 * Set what {@link TiledMap} to use
	 * @param map the map to use
	 */
	public void setMap(TiledMap map) {
		this.map = map;
	}
	
	/**
	 * @return the {@link TiledMapUtils} instance
	 */
	public TiledMapUtils getTiledMapUtils() {
		return tiledMapUtils;
	}
	
	@Override
	public void dispose() {
		mapRenderer.dispose();
	}
	
	@Override
	public String toString() {
		return "TiledMapLevel\n" +
			   "============= \n" +
			   "Map width=" + mapWidth + "\n" +
			   "Map height=" + mapHeight + "\n" +
			   "Tile width=" + tileWidth + "\n" +
			   "Tile height=" + tileHeight + "\n" ;
	}

}
