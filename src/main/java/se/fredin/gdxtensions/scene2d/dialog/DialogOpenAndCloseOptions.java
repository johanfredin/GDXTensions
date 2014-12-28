package se.fredin.gdxtensions.scene2d.dialog;

import se.fredin.gdxtensions.utils.LogUtils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Utility class for handling animation of dialog boxes as they open and close
 * @author Johan Fredin
 */
public class DialogOpenAndCloseOptions {

	private Dialog dialog;
	private Image frame;
	private Interpolation interpolation;
	private OpenCloseAnimation openCloseAnimation;
	private float x;
	private float y;
	private float lineHeight = 4f;
	private float width;
	private float height;
	private float frameWidth;
	private float frameHeight;
	private float duration;
	private boolean isTimeToCloseDialog;
	private boolean canTick;
	
	/**
	 * Construct a new dialog with no {@link Interpolation} and {@link Dialog#defaultDuration}
	 * @param dialog the {@link Dialog} we want to animate
	 */
	public DialogOpenAndCloseOptions(Dialog dialog) {
		this(dialog, Interpolation.linear, Dialog.defaultDuration);
	}
	
	/**
	 * Construct a new dialog with no {@link Interpolation} and custom duration
	 * @param dialog the {@link Dialog} we want to animate
	 * @param duration the duration of the opening and closing animation
	 */
	public DialogOpenAndCloseOptions(Dialog dialog, float duration) {
		this(dialog, Interpolation.linear, duration);
	}
	
	/**
	 * Construct a new dialog with custom {@link Interpolation} and {@link Dialog#defaultDuration}
	 * @param dialog the {@link Dialog} we want to animate
	 * @param interpolation the {@link Interpolation} we want to use for our animation
	 */
	public DialogOpenAndCloseOptions(Dialog dialog, Interpolation interpolation) {
		this(dialog, interpolation, Dialog.defaultDuration);
	}
	
	/**
	 * Construct a new dialog with custom {@link Interpolation} and duration
	 * @param dialog the {@link Dialog} we want to animate
	 * @param interpolation the {@link Interpolation} we want to use for our animation
	 * @param duration the duration of the opening and closing animation
	 */
	public DialogOpenAndCloseOptions(Dialog dialog, Interpolation interpolation, float duration) {
		this.dialog = dialog;
		this.frame = dialog.getFrame();
		this.x = dialog.getX();
		this.y = dialog.getY();
		this.width = dialog.getDialogWidth();
		this.height = dialog.getDialogHeight();
		this.frameWidth = dialog.getFrameWidth();
		this.frameHeight = dialog.getFrameHeight();
		this.interpolation = interpolation;
		this.openCloseAnimation = OpenCloseAnimation.QUAD_WIDTH_FIRST;
		this.duration = duration;
	}
	
	/**
	 * Helper enum to let us decide what type of animation we want to use,
	 * currently there are 3 animations.
	 * @author Johan Fredin
	 *
	 */
	public static enum OpenCloseAnimation {
		/** Will evenly size the dialog from 0 to its default size */
		QUAD_EVEN,
		/** Will first size on the width and then the height */
		QUAD_WIDTH_FIRST,
		/** Will fade in / out the dialog */
		FADE;
	}
	
	/**
	 * Set the height of the starting line (only useful with {@link OpenCloseAnimation#QUAD_EVEN} animation)
	 * @param lineHeight
	 */
	public void setLineHeight(float lineHeight) {
		this.lineHeight = lineHeight;
	}
	
	/**
	 * Set the duration we want for the opening and closing animation
	 * @param duration
	 */
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	/**
	 * @return the duration for opening and closing the dialog
	 */
	public float getDuration() {
		return duration;
	}
	
	/**
	 * Lets us set what kind of {@link Interpolation} we want to use for the animation
	 * @param interpolation
	 */
	public void setInterpolation(Interpolation interpolation) {
		this.interpolation = interpolation;
	}
	
