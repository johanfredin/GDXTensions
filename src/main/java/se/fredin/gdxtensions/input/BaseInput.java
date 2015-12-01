package se.fredin.gdxtensions.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

/**
 * Input handler class that has basic keys mapped for 
 * usual user input actions like jumping, walking etc.
 * At the moment this class only works with a fixed amount of keyboard keys.
 * Will be updated in the near future to work with touchpads and gamepads
 * @author Johan Fredin
 *
 */
public class BaseInput implements InputProcessor {

	protected boolean exitButtonPressed;
	protected boolean leftButtonPressed;
	protected boolean rightButtonPressed;
	protected boolean upButtonPressed;
	protected boolean upRightButtonPressed;
	protected boolean upLeftButtonPressed;
	protected boolean downRightButtonPressed;
	protected boolean downLeftButtonPressed;
	protected boolean downButtonPressed;
	protected boolean shootButtonPressed;
	protected boolean interactButtonPressed;
	protected boolean menuButtonPressed;
	protected boolean mapButtonPressed;
	protected boolean pauseButtonPressed;
	protected boolean crouchButtonPressed;
	protected boolean jumpButtonPressed;
	
	protected short left = Keys.LEFT;
	protected short right = Keys.RIGHT;
	protected short up = Keys.UP;
	protected short down = Keys.DOWN;
	protected short shoot1 = Keys.CONTROL_LEFT;
	protected short shoot2 = Keys.CONTROL_RIGHT;
	protected short exit = Keys.ESCAPE;
	protected short interact = Keys.E;
	protected short menu = Keys.ALT_LEFT;
	protected short pause = Keys.P;
	protected short map = Keys.M;
	protected short jump = Keys.SPACE;
	protected short crouch = Keys.SHIFT_LEFT;
	
	public boolean isExitButtonPressed() {
		return exitButtonPressed;
	}

	public boolean isLeftButtonPressed() {
		return leftButtonPressed;
	}

	public boolean isRightButtonPressed() {
		return rightButtonPressed;
	}

	public boolean isUpButtonPressed() {
		return upButtonPressed;
	}

	public boolean isDownButtonPressed() {
		return downButtonPressed;
	}

	public boolean isShootButtonPressed() {
		return shootButtonPressed;
	}

	public boolean isMenuButtonPressed() {
		return menuButtonPressed;
	}

	public boolean isMapButtonPressed() {
		return mapButtonPressed;
	}

	public boolean isPauseButtonPressed() {
		return pauseButtonPressed;
	}

	public boolean isCrouchButtonPressed() {
		return crouchButtonPressed;
	}

	public boolean isJumpButtonPressed() {
		return jumpButtonPressed;
	}
	
	public boolean isInteractButtonPressed() {
		return interactButtonPressed;
	}
	
	public boolean isDownLeftButtonPressed() {
		return downButtonPressed && leftButtonPressed;
	}
	
	public boolean isDownRightButtonPressed() {
		return downButtonPressed && rightButtonPressed;
	}
	
	public boolean isUpLeftButtonPressed() {
		return upButtonPressed && leftButtonPressed;
	}
	
	public boolean isUpRightButtonPressed() {
		return upButtonPressed && rightButtonPressed;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == left) {
			leftButtonPressed = true;
		} if(keycode == right) {
			rightButtonPressed = true;
		} if(keycode == up) {
			upButtonPressed = true;
		} if(keycode == down) {
			downButtonPressed = true;
		} if(keycode == shoot1 || keycode == shoot2) {
			shootButtonPressed = true;
		} if(keycode == pause) {
			pauseButtonPressed = true;
		} if(keycode == exit) {
			exitButtonPressed = true;
		} if(keycode == crouch) {
			crouchButtonPressed = true;
		} if(keycode == menu) {
			menuButtonPressed = true;
		} if(keycode == jump) {
			jumpButtonPressed = true;
		} if(keycode == interact) {
			interactButtonPressed = true;
		} 
		
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == left) {
			leftButtonPressed = false;
		} if(keycode == right) {
			rightButtonPressed = false;
		} if(keycode == up) {
			upButtonPressed = false;
		} if(keycode == down) {
			downButtonPressed = false;
		} if(keycode == shoot1 || keycode == shoot2) {
			shootButtonPressed = false;
		} if(keycode == map) {
			mapButtonPressed = false;
		} if(keycode == pause) {
			pauseButtonPressed = false;
		} if(keycode == exit) {
			exitButtonPressed = false;
		} if(keycode == crouch) {
			crouchButtonPressed = false;
		} if(keycode == menu) {
			menuButtonPressed = false;
		} if(keycode == jump) {
			jumpButtonPressed = false;
		} if(keycode == interact) {
			interactButtonPressed = false;
		}
		
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * @return whether or not any movement key was pressed (use to detect if a player is not moving for ex)
	 */
	public boolean noMovementKeysPressed() {
		return !leftButtonPressed && !rightButtonPressed && !downButtonPressed && !upButtonPressed;
	}
}
