package se.fredin.gdxtensions.screen;

import se.fredin.gdxtensions.res.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;

public abstract class BaseScreen implements Screen, Disposable {
	
	public static short VIEWPORT_WIDTH = 400;
	public static short VIEWPORT_HEIGHT = 240;
	
	protected OrthographicCamera camera;
	protected SpriteBatch batch;
	protected Game game;
	
	public BaseScreen(Game game) {
		this.game = game;
		this.init();
	}
	
	public OrthographicCamera getCamera() {
		return this.camera;
	}
	
	public SpriteBatch getSpriteBatch() {
		return this.batch;
	}
	
	public void init() {
		batch = new SpriteBatch();
		camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
		camera.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
		camera.update();
	}
		
	@Override
	public void resize(int width, int height) {
        float fixedHeight = 240f; // your preferred viewHeight
        //very useful and easy function to get preferred width and height and still keeping the same aspect ratio :)
        Vector2 size = Scaling.fillY.apply(width, height, width, fixedHeight);

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
