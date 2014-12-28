package se.fredin.gdxtensions.font.xmldialog;

import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XMLDialog {

	private float timeToDisplay;
	private String header;
	private String text;
	
	public XMLDialog(Element element) {
		this.header = getNameAttributeIfAvailable(element);
		this.timeToDisplay = getTimeToDisplayIfAvailable(element);
		this.text = element.getText();
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
	
	public String getHeader() {
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
