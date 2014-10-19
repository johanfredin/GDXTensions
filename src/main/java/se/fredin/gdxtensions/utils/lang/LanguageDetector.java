package se.fredin.gdxtensions.utils.lang;

import java.util.Locale;


/**
 * Class used for detecting the users language via System properties.
 * Currently works for English and Spanish. If another language is detected
 * then English will be the default language
 * @author Johan Fredin
 *
 */
public class LanguageDetector {
	
	/** The default language that will be returned if no other supported language found */
	public static final String DEFAULT_LANGUAGE = "en";
	
	/** If true forces the game to use Spanish language */
	public static boolean useSpanishLanguage;

	private static String language;
	
	/**
	 * Lets us retrieve the users language, will also add a _ suffix if 
	 * we find another language than English, this is used to retrieve language specific
	 * images in our pack files.
	 * @param emptyStringIfEnglish if true we will only return an empty string if language is English
	 * @return a modified user language string to work with our atlas
	 */
	public static String getUserLanguage(boolean emptyStringIfEnglish) {
		if(noSpecialPreferencesSet()) {
			language = Locale.getDefault().getLanguage();
			if(!language.equalsIgnoreCase(DEFAULT_LANGUAGE)){
				if(!isUnsuportedLanguage()) {
					return "_" + language;
				} 
			}
		} else if(useSpanishLanguage) {
			return "_es";
		}
		
		return emptyStringIfEnglish ? "" : "_" + DEFAULT_LANGUAGE;
	}
	
	
	public static String getUserLanguage() {

		language = Locale.getDefault().getLanguage();
		if(noSpecialPreferencesSet() && !isUnsuportedLanguage()) {
			return language;
		} else {
			return DEFAULT_LANGUAGE;
		}
	}
	
	public static boolean isSpanishLanguage() {
		return language == "es" || language == "_es" || useSpanishLanguage;
	}
	
	public static final boolean noSpecialPreferencesSet() {
		return !useSpanishLanguage;
	}
	
	public static boolean isUnsuportedLanguage() {
		return !language.equalsIgnoreCase("es");
	}
 
}
