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
	// Vertex Array Object.
	int vao;
	
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
	 * it. 
	 * 
	 * The vertex buffer object is what OpenGL uses to hold vertex data, 
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
	
	/**
	 * Creates a new Vertex Array Object (VAO), binds it, enables the first 
	 * element (attribute), binds the VBO, and define the array of vertices 
	 * (how they will be read as).
	 * 
	 * The VAO keeps track of the VBOs, the array definitions for each, and 
	 * the attribute (like coordinates, or color) arrays so you don't have to 
	 * re-bind and redefine the VAOs and arrays every time you draw the mesh.
	 */
	void createVAO() {
		// Generate the VAO name.
		vao = GL43.glGenVertexArrays();
		
		// Bind the VAO, making it the "current" VAO being acted upon. Only 
		// one per OpenGL context.
		GL43.glBindVertexArray(vao);
		
		// Enable the first attribute array in the VAO for use.
		GL43.glEnableVertexAttribArray(0);
		
		// Bind the VBO making it the "current" VBO.
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, vbo);
		
		// The first parameter is the index for the VAO to set the currently 
		// bound array buffer to said index. The rest of the parameters are 
		// info on the array buffer's data. 
		// Parameters: index, size, type, normalized, stride, pointer (or offset).
		GL43.glVertexAttribPointer(0, 3, GL43.GL_FLOAT, false, 0, 0L);
	}
	
	void update() {
		
	}
	
}
