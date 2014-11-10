package se.fredin.gdxtensions.scene2d;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.utils.text.AnimatedText;
import se.fredin.gdxtensions.utils.text.OutputFormatter;
import se.fredin.gdxtensions.utils.text.OutputFormatter.LineBreakSettings;

import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

/**
 * A class to represent a dialog box with a text in it. 
 * Think Zelda or any RPG game and you'll get the point
 * @author Johan Fredin
 *
 */
public class Dialog extends TextArea {

	private AnimatedText animatedText;
	private float timePerCharacter;
	private short lineBreakIndex;
	private LineBreakSettings lineBreakSettings;
	
	public Dialog(String text, TextFieldStyle style) {
		this(text, style, AnimatedText.DEFAULT_TIME_PER_CHARACTER, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter) {
		this(text, style, timePerCharacter, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex) {
		this(text, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, LineBreakSettings lineBreakSettings) {
		super(text, style);
		this.animatedText = new AnimatedText(text, timePerCharacter, lineBreakIndex, lineBreakSettings);
		this.text = text;
		this.timePerCharacter = timePerCharacter;
		this.lineBreakIndex = lineBreakIndex;
		this.lineBreakSettings = lineBreakSettings;
		AnimatedBitmapFont font = (AnimatedBitmapFont) style.font;
		float width = font.getWidth(animatedText);
		float height = font.getHeight(animatedText) * animatedText.getAmountOfLineBreaks();
		setSize(width, height);
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
	
	@Override
	public void act(float delta) {
		super.act(delta);
		tick(delta);
	}
	
	public void tick(float delta) {
		animatedText.tick(delta);
		setText(animatedText.getCurrentText());
	}
	
	

}
