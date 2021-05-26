package graphics;

import java.util.ArrayList;

import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL43;

import entity.Triangle;
import graphics.shader.ShaderProgram;
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

	// Shader Program.
	private ShaderProgram shaderProgram;
	
	Renderer(){
		// Must create capabilities before utilizing OpenGL. Makes the 
		// OpenGL bindings available for use.
		GL.createCapabilities();
		
		// Create the texture.
		createTexture();
		
		// Create "entity" to draw to the window.
		createEntity();
		
		// Create the shader program for rendering.
		createShaderProgram();
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
	 * Creates the the shader program to use for drawing meshes 
	 * to the window.
	 */
	private void createShaderProgram() {
		shaderProgram = new ShaderProgram();
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
		
		
		
		GL43.glUseProgram(shaderProgram.getShaderProgramID());
		
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
