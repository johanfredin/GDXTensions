package se.fredin.gdxtensions.scene2d;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.utils.text.AnimatedText;
import se.fredin.gdxtensions.utils.text.OutputFormatter;
import se.fredin.gdxtensions.utils.text.OutputFormatter.LineBreakSettings;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
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
	public static float defaultPadding = 5;
	public static float[] defaultPaddingSettings = {defaultPadding, defaultPadding, defaultPadding, defaultPadding};
	private float[] customPadding;
	private Image bounds;
	private float x;
	private float y;
	private float width;
	private float height;
	
	private AnimatedText animatedText;
	private float timePerCharacter;
	private short lineBreakIndex;
	private LineBreakSettings lineBreakSettings;
	private boolean canTick;
	private boolean isTimeToCloseDialog;
	
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
		this.customPadding = padding;
		this.animatedText = new AnimatedText(text, timePerCharacter, lineBreakIndex, lineBreakSettings);
		this.timePerCharacter = timePerCharacter;
		this.lineBreakIndex = lineBreakIndex;
		this.lineBreakSettings = lineBreakSettings;
		AnimatedBitmapFont font = (AnimatedBitmapFont) style.font;
		this.width = font.getWidth(animatedText);
		this.height = font.getHeight(animatedText);
		this.bounds = new Image(style.background);
		
		// Initial size = 0
		this.setSize(0, 0);
		this.bounds.setSize(0, 0);
		this.bounds.setColor(Color.BLUE);
		
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
	
	public Image getBounds() {
		return bounds;
	}
	
	@Override
	public void setPosition(float x, float y) {
		bounds.setPosition(x - customPadding[LEFT], y - customPadding[TOP]);
		super.setPosition(x, y);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		bounds.draw(batch, parentAlpha);
		super.draw(batch, parentAlpha);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		bounds.act(delta);
		
		if(canTick && !isTimeToCloseDialog) {
			tick(delta);
		}
	}
	
	public boolean isTimeToCloseDialog() {
		return isTimeToCloseDialog;
	}
	
	public void tick(float delta) {
		animatedText.tick(delta);
	    setText(animatedText.getCurrentText());
	}
	
	public void openDialog(float duration) {
		// Temporary values to be replaced when more functionality added
		this.x = 300;
		this.y = 200;
		
		float boundsWidth = width + (customPadding[LEFT] + customPadding[RIGHT]);
		float boundsHeight = height + (customPadding[TOP] + customPadding[BOTTOM]);
		
		float innerDuration = duration / 2;
		
		this.setPosition(x, y);
		this.bounds.setPosition(x, y);
		this.bounds.addAction(Actions.moveBy(-boundsWidth / 2, -boundsHeight / 2, duration));
		this.bounds.addAction(Actions.sequence(Actions.sizeTo(boundsWidth, boundsHeight, duration), Actions.delay(duration / 4), Actions.after(Actions.run(new Runnable() {
			@Override
			public void run() {
				canTick = true;
			}
		}))));
		this.addAction(Actions.moveBy(-width / 2, -height / 2, innerDuration));
		this.addAction(Actions.sizeTo(width, height, innerDuration));
	}
	
	public void closeDialog(float duration) {
		this.isTimeToCloseDialog = true;
		System.out.println("Dialog closing");
		
		// Empty the text in the box when closing
		this.setText("");
		
		float boundsWidth = width + (customPadding[LEFT] + customPadding[RIGHT]);
		float boundsHeight = height + (customPadding[TOP] + customPadding[BOTTOM]);
				
		float innerDuration = duration / 2;
				
		this.bounds.addAction(Actions.moveBy(boundsWidth / 2, boundsHeight / 2, duration));
		this.bounds.addAction(Actions.sizeTo(0, 0, duration));
		this.addAction(Actions.moveBy(width / 2, height / 2, innerDuration));
		this.addAction(Actions.sizeTo(0, 0, innerDuration));
	}

}
