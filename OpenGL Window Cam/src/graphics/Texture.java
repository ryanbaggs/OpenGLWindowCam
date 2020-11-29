package graphics;

import java.nio.ByteBuffer;

/**
 * Texture object that holds the texture data it was created with.
 * <p>
 * @author Ryan Baggs
 * @date Created on 16-Nov-2020
 */
public class Texture {
	
	private ByteBuffer textureData;
	private int width;
	private int height;
	private int channels;
	
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
