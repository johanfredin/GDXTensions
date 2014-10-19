package se.fredin.gdxtensions.utils.lang;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Customized skin that checks for images with a language suffix depending on the language
 * the user has.
 * @author Johan Fredin
 *
 */
public class LanguageBasedSkin extends Skin {
	
	public LanguageBasedSkin(TextureAtlas atlas) {
		super(atlas);
	}

	@Override
	public Drawable getDrawable(String name) {
		String nameWithLanguageSuffix = name + LanguageDetector.getUserLanguage(true);
		if(getAtlas().findRegion(nameWithLanguageSuffix) == null) {
			return super.getDrawable(name);
		}
		return super.getDrawable(nameWithLanguageSuffix);
	}
	
	@Override
	public TextureRegion getRegion(String name) {
		String nameWithLanguageSuffix = name + LanguageDetector.getUserLanguage(true);
		if(getAtlas().findRegion(nameWithLanguageSuffix) == null) {
			return super.getRegion(name);
		}
		return super.getRegion(nameWithLanguageSuffix);
	}

}
