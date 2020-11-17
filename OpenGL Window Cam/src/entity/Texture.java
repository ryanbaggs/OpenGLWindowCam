package entity;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;

/**
 * Texture object that holds data on the image loaded when instantiated.
 * <p>
 * @author Ryan Baggs
 * @date Created on 16-Nov-2020
 */
public class Texture {
	
	private ByteBuffer texture;
	private int width;
	private int height;
	private int numberOfChannels;
	
	public Texture(String fileName) {
		loadTexture(fileName);
	}
	
	/**
	 * Loads the texture from the specified file name and sets the Texture 
	 * fields to the data received.
	 * 
	 * @param fileName the path and file name of the texture to load.
	 */
	public void loadTexture(String fileName){
		// Create the file name as a ByteBuffer.
		ByteBuffer file = ByteBuffer.wrap(fileName.getBytes());
		
		// Create ByteBuffers to get data.
		IntBuffer x = IntBuffer.allocate(1 * Integer.BYTES);
		IntBuffer y = IntBuffer.allocate(1 * Integer.BYTES);
		IntBuffer channels = IntBuffer.allocate(1 * Integer.BYTES);
		
		// Get the texture data.
		texture = STBImage.stbi_load(file, x, y, channels, 0);
		width = x.get();
		height = y.get();
		numberOfChannels = channels.get();
	}
	
	public void freeImage() {
		STBImage.stbi_image_free(texture);
	}

	public ByteBuffer getTexture() {
		return texture;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumberOfChannels() {
		return numberOfChannels;
	}

}
