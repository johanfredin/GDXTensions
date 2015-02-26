package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.Gdx;

/**
 * Helper for common game settings as well as debug options
 * @author Johan Fredin
 *
 */
public class Settings {
	
	/** If true we will use the levels from our test directory */
	public static boolean USE_TEST_LEVELS = false;
	
	/** The speed of the game */
	public static float GAMESPEED = 1.0f;
	
	/** This is the resolution limit (width * height) for deciding if device is low end or not */
	private static int LOW_END_LIMIT = 480 * 320;
	
	/** Renders bounding boxes and makes the cow fly */
	public static boolean FREE_FLYING_MODE = false;
	
	/** Renders the bounding boxes but wont let us fly */
	public static boolean SHOULD_RENDER_BOUNDING_BOXES = false;
	
	/** Makes the cow invincible */
	public static boolean GOD_MODE = true;

	/** Mutes all sounds and music */
	public static boolean MUTE_ALL = true;

	/** Lets us start the game without the load screens */
	public static boolean DEVELOPER_MODE = true;
	
	/** Used to find out what brand we are running */
	public static String androidBrandName;
	
	public static float defaultProjectileSpeed = 5f;
	
	/**
	 * Used to determine if we are running on a low end device.
	 * We set the limit for low end to be 480 * 320 in width and height.
	 * @return
	 */
	public static boolean isLowEndDevice() {
		return Gdx.graphics.getWidth() * Gdx.graphics.getHeight() <= LOW_END_LIMIT;
	}
	
	/**
	 * Sony devices have a weird way of loosing texture content. We need to approach these devices differently
	 * @return
	 */
	public static boolean isSonyDevice() {
		return androidBrandName != null && androidBrandName.equalsIgnoreCase("Sony");
	}
	

}
