package window;

import org.lwjgl.opengl.GL43;

/**
 * Used to render triangle to a window.
 * <p>
 * @author Ryan Baggs
 * @date Created on 08-Nov-2020
 */
class Renderer {
	
	// Vertex Buffer Object.
	int vbo;
	
	// The vertices of the triangle.
	private float[] points = {
		0.0f,  0.5f,  0.0f,
		0.5f, -0.5f,  0.0f,
		-0.5f, -0.5f,  0.0f
	};
	
	Renderer(){
		createVBO();
	}
	
	/**
	 * Creates a new Vertex Buffer Object (VBO), binds it, and adds data to 
	 * it. The vertex buffer object is what OpenGL uses to hold vertex data, 
	 * for example, the location or color.
	 */
	void createVBO() {
		// Get the name of the generated buffer object.
		vbo = GL43.glGenBuffers();
		
		// Bind the buffer object to the array buffer. This sets the 
		// "current" buffer, allowing to add the data.
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, vbo);
		
		// Give the array the data and inform that the data will be set only 
		// once, but will be drawn many times.
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, points, GL43.GL_STATIC_DRAW);
	}
	
	void update() {
		
	}
	
}
