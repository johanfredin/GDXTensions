package se.fredin.gdxtensions.utils.text;

/**
 * Lets us format the output of a labels text to fix into the given
 * dialog.
 * @author Johan Fredin
 *
 */
public class OutputFormatter {
	
	protected byte amountOfLineBreaks;
	
	/**
	 * Lets us format a string with specified preferences.
	 * @param INDEX_FOR_LINE_BREAK the index number of this string where we insert a line break
	 * @param textToFormat the text we want to format
	 * @param absoluteBreakRow if <b>true<b> the formatter will insert a break-row in the middle of a word or
	 * sequence. Else it will take that word or sequence and add to the next row.
	 * @return the formated text
	 */
	public String getFormatedString(final int INDEX_FOR_LINE_BREAK, String textToFormat, boolean absoluteBreakRow) {
		StringBuilder builder = new StringBuilder();
		for(int i = 0, interval = 0; i < textToFormat.length(); i++, interval++) {
			char letter = textToFormat.charAt(i);
			builder.append(letter);
			
			if(interval >= INDEX_FOR_LINE_BREAK) {
				if(absoluteBreakRow) {
					if(textToFormat.charAt(interval) == ' ') {
						builder.setCharAt(i, '\n');
					} else {
						builder.append('\n');
					}
					interval = -1;
					amountOfLineBreaks++;
				} else {
					if(letter != ' ') {
						int lastIndex = builder.lastIndexOf(" ");
						builder.setCharAt(lastIndex, '\n');
						interval = INDEX_FOR_LINE_BREAK - lastIndex;
						amountOfLineBreaks++;
					} else {
						builder.setCharAt(i, '\n');
						interval = -1;
						amountOfLineBreaks++;
					}
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