	/**
	 * Makes the dialog open. Will use the default {@link OpenCloseAnimation#QUAD_EVEN}
	 * and {@link Dialog#defaultDuration} if no values for these have been previously set.
	 */
	public void openDialog() {
		openDialog(openCloseAnimation, duration);
	}
	
	/**
	 * Makes the dialog close. Will use the default {@link OpenCloseAnimation#QUAD_EVEN}
	 * and {@link Dialog#defaultDuration} if no values for these have been previously set.
	 */
	public void closeDialog() {
		closeDialog(openCloseAnimation, duration);
	}
	
	/**
	 * Makes the dialog open with custom duration.
	 * will use {@link OpenCloseAnimation#QUAD_EVEN} if none has been previously set
	 * @param duration the duration of the animation
	 */
	public void openDialog(float duration) {
		openDialog(openCloseAnimation, duration);
	}
	
	/**
	 * Makes the dialog close with custom duration.
	 * will use {@link OpenCloseAnimation#QUAD_EVEN} if none has been previously set
	 * @param duration the duration of the animation
	 */
	public void closeDialog(float duration) {
		closeDialog(openCloseAnimation, duration);
	}
	
	/**
	 * Makes the dialog open with custom animation. will use {@link Dialog#defaultDuration} if none is previously set.
	 * @param openCloseAnimation the animation to use
	 */
	public void openDialog(OpenCloseAnimation openCloseAnimation) {
		openDialog(openCloseAnimation, duration);
	}
	
	/**
	 * Makes the dialog close with custom animation. will use {@link Dialog#defaultDuration} if none is previously set.
	 * @param openCloseAnimation the animation to use
	 */
	public void closeDialog(OpenCloseAnimation openCloseAnimation) {
		closeDialog(openCloseAnimation, duration);
	}
	
	/**
	 * Makes the dialog open with custom animation and duration
	 * @param openCloseAnimation the animation to use 
	 * @param duration the duration of the animation
	 */
	public void openDialog(OpenCloseAnimation openCloseAnimation, final float duration) {
		final float innerDuration = duration / 2;
		this.dialog.setPosition(x, y);
		float delayTime = duration / 8;
		
		switch(openCloseAnimation) {
		case QUAD_EVEN:
			this.frame.addAction(Actions.moveBy(-frameWidth / 2, -frameHeight / 2, duration, interpolation));
			this.dialog.addAction(Actions.moveBy(-width / 2, -height / 2, innerDuration, interpolation));
			this.frame.addAction(Actions.sequence(Actions.sizeTo(frameWidth, frameHeight, duration, interpolation), Actions.delay(delayTime), Actions.after(Actions.run(tickRunnable()))));
			this.dialog.addAction(Actions.sizeTo(width, height, innerDuration, interpolation));
			break;
		case QUAD_WIDTH_FIRST:
			this.frame.addAction(Actions.moveBy(-frameWidth / 2, -lineHeight / 2, duration, interpolation));
			this.dialog.addAction(Actions.moveBy(-width / 2, -lineHeight / 2, innerDuration, interpolation));
			this.frame.addAction(Actions.sequence(Actions.sizeTo(frameWidth, lineHeight, duration, interpolation)));
			this.dialog.addAction(Actions.sequence(Actions.sizeTo(width, lineHeight, innerDuration, interpolation), Actions.delay(delayTime), Actions.after(Actions.run(new Runnable() {
				@Override
				public void run() {
					float halfLine = lineHeight / 2;
					dialog.addAction(Actions.moveBy(0, (-height / 2 - halfLine), innerDuration, interpolation));
					frame.addAction(Actions.moveBy(0, (-frameHeight / 2 - halfLine), duration, interpolation));
					dialog.addAction(Actions.sizeBy(0, (height - lineHeight), innerDuration, interpolation));
					frame.addAction(Actions.sequence(Actions.sizeBy(0, (frameHeight - lineHeight), duration, interpolation), Actions.after(Actions.run(tickRunnable()))));
				}
			}))));
			break;
		case FADE:
			this.dialog.setPosition(dialog.getX() - width / 2, dialog.getY() - height / 2);
			float xDiff = ((frameWidth - width) / 2) * -1;
			float yDiff = ((frameHeight - height) / 2) * -1;
			this.frame.moveBy(xDiff, yDiff);
			this.dialog.addAction(Actions.sequence(Actions.fadeOut(0.0f), Actions.after(Actions.run(size(dialog, width, height)))));
			this.frame.addAction(Actions.sequence(Actions.fadeOut(0.0f), Actions.after(Actions.run(size(frame, frameWidth, frameHeight)))));
			this.dialog.addAction(Actions.fadeIn(duration, interpolation));
			this.frame.addAction(Actions.sequence(Actions.fadeIn(duration, interpolation), Actions.delay(delayTime), Actions.after(Actions.run(tickRunnable()))));

			break;
		}
	}
	
