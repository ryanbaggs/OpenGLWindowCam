package graphics;

import org.lwjgl.opengl.GL43;

/**
 * Vertex Array Object for OpenGL.
 * <p>
 * @author Ryan Baggs
 * @date Created on 29-Nov-2020
 */
public class VertexArrayObject {

	// The OpenGL ID for the VAO.
	private int vaoID;
	
	public VertexArrayObject() {
		generate();
	}
	
	/**
	 * Generates a new VAO.
	 */
	private void generate() {
		vaoID = GL43.glGenVertexArrays();
	}
	
	/**
	 * Binds the VAO making it current.
	 */
	public void bind() {
		GL43.glBindVertexArray(vaoID);
	}
	
	/**
	 * Unbinds the current VAO.
	 */
	public void unBind() {
		GL43.glBindVertexArray(0);
	}
	
	/**
	 * Sets the VAO data for the specified attribute.
	 * 
	 * @param index of the attribute.
	 * @param size the number of values per vertex.
	 * @param type the type of the values (Example: GL43.GL_FLOAT).
	 * @param stride the number of vertices between each set of vertex 
	 * attributes.
	 * @param offset the starting vertex of the attribute.
	 */
	public void attribPointer(int index, int size, int type, int stride, long offset) {
		GL43.glVertexAttribPointer(index, size, type, false, stride, offset);
	}
	
	/**
	 * Deletes the VAO.
	 */
	public void delete() {
		GL43.glDeleteVertexArrays(vaoID);
	}

	public int getVaoID() {
		return vaoID;
	}

	public void setVaoID(int vaoID) {
		this.vaoID = vaoID;
	}
	
}
