package entity;

import org.lwjgl.opengl.GL43;

/**
 * Represents a Triangle entity that has coordinates and can move.
 * <p>
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
	private float[] pointData = {
	     // Positions	       // Texture coords
		 0.0f,  0.5f,  0.0f,   0.5f, 1.0f,   // top-center corner 
		 0.5f, -0.5f,  0.0f,   1.0f, 0.0f,  // lower-right corner
		-0.5f, -0.5f,  0.0f,   0.0f, 0.0f  // lower-left corner
	};
	
	private Texture textureData;
	
	public Triangle(Texture textureData) {
		this.textureData = textureData;
		createTriangle();
	}
	
	private void createTriangle(){
		createVBO();
		createTexture();
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
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, pointData, GL43.GL_DYNAMIC_DRAW);
	}
	
	/**
	 * Creates the texture vertex info.
	 */
	private void createTexture() {
		// Get the name of the generated texture.
		texture = GL43.glGenTextures();
		
		// Bind the texture.
		GL43.glBindTexture(GL43.GL_TEXTURE_2D, texture);
		
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_WRAP_S, GL43.GL_REPEAT);	
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_WRAP_T, GL43.GL_REPEAT);
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_MIN_FILTER, GL43.GL_NEAREST);
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_MAG_FILTER, GL43.GL_NEAREST);
		
		// Check that the data for the texture is not null.
		if(textureData != null) {
			GL43.glTexImage2D(GL43.GL_TEXTURE_2D, 0, GL43.GL_RGBA, 
					textureData.getWidth(), textureData.getHeight(), 0, 
					GL43.GL_RGBA, GL43.GL_UNSIGNED_BYTE, 
					textureData.getTexture());
			GL43.glGenerateMipmap(GL43.GL_TEXTURE_2D);
		} else {
			System.err.println("Failed to load texture.");
		}
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
	 * Enables the first element (attribute), binds the VBO, and define the 
	 * array of vertices (how they will be read as). 
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
		GL43.glVertexAttribPointer(0, 3, GL43.GL_FLOAT, false, (5 * Float.BYTES), 0L);
		
		// Enable the second attribute array in the VAO for use.
		GL43.glEnableVertexAttribArray(1);
		
		// Inform OpenGL what is the active texture.
		GL43.glActiveTexture(GL43.GL_TEXTURE0);
		
		// Bind the texture.
		GL43.glBindTexture(GL43.GL_TEXTURE_2D, texture);
		
		// Second index, with offset to the texture coords.
		GL43.glVertexAttribPointer(1, 2, GL43.GL_FLOAT, false, (5 * Float.BYTES), (3 * Float.BYTES));
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
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, pointData, GL43.GL_DYNAMIC_DRAW);
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
			pointData[1] += 0.02f;
			pointData[4] += 0.02f;
			pointData[7] += 0.02f;
		}
	}
	
	private void checkAndMoveDown() {
		if(moveDown) {
			pointData[1] -= 0.02f;
			pointData[4] -= 0.02f;
			pointData[7] -= 0.02f;
		}
	}
	
	private void checkAndMoveRight() {
		if(moveRight) {
			pointData[0] += 0.02f;
			pointData[3] += 0.02f;
			pointData[6] += 0.02f;
		}
	}
	
	private void checkAndMoveLeft() {
		if(moveLeft) {
			pointData[0] -= 0.02f;
			pointData[3] -= 0.02f;
			pointData[6] -= 0.02f;
		}
	}
	
	/**
	 * Getters and setters.
	 */
	
	public float[] getPoints() {
		return pointData;
	}

	public void setPoints(float[] points) {
		this.pointData = points;
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

	public int getTexture() {
		return texture;
	}
	
}
