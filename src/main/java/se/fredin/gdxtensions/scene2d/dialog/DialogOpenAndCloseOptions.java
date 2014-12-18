package se.fredin.gdxtensions.scene2d.dialog;

import se.fredin.gdxtensions.utils.LogUtils;

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
	private float x;
	private float y;
	private float width;
	private float height;
	private float frameWidth;
	private float frameHeight;
	private boolean isTimeToCloseDialog;
	private boolean canTick;
	
	public DialogOpenAndCloseOptions(Dialog dialog) {
		this.dialog = dialog;
		this.frame = dialog.getFrame();
		this.x = dialog.getX();
		this.y = dialog.getY();
		this.width = dialog.getDialogWidth();
		this.height = dialog.getDialogHeight();
		this.frameWidth = dialog.getFrameWidth();
		this.frameHeight = dialog.getFrameHeight();
	}
	
	public void openDialog(float duration) {
		float innerDuration = duration / 2;
		
		this.dialog.setPosition(x, y);
		this.frame.addAction(Actions.moveBy(-frameWidth / 2, -frameHeight / 2, duration));
		this.frame.addAction(Actions.sequence(Actions.sizeTo(frameWidth, frameHeight, duration), Actions.delay(duration / 4), Actions.after(Actions.run(new Runnable() {

			@Override
			public void run() {
				canTick = true;
			}
		}))));
		this.dialog.addAction(Actions.moveBy(-width / 2, -height / 2, innerDuration));
		this.dialog.addAction(Actions.sizeTo(width, height, innerDuration));
	}
	
	public void closeDialog(float duration) {
		this.isTimeToCloseDialog = true;
		
		// Empty the text in the box when closing
		this.dialog.setText("");
		
		float innerDuration = duration / 2;
				
		this.frame.addAction(Actions.moveBy(frameWidth / 2, frameHeight / 2, duration));
		this.frame.addAction(Actions.sizeTo(0, 0, duration));
		this.dialog.addAction(Actions.moveBy(width / 2, height / 2, innerDuration));
		this.dialog.addAction(Actions.sequence(Actions.sizeTo(0, 0, innerDuration), Actions.delay(duration / 4), Actions.after(Actions.run(new Runnable() {
			@Override
			public void run() {
				Stage stage = dialog.getStage();
				if(stage != null) {
					stage.getActors().removeValue(dialog, true);
					LogUtils.log("Dialog succesfully removed from stage");
				}
			}
		}))));
	}
	
	public boolean isTimeToCloseDialog() {
		return isTimeToCloseDialog;
	}
	
	public boolean isCanTick() {
		return canTick;
	}

}
