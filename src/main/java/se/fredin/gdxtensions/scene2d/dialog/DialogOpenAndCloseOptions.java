package se.fredin.gdxtensions.scene2d.dialog;

import se.fredin.gdxtensions.utils.LogUtils;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Utility class for handeling animation of dialog boxes as they open and close
 * @author Johan Fredin
 *
 */
public class DialogOpenAndCloseOptions {

	private Dialog dialog;
	private Image frame;
	private Interpolation interpolation;
	private OpenCloseAnimation openCloseAnimation;
	private float x;
	private float y;
	private float width;
	private float height;
	private float frameWidth;
	private float frameHeight;
	private float duration;
	private boolean isTimeToCloseDialog;
	private boolean canTick;
	
	public DialogOpenAndCloseOptions(Dialog dialog) {
		this(dialog, Interpolation.linear, Dialog.defaultDuration);
	}
	
	public DialogOpenAndCloseOptions(Dialog dialog, float duration) {
		this(dialog, Interpolation.linear, duration);
	}
	
	public DialogOpenAndCloseOptions(Dialog dialog, Interpolation interpolation) {
		this(dialog, interpolation, Dialog.defaultDuration);
	}
	
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
		this.openCloseAnimation = OpenCloseAnimation.QUAD_EVEN;
		this.duration = duration;
	}
	
	public static enum OpenCloseAnimation {
		QUAD_EVEN,
		QUAD_WIDTH_FIRST,
		FADE;
	}
	
	public void setDuration(float duration) {
		this.duration = duration;
	}
	
	public float getDuration() {
		return duration;
	}
	
	public void setInterpolation(Interpolation interpolation) {
		this.interpolation = interpolation;
	}
	
	public void openDialog() {
		openDialog(openCloseAnimation, duration);
	}
	
	public void closeDialog() {
		closeDialog(openCloseAnimation, duration);
	}
	
	public void openDialog(float duration) {
		openDialog(openCloseAnimation, duration);
	}
	
	public void closeDialog(float duration) {
		closeDialog(openCloseAnimation, duration);
	}
	
	public void openDialog(OpenCloseAnimation openCloseAnimation) {
		openDialog(openCloseAnimation, duration);
	}
	
	public void closeDialog(OpenCloseAnimation openCloseAnimation) {
		closeDialog(openCloseAnimation, duration);
	}
	
	public void openDialog(OpenCloseAnimation openCloseAnimation, float duration) {

		float innerDuration = duration / 2;
		this.dialog.setPosition(x, y);
		
		switch(openCloseAnimation) {
		case QUAD_EVEN:
			this.frame.addAction(Actions.moveBy(-frameWidth / 2, -frameHeight / 2, duration, interpolation));
			this.dialog.addAction(Actions.moveBy(-width / 2, -height / 2, innerDuration, interpolation));
			this.frame.addAction(Actions.sequence(Actions.sizeTo(frameWidth, frameHeight, duration, interpolation), Actions.delay(duration / 4), Actions.after(Actions.run(tickRunnable()))));
			this.dialog.addAction(Actions.sizeTo(width, height, innerDuration, interpolation));
			break;
		case QUAD_WIDTH_FIRST:
			break;
		case FADE:
			this.dialog.addAction(Actions.sequence(Actions.fadeOut(0.0f), Actions.after(Actions.run(size(dialog, width, height)))));
			this.frame.addAction(Actions.sequence(Actions.fadeOut(0.0f), Actions.after(Actions.run(size(frame, frameWidth, frameHeight)))));
			
			this.dialog.addAction(Actions.fadeIn(duration, interpolation));
			this.frame.addAction(Actions.sequence(Actions.fadeIn(duration, interpolation), Actions.after(Actions.run(tickRunnable()))));

			break;
		}
	}
	
	public void closeDialog(OpenCloseAnimation openCloseAnimation, float duration) {
		this.isTimeToCloseDialog = true;
		
		// Empty the text in the box when closing
		this.dialog.setText("");
		
		float innerDuration = duration / 2;
				
		this.frame.addAction(Actions.moveBy(frameWidth / 2, frameHeight / 2, duration));
		this.frame.addAction(Actions.sizeTo(0, 0, duration));
		this.dialog.addAction(Actions.moveBy(width / 2, height / 2, innerDuration));
		this.dialog.addAction(Actions.sequence(Actions.sizeTo(0, 0, innerDuration, interpolation), Actions.delay(duration / 4), Actions.after(Actions.run(closeRunnable()))));
	}
	
	public boolean isTimeToCloseDialog() {
		return isTimeToCloseDialog;
	}
	
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
					LogUtils.log("Dialog succesfully removed from stage");
				}
			}
		};
	}
	

}
