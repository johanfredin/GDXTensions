package se.fredin.gdxtensions.collision;

import se.fredin.gdxtensions.object.GameObject;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/** 
 * Handler for rectangle based collision
 * @author root
 *
 */
public class CollisionHandler {
	
	private GameObject gameObject;
	
	public CollisionHandler() {}
	
	public CollisionHandler(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void checkForCollision(Rectangle collisionRect) {
		Rectangle collider = gameObject.getBounds();
		float x = collider.getX();
		float y = collider.getY();
		if(collider.overlaps(collisionRect)) {
			if(fromLeft(collider, collisionRect)) {
				x = collisionRect.x - collider.width;
			} if(fromRight(collider, collisionRect)) {
				x = collisionRect.x + collisionRect.width;
			} if(fromTop(collider, collisionRect)) {
				y = collisionRect.y + collisionRect.height;
			} if(fromBottom(collider, collisionRect)) {
				y = collisionRect.y - collider.height;
			}
			System.out.println("collision");
			gameObject.setPosition(x, y);
		}
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
	
	public void checkForCollision(Array<Rectangle> bounds) {
		for(Rectangle rect : bounds) {
			checkForCollision(rect);
		}
	}
	
	/**
	 * Used to specify type of block that we collide with.
	 * could be a wall, a grass hill or a door.
	 * @authors Niklas Istenes, Johan Fredin
	 *
	 */
	public static class Filter {
		public static final byte HARD = 1;
		public static final byte SOFT = 2;
		public static final byte DOOR = 4;
		public static final byte SAND = 8;
	}
	

}
