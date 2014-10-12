package se.fredin.gdxtensions.screen;

import se.fredin.gdxtensions.res.Assets;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * MenuBaseScreen extends the BaseScreen and adds further functionality for screens using
 * a stage. Contains a TextureAtlas, Skin and a Stage as well as fields for width, height and center positions.
 * @author Johan Fredin
 *
 */
public abstract class MenuBaseScreen extends BaseScreen {

	protected Skin skin;
	protected TextureAtlas atlas;
	protected Stage stage;

	/** Our stages viewport world width */
	protected float worldWidth;
	/** Our stage viewport world height */
	protected float worldHeight;
	/** Our stages center x position */
	protected float centerX;
	/** Our stages center y position */
	protected float centerY;
	
	/**
	 * Create a new menu base screen
	 * @param game
	 */
	public MenuBaseScreen(Game game) {
		super(game);
		this.stage = new Stage(new StretchViewport(viewportWidth, viewportHeight));
		this.worldWidth = stage.getViewport().getWorldWidth();
		this.worldHeight = stage.getViewport().getWorldHeight();
		this.centerX = worldWidth / 2;
		this.centerY = worldHeight / 2;
		Gdx.input.setInputProcessor(stage);
	}
	
	/**
	 * Create a new menu base screen
	 * @param game
	 * @param pathToPackFile the path to the pack file we want the skin to operate on
	 * @param alternatives what we want to happen when the back button is pressed in this screen.
	 */
	public MenuBaseScreen(Game game, String pathToPackFile) {
		this(game);
		this.atlas = (TextureAtlas) Assets.getInstance().get(pathToPackFile);
		this.skin = new Skin(atlas);
	}
	
	/**
	 * Create a new menu base screen
	 * @param game
	 * @param pathToPackFile the path to the pack file we want the skin to operate on
	 * @param alternatives what we want to happen when the back button is pressed in this screen.
	 * @param viewportForStage the viewport we want the stage to have
	 */
	public MenuBaseScreen(Game game, String pathToPackFile, Viewport viewportForStage) {
		this(game, pathToPackFile);
		this.stage.setViewport(viewportForStage);
	}

	@Override
	public void render(float delta) {
		camera.update();
		stage.draw();
		tick(delta);
	}
	
	/**
	 * update menu states
	 * @param deltaTime
	 */
	public abstract void tick(float deltaTime);
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		stage.getViewport().update(width, height);
	}

	/**
	 * Flashes the actor touched, plays a sound effect and fades out the screen
	 * @button - the button that was pressed
	 * @duration - the fade in duration for the next screen.
	 */
	protected void animateButtonAndFadeOutScreen(Actor button, float buttonFlashDuration, float fadeInDuration) {
		button.addAction(Actions.repeat(3, Actions.sequence(Actions.fadeOut(buttonFlashDuration), Actions.after(Actions.fadeIn(buttonFlashDuration)))));
		stage.addAction(Actions.sequence(Actions.delay(buttonFlashDuration * 6f), Actions.fadeOut(fadeInDuration)));
	}

	/**
	 * If your camera is flipped and the stage is using its own normal camera, 
	 * this method is convenient for easier calculating the correct 
	 * y value our actor needs, makes the code a bit cleaner.
	 * @param button
	 * @param desiredYPos
	 * @return
	 */
	protected float getCorrectY(Button button, float desiredYPos) {
		return worldHeight - button.getPrefHeight() - desiredYPos;
	}

	/**
	 * Our cameras y is flipped, the stage is not, therefore we make this method for easier calculating the correct 
	 * y value our actor needs, makes the code a bit cleaner.
	 * @param textField
	 * @param desiredYPos
	 * @return
	 */
	protected float getCorrectY(TextField textField, float desiredYPos) {
		return worldHeight - textField.getPrefHeight() - desiredYPos;
	}

	/**
	 * Our cameras y is flipped, the stage is not, therefore we make this method for easier calculating the correct 
	 * y value our actor needs, makes the code a bit cleaner.
	 * @param image
	 * @param desiredYPos
	 * @return
	 */
	protected float getCorrectY(Image image, float desiredYPos) {
		return worldHeight - image.getPrefHeight() - desiredYPos;
	}

	/**
	 * Our cameras y is flipped, the stage is not, therefore we make this method for easier calculating the correct 
	 * y value our actor needs, makes the code a bit cleaner.
	 * @param label
	 * @param desiredYPos
	 * @return
	 */
	protected float getCorectY(Label label, float desiredYPos) {
		return worldHeight - label.getPrefHeight() - desiredYPos;
	}

	/**
	 * Lets us create an image with a little more features from start
	 * @param drawable the name of the image
	 * @param x
	 * @param y
	 * @param visible whether or not this image should be visible from start
	 * @return
	 */
	protected Image getCustomizedImage(String drawable, float x, float y, boolean visible) {
		Image image = new Image(skin.getDrawable(drawable));
		image.setPosition(x, getCorrectY(image, y));
		image.setVisible(visible);
		return image;
	}

	/**
	 * Lets us create a button with more features from start.
	 * @param up name of the image used when this button is not touched
	 * @param down name of the image used when this button is touched
	 * @param x
	 * @param y
	 * @param visible whether or not this button should be visible from start
	 * @return
	 */
	protected Button getCustomizedButton(String up, String down, float x, float y, boolean visible) {
		Button button = new Button(skin.getDrawable(up), skin.getDrawable(down));
		button.setPosition(x, getCorrectY(button, y));
		button.setVisible(visible);
		return button;
	}

	/**
	 * Lets us create a checkbox with more features from start
	 * @param unChecked name of the image used when this checkbox is unchecked
	 * @param checked name of the image used when this checkbox is checked
	 * @param visible whether this checkbox should be visible from start
	 * @param isChecked whether this checkbox should be checked from start
	 * @return
	 */
	protected CheckBox getCustomizedCheckBox(String unChecked, String checked, boolean visible, boolean isChecked) {
		CheckBox checkBox = new CheckBox("", new CheckBoxStyle(skin.getDrawable(unChecked), skin.getDrawable(checked), new BitmapFont(), null));
		checkBox.setVisible(visible);
		checkBox.setChecked(isChecked);
		checkBox.setTouchable(visible ? Touchable.enabled : Touchable.disabled);
		return checkBox;
	}
	
	/**
	 * Lets us create a label with more features from start
	 * @param text the text this label will have
	 * @param labelStyle the label style this label will have
	 * @param x
	 * @param y
	 * @param visible whether or not this label should be visible from start.
	 * @return
	 */
	protected Label getCustomizedLabel(String text, LabelStyle labelStyle, float x, float y, boolean visible) {
		Label label = new Label(text, labelStyle);
		label.setPosition(x, y);
		label.setVisible(visible);
		return label;
	}

	/**
	 * Adds all the passed in actors to the stage
	 * @param actors
	 */
	protected void addActorsToStage(Actor...actors) {
		for(Actor actor : actors) {
			stage.addActor(actor);
		}
	}

	/**
	 * Disables touch focus for all actors in the stage.
	 */
	protected void disableActors() {
		for(Actor actor : stage.getActors()) {
			actor.setTouchable(Touchable.disabled);
		}
	}
	
	/**
	 * Check if the actors actions are completed
	 * @param actor
	 * @return true if the actor no longer has any actions
	 */
	protected boolean finishedActing(Actor actor) {
		return actor.getActions().size <= 0;
	}
	
	@Override
	public void hide() {
		super.hide();
		dispose();
	}
	
	@Override
	public void dispose() {
		super.dispose();
		stage.dispose();
	}
}
