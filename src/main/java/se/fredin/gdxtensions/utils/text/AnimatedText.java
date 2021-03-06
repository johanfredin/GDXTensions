package se.fredin.gdxtensions.utils.text;

import se.fredin.gdxtensions.utils.text.OutputFormatter.LineBreakSettings;

/**
 * Used to have text animated like in RPG games e.g the letters show up one after the other. 
 * User will be able to specify the speed and width of the dialogs. 
 * The {@link AnimatedText} class is working closely with  the {@link OutputFormatter} class. 
 * All text that is given is first sent to the {@link OutputFormatter} where line break index 
 * and line break settings are set. If no parameters for line break index of line break settings 
 * are given here the {@link OutputFormatter} will use it's own default settings.
 * @author Johan Fredin
 *
 */
public class AnimatedText {

	/** The default time we want each character to take before displaying the next one */
	public static final float DEFAULT_TIME_PER_CHARACTER = .005f;
	/** Sample text used when no text is provided */
	public static final String DEFAULT_TEXT = "This class lets you output letters in an animated fashion!";
	
	private String formattedText;
	private String currentText;
	private char currentCharacter;
	private byte amountOfLineBreaks;
	private float timePerCharacter;
	private float timer;
	private float totalTime;
	private float counter;
	private short index;
	private short lineBreakIndex;
	private boolean isLogToConsole;
	
	/**
	 * Constructs a new {@link AnimatedText} with default settings
	 */
	public AnimatedText() {
		this(DEFAULT_TEXT, DEFAULT_TIME_PER_CHARACTER, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, false);
	}
	
	/**
	 * Constructs a new {@link AnimatedText} with a given text
	 * @param text the text previously formatted by {@link OutputFormatter} to display
	 */
	public AnimatedText(String text) {
		this(text, DEFAULT_TIME_PER_CHARACTER, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, false);
	}
	
	/**
	 * Constructs a new {@link AnimatedText} with a given text
	 * @param lineBreakIndex the index where we want the line break to occur.
	 */
	public AnimatedText(String text, LineBreakSettings lineBreakSettings) {
		this(text, DEFAULT_TIME_PER_CHARACTER, OutputFormatter.DEFAULT_LINEBREAK_INDEX, lineBreakSettings, false);
	}
	
	/**
	 * Constructs a new {@link AnimatedText} with a given text and time per character 
	 * @param text the text previously formatted by {@link OutputFormatter} to display
	 * @param timePerCharacter the amount of time we want for each character before typing out the next one.
	 */
	public AnimatedText(String text, float timePerCharacter) {
		this(text, timePerCharacter, OutputFormatter.DEFAULT_LINEBREAK_INDEX, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, false);
	}
	
	/**
	 * Constructs a new {@link AnimatedText} with a given text, time per character and index for line break 
	 * @param text the text previously formatted by {@link OutputFormatter} to display
	 * @param timePerCharacter the amount of time we want for each character before typing out the next one.
	 * @param lineBreakIndex the index where we want the line break to occur.
	 */
	public AnimatedText(String text, float timePerCharacter, short lineBreakIndex) {
		this(text, timePerCharacter, lineBreakIndex, OutputFormatter.DEFAULT_LINEBREAK_SETTINGS, false);
	}
	
	/**
	 * Constructs a new {@link AnimatedText} with a given text, time per character and index for line break 
	 * @param text the text previously formatted by {@link OutputFormatter} to display
	 * @param timePerCharacter the amount of time we want for each character before typing out the next one.
	 * @param lineBreakIndex the index where we want the line break to occur.
	 * @param lineBreakSettings the {@link LineBreakSettings} we want to use
	 */
	public AnimatedText(String text, float timePerCharacter, short lineBreakIndex, LineBreakSettings lineBreakSettings) {
		this(text, timePerCharacter, lineBreakIndex, lineBreakSettings, false);
	}
	
