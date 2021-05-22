import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Imagen {
	private int altura;
	private int ancho;
	private Color[][] colores;
	
	public Imagen(String direccion) throws IOException {
		File file =new File(direccion);
		colores=convertirImagenMatriz(file);
		
	}
	
	private Color[][] convertirImagenMatriz(File file) throws IOException{
		BufferedImage image=ImageIO.read(file);
		altura=image.getHeight();
		ancho=image.getWidth();
		
		colores=new Color[altura][ancho];
		
		for (int i = 0; i < colores.length; i++) {
			for (int j = 0; j < colores[0].length; j++) {
				colores[i][j]=new Color(image.getRGB(j, i),true);
			}
		}
		
		return colores;
		
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public Color[][] getColores() {
		return colores;
	}

	public void setColores(Color[][] colores) {
		this.colores = colores;
	}
	
	
	

}
