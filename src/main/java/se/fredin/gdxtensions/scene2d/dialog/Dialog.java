package se.fredin.gdxtensions.scene2d.dialog;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.scene2d.dialog.DialogOpenAndCloseOptions.OpenCloseAnimation;
import se.fredin.gdxtensions.utils.text.AnimatedText;
import se.fredin.gdxtensions.utils.text.OutputFormatter;
import se.fredin.gdxtensions.utils.text.OutputFormatter.LineBreakSettings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

/**
 * A class to represent a dialog box with a text in it. 
 * Think Zelda or any RPG game and you'll get the point.
 * The dialog class is actually a {@link TextArea} with an extra {@link Image} used as a frame.
 * @author Johan Fredin
 *
 */
public class Dialog extends TextArea {
	
	/** The default padding to use for the dialog e.g the frame */
	public static float defaultPadding = 5f;
	/** The default width for each side of the frame */
	public static float[] defaultPaddingSettings = {defaultPadding, defaultPadding, defaultPadding, defaultPadding};
	/** The default duration of the frame animation */
	public static float defaultDuration = .66f;
	
	private final byte TOP = 0;
	private final byte LEFT = 1; 
	private final byte BOTTOM = 2; 
	private final byte RIGHT = 3;

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
	
	/**
	 * Create a new dialog with default settings for text animation speed, linebreak index, linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 */
	public Dialog(String text, TextFieldStyle style) {
		this(text, style, AnimatedText.DEFAULT_TIME_PER_CHARACTER, OutputFormatter.DEFAULT_LINEBREAK_INDEX, LineBreakSettings.NEXT_SEQUENCE, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed and default settings for linebreak index, linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 */
	public Dialog(String text, TextFieldStyle style, float timePerCharacter) {
		this(text, style, timePerCharacter, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed, linebreak index and default settings for linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 * @param lineBreakIndex the index in the text where we want a line break.
	 */
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex) {
		this(text, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed, linebreak, linebreak settings and  default settings for padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 * @param lineBreakIndex the index in the text where we want a line break.
	 * @param the {@link LineBreakSettings} to use
	 */
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, LineBreakSettings settings) {
		this(text, style, timePerCharacter, lineBreakIndex, settings, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed, linebreak index, linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 * @param lineBreakIndex the index in the text where we want a line break.
	 * @param lineBreakSettings the {@link LineBreakSettings} to use
	 * @param padding the padding to use for each side (top, left, bottom, right)
	 */
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
		
		this.openDialog();
	}
	
	/**
	 * @return the animated text used in this dialog
	 */
	public AnimatedText getAnimatedText() {
		return animatedText;
	}
	
	/**
	 * Set the animation speed for the text
	 * @param timePerCharacter the time for each character until updating
	 */
	public void setTimePerCharacter(float timePerCharacter) {
		this.timePerCharacter = timePerCharacter;
	}
	
	/**
	 * @return the animation speed for the text
	 */
	public float getTimePerCharacter() {
		return timePerCharacter;
	}
	
	/**
	 * Set the linebreak index for the text
	 * @param lineBreakIndex
	 */
	public void setLineBreakIndex(short lineBreakIndex) {
		this.lineBreakIndex = lineBreakIndex;
	}
	
	/**
	 * @return the index in the text where there is a line break.
	 */
	public short getLineBreakIndex() {
		return lineBreakIndex;
	}
	
	/**
	 * Set the {@link LineBreakSettings} for the text
	 * @param lineBreakSettings
	 */
	public void setLineBreakSettings(LineBreakSettings lineBreakSettings) {
		this.lineBreakSettings = lineBreakSettings;
	}
	
	/**
	 * @return the {@link LineBreakSettings} for the text
	 */
	public LineBreakSettings getLineBreakSettings() {
		return lineBreakSettings;
	}
	
	/**
	 * @return the frame {@link Image}
	 */
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
	
	/**
	 * @return whether or not its time to close this dialog
	 */
	public boolean isTimeToCloseDialog() {
		return openCloseOptions.isTimeToCloseDialog();
	}
	
	/**
	 * @return the width of the frame surrounding the dialog
	 */
	public float getFrameWidth() {
		return frameWidth;
	}
	
	/**
	 * @return the height of the frame surrounding the dialog
	 */
	public float getFrameHeight() {
		return frameHeight;
	}
	
	/**
	 * Updates the {@link AnimatedText} and sets the text of this dialog char by char
	 * @param delta
	 */
	public void tick(float delta) {
		animatedText.tick(delta);
	    setText(animatedText.getCurrentText());
	}
	
	
	/**
	 * Makes the dialog open. Will use the default {@link OpenCloseAnimation#QUAD_EVEN}
	 * and {@link Dialog#defaultDuration} if no values for these have been previously set.
	 */
	public void openDialog() {
		openCloseOptions.openDialog();
	}
	
	/**
	 * Makes the dialog close. Will use the default {@link OpenCloseAnimation#QUAD_EVEN}
	 * and {@link Dialog#defaultDuration} if no values for these have been previously set.
	 */
	public void closeDialog() {
		openCloseOptions.closeDialog();
	}
	
	/**
	 * Makes the dialog open with custom duration.
	 * will use {@link OpenCloseAnimation#QUAD_EVEN} if none has been previously set
	 * @param duration the duration of the animation
	 */
	public void openDialog(float duration) {
		openCloseOptions.openDialog(duration);
	}
	
	/**
	 * Makes the dialog close with custom duration.
	 * will use {@link OpenCloseAnimation#QUAD_EVEN} if none has been previously set
	 * @param duration the duration of the animation
	 */
	public void closeDialog(float duration) {
		openCloseOptions.closeDialog(duration);
	}
	
	/**
	 * Makes the dialog open with custom animation. will use {@link Dialog#defaultDuration} if none is previously set.
	 * @param openCloseAnimation the animation to use
	 */
	public void openDialog(OpenCloseAnimation openCloseAnimation) {
		openCloseOptions.openDialog(openCloseAnimation);
	}
	
	/**
	 * Makes the dialog close with custom animation. will use {@link Dialog#defaultDuration} if none is previously set.
	 * @param openCloseAnimation the animation to use
	 */
	public void closeDialog(OpenCloseAnimation openCloseAnimation) {
		openCloseOptions.closeDialog(openCloseAnimation);
	}
	
	/***
	 * @return the width of the dialog e.g the inner image
	 */
	public float getDialogWidth() {
		return dialogWidth;
	}
	
	/**
	 * @return the height of the dialog e.g the inner frame
	 */
	public float getDialogHeight() {
		return dialogHeight;
	}
	
}
