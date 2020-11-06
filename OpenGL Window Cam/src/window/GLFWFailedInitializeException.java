package window;

/**
 * Thrown to indicate that </code>GLFW</code> was not initialized 
 * successfully.
 * <p>
 * @author Ryan Baggs
 * @date Created on 05-Nov-2020
 *
 */
public class GLFWFailedInitializeException extends Exception {

	private static final long serialVersionUID = 7101468651502248654L;

	public GLFWFailedInitializeException() {
		super();
	}
	
}
