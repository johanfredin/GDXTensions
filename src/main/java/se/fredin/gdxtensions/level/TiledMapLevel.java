package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.res.Assets;
import se.fredin.gdxtensions.screen.BaseScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

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
	
	public OrthographicCamera getCamera() {
		return camera;
	}

	@SuppressWarnings("unchecked")
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
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
	
	@SuppressWarnings("unchecked")
	public void setShapeRenderer(ShapeRenderer shapeRenderer) {
		this.shapeRenderer = shapeRenderer;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return shapeRenderer;
	}

	@Override
	public void dispose() {
		mapRenderer.dispose();
	}

}
