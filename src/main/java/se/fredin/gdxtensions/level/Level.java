package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.screen.BaseScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Very general superclass for any kind of game level, contains a camera and a shaperenderer
 * @author Johan Fredin
 *
 * @param <T> any class that extends the BaseScreen
 */
public abstract class Level<T extends BaseScreen> implements LevelBase {

	protected OrthographicCamera camera;
	protected ShapeRenderer shapeRenderer;
	protected float zoomLevel = 1.0f;
	
	/**
	 * Instantiate the camera and shaperenderer
	 * @param screen the screen where we can retrieve further objects and switch levels with.
	 */
	public Level(T screen) {
		this.camera = screen.getCamera();
		this.shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void dispose() {
		shapeRenderer.dispose();
	}
	
	public void moveCamera(Vector2 position, int mapWidth, int mapHeight) {
		camera.zoom = zoomLevel;
		float centerX = (camera.viewportWidth * zoomLevel ) / 2;
		float centerY = (camera.viewportHeight * zoomLevel) / 2;
		camera.position.set(position, 0);
		
		if(camera.position.x - centerX <= 0) 
			camera.position.x = centerX;
		if(camera.position.x + centerX >= mapWidth)
			camera.position.x = mapWidth - centerX;
		if(camera.position.y - centerY <= 0)
			camera.position.y = centerY;
		if(camera.position.y + centerY >= mapHeight)
			camera.position.y = mapHeight - centerY;
		
		camera.update();
	}
	
	public void moveCamera(Vector2 position) {
		camera.zoom = zoomLevel;
		camera.position.set(position, 0);
		camera.update();
	}

}
