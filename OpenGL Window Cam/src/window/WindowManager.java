package window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.opengl.GL;

/**
 * Manages GLFW, window, and the OpenGL context for the window.
 * <p>
 * @author Ryan Baggs
 * @date Created on 05-Nov-2020
 */
public class WindowManager {
	
	private Renderer renderer;
	
	private static final long NULL = 0;
	
	// Window specifications.
	private static final int WIDTH = 640;
	private static final int HEIGHT = 480;
	private static final CharSequence TITLE = "Canoe";
	private static final int SWAP_INTERVAL = 1;

	public WindowManager() throws GLFWFailedInitializeException, 
	GLFWFailedWindowCreationException {
		
		initializeGLFW();
		long windowHandle = createWindow();
		
		setKeyCallbacks(windowHandle);
		
		initializeRenderer();
		
		// Perform updates here.
		updateWindow(windowHandle);
		endWindowManager(windowHandle);
	}
	
	/**
	 * Initializes GLFW and sets the error callback.
	 * 
	 * @throws GLFWFailedInitializeException
	 */
	private void initializeGLFW() throws GLFWFailedInitializeException {
		// Set the error callback.
		GLFW.glfwSetErrorCallback(new ErrorCallback());
		
		// Attempt to initialize GLFW.
		if(!GLFW.glfwInit()) {
			// GLFW not successfully initialized, throw new exception.
			throw new GLFWFailedInitializeException();
		}
		
		GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 4);
		GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3);
		GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_ANY_PROFILE);
	}
	
	/**
	 * Creates a window and window context, assigning its handle to the 
	 * windowHandle, makes the context current, and sets the swap interval 
	 * for the frames.
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
			// Window or window context creation failed. Terminate GLFW and 
			// throw exception.
			GLFW.glfwTerminate();
			throw new GLFWFailedWindowCreationException();
		}
		
		// Make the OpenGL context current.
		GLFW.glfwMakeContextCurrent(windowHandle);
		
		// Set the frame swap interval.
		GLFW.glfwSwapInterval(SWAP_INTERVAL);
		
		// Set the window to visible.
		GLFW.glfwShowWindow(windowHandle);
		
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
	 * Instantiates the renderer for use.
	 */
	private void initializeRenderer() {
		// Instantiate the renderer.
		renderer = new Renderer();
	}
	
	/**
	 * Updates the window and renderer, checks if closed, polls the events 
	 * to see if any occurred, and swaps the frame buffers.
	 * 
	 * @param window to update.
	 */
	private void updateWindow(long window) {
		
		// Check if the close flag is set to true.
		while(!GLFW.glfwWindowShouldClose(window)) {
			// Update the renderer to draw current info.
			updateRenderer();
			
			// Inform GLFW to swap the current frame with the next frame.
			GLFW.glfwSwapBuffers(window);
			
			// Poll the events to let system know updating and get any 
			// events that occurred.
			GLFW.glfwPollEvents();
		}
	}
	
	/**
	 * Tell the renderer to update.
	 */
	private void updateRenderer() {
		renderer.update();
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
	 * Error callback for GLFW to call when an error occurs.
	 * 
	 * @author Ryan Baggs
	 * @date Created on 08-Nov-2020
	 */
	public class ErrorCallback implements GLFWErrorCallbackI {
		
		/**
		 * Called when a GLFW error occurs, prints person readable error 
		 * message.
		 * 
		 * @param error the error code.
		 * @param description the readable error message description.
		 */
		@Override
		public void invoke(int error, long description) {
			// Print error to the error output stream.
			System.err.println("Error in " + getClass().getSimpleName() + ": " 
		+ "\nError Number: " + error + "\nDescription: " + description);
		}
		
	}
	
	/**
	 * Key callback for key input in the specified GLFW window.
	 * <p>
	 * @author Ryan Baggs
	 * @date Created on 07-Nov-2020
	 */
	public class KeyCallback implements GLFWKeyCallbackI {

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
