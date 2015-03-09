package se.fredin.gdxtensions.utils.logging;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 * Used for logging directly to to the screen.
 * The console is usually sufficient for logging 
 * what is going on. But sometimes you want constant logging
 * that does not add a new line constantly which can be
 * really hard to follow if you want to catch a special event.
 * This class uses a simple {@link BitmapFont} to log directly to
 * the screen. Users can set color and position.
 * @author Johan Fredin
 *
 */
public class OnScreenLogUtils {
	
	/** The {@link BitmapFont} that will be used as the text font, default is an empty font **/
	public static BitmapFont font = new BitmapFont();
	/** the {@link Color} the font will use, default is red **/
	public static Color color = Color.RED;
	
	/**
	 * Logs a multiline message to the screen, no newline character is required.
	 * @param batch the {@link SpriteBatch} used for rendering
	 * @param x the x position of the font
	 * @param y the y position of the font
	 * @param logs an arbitrary amount of messages to log, a new line will be inserted between the logs
	 */
	public static void log(SpriteBatch batch ,float x, float y, String... logs) {
		log(batch, x, y, color, logs);
	}
	
	/**
	 * Logs a multiline message to the screen, no newline character is required.
	 * @param batch the {@link SpriteBatch} used for rendering
	 * @param position the position of the font
	 * @param logs an arbitrary amount of messages to log, a new line will be inserted between the logs
	 */
	public static void log(SpriteBatch batch, Vector2 position, String... logs) {
		log(batch, position.x, position.y, color, logs);
	}
	
	/**
	 * Logs a multiline message to the screen, no newline character is required.
	 * @param batch the {@link SpriteBatch} used for rendering
	 * @param position the position of the font
	 * @param color the {@link Color} of the font
	 * @param logs an arbitrary amount of messages to log, a new line will be inserted between the logs
	 */
	public static void log(SpriteBatch batch, Vector2 position, Color color, String... logs) {
		log(batch, position.x, position.y, color, logs);
	}
	
	/**
	 * Logs a multiline message to the screen, no newline character is required.
	 * @param batch the {@link SpriteBatch} used for rendering
	 * @param x the x position of the font
	 * @param y the y position of the font
	 * @param color the {@link Color} of the font
	 * @param logs an arbitrary amount of messages to log, a new line will be inserted between the logs
	 */
	public static void log(SpriteBatch batch, float x, float y, Color color, String... logs) {
		String finalLog = "";
		font.setColor(color);
		for(String log : logs) {
			finalLog += log + "\n";
		}
		font.drawMultiLine(batch, finalLog, x, y);
	}
}
