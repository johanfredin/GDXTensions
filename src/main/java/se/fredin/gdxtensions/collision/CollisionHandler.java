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
	
	public void checkForCollision(Rectangle bounds) {
		Rectangle colliderBounds = gameObject.getBounds();
		if(colliderBounds.overlaps(bounds)) {
			float cx = colliderBounds.x;
			float cw = colliderBounds.width;
			float cy = colliderBounds.y;
			float ch = colliderBounds.height;
			float bx = bounds.x;
			float bw = bounds.width;
			float by = bounds.y;
			float bh = bounds.height;
			float cxPW = cx + cw;
			float bxPW = bx + bw;
			float cyPH = cy + ch;
			float byPH = by + bh;
			
			float x = gameObject.getPosition().x, y = gameObject.getPosition().y;
			
			if(cx <= bxPW && cxPW > bx) {
				// From right
				x = bx;
				System.out.println("from right");
			} if(cxPW >= bx && cxPW < bxPW) {
				// From left
				x = bx - cw;
				System.out.println("from left");
			} if(cy <= byPH && cyPH > by) {
				// From top
				y = byPH;
				System.out.println("from top");
			} if(cyPH >= by && cy < byPH) {
				// From bottom
//				y = by - ch;
				System.out.println("from bottom");
			}
			
			gameObject.setPosition(x, y);
		}
	}
	
	public void checkForCollision(Array<Rectangle> bounds) {
		for(Rectangle rect : bounds) {
			checkForCollision(rect);
		}
	}
	

}
