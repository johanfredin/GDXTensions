package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

/**
 * Extension of the standard LibGDX orthographic camera, contains more methods for following positions,
 * specifying bounds and rumble features.
 * @author johan
 *
 */
public class GameCamera extends OrthographicCamera {
	
	private float zoomLevel = 1.0f;
	private Vector2 bounds;
	
	public GameCamera() {
		super();
		this.bounds = new Vector2();
	}
	
	/**
	 * Constructs a new GameCamera
	 * @param viewportWidt the viewport width
	 * @param viewportHeight the viewport height
	 */
	public GameCamera(float viewportWidt, float viewportHeight) {
		super(viewportWidt, viewportHeight);
		this.bounds = new Vector2();
	}
	
	/**
	 * Constructs a new GameCamera
	 * @param viewportWidt the viewport width
	 * @param viewportHeight the viewport height
	 * @param bounds the screen edges we don't want to see
	 */
	public GameCamera(float viewportWidt, float viewportHeight, Vector2 bounds) {
		super(viewportWidt, viewportHeight);
		this.bounds = bounds;
	}
	
	/**
	 * Constructs a new GameCamera
	 * @param viewportWidt the viewport width
	 * @param viewportHeight the viewport height
	 * @param boundsX camera will not go beyond this x position
	 * @param boundsY camera will not go beyond this y position
	 */
	public GameCamera(float viewportWidt, float viewportHeight, float boundsX, float boundsY) {
		super(viewportWidt, viewportHeight);
		this.bounds = new Vector2(boundsX, boundsY);
	}
	
	public void setBounds(Vector2 bounds) {
		this.bounds = bounds;
	}
	
	public Vector2 getBounds() {
		return bounds;
	}
	
	public void setBounds(float boundsX, float boundsY) {
		this.bounds.set(boundsX, boundsY);
	}
	
	/**
	 * Sets the camera to focus on a given position. Unless the given position
	 * is about to go outside of the cameras boundaries it will follow it.
	 * @param positionToFollow the position we want to keep the camera looking at.
	 * @param boundsX camera will not go beyond this x position
	 * @param boundsY camera will not go beyond this y position
	 */
	public void follow(Vector2 positionToFollow, float boundsX, float boundsY) {
		follow(positionToFollow.x, positionToFollow.y, boundsX, boundsY);
	}
	
	/**
	 * Sets the camera to focus on a given position. Unless the given position
	 * is about to go outside of the cameras boundaries it will follow it.
	 * @param positionToFollow the position we want to keep the camera looking at.
	 * @param boundsX camera will not go beyond this x position
	 * @param boundsY camera will not go beyond this y position
	 */
	public void follow(Vector2 positionToFollow, Vector2 bounds) {
		follow(positionToFollow.x, positionToFollow.y, bounds.x, bounds.y);
	}
	
	/**
	 * Sets the camera to focus on a given position. Unless the given position
	 * is about to go outside of the cameras boundaries it will follow it.
	 * @param xCoord the x position we want to keep the camera looking at.
	 * @param yCoord the y position we want to keep the camera looking at.
	 * @param boundsX camera will not go beyond this x position
	 * @param boundsY camera will not go beyond this y position
	 */
	public void follow(float xCoord, float yCoord, float boundsX, float boundsY) {
		zoom = zoomLevel;
		float centerX = (viewportWidth * zoomLevel ) / 2;
		float centerY = (viewportHeight * zoomLevel) / 2;
		position.set(xCoord, yCoord, 0);
		
		if(position.x - centerX <= 0) 
			position.x = centerX;
		if(position.x + centerX >= boundsX)
			position.x = boundsX - centerX;
		if(position.y - centerY <= 0)
			position.y = centerY;
		if(position.y + centerY >= boundsY)
			position.y = boundsY - centerY;
		
		update();
	}
	
	/**
	 * Sets the camera to focus on a given position. If the bounds vector2 has not been set
	 * the camera will have no boundaries.
	 * @param positionToFollow the position we want to follow.
	 */
	public void follow(Vector2 positionToFollow) {
		follow(positionToFollow.x, positionToFollow.y);
	}
	
	/**
	 * Sets the camera to focus on a given position. If the bounds vector2 has not been set
	 * the camera will have no boundaries.
	 * @param xCoord the x position we want to follow.
	 * @param yCoord the y position we want to follow.
	 */
	public void follow(float xCoord, float yCoord) {
		if(boundsSet()) {
			follow(xCoord, yCoord, this.bounds.x, this.bounds.y);
			return;
		}
		
		zoom = zoomLevel;
		position.set(xCoord, yCoord, 0);
		update();
	}
	
	@Override
	public String toString() {
		return "GameCamera\n" +
			   "viewport width=" + viewportWidth + "\n" +
			   "viewport height=" + viewportHeight + "\n" +
			   "boundsX=" + bounds.x + "\n" +
			   "boundsY=" + bounds.y + "\n";
	}
	
	private boolean boundsSet() {
		return bounds.x > 0 && bounds.y > 0;
	}
	
	

}
