package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.assetmanagement.Assets;
import se.fredin.gdxtensions.screen.BaseScreen;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/***
 * Extension of Level, used when we want to create a level that works with a tiled map
 * created in Tiled or any other editor supporting the .TMX format. contains a TiledMap and a
 * OrthogonalTiledMapRenderer for further functionality
 * @author johan
 *
 * @param <T>
 */
@SuppressWarnings("rawtypes")
public abstract class TiledMapLevel<T extends BaseScreen> extends Level {
	
	protected OrthogonalTiledMapRenderer mapRenderer;
	protected TiledMap map;
	protected int mapWidth;
	protected int mapHeight;
	protected int tileWidth;
	protected int tileHeight;
	
	@SuppressWarnings("unchecked")
	public TiledMapLevel(String levelName, T screen) {
		super(screen);
		this.map = (TiledMap) Assets.getInstance().get(levelName);
		this.tileWidth = map.getProperties().get("tilewidth", Integer.class);
		this.tileHeight = map.getProperties().get("tileheight", Integer.class);
		this.mapWidth = map.getProperties().get("width", Integer.class) * tileWidth;
		this.mapHeight = map.getProperties().get("height", Integer.class) * tileHeight;
		this.mapRenderer = new OrthogonalTiledMapRenderer(map, screen.getSpriteBatch());
	}
	
	public void setMapRenderer(OrthogonalTiledMapRenderer mapRenderer) {
		this.mapRenderer = mapRenderer;
	}
	
	public OrthogonalTiledMapRenderer getMapRenderer() {
		return mapRenderer;
	}

	public TiledMap getMap() {
		return map;
	}

	public void setMap(TiledMap map) {
		this.map = map;
	}
	
	public int getMapWidth() {
		return mapWidth;
	}
	
	public int getMapHeight() {
		return mapHeight;
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		mapRenderer.dispose();
	}

}
