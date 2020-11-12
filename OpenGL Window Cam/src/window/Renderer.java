package window;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL43;

import entity.Triangle;

/**
 * Used to render triangle to a window.
 * <p>
 * @author Ryan Baggs
 * @date Created on 08-Nov-2020
 */
class Renderer {
	
	private Triangle triangle;
	private ArrayList<Triangle> triangles;
	
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
		// Must create capabilities before utilizing OpenGL. Makes the 
		// OpenGL bindings available for use.
		GL.createCapabilities();
		
		// Create "entity" to draw to the window.
		createEntity();
		
		createVBO();
		createVAO();
		createShaders();
	}
	
	/**
	 * Creates an "entity" in this case a triangle for use in rendering an 
	 * object in the window. Instantiates the triangles ArrayList.
	 */
	private void createEntity() {
		triangle = new Triangle();
		triangles = new ArrayList<Triangle>();
	}
	
	/**
	 * Creates a new Vertex Buffer Object (VBO), binds it, and adds data to 
	 * it. 
	 * 
	 * The vertex buffer object is what OpenGL uses to hold vertex data, 
	 * for example, the location or color.
	 */
	private void createVBO() {
		// Get the name of the generated buffer object.
		vbo = GL43.glGenBuffers();
		
		// Bind the buffer object to the array buffer. This sets the 
		// "current" buffer, allowing to add the data.
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, vbo);
		
		// Give the array the data and inform that the data will be set many 
		// times and will change often.
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, triangle.getPoints(), GL43.GL_DYNAMIC_DRAW);
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
	private void createVAO() {
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
	private void createShaders() {
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
	
	/**
	 * Updates what is rendered in the window. This would be taken care of by 
	 * another thread and class to do separate updates from the rendering.
	 */
	void update() {
		triangle.update();
		updateVBO();
	}
	
	/**
	 * First clears the window with the buffer bit color, then lets OpenGL 
	 * know to use the program created, then binds the vertex array, and draws 
	 * the arrays in the vertex array.
	 */
	void draw() {
		// Clear the window.
		GL43.glClear(GL43.GL_COLOR_BUFFER_BIT | GL43.GL_DEPTH_BUFFER_BIT);
		
		GL43.glUseProgram(sp);
		
		GL43.glBindVertexArray(vao);
		
		GL43.glDrawArrays(GL43.GL_TRIANGLES, 0, 3);
	}
	
	/**
	 * Binds the VBO and updates it with the new triangle points.
	 */
	private void updateVBO() {
		// Bind the buffer object to the array buffer. This sets the 
		// "current" buffer, allowing to add the data.
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, vbo);
		
		// Give the array the data and inform that the data will be set many 
		// times and will change often.
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, triangle.getPoints(), GL43.GL_DYNAMIC_DRAW);
	}
	
	/**
	 * Set the movement flags for the Triangle.
	 * 
	 * @param flag value, true means move.
	 */
	
	void setUpFlag(boolean flag) {
		triangle.setMoveUp(flag);
	}
	
	void setDownFlag(boolean flag) {
		triangle.setMoveDown(flag);
	}
	
	void setRightFlag(boolean flag) {
		triangle.setMoveRight(flag);
	}
	
	void setLeftFlag(boolean flag) {
		triangle.setMoveLeft(flag);
	}
}
