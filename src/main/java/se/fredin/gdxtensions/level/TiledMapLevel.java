package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.res.Assets;
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
public abstract class TiledMapLevel<T extends BaseScreen> extends Level implements LevelBase {
	
	protected OrthogonalTiledMapRenderer mapRenderer;
	protected TiledMap map;
	
	
	@SuppressWarnings("unchecked")
	public TiledMapLevel(String levelName, T screen) {
		super(screen);
		this.map = (TiledMap) Assets.getInstance().get(levelName);
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
	
	@Override
	public void dispose() {
		super.dispose();
		mapRenderer.dispose();
	}

}
