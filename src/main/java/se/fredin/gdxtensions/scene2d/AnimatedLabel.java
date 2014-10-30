package se.fredin.gdxtensions.scene2d;

import se.fredin.gdxtensions.utils.text.AnimatedText;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class AnimatedLabel extends Label {

	private AnimatedText animatedText = new AnimatedText();
	
	public AnimatedLabel(CharSequence text, Skin skin) {
		super(text, skin);
	}

	public AnimatedLabel(CharSequence text, LabelStyle style) {
		super(text, style);
	}

	public AnimatedLabel(CharSequence text, Skin skin, String styleName) {
		super(text, skin, styleName);
	}

	public AnimatedLabel(CharSequence text, Skin skin, String fontName, Color color) {
		super(text, skin, fontName, color);
	}

	public AnimatedLabel(CharSequence text, Skin skin, String fontName, String colorName) {
		super(text, skin, fontName, colorName);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		tick(delta);
	}
	
	public void tick(float deltaTime) {
		animatedText.tick(deltaTime);
		String currentText = animatedText.getCurrentText();
		setText(currentText);
	}

}
