package se.fredin.gdxtensions.collision;

import se.fredin.gdxtensions.object.GameObject;
import se.fredin.gdxtensions.utils.TiledMapUtils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/** 
 * Handler for rectangle based collision
 * @author root
 *
 */
public class CollisionHandler {
	
	protected Array<Rectangle> hardBlocks;
	protected Array<Rectangle> softBlocks;
	
	public CollisionHandler(TiledMapUtils tiledMapUtils) {
		this.hardBlocks = tiledMapUtils.getRectangularMapObjects("hard-blocks");
		this.softBlocks = tiledMapUtils.getRectangularMapObjects("soft-blocks");
	}
	
	
	public void setHardAndSoftBlocks(Array<Rectangle> hardBlocks, Array<Rectangle> softBlocks) {
		this.hardBlocks = hardBlocks;
		this.softBlocks = softBlocks;
	}
	
	public boolean fromLeft(Rectangle collider, Rectangle collisionRect) {
		return collider.x + collider.width >= collisionRect.x;
	}
	
	public boolean fromRight(Rectangle collider, Rectangle collisionRect) {
		return collider.x <= collisionRect.x + collisionRect.width;
	}
	
	public boolean fromTop(Rectangle collider, Rectangle collisionRect) {
		return collider.y >= collisionRect.y + collisionRect.height;
	}
	
	public boolean fromBottom(Rectangle collider, Rectangle collisionRect) {
		return collider.y + collider.height <= collisionRect.y;
	}
	
	/**
	 * Used to check for collision.
	 * @param bounds the bounding box of the colliding object
	 * @param filter the type of block we collided with (hard, soft or door)
	 * @param collider the colliding object
	 * @return the rectangle of the collided object (if any)
	 */
	public Rectangle getBoundsAt(Rectangle bounds, byte filter, GameObject collider) {
		if((filter&Filter.HARD) == Filter.HARD && hardBlocks != null) {
			for(Rectangle hardBlock : hardBlocks) {
				if(hardBlock.overlaps(bounds)) {
					return hardBlock;
				}
			}
		}

		if((filter&Filter.SOFT) == Filter.SOFT && softBlocks != null) {
			for(Rectangle softBlock : softBlocks) {
				if(softBlock.overlaps(bounds)) {
					return softBlock;
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Used to specify type of block that we collide with.
	 * could be a wall, a grass hill or a door.
	 * @authors Niklas Istenes
	 *
	 */
	public static class Filter {
		public static final byte HARD = 1;
		public static final byte SOFT = 2;
	}

	public Array<Rectangle> getHardBlocks() {
		return hardBlocks;
	}

}
