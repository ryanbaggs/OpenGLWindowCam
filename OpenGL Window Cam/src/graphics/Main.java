package graphics;

/**
 * Creates a new WindowManager.
 * <p>
 * @author Ryan Baggs
 * @date Created on 05-Nov-2020
 */
public class Main {
	
	public static void main(String[] args) {
		
		try {
			new WindowManager();
		} catch (GLFWFailedInitializeException e) {
			e.printStackTrace();
			System.err.println("Failed to initialize GLFW.");
		} catch (GLFWFailedWindowCreationException e) {
			e.printStackTrace();
			System.err.println(
					"Failed to create GLFW window or window context.");
		}
	}
}
