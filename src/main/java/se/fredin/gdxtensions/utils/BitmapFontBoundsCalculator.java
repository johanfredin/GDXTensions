package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.utils.text.AnimatedText;

/** 
 * Helper class to figure out exact width and height of a bitmapfont. 
 * Useful when not using a monospace type font.
 */
public class BitmapFontBoundsCalculator {

	private AnimatedBitmapFont font;
	private float width;
	private float height;
	
	public BitmapFontBoundsCalculator(AnimatedBitmapFont font) {
		this.font = font;
	}
	
	public BitmapFontBoundsCalculator(AnimatedBitmapFont font, AnimatedText text) {
		this.font = font;
		this.width = getCalculatedWidth(text);
		this.height = getCalculatedHeight(text);
	}
	
	public int getCalculatedWidth(String text) {
		BitmapFontData data = font.getData();
		int width = 0;
		for(int i = 0; i < text.length(); i++) {
			char charAt = text.charAt(i);
			Glyph glyphAt = data.getGlyph(charAt);
			char space = ' ';
			if(charAt == space) {
				width += data.spaceWidth;
				System.out.println("Space found");
			} else {
				if(glyphAt != null) {
					System.out.println("Char at = " + charAt);
					width += glyphAt.width;
				}
			}
		}
		return width;
	}
	
	public float getCalculatedWidth(AnimatedText animatedText) {
		BitmapFontData data = font.getData();
		String text = animatedText.getFormattedText();
		int width = 0;
		for(int i = 0; i < text.length() && i <= animatedText.getLineBreakIndex(); i++) {
			char charAt = text.charAt(i);
			Glyph glyphAt = data.getGlyph(charAt);
			char space = ' ';
			if(charAt == space) {
				width += data.spaceWidth;
				System.out.println("Space found");
			} else {
				if(glyphAt != null) {
					System.out.println("Char at = " + charAt);
					width += glyphAt.width;
				}
			}
		}
		return width;
	}
	
	public int getCalculatedHeight(String text) {
		return 0;
	}

	public float getCalculatedHeight(AnimatedText text) {
		BitmapFontData data = font.getData();
		byte amountOfLineBreaks = (byte)(text.getAmountOfLineBreaks() + 1);
		return data.lineHeight * amountOfLineBreaks;
	}
	
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}

}
