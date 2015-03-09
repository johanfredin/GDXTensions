package se.fredin.gdxtensions.utils.logging;

import com.badlogic.gdx.Gdx;

/**
 * Used to simplify logging or being able to get formated logs without too much effort
 * Uses Gdx.app.log for logging 
 * @author Johan Fredin
 *
 */
public class LogUtils {
	
	/** Default tag to use as logging tag */
	public static final String TAG = "GDXTensions Log";
	
	/** 
	 * Logs to console a message.
	 * @param message message to log
	 */
	public static void log(String message) {
		Gdx.app.log(TAG, message);
	}
	
	/**
	 * Logs to the console a message and a value and appends an '=' sign in between
	 * ex. "Value is now" + "=" + value
	 * @param message the message you want to assign to assign to the value
	 * @param value the value
	 */
	public static void log(String message, Object value) {
		Gdx.app.log(TAG, message + "=" + value.toString());
	}
	
	/**
	 * Lets you log an arbitrary amount of messages and then {@link #log(String)} on each
	 * @param messages the messages you want to log
	 */
	public static void log(String... messages) {
		for(String message : messages) {
			log(message);
		}
	}
	
	/**
	 * Logs messages using {@link MVPair}, useful if you want to log a lot of data in one call
	 * @param msvPairs the {@link MVPair}s
	 */
	public static void log(MVPair... msvPairs) {
		for(MVPair msvPair : msvPairs) {
			String message = msvPair.message;
			String value = msvPair.value;
			log(message, value);
		}
	}
	
	
}
