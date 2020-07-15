package se.fredin.gdxtensions.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * General methods that all Game Objects should have
 * @author Niklas Istenes, Johan Fredin
 *
 */
public interface GameObjectBase extends Disposable{

	/**
	 * Renders to the screen
	 * @param batch the sprite batch responsible for drawing
	 */
	void render(SpriteBatch batch);

	/**
	 * Updates the state of the game object
	 * @param deltaTime the time interval since the last rendering occurred 
	 */
	void tick(float deltaTime);
	
	@Override
	void dispose();

}
