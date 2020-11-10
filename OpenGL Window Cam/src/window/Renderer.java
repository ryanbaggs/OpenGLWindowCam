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
	// Vertex Shader.
	int vs;
	// Fragment Shader.
	int fs;
	// Shader Program.
	int sp;
	
	// The vertices of the triangle.
	private float[] points = {
		0.0f,  0.5f,  0.0f,
		0.5f, -0.5f,  0.0f,
		-0.5f, -0.5f,  0.0f
	};
	
	// Vertex Shader code.
	private CharSequence vertexShaderCode = 
			"#version 430\n" + 
			"in vec3 point;" + 
			"void main() {" + 
			"    gl_Position = vec4(point, 1.0);" + 
			"}";
	
	// Fragment Shader code.
	private CharSequence fragmentShaderCode = 
			"#version 430\n" + 
			"out vec4 frag_color;" + 
			"void main() {" + 
			"    frag_color = vec4(1.0, 0.0, 0.0, 1.0);" + 
			"}";
	
	Renderer(){
		createVBO();
		createVAO();
		createShaders();
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
	
	/**
	 * Creates the shaders and the shader program to use for drawing meshes 
	 * to the window.
	 */
	void createShaders() {
		vs = compileShader(GL43.GL_VERTEX_SHADER, vertexShaderCode);
		fs = compileShader(GL43.GL_FRAGMENT_SHADER, fragmentShaderCode);
		linkProgram();
	}
	
	/**
	 * Creates a shader of the specified type, sets the specified code to the 
	 * shader source code, and compiles the provided code.
	 * 
	 * @param shaderType The type of shader (vertex, fragment, etc...)
	 * @param shaderCode to be used as the shader's source code.
	 * @return the compiled shader's name.
	 */
	private int compileShader( int shaderType, CharSequence shaderCode) {
		// Create the shader, set the name value.
		int shader = GL43.glCreateShader(shaderType);
		
		// Set the shader source code with the shader code for the specified 
		// shader.
		GL43.glShaderSource(shader, shaderCode);
		
		// Compile the code.
		GL43.glCompileShader(shader);
		
		// Return the compiled shader name.
		return shader;
	}
	
	/**
	 * Creates a shader program, attaches the shaders to it and links the 
	 * shaders together into a single program.
	 */
	private void linkProgram() {
		// Create the shader program, set the name value.
		sp = GL43.glCreateProgram();
		
		// Attach the vertex and fragment shaders to the shader program.
		GL43.glAttachShader(sp, vs);
		GL43.glAttachShader(sp, fs);
		
		// Link the shaders together into single program.
		GL43.glLinkProgram(sp);
	}
	
	void update() {
		// Clear the window.
		GL43.glClear(GL43.GL_COLOR_BUFFER_BIT | GL43.GL_DEPTH_BUFFER_BIT);
		
		GL43.glUseProgram(sp);
		
		GL43.glBindVertexArray(vao);
		
		GL43.glDrawArrays(GL43.GL_TRIANGLES, 0, 3);
	}
	
}
