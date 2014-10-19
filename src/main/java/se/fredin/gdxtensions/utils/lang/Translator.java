package se.fredin.gdxtensions.utils.lang;

import java.io.IOException;
import java.io.InputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

/**
 * Class for retrieving map hints depending on users language. Currently only english
 * works. Uses an XML file to retrieve the map hints
 * @author Johan Fredin, Tracy Wang
 *
 */
public class Translator {
	
	private static Translator translator;
	
	/** Our hints map */
	private Properties properties;
	
	/**
	 * Checks our object map for the given keys and puts them into an array
	 * @param keys the hint keys4
	 * @return'
	 * +
	 * 
	 */
	public Array<String> getHints(String... keys) {
		Array<String> hintsArray = new Array<String>();
		for(String hint : keys) {
			try {
				hintsArray.add((String) properties.get(hint));
			} catch(Exception ex) {}
		}
		return hintsArray;
	}
	
	public static Translator getInstance() {
		if(translator == null){
			translator = new Translator();
		}
		return translator;
	}
	
	/**
	 * Retrieve the correct language string associated with users language
	 * @param key the string we want to translate
	 * @return a translated string
	 */
	public String getTranslation(String key) {
		return (String) properties.get(key);
	}
	
	/** Instantiates the XMLReader and our elements map */
	private Translator() {
		properties = new Properties();
		InputStream hintsXmlFile = Gdx.files.internal("language_files/" + LanguageDetector.getUserLanguage() + ".xml").read();
		
		
		try {
			properties.loadFromXML(hintsXmlFile);
		} catch (InvalidPropertiesFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	
}
