package graphics.shader;

import org.lwjgl.opengl.GL43;

/**
 * FragmentShader object.
 * <p>
 * @author Ryan Baggs
 * @date Created on 02-Dec-2020
 */
class FragmentShader extends Shader{
	
	// Fragment shader type.
	private static final int FRAGMENT_SHADER = GL43.GL_FRAGMENT_SHADER;
	
	// Fragment shader source code.
	private static final CharSequence FRAGMENT_SHADER_CODE = 
			"#version 430\n" + 
			"in vec2 texPoint;" +
			"out vec4 fragColor;" + 
			"uniform sampler2D samplerTexture;" +
			"void main() {" + 
			"    fragColor = texture(samplerTexture, texPoint);" + 
			"}";
	
	private static final String ATTRIBUTE_VARIABLE= "texPointIn";

	FragmentShader() {
		super(FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);
	}

	@Override
	protected int getShaderID() {
		return super.getShaderID();
	}

	static String getAttributeVariable() {
		return ATTRIBUTE_VARIABLE;
	}
	
}
