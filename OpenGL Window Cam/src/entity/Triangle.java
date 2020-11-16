package entity;

import org.lwjgl.opengl.GL43;

/**
 * Represents a Triangle entity that has coordinates and can move.
 *  
 * @author Ryan Baggs
 * @date Created on 11-Nov-2020
 */
public class Triangle {
	
	// Triangle specific VAO, VBO, and Texture.
	int vao = 0;
	int vbo = 0;
	int texture = 0;
	
	// Movement flags.
	private boolean moveUp = false;
	private boolean moveDown = false;
	private boolean moveLeft = false;
	private boolean moveRight = false;
	
	// The vertices of the triangle.
	private float[] points = {
		0.0f,  0.5f,  0.0f,
		0.5f, -0.5f,  0.0f,
		-0.5f, -0.5f,  0.0f
	};
	
	private int[] elements = {
		0, 1, 2	
	};
	
	public Triangle() {
		createTriangle();
	}
	
	private void createTriangle(){
		createVBO();
		createVAO();
		addDataToVAO();
		unBind();
	}
	
	/**
	 * Creates a new Vertex Buffer Object (VBO) at specified index, binds it, 
	 * and adds data to it.
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
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, points, GL43.GL_DYNAMIC_DRAW);
	}
	
	/**
	 * Creates the texture vertex info.
	 */
	private void createTexture() {
		// Get the name of the generated Texture.
		texture = GL43.glGenTextures();
	}
	
	/**
	 * Creates a new Vertex Array Object (VAO) and binds it.
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
	}
	
	/**
	 * enables the first element (attribute), binds the VBO, and define the 
	 * array of vertices (how they will be read as).
	 * 
	 * @param vbo to be added to the VAO.
	 */
	private void addDataToVAO() {
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
	 * Updates the coordinates of the triangle
	 */
	public void update() {
		checkAndMoveUp();
		checkAndMoveDown();
		checkAndMoveRight();
		checkAndMoveLeft();
		updateVBO();
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
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, points, GL43.GL_DYNAMIC_DRAW);
	}
	
	/**
	 * Unbinds the VBO and VAO.
	 */
	private void unBind() {
		// Unbind the VBO.
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, 0);
		
		// Unbind the VAO.
		GL43.glBindVertexArray(vao);
	}
	
	/**
	 * Deletes the VAO and VBO associated with triangle.
	 */
	public void deleteVertexObjects() {
		GL43.glDeleteBuffers(vbo);
		GL43.glDeleteVertexArrays(vao);
	}
	
	/**
	 * Check flags and move if necessary.
	 */
	
	private void checkAndMoveUp() {
		if(moveUp) {
			points[1] += 0.02f;
			points[4] += 0.02f;
			points[7] += 0.02f;
		}
	}
	
	private void checkAndMoveDown() {
		if(moveDown) {
			points[1] -= 0.02f;
			points[4] -= 0.02f;
			points[7] -= 0.02f;
		}
	}
	
	private void checkAndMoveRight() {
		if(moveRight) {
			points[0] += 0.02f;
			points[3] += 0.02f;
			points[6] += 0.02f;
		}
	}
	
	private void checkAndMoveLeft() {
		if(moveLeft) {
			points[0] -= 0.02f;
			points[3] -= 0.02f;
			points[6] -= 0.02f;
		}
	}
	
	/**
	 * Getters and setters.
	 */
	
	public float[] getPoints() {
		return points;
	}

	public void setPoints(float[] points) {
		this.points = points;
	}
	
	public int getVao() {
		return vao;
	}

	public void setVao(int vao) {
		this.vao = vao;
	}

	public int getVbo() {
		return vbo;
	}

	public void setVbo(int vbo) {
		this.vbo = vbo;
	}

	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	public void setMoveDown(boolean moveDown) {
		this.moveDown = moveDown;
	}

	public void setMoveLeft(boolean moveLeft) {
		this.moveLeft = moveLeft;
	}

	public void setMoveRight(boolean moveRight) {
		this.moveRight = moveRight;
	}
	
	
	
}
