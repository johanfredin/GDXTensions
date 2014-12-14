package se.fredin.gdxtensions.scene2d;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.utils.BitmapFontBoundsCalculator;
import se.fredin.gdxtensions.utils.LogUtils;
import se.fredin.gdxtensions.utils.MVPair;
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
		this(text, style, .005f, (short)15, LineBreakSettings.NEXT_SEQUENCE, 10, 10);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter) {
		this(text, style, timePerCharacter, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, 10, 10);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex) {
		this(text, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, 10, 10);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, float xBorder) {
		this(text, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, xBorder, 10);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, float xBorder, float yBorder) {
		this(text, style, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, xBorder, yBorder);
	}
	
	public Dialog(String text, TextFieldStyle style, float timePerCharacter, short lineBreakIndex, LineBreakSettings lineBreakSettings, float xBorder, float yBorder) {
		super(text, style);
		this.animatedText = new AnimatedText(text, timePerCharacter, lineBreakIndex, lineBreakSettings);
		this.animatedText.setLogToConsole(true);
		this.text = text;
		this.timePerCharacter = timePerCharacter;
		this.lineBreakIndex = lineBreakIndex;
		this.lineBreakSettings = lineBreakSettings;
		setOrigin(50);
		AnimatedBitmapFont font = (AnimatedBitmapFont) style.font;
		BitmapFontBoundsCalculator boundCalc = new BitmapFontBoundsCalculator(font, animatedText);
		float width = boundCalc.getWidth() + xBorder * 4;
		float height = boundCalc.getHeight() + yBorder;
		
		setWidth(width);
		setHeight(height);
		LogUtils.log(
			new MVPair("Amount of breaks", animatedText.getAmountOfLineBreaks()),
			new MVPair("Font width i guess will be", font.getWidth(animatedText)),
			new MVPair("box width", getPrefWidth()),
			new MVPair("Current line break setting", lineBreakSettings.name()),
			new MVPair("dialog box size", getWidth() + "*" + getHeight()));
			
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
