package window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallbackI;

/**
 * Manages GLFW, window, and the OpenGL context for the window.
 * <p>
 * @author Ryan Baggs
 * @date Created on 05-Nov-2020
 */
public class WindowManager {
	
	private static final long NULL = 0;
	
	// Window specifications.
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final CharSequence TITLE = "Canoe";

	public WindowManager() throws GLFWFailedInitializeException, 
	GLFWFailedWindowCreationException {
		
		initializeGLFW();
		long windowHandle = createWindow();
		
		// Make the OpenGL context current.
		GLFW.glfwMakeContextCurrent(windowHandle);
		
		setKeyCallbacks(windowHandle);
		
		// Perform updates here.
		updateWindow(windowHandle);
		endWindowManager(windowHandle);
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
	 * @return the window handle, address, of the window in memory.
	 * @throws GLFWFailedWindowCreationException if window or window 
	 * context was not successfully created.
	 */
	private long createWindow() throws GLFWFailedWindowCreationException {
		// Create a window, with the specified height, width, title, monitor 
		// for fullscreen, and sharing.
		long windowHandle = GLFW.glfwCreateWindow(WIDTH, HEIGHT, TITLE, NULL, 
				NULL);
		
		if(windowHandle == NULL) {
			// Window or window context creation failed.
			throw new GLFWFailedWindowCreationException();
		}
		
		return windowHandle;
	}
	
	/**
	 * Sets the key callbacks creating for the specified window.
	 * 
	 * @param window to set the key callbacks for.
	 */
	private void setKeyCallbacks(long window) {
		GLFW.glfwSetKeyCallback(window, new KeyCallback());
	}
	
	/**
	 * Updates the window, checks if closed, and polls the events to see 
	 * if any occurred.
	 * 
	 * @param window to update.
	 */
	private void updateWindow(long window) {
		// Check if the close flag is set to true.
		while(!GLFW.glfwWindowShouldClose(window)) {
			try {
				// Poll the events to let system know updating and get any 
				// events that occurred.
				GLFW.glfwPollEvents();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.err.println(Thread.currentThread().getName() + 
						" thread interrupted.");
			}
		}
	}
	
	/**
	 * Destroys the window then terminates GLFW.
	 * 
	 * @param window to destroy.
	 */
	private void endWindowManager(long window) {
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
	}
	
	/**
	 * Key callback for key input in the specified GLFW window.
	 * <p>
	 * @author Ryan Baggs
	 * @date Created on 07-Nov-2020
	 */
	public class KeyCallback implements GLFWKeyCallbackI{

		/**
		 * Gets called when key input is pressed, repeated, or released and 
		 * provides information on the input.
		 * 
		 * @param windowHandle of the window that received the input.
		 * @param key that was pressed.
		 * @param scancode system specific scancode of key.
		 * @param action of the key
		 * @param mods identifier for the modifier keys held down.
		 */
		@Override
		public void invoke(long window, int key, int scancode, int action, 
				int mods) {
			// If the escape key is pressed, close the window.
			if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
				GLFW.glfwSetWindowShouldClose(window, true);
			}
		}
	}
}
