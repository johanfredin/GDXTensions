package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * The {@link TiledMapUtils} class comes equipped with a whole bunch of helpful
 * methods for retrieving the most common as well as custom properties, layers, objects
 * and positions. 
 * @author Johan Fredin
 *
 */
public class TiledMapUtils {
	
	private TiledMap map;
	
	/**
	 * Construct a new {@link TiledMapUtils} instance
	 * users will need to set which {@link TiledMap} to use 
	 * manually otherwise you will get {@link NullPointerException}s when
	 * trying to call any of the methods
	 */
	public TiledMapUtils(){}
	
	/**
	 * Construct a new {@link TiledMapUtils} instance.
	 * @param map the {@link TiledMap} to operate on
	 */
	public TiledMapUtils(TiledMap map) {
		this.map = map;
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
		return getIntegerMapProperty("width") * getIntegerMapProperty("tilewidth");
	}
	
	/**
	 * @return the height of the map e.g the height * tile height of each tile
	 */
	public int getMapHeight() {
		return getIntegerMapProperty("height") * getIntegerMapProperty("tileheight");
	}
	
	/**
	 * @return the width of the tiles
	 */
	public int getTileWidth() {
		return getIntegerMapProperty("tilewidth");
	}
	
	/**
	 * @return the height of the tiles
	 */
	public int getTileHeight() {
		return getIntegerMapProperty("tileheight");
	}
	
	/**
	 * Lets us retrieve a map object layer, could be rectangles used for detecting collision
	 * or other custom triggers. Returns an {@link Object} so users calling this method will
	 * need to cast it to the appropriate type.
	 * @param objectLayerName the name of the object layer
	 * @return the {@link Object} associated with the object layer name passed in
	 */
	public Object getMapObjectLayerByType(String objectLayerName) {
		return map.getLayers().get(objectLayerName).getObjects();
	}
	
	/**
	 * Lets us retrieve a map property like tile width, map width or custom properties
	 * like a time limit for example. This method return an {@link Object} so users calling
	 * this method will need to cast it to appropriate type.
	 * @param propertyName the name of the map property.
	 * @return the {@link Object} associated with the propertyName passed in
	 */
	public Object getMapProperty(String propertyName) {
		return map.getProperties().get(propertyName);
	}
	
	/**
	 * Let's us get a finished {@link Rectangle} array from given {@link RectangleMapObject}s
	 * Very useful for getting rectangles for collision etc... right away. 
	 * @param objectLayerName the name of the object layer
	 * @return a new {@link Array} containing the map object layer
	 */
	public Array<Rectangle> getRectangularMapObjects(String objectLayerName) {
		Array<RectangleMapObject> rectangleMapObjects = map.getLayers().get(objectLayerName).getObjects().getByType(RectangleMapObject.class);
		Array<Rectangle> rectangles = new Array<>();
		for(RectangleMapObject rectangleMapObject : rectangleMapObjects) {
			rectangles.add(rectangleMapObject.getRectangle());
		}
		return rectangles;
	}
	
	/**
	 * Lets us retrieve a map object.
	 * @param objectLayerName the object layer we want to get the map object from
	 * @param objectName the name of the map object
	 * @return the map object with given name from object layer
	 */
	public MapObject getMapObject(String objectLayerName, String objectName) {
		return map.getLayers().get(objectLayerName).getObjects().get(objectName);
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
	public float getFloatMapObjectProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, Float.class);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the Boolean property corresponding to given map object and property name
	 */
	public boolean getBooleanMapObjectProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, Boolean.class);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the Integer property corresponding to given map object and property name
	 */
	public int getIntegerMapObjectProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, Integer.class);
	}

	/**
	 * @param mapObject the {@link MapObject} we need the property from
	 * @param propertyName the name of the property
	 * @return the String property corresponding to given map object and property name
	 */
	public String getStringMapObjectProperty(MapObject mapObject, String propertyName) {
		return mapObject.getProperties().get(propertyName, String.class);
	}
	
	/**
	 * @param propertyName the name of the property e.g the key
	 * @return the {@link String} property corresponding to the key passed in
	 */
	public String getStringMapProperty(String propertyName) {
		return map.getProperties().get(propertyName, String.class);
	}
	
	/**
	 * @param propertyName the name of the property e.g the key
	 * @return the {@link Float} property corresponding to the key passed in
	 */
	public float getFloatMapProperty(String propertyName) {
		return map.getProperties().get(propertyName, Float.class);
	}
	
	/**
	 * @param propertyName the name of the property e.g the key
	 * @return the {@link Integer} property corresponding to the key passed in
	 */
	public int getIntegerMapProperty(String propertyName) {
		return map.getProperties().get(propertyName, Integer.class);
	}
	
	/**
	 * @param propertyName the name of the property e.g the key
	 * @return the {@link Boolean} property corresponding to the key passed in
	 */
	public boolean getBooleanMapProperty(String propertyName) {
		return map.getProperties().get(propertyName, Boolean.class);
	}
	
	/**
	 * @param mapObject the {@link MapObject} we need the positions from
	 * @param xPosition the property we want as x position
	 * @param yPosition the property we want as y position
	 * @return 
	 */
	public Vector2 getVector2(MapObject mapObject, String xPosition, String yPosition) {
		return new Vector2(getFloatMapObjectProperty(mapObject, xPosition), getFloatMapObjectProperty(mapObject, yPosition));
	}

}