	/**
	 * Makes the dialog close with custom animation and duration
	 * @param openCloseAnimation the animation to use 
	 * @param duration the duration of the animation
	 */
	public void closeDialog(OpenCloseAnimation openCloseAnimation, final float duration) {
		this.isTimeToCloseDialog = true;
		
		// Empty the text in the box when closing
		this.dialog.setText("");
		final float innerDuration = duration / 2;
				
		switch(openCloseAnimation) {
		case QUAD_EVEN:
			this.frame.addAction(Actions.moveBy(frameWidth / 2, frameHeight / 2, duration));
			this.frame.addAction(Actions.sizeTo(0, 0, duration));
			this.dialog.addAction(Actions.moveBy(width / 2, height / 2, innerDuration));
			this.dialog.addAction(Actions.sequence(Actions.sizeTo(0, 0, innerDuration, interpolation), Actions.delay(duration / 4), Actions.after(Actions.run(closeRunnable()))));
			break;
		case QUAD_WIDTH_FIRST:
			this.frame.addAction(Actions.moveBy(0, (frameHeight / 2) + (lineHeight), duration, interpolation));
			this.dialog.addAction(Actions.moveBy(0, (height / 2) + (lineHeight), innerDuration, interpolation));
			this.frame.addAction(Actions.sizeBy(0, -frameHeight + (lineHeight), duration, interpolation));
			this.dialog.addAction(Actions.sequence(Actions.sizeBy(0, -height + (lineHeight), innerDuration, interpolation), Actions.after(Actions.run(new Runnable() {
				@Override
				public void run() {
					dialog.addAction(Actions.moveBy(width / 2, 0, innerDuration, interpolation));
					frame.addAction(Actions.moveBy(frameWidth / 2, 0, duration, interpolation));
					dialog.addAction(Actions.sizeBy(-width, 0, innerDuration, interpolation));
					frame.addAction(Actions.sequence(Actions.sizeBy(-frameWidth, 0, duration, interpolation), Actions.after(Actions.run(closeRunnable()))));
				}
			}))));
			break;
		case FADE:
			this.dialog.addAction(Actions.fadeOut(duration, interpolation));
			this.frame.addAction(Actions.sequence(Actions.fadeOut(duration, interpolation), Actions.after(Actions.run(closeRunnable()))));
			break;
		}
	}
	
	/**
	 * @return whether or not the dialog can be closed
	 */
	public boolean isTimeToCloseDialog() {
		return isTimeToCloseDialog;
	}
	
	/**
	 * @return whether or not we can start displaying text in the dialog
	 */
	public boolean isCanTick() {
		return canTick;
	}
	
	private Runnable size(final Actor actor, final float width, final float height) {
		return new Runnable() {
			@Override
			public void run() {
				actor.setSize(width, height);
			}
		};
	}
	
	private Runnable tickRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				canTick = true;
			}
		};
	}
	
	private Runnable closeRunnable() {
		return new Runnable() {
			@Override
			public void run() {
				Stage stage = dialog.getStage();
				if(stage != null) {
					stage.getActors().removeValue(dialog, true);
					stage.getActors().removeValue(frame, true);
					LogUtils.log("Dialog succesfully removed from stage");
				}
			}
		};
	}
	

}
