package graphics;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL43;

import entity.Triangle;
import io.TextureLoader;

/**
 * Used to render triangle to a window.
 * <p>
 * @author Ryan Baggs
 * @date Created on 08-Nov-2020
 */
class Renderer {
	
	private ArrayList<Triangle> triangles = new ArrayList<Triangle>();
	
	// Constants.
	private static final int MAX_TRIANGLES = 10;
	private static final String FILE_NAME = "src/resources/bricks_texture.png";
	
	// Texture.
	private Texture texture;
	
	// Vertex Shader.
	int vs;
	// Fragment Shader.
	int fs;
	// Shader Program.
	int sp;
	
	// Vertex Shader code.
	private CharSequence vertexShaderCode = 
			"#version 430\n" + 
			"layout (location = 0) in vec3 point;" + 
			"layout (location = 1) in vec2 texPointIn;" + 
			"out vec2 texPoint;" + 
			"void main() {" + 
			"    gl_Position = vec4(point, 1.0);" + 
			"    texPoint = texPointIn;" +
			"}";
	
	// Fragment Shader code.
	private CharSequence fragmentShaderCode = 
			"#version 430\n" + 
			"in vec2 texPoint;" +
			"out vec4 fragColor;" + 
			"uniform sampler2D samplerTexture;" +
			"void main() {" + 
			"    fragColor = texture(samplerTexture, texPoint);" + 
			"}";
	
	Renderer(){
		// Must create capabilities before utilizing OpenGL. Makes the 
		// OpenGL bindings available for use.
		GL.createCapabilities();
		
		// Create the texture.
		createTexture();
		
		// Create "entity" to draw to the window.
		createEntity();
		
		// Create the shaders for rendering.
		createShaders();
	}
	
	private void createTexture() {
		TextureLoader textureLoader = new TextureLoader();
		texture = textureLoader.loadTexture(FILE_NAME);
	}
	
	/**
	 * Creates an "entity" in this case a triangle for use in rendering an 
	 * object in the window.
	 */
	void createEntity() {
		Triangle triangle = new Triangle(texture);	
		triangles.add(triangle);
	}
	
	/**
	 * Creates the shaders and the shader program to use for drawing meshes 
	 * to the window.
	 */
	private void createShaders() {
		vs = compileShader(GL43.GL_VERTEX_SHADER, vertexShaderCode);
		fs = compileShader(GL43.GL_FRAGMENT_SHADER, fragmentShaderCode);
		linkProgram();
		bindAttributes();
	}
	
	/**
	 * Creates a shader of the specified type, sets the specified code to the 
	 * shader source code, and compiles the provided code.
	 * 
	 * @param shaderType The type of shader (vertex, fragment, etc...)
	 * @param shaderCode to be used as the shader's source code.
	 * @return the compiled shader's name.
	 */
	private int compileShader(int shaderType, CharSequence shaderCode) {
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
	
	private void bindAttributes() {
		GL43.glBindAttribLocation(sp, 0, "point");
		GL43.glBindAttribLocation(sp, 1, "texPointIn");
	}

	/**
	 * Updates what is rendered in the window.
	 */
	void update(boolean addEntity) {
		
		if(addEntity && triangles.size() < MAX_TRIANGLES)
			createEntity();
		
		triangles.get(0).update();
	}
	
	/**
	 * First clears the window with the buffer bit color, then lets OpenGL 
	 * know to use the program created, then binds the vertex array, and draws 
	 * the arrays in the vertex array.
	 */
	void draw() {
		// Set the buffer color bit.
		GL43.glClearColor(1, 0, 0, 1);
		
		// Clear the window with the color.
		GL43.glClear(GL43.GL_COLOR_BUFFER_BIT);
		
		
		
		GL43.glUseProgram(sp);
		
		for(Triangle triangle : triangles) {
			GL43.glBindTexture(GL43.GL_TEXTURE_2D, triangle.getTexture());
			
			GL43.glBindVertexArray(triangle.getVao());
			
			GL43.glEnableVertexAttribArray(0);
			GL43.glEnableVertexAttribArray(1);
			
			GL43.glDrawArrays(GL43.GL_TRIANGLES, 0, 3);
			
			GL43.glBindVertexArray(0);
		}
	}
	
	/**
	 * Deletes the VAOs and the VBOs to properly close window.
	 */
	void deleteVertexObjects() {
		for(Triangle triangle : triangles) {
			triangle.deleteVertexObjects();
		}
	}
	
	/**
	 * Set the movement flags for the Triangle.
	 * 
	 * @param flag value, true means move.
	 */
	
	void setUpFlag(boolean flag) {
		triangles.get(0).setMoveUp(flag);
	}
	
	void setDownFlag(boolean flag) {
		triangles.get(0).setMoveDown(flag);
	}
	
	void setRightFlag(boolean flag) {
		triangles.get(0).setMoveRight(flag);
	}
	
	void setLeftFlag(boolean flag) {
		triangles.get(0).setMoveLeft(flag);
	}
}
