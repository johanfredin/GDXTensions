package se.fredin.gdxtensions.object;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * General methods that all Game Objects should have
 * @author Niklas Istenes, Johan Fredin
 *
 */
interface GameObjectBase {

	/**
	 * Renders to the screen
	 * @param batch the sprite batch responsible for drawing
	 */
	void render(SpriteBatch batch);

	/**
	 * Updates game state
	 * @param deltaTime the time interval since the last rendering occurred 
	 * @param player the player object to check for collision (used by the enemy objects etc)
	 */
	void tick(float deltaTime, GameObject gameObject);

}