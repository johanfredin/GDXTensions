package se.fredin.gdxtensions.screen.ingame;

import se.fredin.gdxtensions.assetmanagement.Assets;
import se.fredin.gdxtensions.level.LevelBase;
import se.fredin.gdxtensions.screen.BaseScreen;
import se.fredin.gdxtensions.utils.lang.LanguageBasedSkin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.FillViewport;

/**
 * Super class for all in game scene2d objects
 * @author Johan Fredin
 *
 */
public abstract class InGameScreen implements Disposable {

	/** Used to detect if the game is paused */
	public static boolean isPaused;

	protected LanguageBasedSkin skin;
	protected LevelBase levelBase;
	protected Stage stage;
	
	protected Image imageToUseForFlashingEffect;
	
	/** The view port world width */
	protected float worldWidth;
	/** The view port world height */
	protected float worldHeight;
	
	/**
	 * Create a new dialog and set up an input multiplexer so we can receive input 
	 * from the stage AND our customized back button handler.
	 * @param levelBase the levelBase this dialog will be operating on
	 */
	public InGameScreen(LevelBase levelBase, String packFilePath) {
		this.levelBase = levelBase;
		
		Vector2 size = Scaling.fill.apply(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), BaseScreen.viewportWidth, BaseScreen.viewportHeight);
		this.stage = new Stage(new FillViewport(size.x, size.y));
		this.stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		
		this.worldHeight = stage.getViewport().getWorldHeight();
		this.worldWidth = stage.getViewport().getWorldWidth();
		
		TextureAtlas atlas = (TextureAtlas) Assets.getInstance().get(packFilePath);
		this.skin = new LanguageBasedSkin(atlas);
		
		// We only instantiate the white canvas image here, it's up to the sub-classes to handle it 
		// the way they see fit.
//		this.imageToUseForFlashingEffect = new Image(skin.getDrawable("whiterect"));
		
		// We want to handle the back button differently in pause dialog!
		Gdx.input.setInputProcessor(this.stage);

	}

	/**
	 * Used to animate a selection or all of the actors in the stage
	 * @param down whether to move the actors down or up.
	 * @param actionPerformedOnceFinished what we want to do when the actors are finished (if any)
	 */
	protected void animateStage(boolean down, byte actionPerformedOnceFinished) {}
	
	/**
	 * Sets the initial positions of the actors.
	 */
	protected void setInitialPositions() {}
	
	/**
	 * Draws the stage
	 */
	public void render() {
		stage.draw();
	}
	
	/**
	 * Updates our stage. Also keeps track
	 * of if the screen has been touched so 
	 * that the player knows it should jump
	 * @param deltaTime
	 */
	public void tick(float deltaTime) {
		stage.act(deltaTime);
	}

	protected float getCorectY(Button button, float desiredYPos) {
		return worldHeight - button.getPrefHeight() - desiredYPos;
	}

	protected float getCorectY(Label button, float desiredYPos) {
		return worldHeight - button.getPrefHeight() - desiredYPos;
	}

	protected float getCorectY(Image image, float desiredYPos) {
		return worldHeight - image.getPrefHeight() - desiredYPos;
	}

	/**
	 * Creates a repeating flash animation for an actor.
	 * @param actor the actor we want to affect
	 */
	protected void createFlashingAnimation(Actor actor) {
		actor.addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(Actions.fadeOut(0.1f), Actions.delay(0.6f), Actions.fadeIn(0.1f), Actions.delay(0.6f))));
	}
	
	/**
	 * Adds a group of actors to the stage at once
	 * @param actors the actors we want to add to the stage
	 */
	protected void addActorsToStage(Actor...actors) {
		for(Actor actor : actors) {
			stage.addActor(actor);
		}
	}
	
	/**
	 * Used to fade out the white canvas we use.
	 * Once its finished fading we hide it so that the other actors 
	 * can receive touch focus.
	 * @param fadeTime
	 */
	protected void fadeOutAndHideWhiteCanvas(float fadeTime) {
		imageToUseForFlashingEffect.addAction(Actions.sequence(Actions.fadeOut(fadeTime), Actions.run(new Runnable() {
			@Override
			public void run() {
				imageToUseForFlashingEffect.setVisible(false);
			}
		})));
	}

	/**
	 * Decides what to do depending on what button you pressed. levelBase finish and death dialog share this and therefore it has a boolean
	 * to check that the player don't unlock another levelBase if you died
	 * @param button the button to affect.
	 * @param ACTION what to do after the button was pressed
	 */
	protected void setButtonListener(Button button, final byte ACTION) {
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				// If we did not die or reach the last levelBase we want to unlock the following levelBase.
				animateStage(false, ACTION);
			}
		});
	}

	/**
	 * Flashes the white canvas, used when the player hits the orb
	 */
	protected void flashScreen() {
		imageToUseForFlashingEffect.setVisible(true);
		imageToUseForFlashingEffect.addAction(Actions.sequence(Actions.fadeIn(0.0f), Actions.fadeOut(1.2f), Actions.run(new Runnable() {
			@Override
			public void run() {
				imageToUseForFlashingEffect.setVisible(false);
			}
		})));
	}
	
	public Stage getStage() {
		return stage;
	}
	
	@Override
	public void dispose() {
		Gdx.app.log(this.getClass().getSimpleName(), "dispose called");
		stage.dispose();
	}
	
	/**
	 * Disables touch focus for all the actors of the stage
	 */
	protected void disableStage() {
		for(Actor actor : stage.getActors()) {
			actor.setTouchable(Touchable.disabled);
		}
	}

}