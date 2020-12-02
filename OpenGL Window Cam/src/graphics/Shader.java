package graphics;

import org.lwjgl.opengl.GL43;

/**
 * Shader class to be inherited by specific type shaders.
 * <p>
 * @author Ryan Baggs
 * @date Created on 02-Dec-2020
 */
abstract class Shader {
	
	// OpenGL ID for the Shader.
	protected int shaderID;
	
	/**
	 * Creates a new Shader of the specified type.
	 * 
	 * @param shaderType one of OpenGL's shader types (Example: 
	 * GL43.GL_VERTEX_SHADER).
	 */
	protected Shader(int shaderType) {
		create(shaderType);
	}
	
	/**
	 * Creates a new OpenGL shader of the specified type.
	 * 
	 * @param shaderType one of OpenGL's shader types (Example: 
	 * GL43.GL_VERTEX_SHADER).
	 */
	private void create(int shaderType) {
		shaderID = GL43.glCreateShader(shaderType);
	}
	
	/**
	 * Sets the shader source code.
	 * 
	 * @param sourceCode of the shader.
	 */
	public void setSource(CharSequence sourceCode) {
		GL43.glShaderSource(shaderID, sourceCode);
	}
	
	/**
	 * Compiles the source code.
	 */
	public void compile() {
		GL43.glCompileShader(shaderID);
	}

	public int getShaderID() {
		return shaderID;
	}

	public void setShaderID(int shaderID) {
		this.shaderID = shaderID;
	}

}
