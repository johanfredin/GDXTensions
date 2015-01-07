package se.fredin.gdxtensions.xml;

import com.badlogic.gdx.utils.Array;

/**
 * Simple class managing collections of {@link XMLDialog}s. This could have been a simple {@link Array} but the 
 * "level-dialogs" element from the xml could also hold a position attribute, if it does we will retrieve that position here.
 * That position will then be the same for each {@link XMLDialog} of the collection unless one of those dialogs would itself hold a position.
 * @author Johan Fredin
 *
 */
public class XMLDialogs extends XMLCommons {
	
	private Array<XMLDialog> dialogElements;
	
	/**
	 * Constructs a new {@link XMLDialogs} object
	 * @param dialogElements the {@link Array} of {@link XMLDialog} objects that we will work with.
	 * @param xPosition the x position retrieved from the xml (will be -1 if none is found)
	 * @param yPosition the y position retrieved from the xml (will be -1 if none is found)
	 */
	public XMLDialogs(Array<XMLDialog> dialogElements, float xPosition, float yPosition) {
		this.x = xPosition;
		this.y = yPosition;
		this.dialogElements = dialogElements;
	}
	
	/**
	 * @return the {@link XMLDialog} elements
	 */
	public Array<XMLDialog> getDialogElements() {
		return dialogElements;
	}

}
