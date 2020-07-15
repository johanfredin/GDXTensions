package se.fredin.gdxtensions.utils;

import se.fredin.gdxtensions.utils.text.AnimatedText;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;

/** 
 * Helper class to figure out exact width and height of a {@link BitmapFont}. 
 * Useful when not using a monospace type font.
 */
public class BitmapFontBoundsCalculator {

	private final BitmapFont font;
	private float width;
	private float height;
	private byte amountOfLineBreaks;
	
	/**
	 * Construct a new {@link BitmapFontBoundsCalculator}
	 * @param font the {@link BitmapFont} to use for calculation
	 */
	public BitmapFontBoundsCalculator(BitmapFont font) {
		this.font = font;
	}
	
	/**
	 * Construct a new {@link BitmapFontBoundsCalculator}
	 * @param font the {@link BitmapFont} to use for calculation
	 * @param text the {@link AnimatedText} object to use for calculating width and height
	 */
	public BitmapFontBoundsCalculator(BitmapFont font, AnimatedText text) {
		this.font = font;
		this.width = getCalculatedWidth(text);
		this.height = getCalculatedHeight(text);
	}
	
	/**
	 * Calculates what the width would be using an animated font.
	 * It checks each row of the text, and combines the width of each character to get the
	 * correct width, also checks if another row has more characters than the previous.
	 * @param animatedText the {@link AnimatedText} to use for calculation
	 * @return the correct width to use
	 */
	public float getCalculatedWidth(AnimatedText animatedText) {
		BitmapFontData data = font.getData();
		float finalWidth = 0;
		byte rowCount = 0;
		for(String row : animatedText.getRows()) {
			float dynamicWidth = 0;
			
			for(int i = 0; i < row.length(); i++) {
				char charAt = row.charAt(i);
				Glyph glyphAt = data.getGlyph(charAt);
				char space = ' ';
				if(charAt == space) {
					dynamicWidth += data.spaceXadvance;
				} else {
					if(glyphAt != null) {
						dynamicWidth += glyphAt.width;
					}
				}
			}
			
			if(rowCount < 1) {
				finalWidth = dynamicWidth;
			} else {
				if(dynamicWidth > finalWidth) {
					finalWidth = dynamicWidth;
				}
			}
			
			rowCount++;
		}
		return finalWidth;
	}
	
	/**
	 * Get the calculated height according to font height and amount of line breaks
	 * @param text the {@link AnimatedText} to use for calculation
	 * @return the correct height
	 */
	public float getCalculatedHeight(AnimatedText text) {
		return getCalculatedHeight(text, (byte) 0);
	}
	
	/**
	 * Get the calculated height according to font width and amount of line breaks
	 * @param text the text 
	 * @param additionalLinesIfHeaderExists if a header exist you need to add the correct extra amount of line breaks.
	 * @return
	 */
	public float getCalculatedHeight(AnimatedText text, byte additionalLinesIfHeaderExists) {
		BitmapFontData data = font.getData();
		this.amountOfLineBreaks = (byte)(text.getAmountOfLineBreaks() + 1 + additionalLinesIfHeaderExists);
		return data.lineHeight * amountOfLineBreaks;
	}
	
	/**
	 * Checks if the width of each {@link Glyph} differs, slight differ indicates that the font is not monospace
	 * @return whether or not the font is a monospace type font.
	 */
	public boolean isMonoSpace() {
		BitmapFontData data = font.getData();
		float initialWidth = data.getFirstGlyph().width;
		int index = 0;
		for(Glyph[] page : data.glyphs) {
			if(page != null) {
				for(Glyph glyph : page) {
					if(glyph != null) {
						float glyphWidth = glyph.width;
						if(index > 0 && glyphWidth > 0 && glyphWidth != initialWidth) {
							return true;
						}
						index++;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Adds an optional amount of line breaks to the text
	 * @param amount the amount of line breaks to add
	 */
	public void addLinebreaks(byte amount) {
		this.amountOfLineBreaks += amount;
	}
	
	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}
	
	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	

}
