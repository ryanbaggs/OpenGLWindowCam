package graphics.shader;

import org.lwjgl.opengl.GL43;

/**
 * VertexShader object.
 * <p>
 * @author Ryan Baggs
 * @date Created on 02-Dec-2020
 */
class VertexShader extends Shader{
	
	
	// Vertex shader type.
	private static final int VERTEX_SHADER = GL43.GL_VERTEX_SHADER;
	
	// Vertex shader source code.
	private static final CharSequence VERTEX_SHADER_CODE = 
			"#version 430\n" + 
			"layout (location = 0) in vec3 point;" + 
			"layout (location = 1) in vec2 texPointIn;" + 
			"out vec2 texPoint;" + 
			"void main() {" + 
			"    gl_Position = vec4(point, 1.0);" + 
			"    texPoint = texPointIn;" +
			"}";
	
	private static final String ATTRIBUTE_VARIABLE = "point";

	VertexShader() {
		super(VERTEX_SHADER, VERTEX_SHADER_CODE);
	}

	@Override
	protected int getShaderID() {
		return super.getShaderID();
	}

	static String getAttributeVariable() {
		return ATTRIBUTE_VARIABLE;
	}

}
