package se.fredin.gdxtensions.xml;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.XmlReader.Element;

/**
 * Class containing common methods and variables for positioning that the other xml classes use.
 * @author Johan Fredin
 *
 */
public abstract class XMLCommons {
	
	protected float x;
	protected float y;
	
	/**
	 * Creates a new empty object 
	 */
	protected XMLCommons() {}
	
	/**
	 * Create a new {@link XMLCommons} instance setting the x and y fields from the xml (if a position exists)
	 * @param element the {@link Element} from the xml
	 */
	protected XMLCommons(Element element) {
		this.x = getPosOrNegativeOneValue(element, 'x');
		this.y = getPosOrNegativeOneValue(element, 'y');
	}
	
	/**
	 * @return the x position retrieved from the xml (will be -1 if none is found)
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * @return the y position retrieved from the xml (will be -1 if none is found)
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * @return a new {@link Vector2} object with the x and y positions
	 */
	public Vector2 getPosition() {
		return new Vector2(x, y);
	}
	
	/**
	 * @return whether or not a position was found in the xml
	 */
	public boolean hasPositionSet() {
		return x != -1.0f && y != -1.0f;
	}
	
	protected float getPosOrNegativeOneValue(Element element, char position) {
		try {
			String posAttributes[] = element.getAttribute("pos").split(",");
			switch(position) {
			case 'x':
				return Float.parseFloat(posAttributes[0]);
			case 'y':
				return Float.parseFloat(posAttributes[1]);
			}
		} catch(GdxRuntimeException ex) {
			System.err.println("Element " + element.getName() + " does not have the attribute pos");
		}
		return -1f;
	}

}
