package se.fredin.gdxtensions.scene2d.dialog;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.utils.text.AnimatedText;
import se.fredin.gdxtensions.utils.text.OutputFormatter;
import se.fredin.gdxtensions.utils.text.OutputFormatter.LineBreakSettings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

/**
 * A class to represent a dialog box with a text in it. 
 * Think Zelda or any RPG game and you'll get the point
 * @author Johan Fredin
 *
 */
public class Dialog extends TextArea {
	
	public static final int TOP = 0, LEFT = 1, BOTTOM = 2, RIGHT = 3;
	public static float defaultDuration = .66f;
	
	public static float defaultPadding = 5;
	public static float[] defaultPaddingSettings = {defaultPadding, defaultPadding, defaultPadding, defaultPadding};
	
	private float dialogWidth;
	private float dialogHeight;
	private float frameWidth;
	private float frameHeight;
	private float timePerCharacter;
	private short lineBreakIndex;

	private Image frame;
	private DialogOpenAndCloseOptions openCloseOptions;
	private AnimatedText animatedText;
	private LineBreakSettings lineBreakSettings;
	
	public Dialog(String text, TextFieldStyle style) {
		this(text, style, .005f, OutputFormatter.DEFAULT_LINEBREAK_INDEX, LineBreakSettings.NEXT_SEQUENCE, defaultPaddingSettings);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter) {
		this(text, style, timePerCharacter, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, defaultPaddingSettings);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex) {
		this(text, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, defaultPaddingSettings);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, LineBreakSettings lineBreakSettings, float[] padding) {
		super("", style);
		this.frame = new Image(style.background);
		
		//TODO: Remove later this temporary hard-coded values
		setPosition(300, 200);
		
		this.animatedText = new AnimatedText(text, timePerCharacter, lineBreakIndex, lineBreakSettings);
		this.timePerCharacter = timePerCharacter;
		this.lineBreakIndex = lineBreakIndex;
		this.lineBreakSettings = lineBreakSettings;
		AnimatedBitmapFont font = (AnimatedBitmapFont) style.font;

		this.dialogWidth = font.getWidth(animatedText);
		this.dialogHeight = font.getHeight(animatedText);
		this.frameWidth = dialogWidth + (padding[LEFT] + padding[RIGHT]);
		this.frameHeight =  dialogHeight + (padding[TOP] + padding[BOTTOM]);
		
		this.openCloseOptions = new DialogOpenAndCloseOptions(this);
		// Initial size = 0
		this.setSize(0, 0);
		this.frame.setSize(0, 0);
		this.frame.setColor(Color.BLUE);
		
		this.openDialog(.8f);
	}
	
	public AnimatedText getAnimatedText() {
		return animatedText;
	}
	
	public void setTimePerCharacter(float timePerCharacter) {
		this.timePerCharacter = timePerCharacter;
	}
	
	public float getTimePerCharacter() {
		return timePerCharacter;
	}
	
	public void setLineBreakIndex(short lineBreakIndex) {
		this.lineBreakIndex = lineBreakIndex;
	}
	
	public short getLineBreakIndex() {
		return lineBreakIndex;
	}
	
	public void setLineBreakSettings(LineBreakSettings lineBreakSettings) {
		this.lineBreakSettings = lineBreakSettings;
	}
	
	public LineBreakSettings getLineBreakSettings() {
		return lineBreakSettings;
	}
	
	public Image getFrame() {
		return frame;
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		frame.setPosition(x, y);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		frame.draw(batch, parentAlpha);
		super.draw(batch, parentAlpha);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		frame.act(delta);
		
		if(openCloseOptions.isCanTick() && !isTimeToCloseDialog()) {
			tick(delta);
		}
	}
	
	public boolean isTimeToCloseDialog() {
		return openCloseOptions.isTimeToCloseDialog();
	}
	
	public float getFrameWidth() {
		return frameWidth;
	}
	
	public float getFrameHeight() {
		return frameHeight;
	}
	
	public void tick(float delta) {
		animatedText.tick(delta);
	    setText(animatedText.getCurrentText());
	}
	
	public void openDialog(float duration) {
		this.openCloseOptions.openDialog(duration);
	}
	
	public void closeDialog(float duration) {
		this.openCloseOptions.closeDialog(duration);
	}
	
	public float getDialogWidth() {
		return dialogWidth;
	}
	
	public float getDialogHeight() {
		return dialogHeight;
	}
	
}
