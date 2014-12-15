package se.fredin.gdxtensions.utils;

import se.fredin.gdxtensions.font.AnimatedBitmapFont;
import se.fredin.gdxtensions.utils.text.AnimatedText;

import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.BitmapFont.Glyph;

/** 
 * Helper class to figure out exact width and height of a bitmapfont. 
 * Useful when not using a monospace type font.
 */
public class BitmapFontBoundsCalculator {

	private AnimatedBitmapFont font;
	private float width;
	private float height;
	private boolean isMonoSpace;
	
	public BitmapFontBoundsCalculator(AnimatedBitmapFont font) {
		this.font = font;
	}
	
	public BitmapFontBoundsCalculator(AnimatedBitmapFont font, AnimatedText text) {
		this.font = font;
		this.width = getCalculatedWidth(text);
		this.height = getCalculatedHeight(text);
	}
	
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
					dynamicWidth += data.spaceWidth;
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
	
	public float getCalculatedHeight(AnimatedText text) {
		BitmapFontData data = font.getData();
		byte amountOfLineBreaks = (byte)(text.getAmountOfLineBreaks() + 1);
		return data.lineHeight * amountOfLineBreaks;
	}
	
	/**
	 * Checks if the width of each glyph differs, slight differ indicates that the font is not monospace
	 * @return whether or not the font is a monospace type font.
	 */
	public boolean checkIfMonoSpace() {
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
	
	
	public float getWidth() {
		return width;
	}
	
	public float getHeight() {
		return height;
	}
	
	public boolean isMonoSpace() {
		return isMonoSpace;
	}

}
