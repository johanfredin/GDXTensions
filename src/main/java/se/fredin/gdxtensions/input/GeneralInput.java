package se.fredin.gdxtensions.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class GeneralInput {

	private boolean exitButtonPressed;
	private boolean leftButtonPressed;
	private boolean rightButtonPressed;
	private boolean upButtonPressed;
	private boolean downButtonPressed;
	private boolean shootButtonPressed;
	private boolean menuButtonPressed;
	private boolean mapButtonPressed;
	private boolean pauseButtonPressed;
	private boolean isCrouchButtonPressed;
	private boolean isJumpButtonPressed;
	
	private short left = Keys.LEFT;
	private short right = Keys.RIGHT;
	private short up = Keys.UP;
	private short down = Keys.DOWN;
	private short shoot = Keys.CONTROL_LEFT;
	private short exit = Keys.ESCAPE;
	private short menu = Keys.ALT_LEFT;
	private short pause = Keys.P;
	private short map = Keys.M;
	private short jump = Keys.SPACE;
	private short crouch = Keys.SHIFT_LEFT;
	
	private InputAdapter keyBoardInputAdapter;
	
	public GeneralInput(InputDevice device) {
		switch(device) {
		case KEYBOARD:
			this.keyBoardInputAdapter = getKeyboard();
			Gdx.input.setInputProcessor(keyBoardInputAdapter);
			break;
		default:
			break;
		}
	}
	
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

	private InputAdapter getKeyboard() {
		InputAdapter adapter = new InputAdapter(){
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
		};
		return adapter;
	}
}
