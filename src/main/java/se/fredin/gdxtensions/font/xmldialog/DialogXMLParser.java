package se.fredin.gdxtensions.font.xmldialog;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class DialogXMLParser {

	private final String LEVEL_DIALOGS = "level-dialogs";
	private final String KEY = "key";
	
	private Map<String, Array<XMLDialog>> dialogElements;
	
	public DialogXMLParser(String path) {
		this.dialogElements = new HashMap<String, Array<XMLDialog>>();
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
			String key = levelDialogElement.getAttribute(KEY);
			Array<XMLDialog> xmlDialogs = new Array<XMLDialog>();
			for(short j = 0; j < levelDialogElement.getChildCount(); j++) {
				Element dialogElement = levelDialogElement.getChild(j);
				XMLDialog xmlDialog = new XMLDialog(dialogElement);
				xmlDialogs.add(xmlDialog);
			}
			this.dialogElements.put(key, xmlDialogs);
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(String key : dialogElements.keySet()) {
			sb.append("=============================================\n");
			sb.append("XMLDialog added!\n");
			sb.append("key=" + key + "\n");
			Array<XMLDialog> xmlDialogs = dialogElements.get(key);
			for(XMLDialog xmlDialog : xmlDialogs) {
				sb.append("header=" + xmlDialog.getHeader() + "\n");
				sb.append("timeToDisplay=" + xmlDialog.getTimeToDisplay() + "\n");
				sb.append("text=" + xmlDialog.getText() + "\n------------------------------\n");
			}
		}
		return sb.toString();
	}
	
	public Array<XMLDialog> getXMLDialog(String key) {
		return dialogElements.get(key);
	}
	
	
}
