package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.assetmanagement.Assets;
import se.fredin.gdxtensions.collision.CollisionHandler;
import se.fredin.gdxtensions.screen.BaseScreen;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/***
 * Extension of Level, used when we want to create a level that works with a tiled map
 * created in Tiled or any other editor supporting the .TMX format. contains a TiledMap and a
 * OrthogonalTiledMapRenderer for further functionality. Also comes equipped with a bunch of neat little 
 * helper methods for quickly retrieving map objects, properties and layers.
 * @author Johan Fredin
 *
 * @param <T> any class extending {@link BaseScreen}
 */
@SuppressWarnings("rawtypes")
public abstract class TiledMapLevel<T extends BaseScreen> extends Level {
	
	protected OrthogonalTiledMapRenderer mapRenderer;
	protected TiledMap map;
	protected CollisionHandler collisionHandler;
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
	 * @return the width of the map e.g the width * tile width of each tile
	 */
	public int getMapWidth() {
		return mapWidth;
	}
	
	/**
	 * @return the height of the map e.g the height * height of each tile
	 */
	public int getMapHeight() {
		return mapHeight;
	}
	
	/**
	 * @return the width of the tiles
	 */
	public int getTileWidth() {
		return tileWidth;
	}
	
	/**
	 * @return the height of the tiles
	 */
	public int getTileHeight() {
		return tileHeight;
	}
	
	/**
	 * Lets us retrieve a raw object type by class, users calling this method will need to cast
	 * @param objectLayerName
	 * @param type
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object getMapObjectLayerByType(String objectLayerName, Class type) {
		return map.getLayers().get(objectLayerName).getObjects().getByType(type);
	}
	
	/**
	 * Let's us get a finished {@link Rectangle} array from given {@link RectangleMapObject}s
	 * Very useful for getting rectangles for collision etc... right away. 
	 * @param objectLayerName the name of the object layer
	 * @return a new {@link Array} containing the map object layer
	 */
	public Array<Rectangle> getRectangularMapObjects(String objectLayerName) {
		Array<RectangleMapObject> rectangleMapObjects = map.getLayers().get(objectLayerName).getObjects().getByType(RectangleMapObject.class);
		Array<Rectangle> rectangles = new Array<Rectangle>();
		for(RectangleMapObject rectangleMapObject : rectangleMapObjects) {
			rectangles.add(rectangleMapObject.getRectangle());
		}
		return rectangles;
	}
	
	/**
	 * Lets us retrieve a map object.
	 * @param layerName the object layer we want to get the map object from
	 * @param objectName the name of the map object
	 * @return the map object with given name from object layer
	 */
	public MapObject getMapObject(String layerName, String objectName) {
		return map.getLayers().get(layerName).getObjects().get(objectName);
	}
	
	/**
	 * Lets us retrieve a {@link TiledMapTileLayer} from the map.
	 * @param layerName the name of the layer
	 * @return the layer
	 */
	public TiledMapTileLayer getLayer(String layerName) {
		return (TiledMapTileLayer) map.getLayers().get(layerName);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the Float property corresponding to given map object and property name
	 */
	public float getFloatProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, Float.class);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the Boolean property corresponding to given map object and property name
	 */
	public boolean getBooleanProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, Boolean.class);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the Integer property corresponding to given map object and property name
	 */
	public int getIntegerProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, Integer.class);
	}

	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the String property corresponding to given map object and property name
	 */
	public String getStringProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, String.class);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the positions from
	 * @param xPosition the property we want as x position
	 * @param yPosition the property we want as y position
	 * @return 
	 */
	public Vector2 getVector2(MapObject mapObject, String xPosition, String yPosition) {
		return new Vector2(getFloatProperty(mapObject, xPosition), getFloatProperty(mapObject, yPosition));
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
