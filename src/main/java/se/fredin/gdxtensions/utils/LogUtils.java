package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.Gdx;

public class LogUtils {
	
	public static final String TAG = "GDXTensions Log";
	
	public static void log(String message) {
		Gdx.app.log(TAG, message);
	}
	
	public static void log(String message, String value) {
		Gdx.app.log(TAG, message + "=" + value);
	}
	
	public static void log(String... messages) {
		for(String message : messages) {
			log(message);
		}
	}
	
	public static void log(MVPair... msvPairs) {
		for(MVPair msvPair : msvPairs) {
			String message = msvPair.message;
			String value = msvPair.value;
			log(message, value);
		}
	}
	
}
