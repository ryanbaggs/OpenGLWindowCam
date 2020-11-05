package window;

import org.lwjgl.glfw.GLFW;

public class WindowManager {

	public WindowManager() throws GLFWFailedInitializeException {
		
		// Attempt to initialize GLFW.
		if(!GLFW.glfwInit()) {
			// GLFW not successfully initialized, throw new exception.
			throw new GLFWFailedInitializeException();
		}
		
	}
	
}
