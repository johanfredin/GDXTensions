package se.fredin.gdxtensions.screen.ingame;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.scene2d.dialog.Dialog;
import se.fredin.gdxtensions.xml.XMLDialog;
import se.fredin.gdxtensions.xml.XMLDialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Array;

public class Dialogs extends InGameScreen {

	private Array<Dialog> dialogs;
	private AnimatedBitmapFont font;
	
	public Dialogs(XMLDialogs xmlDialogs, TextureAtlas atlas, AnimatedBitmapFont font, Color color) {
		super(atlas);
		this.font = font;
		TextFieldStyle style = new TextFieldStyle(font, color, null, null, skin.getDrawable("bg"));
		this.dialogs = this.populateFromXML(xmlDialogs, style);
	}
	
	public Dialogs(XMLDialogs xmlDialogs, String pathToPackFile, String pathToFont, Color color) {
		super(pathToPackFile);
		this.font = new AnimatedBitmapFont(Gdx.files.internal(pathToFont));
		TextFieldStyle style = new TextFieldStyle(font, color, null, null, skin.getDrawable("bg"));
		this.dialogs = this.populateFromXML(xmlDialogs, style);
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
	
	@Override
	public void tick(float deltaTime) {
		super.tick(deltaTime);
		if(dialogs.size > 0) {
			for(short i = 0; i < dialogs.size; i++) {
				Dialog dialog = dialogs.get(i);
				if(i == 0 && !dialog.isAllowedToStart()) {
					// If index is 0 we know we can start this dialog
					dialog.setAllowedToStart(true);
				}
				
				if(dialog.isAllowedToStart() && !dialog.isOpened()) {
					dialog.openDialog();
				}
				
				if(dialogs.get(i).isClosed()) {
					dialogs.removeIndex(i);
				}
			}
		}
	}
	
	private Array<Dialog> populateFromXML(XMLDialogs xmlDialogs, TextFieldStyle style) {
		Array<Dialog> dialogs = new Array<Dialog>();
		float parentX = xmlDialogs.getX();
		float parentY = xmlDialogs.getY();
		for(XMLDialog xmlDialog : xmlDialogs.getDialogElements()) {
			String text = xmlDialog.getText();
			float x = parentX;
			float y = parentY;
			if(xmlDialog.hasPositionSet()) {
				x = xmlDialog.getX();
				y = xmlDialog.getY();
			} 
			Dialog dialog = new Dialog(text, x - 150, y, style);
			if(xmlDialog.isTimeLimited()) {
				dialog.setTimeToDisplay(xmlDialog.getTimeToDisplay());
			} if(xmlDialog.hasHeader()) {
				dialog.setHeader(xmlDialog.getHeader(true));
				dialog.addAdditionalLineBreaks((byte)2);
			}
			this.stage.addActor(dialog);
			dialogs.add(dialog);
		}
		return dialogs;
	}
	
}
