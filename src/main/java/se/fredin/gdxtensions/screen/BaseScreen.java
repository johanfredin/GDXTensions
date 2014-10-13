package se.fredin.gdxtensions.screen;

import se.fredin.gdxtensions.assetmanagement.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;

/**
 * BaseScreen holds general objects that each Screen object in libGDX usually requires,
 * a camera, spritebatch and a game object. It also contains static fields for default 
 * viewport width and height. 
 * @author Johan Fredin
 *
 */
public abstract class BaseScreen implements Screen, Disposable {
	
	/** Default viewport width for the camera */
	public static short viewportWidth = 640;
	/** Default viewport height for the camera */
	public static short viewportHeight = 360;
	
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	protected Game game;
	
	/**
	 * Instantiates the base screen and the objects 
	 * @param game our game instance responsible for switching screens
	 */
	public BaseScreen(Game game) {
		this.game = game;
		this.batch = new SpriteBatch();
		this.camera = new OrthographicCamera(viewportWidth, viewportHeight);
		this.camera.position.set(viewportWidth / 2, viewportHeight / 2, 0);
		this.camera.update();
	}
	
	/**
	 * Set the camera y to be top or bottom left
	 * @param yDown if <b>true</b> the camera y will start at the bottom left corner
	 */
	public void setCameraYDown(boolean yDown) {
		this.camera.setToOrtho(yDown);
	}
	
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}
	
	@Override
	public void resize(int width, int height) {
        Vector2 size = Scaling.fillY.apply(width, height, width, viewportHeight);
        camera.setToOrtho(false, size.x, size.y);
		camera.update();
	}
	
	@Override
	public void dispose() {
		Gdx.app.log(this.getClass().getSimpleName(), "dispose called");
	}
	
	@Override
	public void show() {
		Gdx.app.log(this.getClass().getSimpleName(), "show called");
	}

	@Override
	public void hide() {
		Gdx.app.log(this.getClass().getSimpleName(), "hide called");
	}
	
	@Override
	public void pause() {
		Gdx.app.log(this.getClass().getSimpleName(), "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log(this.getClass().getSimpleName(), "resume called");
		Assets.getInstance().finishLoading();
	}

}
