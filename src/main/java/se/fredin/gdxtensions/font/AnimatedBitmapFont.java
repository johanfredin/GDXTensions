package se.fredin.gdxtensions.font;

import se.fredin.gdxtensions.utils.BitmapFontBoundsCalculator;
import se.fredin.gdxtensions.utils.text.AnimatedText;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;

/**
 * Extension of the standard GDX {@link BitmapFont} but with methods for easier drawing
 * {@link AnimatedText} strings. Can be used as a standard bitmap font as well as all of the constructors
 * from {@link BitmapFont} are extended as well.
 * @author Johan Fredin
 *
 */
public class AnimatedBitmapFont extends BitmapFont {
	
	/** the x position of the font */
	public float x;
	/** the y position of the font */
	public float y;
	/** whether or not to use line breaks (default is <code>true</code>)*/
	public boolean isMultiLine = true;
	private BitmapFontBoundsCalculator bitmapFontBoundsCalculator = new BitmapFontBoundsCalculator(this);
	
	/** 
	 * Creates a BitmapFont using the default 15pt Arial font included in the libgdx JAR file. This is convenient to 
 	 * easily display text without bothering with generating a bitmap font.
	*/
	public AnimatedBitmapFont() {
		super();
	}
	
	/** Creates a BitmapFont using the default 15pt Arial font included in the libgdx JAR file. This is convenient to easily display
	 * text without bothering with generating a bitmap font.
	 * @param flip If true, the glyphs will be flipped for use with a perspective where 0,0 is the upper left corner. */
	public AnimatedBitmapFont(boolean flip) {
		super(flip);
	}

	/** Creates a BitmapFont from a BMFont file. The image file name is read from the BMFont file and the image is loaded from the
	 * same directory. The font data is not flipped. */
	public AnimatedBitmapFont(FileHandle fontFile) {
		super(fontFile);
	}

	/** Creates a BitmapFont with the glyphs relative to the specified region. If the region is null, the glyph textures are loaded
	 * from the image file given in the font file. The {@link #dispose()} method will not dispose the region's texture in this
	 * case!
	 * 
	 * The font data is not flipped.
	 * 
	 * @param fontFile the font definition file
	 * @param region The texture region containing the glyphs. The glyphs must be relative to the lower left corner (ie, the region
	 *           should not be flipped). If the region is null the glyph images are loaded from the image path in the font file. */
	public AnimatedBitmapFont(FileHandle fontFile, TextureRegion region) {
		super(fontFile, region);
	}

	public AnimatedBitmapFont(FileHandle fontFile, boolean flip) {
		super(fontFile, flip);
	}

	/** Creates a BitmapFont with the glyphs relative to the specified region. If the region is null, the glyph textures are loaded
	 * from the image file given in the font file. The {@link #dispose()} method will not dispose the region's texture in this
	 * case!
	 * @param region The texture region containing the glyphs. The glyphs must be relative to the lower left corner (ie, the region
	 *           should not be flipped). If the region is null the glyph images are loaded from the image path in the font file.
	 * @param flip If true, the glyphs will be flipped for use with a perspective where 0,0 is the upper left corner. */
	public AnimatedBitmapFont(FileHandle fontFile, TextureRegion region, boolean flip) {
		super(fontFile, region, flip);
	}

	/** Constructs a new BitmapFont from the given {@link BitmapFontData} and {@link TextureRegion}. If the TextureRegion is null,
	 * the image path(s) will be read from the BitmapFontData. The dispose() method will not dispose the texture of the region(s)
	 * if the region is != null.
	 * 
	 * Passing a single TextureRegion assumes that your font only needs a single texture page. If you need to support multiple
	 * pages, either let the Font read the images themselves (by specifying null as the TextureRegion), or by specifying each page
	 * manually with the TextureRegion[] constructor.
	 * 
	 * @param integer If true, rendering positions will be at integer values to avoid filtering artifacts. */
	public AnimatedBitmapFont(FileHandle fontFile, FileHandle imageFile, boolean flip) {
		super(fontFile, imageFile, flip);
	}
	