	/**
	 * Constructs a new {@link AnimatedText} with a given text, time per character and index for line break 
	 * @param text the text previously formatted by {@link OutputFormatter} to display
	 * @param timePerCharacter the amount of time we want for each character before typing out the next one.
	 * @param lineBreakIndex the index where we want the line break to occur.
	 * @param lineBreakSettings the {@link LineBreakSettings} we want to use
	 * @param isLogToConsole whether or not to print to console using {@link System#out}
	 */
	public AnimatedText(String text, float timePerCharacter, short lineBreakIndex, LineBreakSettings lineBreakSettings, boolean isLogToConsole) {
		this.timePerCharacter = timePerCharacter;
		OutputFormatter formatter = new OutputFormatter();
		this.formattedText = formatter.getFormatedString(text, lineBreakIndex, lineBreakSettings);
		this.isLogToConsole = isLogToConsole;
		this.amountOfLineBreaks = formatter.getAmountOfLineBreaks();
		this.currentText = "";
		this.lineBreakIndex = lineBreakIndex;
		this.totalTime = timePerCharacter * text.length();
	}

	/**
	 * Updates the dialog
	 * @param deltaTime
	 */
	public void tick(float deltaTime) {
		if(timer >= counter && index < formattedText.length()) {
			currentCharacter = formattedText.charAt(index);
			currentText += currentCharacter;
			if(isLogToConsole) {
				System.out.print(currentCharacter);
			}
			counter += timePerCharacter;
			index++;
		} else {
			timer += deltaTime;
		}
	}
	
	/**
	 * Splits up the text for each line break character and returns an array of each row
	 * @return
	 */
	public String[] getRows() {
		return formattedText.split("\n");
	}
	
	/**
	 * get the time taken for each character
	 * @return
	 */
	public float getTimePerCharacter() {
		return timePerCharacter;
	}

	/**
	 * Set how much time we want it to take for each character
	 * @param timePerCharacter
	 */
	public void setTimePerCharacter(float timePerCharacter) {
		this.timePerCharacter = timePerCharacter;
	}

	/**
	 * Get the text previously formatted by {@link OutputFormatter} that we want to display
	 * @return
	 */
	public String getFormattedText() {
		return formattedText;
	}

	/**
	 * Set the text that we want to be formatted by {@link OutputFormatter}
	 * @param formattedText
	 */
	public void setFormattedText(String formattedText) {
		this.formattedText = formattedText;
	}

	/**
	 * Get the current character being displayed in the text
	 * @return
	 */
	public char getCurrentCharacter() {
		return currentCharacter;
	}

	/**
	 * Set the current character to be displayed
	 * @param currentCharacter
	 */
	public void setCurrentCharacter(char currentCharacter) {
		this.currentCharacter = currentCharacter;
	}

	/**
	 * Whether or not text is being displayed in the console
	 * @return
	 */
	public boolean isLogToConsole() {
		return isLogToConsole;
	}

	/**
	 * Set whether or not to log the text to the console
	 * @param isLogToConsole
	 */
	public void setLogToConsole(boolean isLogToConsole) {
		this.isLogToConsole = isLogToConsole;
	}
	
	/**
	 * @return the index where there will be a linebreak
	 */
	public short getLineBreakIndex() {
		return lineBreakIndex;
	}
	
	/**
	 * Return the text as it is right now
	 * @return
	 */
	public String getCurrentText() {
		return currentText;
	}
	
	/**
	 * @return whether or not the current character of the text is a line break character e.g '\n'
	 */
	public boolean isLineBreakCharacter() {
		return currentCharacter == '\n';
	}
	
	/**
	 * @return the amount of line breaks the complete text contains
	 */
	public byte getAmountOfLineBreaks() {
		return amountOfLineBreaks;
	}
	
	/**
	 * Let's us find out if all the characters of the text has been added by comparing them to the text passed in
	 * @param text the text to compare to
	 * @return true if passed in text equals the complete text
	 */
	public boolean finishedAnimating(String text) {
		return text.equals(formattedText);
	}
	
	/**
	 * Resets the timer and the current text so that the animation could start over again
	 */
	public void reset() {
		this.timer = 0.0f;
		this.counter = 0.0f;
		this.index = 0;
		this.currentText = "";
	}
	
	/**
	 * @return the total time it will take until all the characters of this text is visible
	 */
	public float getTotalTime() {
		return totalTime;
	}
}
