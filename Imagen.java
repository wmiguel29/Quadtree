import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {

	private int height;
	private int width;
	
	private Color pixels[][]; 

	public Imagen(File file) {
		pixels = convertImageToArray(file);
	}
	
	
	private Color[][] convertImageToArray(File file) {
		
		try {
			
			//cria um buffer com o conteÃºdo da imagem
			BufferedImage buffer = ImageIO.read(file);
			
			//dimensÃµes da imagem
			height = buffer.getHeight();
			width = buffer.getWidth();
			
			pixels = new Color[height][width];
			
			for(int i = 0; i < height; ++i) {
				for(int j = 0; j < width; ++j) {
					pixels[i][j] = new Color(buffer.getRGB(j, i), true);
				}
			}
			
		} catch (IOException e) { e.printStackTrace(); }
		
		return pixels;
		
	}
}
