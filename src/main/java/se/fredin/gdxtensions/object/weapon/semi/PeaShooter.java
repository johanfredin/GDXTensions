package se.fredin.gdxtensions.object.weapon.semi;

import se.fredin.gdxtensions.input.BaseInput;

public class PeaShooter extends SemiAutomaticWeapon {

	public PeaShooter(BaseInput input) {
		super(0, 17, SHOOTING_INTERVAL_DEFAULT / 4f, input);
	}

	
}
