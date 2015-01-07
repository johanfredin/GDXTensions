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
	
	private float[] padding;
	
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
	
	private float timeToDisplay;
	private float timer;
	private String header;
	
	private Image frame;
	private DialogOpenAndCloseOptions openCloseOptions;
	private AnimatedText animatedText;
	private LineBreakSettings lineBreakSettings;
	private AnimatedBitmapFont font;
	
	private boolean isAllowedToStart;
	private boolean isOpened;
	
	
	/**
	 * Create a new dialog with default settings for text animation speed, linebreak index, linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 */
	public Dialog(String text, float x, float y, TextFieldStyle style) {
		this(text, x, y, style, AnimatedText.DEFAULT_TIME_PER_CHARACTER, OutputFormatter.DEFAULT_LINEBREAK_INDEX, LineBreakSettings.NEXT_SEQUENCE, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed and default settings for linebreak index, linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 */
	public Dialog(String text, float x, float y, TextFieldStyle style, float timePerCharacter) {
		this(text, x, y, style, timePerCharacter, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed, linebreak index and default settings for linebreak settings and padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 * @param lineBreakIndex the index in the text where we want a line break.
	 */
	public Dialog(String text, float x, float y, TextFieldStyle style, float timePerCharacter, short lineBreakIndex) {
		this(text, x, y, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, defaultPaddingSettings);
	}
	
	/**
	 * Create a new dialog with custom settings for animation speed, linebreak, linebreak settings and  default settings for padding.
	 * @param text the text to use for this dialog box
	 * @param style the {@link TextFieldStyle}
	 * @param timePerCharacter the update speed of the text to be displayed in the box.
	 * @param lineBreakIndex the index in the text where we want a line break.
	 * @param the {@link LineBreakSettings} to use
	 */
	public Dialog(String text, float x, float y, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, LineBreakSettings settings) {
		this(text, x, y, style, timePerCharacter, lineBreakIndex, settings, defaultPaddingSettings);
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
	public Dialog(String text, float x, float y, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, LineBreakSettings lineBreakSettings, float[] padding) {
		super("", style);
		this.frame = new Image(style.background);
		this.padding = padding;
		
		this.animatedText = new AnimatedText(text, timePerCharacter, lineBreakIndex, lineBreakSettings);
		this.timePerCharacter = timePerCharacter;
		this.lineBreakIndex = lineBreakIndex;
		this.lineBreakSettings = lineBreakSettings;
		this.font = (AnimatedBitmapFont) style.font;

		this.dialogWidth = font.getWidth(animatedText);
		this.dialogHeight = font.getHeight(animatedText);
		this.frameWidth = dialogWidth + (padding[LEFT] + padding[RIGHT]);
		this.frameHeight = dialogHeight + (padding[TOP] + padding[BOTTOM]);
		
		this.setPosition(x, y);
		
		// Initial size = 0
		this.setSize(0, 0);
		this.frame.setSize(0, 0);
		this.frame.setColor(Color.BLUE);
		
		this.openCloseOptions = new DialogOpenAndCloseOptions(this);
	
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
	
	public boolean isClosed() {
		return openCloseOptions.isClosed();
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
		String currentText = animatedText.getCurrentText();
		if(hasHeader()) {
			setText(header + currentText);
		} else {
			setText(currentText);
		}
		
		if(isTimeLimited()) {
			if(animatedText.finishedAnimating(currentText)) {
				timer += delta;
				if(timer >= timeToDisplay) {
					closeDialog();
				}
			}
		}
	}
	
	
	/**
	 * Makes the dialog open. Will use the default {@link OpenCloseAnimation#QUAD_EVEN}
	 * and {@link Dialog#defaultDuration} if no values for these have been previously set.
	 * The dialog will NOT open more than once and if {@link #isAllowedToStart()} is <b>true</b>
	 */
	public void openDialog() {
		if(isAllowedToStart && !isOpened) {
			openCloseOptions.openDialog();
			this.isOpened = true;
		}
	}
	
	/**
	 * @return whether or not this dialog has been opened
	 */
	public boolean isOpened() {
		return isOpened;
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
	
	/** 
	 * Let's us find out if this dialog should be openened withing a limited amount of time
	 * @return true if {@link #timeToDisplay} > 0 
	 */
	public boolean isTimeLimited() {
		return timeToDisplay > 0;
	}
	
	/**
	 * @return the amount of time this dialog should be opened, if 0 or less it means this dialog is NOT timebased
	 */
	public float getTimeToDisplay() {
		return timeToDisplay;
	}
	
	/**
	 * Set how long this dialog should be used
	 * @param timeToDisplay the time to display this dialog in millis
	 */
	public void setTimeToDisplay(float timeToDisplay) {
		this.timeToDisplay = timeToDisplay;
	}
	
	/**
	 * Set the header to use e.g the text on top that will not be animated
	 * @param header the header text
	 */
	public void setHeader(String header) {
		this.header = header;
	}
	
	/**
	 * @return the header text
	 */
	public String getHeader() {
		return header;
	}
	
	/**
	 * @return whether or not this dialog has a header meaning {@link #header} != null
	 */
	public boolean hasHeader() {
		return header != null;
	}
	
	/**
	 * Sometimes we will need additional line breaks to make the dialog bigger. 
	 * When there is a header involved this is mandatory or the text will not be displayed
	 * correctly.
	 * @param amount the amount of extra line breaks
	 */
	public void addAdditionalLineBreaks(byte amount) {
		this.dialogHeight = font.getHeight(animatedText, amount);
		this.frameHeight = dialogHeight + (padding[TOP] + padding[BOTTOM]);
		this.openCloseOptions.updateAmountOfLineBreaks();
	}
	
	/**
	 * Set whether or not this dialog can start
	 * @param isAllowedToStart
	 */
	public void setAllowedToStart(boolean isAllowedToStart) {
		this.isAllowedToStart = isAllowedToStart;
	}
	
	/**
	 * @return whether or not this dialog is allowed to start
	 */
	public boolean isAllowedToStart() {
		return isAllowedToStart;
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		this.frame.setVisible(visible);
	}
	
}
