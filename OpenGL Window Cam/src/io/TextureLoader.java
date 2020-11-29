package io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

import graphics.Texture;

/**
 * Loads the specified texture from the project files.
 * <p>
 * @author Ryan Baggs
 * @date Created on 29-Nov-2020
 */
public class TextureLoader {
	
	/**
	 * Loads the texture from the specified file name and returns a new 
	 * Texture.
	 * 
	 * @param fileName the path and file name of the texture to load.
	 * @return a new Texture.
	 */
	public Texture loadTexture(String fileName) {
		
		try(MemoryStack stack = MemoryStack.stackPush()){
			// Create ByteBuffers to get data.
			IntBuffer x = stack.mallocInt(1);
			IntBuffer y = stack.mallocInt(1);
			IntBuffer channels = stack.mallocInt(1);
			
			// Get the texture data.
			ByteBuffer textureData = STBImage.stbi_load(fileName, x, y, channels, 4);
			
			// If the texture data is null, print out the load failure reason.
			if(textureData == null)
				System.out.println(STBImage.stbi_failure_reason());
			
			// Return the new Texture.
			return new Texture(textureData, x.get(), y.get(), channels.get());
		}
	}

}
