package se.fredin.gdxtensions.screen.ingame;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.input.BaseInput;
import se.fredin.gdxtensions.scene2d.dialog.Dialog;
import se.fredin.gdxtensions.xml.XMLDialog;
import se.fredin.gdxtensions.xml.XMLDialogs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.Array;

/**
 * This handles an {@link Array} of {@link Dialog} objects. 
 * The objects are created from an {@link XMLDialogs} objects.
 * @author Johan Fredin
 *
 */
public class Dialogs extends InGameScreen {

	private Array<Dialog> dialogs;
	private AnimatedBitmapFont font;
	private BaseInput baseInput;
	
	/**
	 * Construct a new {@link Dialogs} object
	 * @param baseInput the {@link BaseInput} object you wish to use for interacting with the dialogs
	 * @param xmlDialogs the {@link XMLDialogs} from the xml file
	 * @param atlas the {@link TextureAtlas} the {@link Stage} will use. Since this class 
	 * extends {@link InGameScreen} it is required
	 * @param font the {@link AnimatedBitmapFont} to use
	 * @param color the {@link Color} to use for the dialogs
	 */
	public Dialogs(BaseInput baseInput, XMLDialogs xmlDialogs, TextureAtlas atlas, AnimatedBitmapFont font, Color color) {
		super(atlas);
		this.baseInput = baseInput;
		this.font = font;
		TextFieldStyle style = new TextFieldStyle(font, color, null, null, skin.getDrawable("bg"));
		this.dialogs = this.populateFromXML(xmlDialogs, style);
		
	}
	
	/**
	 * Construct a new {@link Dialogs} object
	 * @param baseInput the {@link BaseInput} object you wish to use for interacting with the dialogs
	 * @param xmlDialogs the {@link XMLDialogs} from the xml file
	 * @param pathToPackFile the path to the packfile that the {@link TextureAtlas} will use. Since this class 
	 * extends {@link InGameScreen} it is required
	 * @param pathToFont the path to the {@link AnimatedBitmapFont} to use
	 * @param color the {@link Color} to use for the dialogs
	 */
	public Dialogs(BaseInput baseInput, XMLDialogs xmlDialogs, String pathToPackFile, String pathToFont, Color color) {
		super(pathToPackFile);
		this.baseInput = baseInput;
		this.font = new AnimatedBitmapFont(Gdx.files.internal(pathToFont));
		TextFieldStyle style = new TextFieldStyle(font, color, null, null, skin.getDrawable("bg"));
		this.dialogs = this.populateFromXML(xmlDialogs, style);
	}
	
	/**
	 * Set the {@link BaseInput} you wish to use
	 * @param baseInput
	 */
	public void setBaseInput(BaseInput baseInput) {
		this.baseInput = baseInput;
	}
	
	/**
	 * @return the {@link BaseInput} used for these dialogs
	 */
	public BaseInput getBaseInput() {
		return baseInput;
	}
	
	/**
	 * Will open a dialog at a given index
	 * @param index the index of the list
	 */
	public void openDialog(int index) {
		Dialog dialog = this.dialogs.get(index);
		dialog.openDialog();
	}
	
	/**
	 * Sets the position of all dialogs, not a good approach unless you want them all at the same place
	 * @param position the {@link Vector2} object holding the position
	 */
	public void setPosition(Vector2 position) {
		for(Dialog dialog : dialogs) {
			dialog.setPosition(position.x, position.y);
		}
	}
	
	/**
	 * Sets the position of all dialogs, not a good approach unless you want them all at the same place
	 * @param x the x position
	 * @param y the y position
	 */
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
					dialog.openDialog();
				}
				
				if(dialogs.get(i).isClosed()) {
					dialogs.removeIndex(i);
				}
			}
		}
	}
	
	public boolean hasInput() {
		return baseInput != null;
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
			
			if(this.hasInput()) {
				System.out.println("has input daddy");
				dialog.setBaseInput(baseInput);
			}
			
			this.stage.addActor(dialog);
			dialogs.add(dialog);
		}
		return dialogs;
	}
	
	
	
}
