package se.fredin.gdxtensions.xml;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

public class XMLDialog {

	private String header;
	private String text;
	private float timeToDisplay;
	private float x;
	private float y;
	
	public XMLDialog(Element element) {
		this.header = element.getAttribute("header", null);
		this.timeToDisplay = element.getFloatAttribute("timeToDisplay", 0.0f);
		this.text = element.getText();
		this.x = getPosOrNegativeOneValue(element, 'x');
		this.y = getPosOrNegativeOneValue(element, 'y');
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
	
	public boolean hasPositionSet() {
		return this.x > -1.0f && this.y > -1.0f;
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
	
	private float getPosOrNegativeOneValue(Element element, char position) {
		try {
			String posAttributes[] = element.getAttribute("pos").split(",");
			switch(position) {
			case 'x':
				return Float.parseFloat(posAttributes[0]);
			case 'y':
				return Float.parseFloat(posAttributes[1]);
			}
		} catch(GdxRuntimeException ex) {
			System.err.println("Element " + element.getName() + " does not have the attribute pos");
		}
		return -1f;
	}
	
}
