package se.fredin.gdxtensions.utils.logging;

/**
 * A very basic helper key value pair class but with only Strings
 * Main purpose is to use it for logging combined with {@link LogUtils}
 * @author Johan Fredin
 *
 */
public class MVPair {

	/** The message or key */
	public String message;
	/** The value associated with the key */
	public String value;
	
	/**
	 * Create a new MVPair with a message and a value
	 * @param message
	 * @param value
	 */
	public MVPair(String message, Object value) {
		this.message = message;
		this.value = value.toString();
	}
	
}
