package graphics;

import org.lwjgl.opengl.GL43;

/**
 * FragmentShader object.
 * <p>
 * @author Ryan Baggs
 * @date Created on 02-Dec-2020
 */
public class FragmentShader extends Shader{
	
	// Fragment shader source code.
	private CharSequence fragmentShaderCode = 
			"#version 430\n" + 
			"in vec2 texPoint;" +
			"out vec4 fragColor;" + 
			"uniform sampler2D samplerTexture;" +
			"void main() {" + 
			"    fragColor = texture(samplerTexture, texPoint);" + 
			"}";
	
	// Fragment shader type.
	private static final int FRAGMENT_SHADER = GL43.GL_FRAGMENT_SHADER;

	protected FragmentShader() {
		super(FRAGMENT_SHADER);
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

	public CharSequence getFragmentShaderCode() {
		return fragmentShaderCode;
	}

}
