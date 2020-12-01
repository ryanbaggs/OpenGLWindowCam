package graphics;

import org.lwjgl.opengl.GL43;

/**
 * Vertex Buffer Object for OpenGL.
 * <p>
 * @author Ryan Baggs
 * @date Created on 01-Dec-2020
 */
public class VertexBufferObject {

	// OpenGL ID for the VBO.
	private int vboID;
	
	public VertexBufferObject() {
		generate();
	}
	
	/**
	 * Generates a new VBO.
	 */
	private void generate() {
		vboID = GL43.glGenBuffers();
	}
	
	/**
	 * Binds the VBO making it current.
	 */
	public void bind() {
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, vboID);
	}
	
	/**
	 * Unbinds the currently bound VBO.
	 */
	public void unBind() {
		GL43.glBindBuffer(GL43.GL_ARRAY_BUFFER, 0);
	}
	
	/**
	 * Sets the data for the OpenGL Array Buffer for current VBO.
	 * 
	 * @param vertexData to be set.
	 * @param usage tells OpenGL the frequency of data change and frequency 
	 * of redraws. (Example: GL43.GL_DYNAMIC_DRAW which tells OpenGL that the 
	 * data will be changed and redrawn frequently.)
	 */
	public void bufferData(float[] vertexData, int usage) {
		GL43.glBufferData(GL43.GL_ARRAY_BUFFER, vertexData, usage);
	}
	
	/**
	 * Deletes this VBO.
	 */
	public void delete() {
		GL43.glDeleteBuffers(vboID);
	}

	public int getVboID() {
		return vboID;
	}

	public void setVboID(int vboID) {
		this.vboID = vboID;
	}
	
}
