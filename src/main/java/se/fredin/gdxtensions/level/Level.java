package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.screen.BaseScreen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Very general superclass for any kind of game level, contains a camera and a shaperenderer
 * @author Johan Fredin
 *
 * @param <T> any class that extends the BaseScreen
 */
public abstract class Level<T extends BaseScreen> implements LevelBase {

	protected OrthographicCamera camera;
	protected ShapeRenderer shapeRenderer;
	
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

}
