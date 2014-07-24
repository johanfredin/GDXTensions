package se.fredin.gdxtensions.scene2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Used for having a texture wrapped texture work with a scene2d stage
 * @author Johan Fredin
 *
 */
public class WrappedImage extends Image {

	private Sprite sprite;
	
	public WrappedImage(float width, float height, Texture texture) {
		super(texture);
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		this.sprite = new Sprite(texture);
		this.sprite.setSize(width, height);
		this.sprite.scroll(1, 0);
	}
	
	public WrappedImage(float x, float y, float width, float height, Texture texture) {
		super(texture);
		setX(x);
		setY(y);
		texture.setWrap(TextureWrap.Repeat, TextureWrap.Repeat);
		this.sprite = new Sprite(texture);
		this.sprite.setBounds(x, y, width, height);
		this.sprite.scroll(1, 0);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		this.sprite.setPosition(x, y);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		sprite.draw(batch);
	}
	
	public void draw(Batch batch, float parentAlpha, float x, float y) {
		super.draw(batch, parentAlpha);
		batch.draw(sprite, x, y);
	}
	
}
