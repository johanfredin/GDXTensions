package se.fredin.gdxtensions.utils;

import se.fredin.gdxtensions.utils.text.OutputFormatter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Used to display an old school RPG like dialog where the characters
 * show up one after the other. User will be able to specify the 
 * speed and width of the dialogs.
 * @author Johan Fredin
 *
 */
public class Dialog {

	private float timePerCharacter;
	private float timer;
	private String formattedText;
	
	/**
	 * Constructs a new {@link Dialog} instance
	 * @param speed how fast the characters will appear
	 * @param maxWidth the max width until break row
	 */
	public Dialog(String text, float timePerCharacter, int lineBreakIndex, boolean absoluteBreakRow) {
		this.timePerCharacter = timePerCharacter;
		this.formattedText = new OutputFormatter().getFormatedString(lineBreakIndex, text, absoluteBreakRow);
	}

	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	int index = 0;
	float counter = timePerCharacter;
	public void tick(float deltaTime, String text) {
		if(timer >= counter && index < formattedText.length()) {
			System.out.print(formattedText.charAt(index));
			counter += timePerCharacter;
			index++;
		} else {
			timer += deltaTime;
		}
	}
	
	
	
}
