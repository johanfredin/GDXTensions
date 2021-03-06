package se.fredin.gdxtensions.collision;

import se.fredin.gdxtensions.object.RichGameObject;
import se.fredin.gdxtensions.utils.TiledMapUtils;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/** 
 * Handler for rectangle based collision
 * @author root
 *
 */
public class CollisionHandler {
	
	private Array<Rectangle> hardBlocks;
	private Array<Rectangle> softBlocks;
	private Rectangle colliderBounds;
	
	public CollisionHandler() {}
	
	public CollisionHandler(Rectangle colliderBounds) {
		this(colliderBounds, null);
	}
	
	public CollisionHandler(TiledMapUtils tiledMapUtils) {
		this(null, tiledMapUtils);
	}
	
	public CollisionHandler(Rectangle colliderBounds, TiledMapUtils tiledMapUtils) {
		this.hardBlocks = tiledMapUtils.getRectangularMapObjects("hard-blocks");
		this.softBlocks = tiledMapUtils.getRectangularMapObjects("soft-blocks");
		this.colliderBounds = colliderBounds;
	}
	
	public void setColliderBounds(Rectangle colliderBounds) {
		this.colliderBounds = colliderBounds;
	}
	
	public Rectangle getColliderBounds() {
		return colliderBounds;
	}

	public void setHardBlocks(Array<Rectangle> hardBlocks) {
		this.hardBlocks = hardBlocks;
	}
	
	public Array<Rectangle> getHardBlocks() {
		return hardBlocks;
	}
	
	public void setSoftBlocks(Array<Rectangle> softBlocks) {
		this.softBlocks = softBlocks;
	}
	
	public Array<Rectangle> getSoftBlocks() {
		return softBlocks;
	}

	
	/**
	 * Used to check for collision.
	 * @param bounds the bounding box of the colliding object
	 * @param filter the type of block we collided with (hard, soft or door)
	 * @param collider the colliding object
	 * @return the rectangle of the collided object (if any)
	 */
	public Rectangle getBoundsAt(Rectangle bounds, byte filter, RichGameObject collider) {
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
	
	public boolean isCollisionWithHardBlock(Rectangle collider) {
		for(Rectangle hardBlock : hardBlocks) {
			if(collider.overlaps(hardBlock)) {
				return true;
			}
		}
		return false;
	}
	
	
	public boolean isCollision(Rectangle collisionRect) {
		return this.colliderBounds.overlaps(collisionRect);
	}
	
	public boolean fromLeft(Rectangle collisionRect) {
		return this.colliderBounds.x + this.colliderBounds.width >= collisionRect.x;
	}
	
	public boolean fromRight(Rectangle collisionRect) {
		return this.colliderBounds.x <= collisionRect.x + collisionRect.width;
	}
	
	public boolean fromTop(Rectangle collisionRect) {
		return this.colliderBounds.y >= collisionRect.y + collisionRect.height;
	}
	
	public boolean fromBottom(Rectangle collisionRect) {
		return this.colliderBounds.y + this.colliderBounds.height <= collisionRect.y;
	}
	
	public boolean isCollision(Rectangle collider, Rectangle collisionRect) {
		return collider.overlaps(collisionRect);
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
	 * Used to specify type of block that we collide with.
	 * could be a wall, a grass hill or a door.
	 * @authors Niklas Istenes
	 *
	 */
	public static class Filter {
		public static final byte HARD = 1;
		public static final byte SOFT = 2;
	}
	
}
