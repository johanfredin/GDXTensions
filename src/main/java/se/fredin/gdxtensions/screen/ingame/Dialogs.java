package se.fredin.gdxtensions.screen.ingame;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.scene2d.dialog.Dialog;
import se.fredin.gdxtensions.xml.XMLDialog;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Array;

public class Dialogs extends InGameScreen {

	private Array<Dialog> dialogs;
	private AnimatedBitmapFont font;
	
	public Dialogs(Array<XMLDialog> xmlDialogs, TextureAtlas atlas, AnimatedBitmapFont font, Color color) {
		super(atlas);
		this.font = font;
		this.dialogs = new Array<Dialog>();
		TextFieldStyle style = new TextFieldStyle(font, color, null, null, skin.getDrawable("bg"));
		this.populateFromXML(xmlDialogs, style);
	}
	
	public Dialogs(Array<XMLDialog> xmlDialogs, String pathToPackFile, String pathToFont, Color color) {
		super(pathToPackFile);
		this.font = new AnimatedBitmapFont(Gdx.files.internal(pathToFont));
		this.dialogs = new Array<Dialog>();
		TextFieldStyle style = new TextFieldStyle(font, color, null, null, skin.getDrawable("bg"));
		this.populateFromXML(xmlDialogs, style);
	}
	
	public void openDialog(int index) {
		Dialog dialog = this.dialogs.get(index);
		dialog.openDialog();
	}
	
	public void setPosition(Vector2 position) {
		for(Dialog dialog : dialogs) {
			dialog.setPosition(position.x, position.y);
		}
	}
	
	public void setPosition(float x, float y) {
		for(Dialog dialog : dialogs) {
			dialog.setPosition(x, y);
		}
	}
	
	
	
	public void startDialogs() {
		for(int i = 0; i < dialogs.size; i++) {
			Dialog dialog = dialogs.get(i);
			if(!dialog.isOpened()) {
				System.out.println("opening dialog and i=" + i);
				dialog.openDialog();
			}
		}
	}
	
	private void populateFromXML(Array<XMLDialog> xmlDialogs, TextFieldStyle style) {
		for(XMLDialog xmlDialog : xmlDialogs) {
			String text = xmlDialog.getText();
			float x = xmlDialog.getX();
			float y = xmlDialog.getY();
			Dialog dialog = new Dialog(text, x - 150, y, style);
			if(xmlDialog.isTimeLimited()) {
				dialog.setTimeToDisplay(xmlDialog.getTimeToDisplay());
			} if(xmlDialog.hasHeader()) {
				dialog.setHeader(xmlDialog.getHeader(true));
				dialog.addAdditionalLineBreaks((byte)2);
			}
			this.stage.addActor(dialog);
			this.dialogs.add(dialog);
		}
	}
	
}
