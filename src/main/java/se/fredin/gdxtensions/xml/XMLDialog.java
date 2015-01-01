package se.fredin.gdxtensions.xml;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XMLDialog {

	private float timeToDisplay;
	private String header;
	private String text;
	private float x;
	private float y;
	
	public XMLDialog(Element element) {
		this.header = getNameAttributeIfAvailable(element);
		this.timeToDisplay = getTimeToDisplayIfAvailable(element);
		this.text = element.getText();
		this.x = element.getFloat("x", 300.0f);
		this.y = element.getFloat("y", 200.0f);
	}
	
	private String getNameAttributeIfAvailable(Element element) {
		try {
			return element.getAttribute("header");
		} catch(GdxRuntimeException ex) {
			//TODO: Replace hardcoded strings with variables
			System.err.println("No attribute with name=" + "header");
		}
		return null;
	}
	
	private float getTimeToDisplayIfAvailable(Element element) {
		try {
			return element.getFloatAttribute("timeToDisplay");
		} catch(GdxRuntimeException ex) {
			//TODO: Replace hardcoded strings with variables
			System.err.println("No attribute with name=" + "timeToDisplay");
		} catch(NumberFormatException ex) {
			System.err.println("Could not parse attribute timeToDisplay " + ex.getMessage());
		}
		return 0.0f;
	}
	
	public void setTimeToDisplay(float timeToDisplay) {
		this.timeToDisplay = timeToDisplay;
	}
	
	public float getTimeToDisplay() {
		return timeToDisplay;
	}
	
	public void setHeader(String name) {
		this.header = name;
	}
	
	/**
	 * Get the header
	 * @param includeDottedLine if true a dotted underline will be included 
	 * @return
	 */
	public String getHeader(boolean includeDottedLine) {
		if(includeDottedLine) {
			char[] dottedLine = new char[header.length()];
			for(byte i = 0; i < dottedLine.length; i++) {
				dottedLine[i] = '-';
			}
			String underline = new String(dottedLine);
			return header + "\n" + underline + "\n";
		}
		return header;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public boolean isTimeLimited() {
		return timeToDisplay > 0;
	}
	
	public boolean hasHeader() {
		return header != null;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
	
	/**
	 * Gives us an array including the header (if any) and the text.
	 * If no header is given it will simply return an array only containing the one index e.g the text
	 * @param includeLineChars if true will include a dotted underline after the header eg. Hero ----- text
	 * @return 
	 */
	public String[] getDialogAndHeader(boolean includeLineChars) {
		if(header != null) {
			if(includeLineChars) {
				return new String[]{header, "-----------", text};
			}
			return new String[]{header, text};
		}
		return new String[]{text};
	}
	
	@Override
	public String toString() {
		return "Name=" + header + "\nText=" + text + "\ntime=" + timeToDisplay;
	}
	
}
