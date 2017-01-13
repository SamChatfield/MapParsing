package com.samchatfield.mapParsing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PngParser {

	private BufferedImage image;
	
	public PngParser(File file) throws IOException {
		image = ImageIO.read(file);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public static BufferedImage getImage(File f) throws IOException {
		return ImageIO.read(f);
	}
	
}
