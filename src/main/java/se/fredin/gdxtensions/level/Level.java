package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.input.BaseInput;
import se.fredin.gdxtensions.screen.BaseScreen;
import se.fredin.gdxtensions.utils.GameCamera;

import com.badlogic.gdx.InputMultiplexer;
/**
 * Very general superclass for any kind of game level, contains a camera and a shaperenderer as well as some methods for 
 * moving the camera.
 * @author Johan Fredin
 *
 * @param <T> any class that extends the BaseScreen
 */
public abstract class Level<T extends BaseScreen> implements LevelBase {

	protected GameCamera camera;
	protected BaseInput baseInput;
	protected InputMultiplexer inputMultiplexer;
	
	/**
	 * Instantiate the camera and shaperenderer
	 * @param screen the screen where we can retrieve further objects and switch levels with.
	 */
	public Level(T screen) {
		this.camera = screen.getCamera();
		this.inputMultiplexer = new InputMultiplexer();
		// TODO: Fix to be relevant to what device is being played on
		this.baseInput = new BaseInput();
	}
	
	public void setBaseInput(BaseInput baseInput) {
		this.baseInput = baseInput;
	}
	
	public BaseInput getBaseInput() {
		return baseInput;
	}
	
	public void setCamera(GameCamera camera) {
		this.camera = camera;
	}
	
	public GameCamera getCamera() {
		return camera;
	}
	
	public void setInputMultiplexer(InputMultiplexer inputMultiplexer) {
		this.inputMultiplexer = inputMultiplexer;
	}
	
	public InputMultiplexer getInputMultiplexer() {
		return inputMultiplexer;
	}
	
}
