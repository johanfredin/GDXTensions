package se.fredin.gdxtensions.xml;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * This class takes an xml file that will contain all dialogs for a level and parses it into a {@link Map}. Please refer to the documentation of
 * how this xmlfile needs to be built or check out the existing "examples.xml" file. Parsing is made using libGDX's {@link XmlReader} and {@link Element}
 * @author burtburgenstein
 *
 */
public class DialogXMLParser {

	private final String LEVEL_DIALOGS = "level-dialogs";
	private final String KEY = "key";
	
	private Map<String, XMLDialogs> dialogElements;
	
	/**
	 * Constructs a new {@link DialogXMLParser} passing in the path to the xml file that should be parsed
	 * @param path the path to the xmlfile that should be parsed
	 */
	public DialogXMLParser(String path) {
		this.dialogElements = new HashMap<String, XMLDialogs>();
		XmlReader reader = new XmlReader();
		Element xmlFile = null;
		try {
			xmlFile = reader.parse(Gdx.files.internal(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Element levelDialogsElement = xmlFile.getChildByName(LEVEL_DIALOGS);
		for(short i = 0; i < levelDialogsElement.getChildCount(); i++) {
			Element levelDialogElement = levelDialogsElement.getChild(i);
			float xPosition = getPosOrNegativeOneValue(levelDialogElement, 'x');
			float yPosition = getPosOrNegativeOneValue(levelDialogElement, 'y');
			String key = levelDialogElement.getAttribute(KEY);
			Array<XMLDialog> xmlDialogs = new Array<XMLDialog>();
			for(short j = 0; j < levelDialogElement.getChildCount(); j++) {
				Element dialogElement = levelDialogElement.getChild(j);
				XMLDialog xmlDialog = new XMLDialog(dialogElement);
				xmlDialogs.add(xmlDialog);
			}
			this.dialogElements.put(key, new XMLDialogs(xmlDialogs, xPosition, yPosition));
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String key : dialogElements.keySet()) {
			sb.append("=============================================\n");
			sb.append("XMLDialog added!\n");
			sb.append("key=" + key + "\n");
			XMLDialogs xmlDialogs = dialogElements.get(key);
			for(XMLDialog xmlDialog : xmlDialogs.getDialogElements()) {
				sb.append("header=" + xmlDialog.getHeader(false) + "\n");
				sb.append("timeToDisplay=" + xmlDialog.getTimeToDisplay() + "\n");
				sb.append("text=" + xmlDialog.getText() + "\n------------------------------\n");
			}
		}
		return sb.toString();
	}
	
	/**
	 * Retrieve the {@link XMLDialogs} passing in the corresponding key
	 * @param key the key to this xml dialogs
	 * @return the {@link XMLDialogs} 
	 */
	public XMLDialogs getXMLDialog(String key) {
		return dialogElements.get(key);
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
