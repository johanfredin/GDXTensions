package se.fredin.gdxtensions.scene2d;

import se.fredin.gdxtensions.utils.AnimationUtils;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Special class for merging an animated texture region into an actor so it can be used in a stage
 * @author Johan Fredin
 */
public class AnimatedActor extends Image {

	private Animation animation;
	private TextureRegion currentFrame;
	private float stateTime;
	
	/**
	 * Create a new instance
	 * @param region the texture region we want to split up and use for animation
	 * @param ANIM_SPEED
	 * @param frameWidth
	 * @param frameHeight
	 * @param visible whether this actor should be visible or not from start
	 */
	public AnimatedActor(TextureRegion region, final float ANIM_SPEED, int frameWidth, int frameHeight, boolean visible) {
		super(region);
		this.animation = AnimationUtils.getFramesFromRegion(region, ANIM_SPEED, frameWidth, frameHeight);
		this.currentFrame = animation.getKeyFrame(stateTime);
		this.setWidth(frameWidth);
		this.setHeight(frameHeight);
		this.setVisible(visible);
	}
	
	/**
	 * Create a new instance
	 * @param region the texture region we want to split up and use for animation
	 * @param ANIM_SPEED
	 * @param xPos
	 * @param yPos
	 * @param frameWidth
	 * @param frameHeight
	 * @param visible whether this actor should be visible or not from start
	 */
	public AnimatedActor(TextureRegion region, final float ANIM_SPEED, float xPos, float yPos, int frameWidth, int frameHeight, boolean visible) {
		this(region, ANIM_SPEED, frameWidth, frameHeight, visible);
		this.setPosition(xPos, yPos);
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		stateTime += delta;
		currentFrame = animation.getKeyFrame(stateTime, true);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(1,1,1,1);
		batch.draw(currentFrame, this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}
	

}