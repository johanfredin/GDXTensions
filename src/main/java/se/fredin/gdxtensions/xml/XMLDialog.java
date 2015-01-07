package se.fredin.gdxtensions.xml;

import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * This class corresponds to the "dialog" elements of the xml.  
 * @author burtburgenstein
 *
 */
public class XMLDialog extends XMLCommons {

	private String header;
	private String text;
	private float timeToDisplay;
	
	/**
	 * Create a new {@link XMLDialog}
	 * @param element the {@link Element} from the xml, in this case it will be the dialog element
	 */
	public XMLDialog(Element element) {
		super(element);
		this.header = element.getAttribute("header", null);
		this.timeToDisplay = element.getFloatAttribute("timeToDisplay", 0.0f);
		this.text = element.getText();
	}
	
	/**
	 * @return the time this dialog should be played (if 0 or less this dialog will not be time limited)
	 */
	public float getTimeToDisplay() {
		return timeToDisplay;
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
	
	/**
	 * @return the text from the element
	 */
	public String getText() {
		return text;
	}
	
	/**
	 * @return whether or not this text is time limited
	 */
	public boolean isTimeLimited() {
		return timeToDisplay > 0;
	}
	
	/**
	 * @return whether or not this element has a header attribute
	 */
	public boolean hasHeader() {
		return header != null;
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
