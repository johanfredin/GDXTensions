package se.fredin.gdxtensions.utils.text;


/**
 * Lets us format the output of a labels text to fix into the given
 * dialog.
 * @author Johan Fredin
 *
 */
public class OutputFormatter {
	
	/** Default index for a line break */
	public static final short DEFAULT_LINEBREAK_INDEX = 20;
	/** Counter for how many line breaks a text will have */
	protected byte amountOfLineBreaks;
	/** The index of the text where we want the line breaks to be */
	protected short lineBreakIndex = DEFAULT_LINEBREAK_INDEX;
	/** How we will handle our line breaks, default is {@link LineBreakSettings#ABSOLUTE} */
	protected LineBreakSettings lineBreakSettings;
	
	/**
	 * Constructs a new {@link OutputFormatter} instance with {@link #DEFAULT_LINEBREAK_INDEX}
	 * and {@link LineBreakSettings#ABSOLUTE}
	 */
	public OutputFormatter() {
		this.lineBreakIndex = DEFAULT_LINEBREAK_INDEX;
		this.lineBreakSettings = LineBreakSettings.ABSOLUTE;
	}
	
	/**
	 * Constructs a new {@link OutputFormatter} with a given index for line break.
	 * {@link #lineBreakSettings} will be {@link LineBreakSettings#ABSOLUTE}
	 * @param lineBreakIndex the index where we want a line break to be inserted
	 */
	public OutputFormatter(short lineBreakIndex) {
		this.lineBreakIndex = lineBreakIndex;
	}
	
	/**
	 * Constructs a new {@link OutputFormatter} with a given {@link LineBreakSettings}.
	 * line break index will be equal {@link #DEFAULT_LINEBREAK_INDEX}
	 * @param lineBreakSettings how we want to handle our line breaks
	 */
	public OutputFormatter(LineBreakSettings lineBreakSettings) {
		this.lineBreakSettings = lineBreakSettings;
	}
	
	/**
	 * Constructs a new {@link OutputFormatter} with a given {@link #lineBreakIndex} and
	 * {@link LineBreakSettings}
 	 * @param lineBreakIndex where we want the line break index to be
	 * @param lineBreakSettings how we want to handle our line breaks
	 */
	public OutputFormatter(short lineBreakIndex, LineBreakSettings lineBreakSettings) {
		this.lineBreakIndex = lineBreakIndex;
		this.lineBreakSettings = lineBreakSettings;
	}
	
	/** 
	 * Enumerator to decide how we want to handle line breaks in
	 * a text
	 * @author Johan Fredin
	 *
	 */
	public static enum LineBreakSettings {
		/** We want the line break to be absolute e.g in the middle of a word or sequence. */
		ABSOLUTE,
		/** 
		 * We want the line break to appear after the current word, 
		 * this will lead to the {@link OutputFormatter#lineBreakIndex} to increase 
		 */
		NEXT_SEQUENCE,
		/** We want the line break to appear before the current word. */
		PREVIOUS_SEQUENCE
	}
	
	/**
	 * Lets us format a string with specified preferences.
	 * If no settings for {@link #lineBreakIndex} or {@link LineBreakSettings} have been
	 * made previously, the method will use the default settings for both.
	 * @param textToFormat the text we want to format
	 * @return the formated text
	 */
	public String getFormatedString(String textToFormat) {
		return getFormatedString(textToFormat, this.lineBreakIndex, this.lineBreakSettings);
	}
	
	/**
	 * Lets us format a string with specified {@link #lineBreakIndex}.
	 * If no settings for {@link LineBreakSettings} have been
	 * made previously, the method will use {@link LineBreakSettings#ABSOLUTE}.
	 * @param textToFormat the text we want to format
	 * @param lineBreakIndex where we want the line break index to be
	 * @return the formated text
	 */
	public String getFormatedString(String textToFormat, short lineBreakIndex) {
		return getFormatedString(textToFormat, lineBreakIndex, this.lineBreakSettings);
	}
	
	/**
	 * Lets us format a string with specified preferences.
	 * If no settings for {@link #lineBreakIndex} have been
	 * made previously, the method will use {@link #DEFAULT_LINEBREAK_INDEX}.
	 * @param textToFormat the text we want to format
	 * @param lineBreakSettings how we want to handle line breaks
	 * @return the formated text
	 */
	public String getFormatedString(String textToFormat, LineBreakSettings lineBreakSettings) {
		return getFormatedString(textToFormat, this.lineBreakIndex, lineBreakSettings);
	}
	
	/**
	 * Lets us format a string with specified preferences.
	 * @param textToFormat the text we want to format
	 * @param lineBreakIndex the index number of this string where we insert a line break
	 * @param lineBreakSettings how we want to handle a line break
	 * @return the formated text
	 */
	public String getFormatedString(String textToFormat, short lineBreakIndex, LineBreakSettings lineBreakSettings) {
		StringBuilder builder = new StringBuilder();
		Character separator = '-';
		char newLine = '\n';
		for(short i = 0, interval = 0; i < textToFormat.length(); i++, interval++) {
			char letter = textToFormat.charAt(i);
			builder.append(letter);
			
			if(interval >= lineBreakIndex) {
				switch(lineBreakSettings) {
				case ABSOLUTE:
					if(letter == separator) {
						builder.setCharAt(i, newLine);
					} else {
						builder.append(newLine);
					}
					interval = -1;
					amountOfLineBreaks++;
					break;
				case NEXT_SEQUENCE:
					if(letter != separator && amountOfLineBreaks < 1) {
						lineBreakIndex += 1;
						break;
					} else {
						builder.setCharAt(i, newLine);
						interval = -1;
						amountOfLineBreaks++;
					}
					break;
				case PREVIOUS_SEQUENCE:
					
					if(letter != ' ') {
						int lastIndex = builder.lastIndexOf(separator.toString());
						builder.setCharAt(lastIndex, newLine);
						interval = (short) (lineBreakIndex - lastIndex);
						amountOfLineBreaks++;
					} else {
						builder.setCharAt(i, newLine);
						lineBreakIndex = (short) (interval);
						interval = -1;
						amountOfLineBreaks++;
					}
					break;
				}
			}
		}
		return builder.toString();
	}
	
	
	
	/**
	 * @return the amount of line breaks for this text
	 */
	public byte getAmountOfLineBreaks() {
		return amountOfLineBreaks;
	}

}
