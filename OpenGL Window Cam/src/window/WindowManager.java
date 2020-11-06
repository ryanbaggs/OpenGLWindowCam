package window;

import org.lwjgl.glfw.GLFW;

/**
 * Manages GLFW, window, and the OpenGL context for the window.
 * <p>
 * @author Ryan Baggs
 * @date Created on 05-Nov-2020
 *
 */
public class WindowManager {
	
	private static final long NULL = 0;
	
	// Window specifications.
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final CharSequence TITLE = "Canoe";
	
	private long windowHandle = 0;

	public WindowManager() throws GLFWFailedInitializeException, GLFWFailedWindowCreationException {
		
		initializeGLFW();
		createWindow();
		
		// Make the OpenGL context current.
		GLFW.glfwMakeContextCurrent(windowHandle);
	}
	
	/**
	 * Initializes GLFW.
	 * 
	 * @throws GLFWFailedInitializeException
	 */
	private void initializeGLFW() throws GLFWFailedInitializeException {
		// Attempt to initialize GLFW.
		if(!GLFW.glfwInit()) {
			// GLFW not successfully initialized, throw new exception.
			throw new GLFWFailedInitializeException();
		}
	}
	
	/**
	 * Creates a window and window context, assigning its handle to the 
	 * windowHandle.
	 * 
	 * @throws GLFWFailedWindowCreationException if window or window 
	 * context was not successfully created.
	 */
	private void createWindow() throws GLFWFailedWindowCreationException {
		// Create a window, with the specified height, width, title, monitor 
		// for fullscreen, and sharing.
		windowHandle = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, NULL);
		
		if(windowHandle == NULL) {
			// Window or window context creation failed.
			throw new GLFWFailedWindowCreationException();
		}
	}
	
	/**
	 * Destroys the window then terminates GLFW.
	 */
	public void endWindowManager() {
		GLFW.glfwDestroyWindow(windowHandle);
		GLFW.glfwTerminate();
	}
}
