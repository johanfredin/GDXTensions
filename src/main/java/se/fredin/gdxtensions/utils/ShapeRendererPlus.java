package se.fredin.gdxtensions.utils;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 * Extension of the libGDX ShapeRenderer with extra methods for rendering rectangles.
 * @author Johan Fredin
 *
 */
public class ShapeRendererPlus extends ShapeRenderer {

	private GameCamera camera;
	private ShapeType shapeType = ShapeType.Line;
	
	/**
	 * Constructs a new ShapeRendererPlus with default settings
	 */
	public ShapeRendererPlus() {
		super();
	}
	
	/**
	 * Constructs a new ShapeRendererPlus with a given ShapeType
	 * @param shapeType what shapeType to use
	 */
	public ShapeRendererPlus(ShapeType shapeType) {
		this();
		this.shapeType = shapeType;
	}
	
	/**
	 * Constructs a new ShapeRendererPlus with a given camera
	 * @param camera the GameCamera to use
	 */
	public ShapeRendererPlus(GameCamera camera) {
		this();
		this.camera = camera;
	}
	
	/**
	 * Constructs a new ShapeRendererPlus with a given camera and shapeType
	 * @param camera the GameCamera to use
	 * @param shapeType what shapeType to use
	 */
	public ShapeRendererPlus(GameCamera camera, ShapeType shapeType) {
		this();
		this.camera = camera;
		this.shapeType = shapeType;
	}
	
	public void setCamera(GameCamera camera) {
		this.camera = camera;
	}
	
	public GameCamera getCamera() {
		return camera;
	}
	
	public void setShapeType(ShapeType shapeType) {
		this.shapeType = shapeType;
	}
	
	public ShapeType getShapeType() {
		return shapeType;
	}
	
	/**
	 * Renders an arbitrary amount of rectangles, if no shapeType has been set it will use its default Line setting.
	 * Will throw a null pointer exception if no camera have been given
	 * @param rectangles the rectangles to draw.
	 */
	public void renderBoundaries(Rectangle... rectangles) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Rectangle rectangle : rectangles) {
			this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangles, if no shapeType has been set it will use its default Line setting.
	 * @param camera the camera to use
	 * @param rectangles the rectangles to draw.
	 */
	public void renderBoundaries(GameCamera camera, Rectangle... rectangles) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Rectangle rectangle : rectangles) {
			this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangles, if no shapeType has been set it will use its default Line setting.
	 * Will throw a NullPointerException if no camera is given
	 * @param shapeType the shapeType to use
	 * @param rectangles the rectangles to draw.
	 */
	public void renderBoundaries(ShapeType shapeType, Rectangle... rectangles) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Rectangle rectangle : rectangles) {
			this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangles, if no shapeType has been set it will use its default Line setting.
	 * @param camera the camera to use
	 * @param shapeType the shapeType to use
	 * @param rectangles the rectangles to draw.
	 */
	public void renderBoundaries(GameCamera camera, ShapeType shapeType, Rectangle... rectangles) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Rectangle rectangle : rectangles) {
			this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangle arrays. Useful when you have different collections of objects.
	 * Perhaps some enemies, bullets etc and you want to render their edges of fill them.
	 * If no shapetype have been given the default "Line" will be used.
	 * Will throw a nullpointer exception if no camera has been set.
	 * @param rectangleArrays the rectangles to draw
	 */
	public void renderBoundaries(Array<Rectangle>... rectangleArrays) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Array<Rectangle> rectangleArray : rectangleArrays) {
			for(Rectangle rectangle : rectangleArray) {
				this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			}
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangle arrays. Useful when you have different collections of objects.
	 * Perhaps some enemies, bullets etc and you want to render their edges of fill them.
	 * Will use the default Line if no {@link ShapeType} have been set
	 * @param camera the {@link GameCamera} to use
	 * @param rectangleArrays the {@link Array} of {@link Rectangle}'s to draw
	 */
	public void renderBoundaries(GameCamera camera, Array<Rectangle>... rectangleArrays) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Array<Rectangle> rectangleArray : rectangleArrays) {
			for(Rectangle rectangle : rectangleArray) {
				this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			}
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangle arrays. Useful when you have different collections of objects.
	 * Perhaps some enemies, bullets etc and you want to render their edges of fill them.
	 * will throw a {@link NullPointerException} if no {@link GameCamera} has been set
	 * @param shapeType the {@link ShapeType} to use
	 * @param rectangleArrays the rectangles to draw
	 * */
	public void renderBoundaries(ShapeType shapeType, Array<Rectangle>... rectangleArrays) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Array<Rectangle> rectangleArray : rectangleArrays) {
			for(Rectangle rectangle : rectangleArray) {
				this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			}
		}
		this.end();
	}
	
	/**
	 * Renders an arbitrary amount of rectangle arrays. Useful when you have different collections of objects.
	 * Perhaps some enemies, bullets etc and you want to render their edges of fill them.
	 * @param camera the {@link GameCamera} to use
	 * @param shapeType the {@link ShapeType} to use
	 * @param rectangleArrays the rectangles to draw
	 * */
	public void renderBoundaries(GameCamera camera, ShapeType shapeType, Array<Rectangle>... rectangleArrays) {
		this.setProjectionMatrix(camera.combined);
		this.begin(shapeType);
		for(Array<Rectangle> rectangleArray : rectangleArrays) {
			for(Rectangle rectangle : rectangleArray) {
				this.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
			}
		}
		this.end();
	}
	
	
}
