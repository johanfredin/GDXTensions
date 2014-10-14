package se.fredin.gdxtensions.level;

import se.fredin.gdxtensions.utils.ScreenType;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Collection of methods that a level must have
 * @author Johan Fredin
 *
 */
public interface LevelBase extends Disposable {
	
	/**
	 * Used to switch level
	 */
	void switchLevel();

	/**
	 * Used to restart the level
	 * @param allowedToShowAdd if we restart from death or pause screen we don't want to display adds
	 */
	void restart(boolean allowedToShowAdd);

	/**
	 * Used to end the level
	 * @param screenToGoTo the screen we wish to go to
	 * @param allowedToShowAdd if we restart from death or pause screen we don't want to display adds
	 */
	void end(ScreenType screenToGoTo, boolean allowedToShowAdd);

	/**
	 * Update the level state
	 * @param deltatime the time interval since last rendering occurred
	 */
	void tick(float deltatime);

	/**
	 * Renders to the screen
	 * @param batch
	 * @param camera
	 */
	void render(SpriteBatch batch, OrthographicCamera camera);
	
}