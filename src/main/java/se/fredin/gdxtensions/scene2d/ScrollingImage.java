package se.fredin.gdxtensions.scene2d;

import se.fredin.gdxtensions.assetmanagement.Assets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Helper class for having a wrapped sprite work as an actor in a stage.
 * @author Johan Fredin
 *
 */
public class ScrollingImage extends Image {
	
	private Sprite scrollSprite;
	private Color color;
	private float speed;
	private boolean shouldMove = true;
	
	/**
	 * Create a new scrolling image
	 * @param path the path to the texture
	 * @param speed the speed of the scrolling
	 * @param worldWidth the width e.g how much to wrap.
	 */
	public ScrollingImage(String path, float speed, float worldWidth) {
		this.speed = speed;
		this.scrollSprite = new Sprite(getScrollableImage(path));
		this.scrollSprite.setSize(worldWidth, scrollSprite.getHeight());
	}
	
	/**
	 * Create a new scrolling image with a start u and u2 value
	 * @param path path the path to the texture
	 * @param speed speed the speed of the scrolling
	 * @param u the starting u position of the sprite
	 * @param u2 the starting u2 position of the sprite
	 * @param worldWidth worldWidth the width e.g how much to wrap.
	 */
	public ScrollingImage(String path, float speed, float u, float u2, float worldWidth) {
		this(path, speed, worldWidth);
		this.scrollSprite.setU(u);
		this.scrollSprite.setU2(u2);
	}
	
	public float getTimer() {
		return scrollSprite.getOriginX();
	}
	
	public void setShouldMove(boolean shouldMove) {
		this.shouldMove = shouldMove;
	}
	
	@Override
	public void act(float delta) {
		updateParallaxLayer(delta, speed);
	}
	
	public float getU() {
		return scrollSprite.getU();
	}
	
	public float getU2() {
		return scrollSprite.getU2();
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		batch.setColor(this.color == null ? Color.WHITE : color);
		super.draw(batch, parentAlpha);
		batch.draw(scrollSprite, scrollSprite.getX(), scrollSprite.getY(), scrollSprite.getWidth(), scrollSprite.getHeight());
		batch.setColor(Color.WHITE);
	}
	
	public void setSpriteColor(Color color) {
		this.color = color;
	}
	
	@Override
	public void setPosition(float x, float y) {
		scrollSprite.setPosition(x, y);
	}
	
	/**
	 * Creates a texture and wraps it to scroll repeatedly,
	 * later binds that texture to a sprite
	 * @param path the path to the texture
	 * @return a new scrollable sprite
	 */
	private Texture getScrollableImage(String path) {
		Texture texture = (Texture) Assets.getInstance().get(path);
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		return texture;
	}
	
	/**
	 * Used to update the scrollable sprites. The sprite will center around the cameras x position
	 * if we are allowed to scroll.
	 * @param deltaTime time since last rendering occurred
	 * @param speed the scroll speed of the sprite
	 * as additional information about the sprite we want to use and then the corresponding scroll timer will be updated. 
	 */
	private void updateParallaxLayer(float deltaTime, float speed) {
		scrollSprite.scroll(shouldMove ? speed * deltaTime : 1, 0);
	}
}
