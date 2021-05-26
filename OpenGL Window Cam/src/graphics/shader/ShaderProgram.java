package graphics.shader;

import org.lwjgl.opengl.GL43;

/**
 * Compiles the shaders and links them into a ShaderProgram.
 * <p>
 * @author Ryan Baggs
 * Created on May 26, 2021
 */
public class ShaderProgram {
	
	private VertexShader vso;
	private FragmentShader fso;
	
	private int shaderProgramID;

	public ShaderProgram() {
		initShaderObjects();
		create();
		attachShaders();
		link();
		bindAttributes();
	}
	
	/**
	 * Initialize the Shader objects, creating and compiling them for use.
	 */
	private void initShaderObjects() {
		vso = new VertexShader();
		fso = new FragmentShader();
	}
	
	/**
	 * Create the shader program, set the ID value.
	 */
	private void create() {
		shaderProgramID = GL43.glCreateProgram();
	}
	
	/**
	 * Attach the shaders to the shader program.
	 */
	private void attachShaders() {
		GL43.glAttachShader(shaderProgramID, vso.getShaderID());
		GL43.glAttachShader(shaderProgramID, fso.getShaderID());
	}
	
	/**
	 * Link the shaders together into a single shader program.
	 */
	private void link() {
		GL43.glLinkProgram(shaderProgramID);
	}
	
	/**
	 * Bind the attributes to their index locations and variable names.
	 */
	private void bindAttributes() {
		GL43.glBindAttribLocation(shaderProgramID, 0, VertexShader.getAttributeVariable());
		GL43.glBindAttribLocation(shaderProgramID, 1, FragmentShader.getAttributeVariable());
	}

	public int getShaderProgramID() {
		return shaderProgramID;
	}
	
}
