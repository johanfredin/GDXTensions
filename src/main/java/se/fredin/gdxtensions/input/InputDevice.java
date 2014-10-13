package se.fredin.gdxtensions.input;

/**
 * Helper enum to decide what kind of input to use, Desktop have the option
 * to be either keyboard or gamepad while android and iOS only have the touchpad
 * option.
 * @author Johan Fredin
 *
 */
public enum InputDevice {
	
	/** Indicating we are going to use the keyboard for input */
	KEYBOARD,
	/** Indicating we are going to use a gamepad (Xbox360, PS3 etc...) for input */
	GAMEPAD,
	/** Indicating we are going to use a touchpad for input (Android, iOS) */
	TOUCHPAD;

}
