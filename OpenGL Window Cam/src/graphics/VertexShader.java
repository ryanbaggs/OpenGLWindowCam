package graphics;

import org.lwjgl.opengl.GL43;

/**
 * VertexShader object.
 * <p>
 * @author Ryan Baggs
 * @date Created on 02-Dec-2020
 */
public class VertexShader extends Shader{
	
	// Vertex shader source code.
	private CharSequence vertexShaderCode = 
			"#version 430\n" + 
			"layout (location = 0) in vec3 point;" + 
			"layout (location = 1) in vec2 texPointIn;" + 
			"out vec2 texPoint;" + 
			"void main() {" + 
			"    gl_Position = vec4(point, 1.0);" + 
			"    texPoint = texPointIn;" +
			"}";
	
	// Vertex shader type.
	private static final int VERTEX_SHADER = GL43.GL_VERTEX_SHADER;

	protected VertexShader() {
		super(VERTEX_SHADER);
	}

	/**
	 * @see Shader#setSource(CharSequence)
	 */
	@Override
	public void setSource(CharSequence sourceCode) {
		super.setSource(sourceCode);
	}

	/**
	 * @see Shader#compile()
	 */
	@Override
	public void compile() {
		super.compile();
	}

	@Override
	public int getShaderID() {
		return super.getShaderID();
	}

	@Override
	public void setShaderID(int shaderID) {
		super.setShaderID(shaderID);
	}

	public CharSequence getVertexShaderCode() {
		return vertexShaderCode;
	}

}
