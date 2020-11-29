package graphics;

/**
 * Thrown to indicate that </code>GLFW</code> window or window context was 
 * not created successfully.
 * <p>
 * @author Ryan Baggs
 * @date Created on 06-Nov-2020
 *
 */
public class GLFWFailedWindowCreationException extends Exception {

	private static final long serialVersionUID = -6510164473261912971L;

	public GLFWFailedWindowCreationException() {
		super();
	}
}
