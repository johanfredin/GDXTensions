package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.screen.BaseScreen;
import se.fredin.gdxtensions.utils.ScreenType;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Level<T extends BaseScreen> implements LevelBase {

	protected OrthographicCamera camera;
	protected ShapeRenderer shapeRenderer;
	
	public Level(T screen) {
		this.camera = screen.getCamera();
		this.shapeRenderer = new ShapeRenderer();
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void switchLevel() {
		// TODO Auto-generated method stub

	}

	@Override
	public void restart(boolean allowedToShowAdd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void end(ScreenType screenToGoTo, boolean allowedToShowAdd) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tick(float deltatime) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(SpriteBatch batch, OrthographicCamera camera) {
		// TODO Auto-generated method stub

	}

}
