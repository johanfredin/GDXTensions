package se.fredin.gdxtensions.input;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class GeneralInput {

	public boolean exitButtonPressed;
	public boolean leftButtonPressed;
	public boolean rightButtonPressed;
	public boolean upButtonPressed;
	public boolean downButtonPressed;
	public boolean shootButtonPressed;
	public boolean menuButtonPressed;
	public boolean mapButtonPressed;
	public boolean pauseButtonPressed;
	public boolean isCrouchButtonPressed;
	public boolean isJumpButtonPressed;
	
	public final short left = Keys.LEFT;
	public final short right = Keys.RIGHT;
	public final short up = Keys.UP;
	public final short down = Keys.DOWN;
	public final short shoot = Keys.CONTROL_LEFT;
	public final short exit = Keys.ESCAPE;
	public final short menu = Keys.ALT_LEFT;
	public final short pause = Keys.P;
	public final short map = Keys.M;
	public final short jump = Keys.SPACE;
	public final short crouch = Keys.SHIFT_LEFT;
	
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

	private InputAdapter getKeyboard() {
		InputAdapter adapter = new InputAdapter(){
			@Override
			public boolean keyDown(int keycode) {
				switch(keycode) {
				case left:
					leftButtonPressed = true;
					break;
				case right:
					rightButtonPressed = true;
					break;
				case up:
					upButtonPressed = true;
					break;
				case down:
					downButtonPressed = true;
					break;
				case shoot:
					shootButtonPressed = true;
					break;
				case map:
					mapButtonPressed = true;
					break;
				case pause:
					pauseButtonPressed = true;
					break;
				case exit:
					exitButtonPressed = true;
					break;
				default:
					return false;
				}
				return true;
			}
			
			@Override
			public boolean keyUp(int keycode) {
				switch(keycode) {
				case left:
					leftButtonPressed = false;
					break;
				case right:
					rightButtonPressed = false;
					break;
				case up:
					upButtonPressed = false;
					break;
				case down:
					downButtonPressed = false;
					break;
				case shoot:
					shootButtonPressed = false;
					break;
				case map:
					mapButtonPressed = false;
					break;
				case pause:
					pauseButtonPressed = false;
					break;
				case exit:
					exitButtonPressed = false;
					break;
				default:
					return false;
				}
				return true;
			}
		};
		return adapter;
	}
}
