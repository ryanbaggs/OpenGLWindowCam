package entity;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

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
	public void loadTexture(String fileName) {
		
		try(MemoryStack stack = MemoryStack.stackPush()){
			// Create ByteBuffers to get data.
			IntBuffer x = stack.mallocInt(1);
			IntBuffer y = stack.mallocInt(1);
			IntBuffer channels = stack.mallocInt(1);
			
			// Get the texture data.
			texture = STBImage.stbi_load(fileName, x, y, channels, 4);
			
			if(texture == null)
				System.out.println(STBImage.stbi_failure_reason());
			
			width = x.get();
			height = y.get();
			numberOfChannels = channels.get();
			
			System.out.println("Channels: " + numberOfChannels);
		}
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
