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
	protected boolean downButtonPressed;
	protected boolean shootButtonPressed;
	protected boolean menuButtonPressed;
	protected boolean mapButtonPressed;
	protected boolean pauseButtonPressed;
	protected boolean isCrouchButtonPressed;
	protected boolean isJumpButtonPressed;
	
	protected short left = Keys.LEFT;
	protected short right = Keys.RIGHT;
	protected short up = Keys.UP;
	protected short down = Keys.DOWN;
	protected short shoot = Keys.CONTROL_LEFT;
	protected short exit = Keys.ESCAPE;
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
		return isCrouchButtonPressed;
	}

	public boolean isJumpButtonPressed() {
		return isJumpButtonPressed;
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
		} if(keycode == shoot) {
			shootButtonPressed = true;
		} if(keycode == map) {
			mapButtonPressed = true;
		} if(keycode == pause) {
			pauseButtonPressed = true;
		} if(keycode == exit) {
			exitButtonPressed = true;
		} if(keycode == crouch) {
			isCrouchButtonPressed = true;
		} if(keycode == menu) {
			menuButtonPressed = true;
		} if(keycode == jump) {
			isJumpButtonPressed = true;
		} else {
			return false;
		}
		return false;
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
		} if(keycode == shoot) {
			shootButtonPressed = false;
		} if(keycode == map) {
			mapButtonPressed = false;
		} if(keycode == pause) {
			pauseButtonPressed = false;
		} if(keycode == exit) {
			exitButtonPressed = false;
		} if(keycode == crouch) {
			isCrouchButtonPressed = false;
		} if(keycode == menu) {
			menuButtonPressed = false;
		} if(keycode == jump) {
			isJumpButtonPressed = false;
		} else {
			return false;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
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
}
