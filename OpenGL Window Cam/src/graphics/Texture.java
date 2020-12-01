package graphics;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL43;

/**
 * Texture object that holds the texture data it was created with.
 * <p>
 * @author Ryan Baggs
 * @date Created on 16-Nov-2020
 */
public class Texture {
	
	// Texture information.
	private ByteBuffer textureData;
	private int width;
	private int height;
	private int channels;
	
	// OpenGL ID for the Texture.
	private int textureID;
	
	/**
	 * Creates a new Texture that holds the specified data.
	 * 
	 * @param textureData in the form of a ByteBuffer.
	 * @param width of the texture image.
	 * @param height of the texture image.
	 * @param channels the number of channels for the image. (Example: RGB 
	 * is 3).
	 */
	public Texture(ByteBuffer textureData, int width, int height, int channels) {
		this.textureData = textureData;
		this.width = width;
		this.height = height;
		this.channels = channels;
		
		generate();
	}
	
	/**
	 * Generates a new OpenGL texture.
	 */
	private void generate() {
		textureID = GL43.glGenTextures();
	}
	
	/**
	 * Binds the texture making it current.
	 */
	public void bind() {
		GL43.glBindTexture(GL43.GL_TEXTURE_2D, textureID);
	}
	
	/**
	 * Unbinds the current texture.
	 */
	public void unBind() {
		GL43.glBindTexture(GL43.GL_TEXTURE_2D, 0);
	}
	
	/**
	 * Sets the texture parameters.
	 */
	public void setTextureParams() {
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_WRAP_S, GL43.GL_REPEAT);	
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_WRAP_T, GL43.GL_REPEAT);
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_MIN_FILTER, GL43.GL_NEAREST);
		GL43.glTexParameteri(GL43.GL_TEXTURE_2D, GL43.GL_TEXTURE_MAG_FILTER, GL43.GL_NEAREST);
	}
	
	/**
	 * Checks if the texture data is null and prints out an error if true.
	 */
	public void checkTextureData() {
		if(textureData == null)
			System.err.println("Failed to load texture.");
	}
	
	/**
	 * Sets the texture image data.
	 */
	public void setTextureImage() {
		GL43.glTexImage2D(GL43.GL_TEXTURE_2D, 0, GL43.GL_RGBA, width, 
				height, 0, GL43.GL_RGBA, GL43.GL_UNSIGNED_BYTE, 
				textureData);
	}
	
	/**
	 * Generates the Mipmap of the texture.
	 */
	public void generateMipmap() {
		GL43.glGenerateMipmap(GL43.GL_TEXTURE_2D);
	}
	
	public ByteBuffer getTextureData() {
		return textureData;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getChannels() {
		return channels;
	}

}