	/** Constructs a new BitmapFont from the given {@link BitmapFontData} and {@link TextureRegion}. If the TextureRegion is null,
	 * the image path(s) will be read from the BitmapFontData. The dispose() method will not dispose the texture of the region(s)
	 * if the region is != null.
	 * 
	 * Passing a single TextureRegion assumes that your font only needs a single texture page. If you need to support multiple
	 * pages, either let the Font read the images themselves (by specifying null as the TextureRegion), or by specifying each page
	 * manually with the TextureRegion[] constructor.
	 * 
	 * @param integer If true, rendering positions will be at integer values to avoid filtering artifacts. */
	public AnimatedBitmapFont(BitmapFontData data, TextureRegion region, boolean integer) {
		super(data, region, integer);
	}

	/** Constructs a new BitmapFont from the given {@link BitmapFontData} and array of {@link TextureRegion}. If the TextureRegion
	 * is null or empty, the image path(s) will be read from the BitmapFontData. The dispose() method will not dispose the texture
	 * of the region(s) if the regions array is != null and not empty.
	 * 
	 * @param integer If true, rendering positions will be at integer values to avoid filtering artifacts. */
	public AnimatedBitmapFont(BitmapFontData data, TextureRegion[] regions, boolean integer) {
		super(data, regions, integer);
	}

	/** Creates a BitmapFont from a BMFont file, using the specified image for glyphs. Any image specified in the BMFont file is
	 * ignored.
	 * @param flip If true, the glyphs will be flipped for use with a perspective where 0,0 is the upper left corner.
	 * @param integer If true, rendering positions will be at integer values to avoid filtering artifacts. */
	public AnimatedBitmapFont(FileHandle fontFile, FileHandle imageFile, boolean flip, boolean integer) {
		super(fontFile, imageFile, flip, integer);
	}
	
	/**
	 * Lets you draw the current state of an {@link AnimatedText}. The outcome will be similar to a automatically updating text being displayed.
	 * @param batch the {@link SpriteBatch} to draw the text
	 * @param animatedText the {@link AnimatedText}
	 */
	public void drawAnimatedText(SpriteBatch batch, AnimatedText animatedText) {
		drawAnimatedText(batch, animatedText, x, y);
	}
	
	/**
	 * Lets you draw the current state of an {@link AnimatedText}. The outcome will be similar to a automatically updating text being displayed.
	 * @param batch the {@link SpriteBatch} to draw the text
	 * @param animatedText the {@link AnimatedText}
	 * @param x the x position
	 * @param y the y position
	 */
	public void drawAnimatedText(SpriteBatch batch, AnimatedText animatedText, float x, float y) {
		if(isMultiLine) {
			drawMultiLine(batch, animatedText.getCurrentText(), x, y);
		} else {
			draw(batch, animatedText.getCurrentText(), x, y);
		}
	}
	
	public float getWidth(AnimatedText animatedText) {
		return this.bitmapFontBoundsCalculator.getCalculatedWidth(animatedText);
	}
	
	public float getHeight(AnimatedText animatedText) {
		return this.bitmapFontBoundsCalculator.getCalculatedHeight(animatedText);
	}
	
	public float getHeight(AnimatedText animatedText, byte amountOfAdditionalLineBreaks) {
		return this.bitmapFontBoundsCalculator.getCalculatedHeight(animatedText, amountOfAdditionalLineBreaks);
	}
	
	public void addLineBreaks(byte amount) {
		this.bitmapFontBoundsCalculator.addLinebreaks(amount);
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public void setMultiLine(boolean isMultiLine) {
		this.isMultiLine = isMultiLine;
	}
	
	public boolean isMultiLine() {
		return isMultiLine;
	}

	
}
