package graphics.shader;

import org.lwjgl.opengl.GL43;

/**
 * Shader class to be inherited by specific type shaders.
 * <p>
 * @author Ryan Baggs
 * @date Created on 02-Dec-2020
 */
abstract class Shader {
	
	// OpenGL ID for the Shader.
	private int shaderID;
	
	/**
	 * Creates a new Shader of the specified type.
	 * 
	 * @param shaderType one of OpenGL's shader types (Example: 
	 * GL43.GL_VERTEX_SHADER).
	 */
	protected Shader(int shaderType, CharSequence sourceCode) {
		create(shaderType);
		setSource(sourceCode);
		compile();
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
	private void setSource(CharSequence sourceCode) {
		GL43.glShaderSource(shaderID, sourceCode);
	}
	
	/**
	 * Compiles the source code.
	 */
	private void compile() {
		GL43.glCompileShader(shaderID);
	}

	protected int getShaderID() {
		return shaderID;
	}

}
