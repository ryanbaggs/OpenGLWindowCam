package io;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBImage;

/**
 * Reads in image specified by the filename.
 * <p>
 * @author Ryan Baggs
 * @date Created on 16-Nov-2020
 */
public class ReadImage {
	
	private String fileName;
	
	public ReadImage(String fileName) {
		this.fileName = fileName;
	}
	
	// TODO: add width, height.
	// TODO: modify to Texture class.
	
	public ByteBuffer readImage(){
		// Create the file name as a ByteBuffer.
		ByteBuffer file = ByteBuffer.wrap(fileName.getBytes());
		
		IntBuffer x = IntBuffer.allocate(1 * Integer.BYTES);
		IntBuffer y = IntBuffer.allocate(1 * Integer.BYTES);
		IntBuffer channels = IntBuffer.allocate(1 * Integer.BYTES);
		
		return STBImage.stbi_load(file, x, y, channels, 0);
	}

}
