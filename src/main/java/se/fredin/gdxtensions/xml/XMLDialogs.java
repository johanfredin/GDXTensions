package se.fredin.gdxtensions.xml;

import com.badlogic.gdx.utils.Array;

public class XMLDialogs {
	
	private Array<XMLDialog> dialogElements;
	private float x;
	private float y;
	
	public XMLDialogs(Array<XMLDialog> xmlDialogs, float xPosition, float yPosition) {
		this.x = xPosition;
		this.y = yPosition;
		this.dialogElements = xmlDialogs;
	}
	
	public Array<XMLDialog> getDialogElements() {
		return dialogElements;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	

}
