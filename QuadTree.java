import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QuadTree implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5708602956128894119L;
	/**
	 * 
	 */


	private Nodo raiz;
	private int altura;
	private int ancho;
	private double precision;


	public QuadTree(String direccion,double presicion) throws IOException {
		Imagen imagen=new Imagen(direccion);
		Color[][] colores= imagen.getColores();

		altura=imagen.getAltura();
		ancho=imagen.getAncho();
		this.precision=precision;

		raiz=comprimir(colores,0,0,altura,ancho);
	}

	private Nodo comprimir(Color colores[][],int x,int y,int altura,int ancho) {
		Nodo nodo= new Nodo(x,y,altura,ancho);
		Color c;
		if(altura==1 && ancho==1) {
			nodo.setColor(colores[y][x]);
		}
		else if(altura==1||ancho==1) {
			if(altura==1) {
				if(ancho<=4) {
					for (int i = 0; i < ancho; i++) {
						nodo.getNodos()[i]=comprimir(colores,x,y+i,altura,1);
					}
				}else {
					int ancho2=ancho/2;
					nodo.getNodos()[0]=comprimir(colores,x,y,altura,ancho2);
					nodo.getNodos()[1]=comprimir(colores,x,y+ancho2,altura,ancho-ancho2);
				}
			}
			nodo.setColor(nodo.promedioHijos());
		}
		else if((c=getColorNodo(colores,x,y,altura,ancho))!=null) {
			nodo.setColor(c);
		}else {
			int altura2=altura/2;
			int ancho2=ancho/2;

			nodo.getNodos()[0]=comprimir(colores,x,y+altura2,ancho2,altura-altura2);
			nodo.getNodos()[1]=comprimir(colores,x+ancho2,y+altura2,altura-altura2,ancho-ancho2);
			nodo.getNodos()[2]=comprimir(colores,x+ancho2,y,altura2,ancho-ancho2);
			nodo.getNodos()[3]=comprimir(colores,x,y,altura2,ancho2);
		}

		return nodo;

	}
	
	//////********El metodo de get color nodo en espanol
	
// 	private Color getColorNodo(Color colores[][], int x, int y, int altura, int ancho) {

// 		Map<Color, Integer> colors = new HashMap<>();

// 		float r = 0.0f;
// 		float g = 0.0f;
// 		float b = 0.0f;
// 		float a = 0.0f;

// 		for(int i = 0; i < altura; ++i) {

// 			for(int j = 0; j < ancho; ++j) {

// 				Color c = colores[i + x][j + y];

// 				r = r + (c.getRed() / 255.0f);
// 				g = g + (c.getGreen() / 255.0f);
// 				b = b + (c.getBlue() / 255.0f);
// 				a = a + (c.getAlpha() / 255.0f);


// 				if(!colors.containsKey(c)) {
// 					colors.put(c, 1);
// 				}
// 				else {
// 					int contar = colors.get(c) + 1;
// 					colors.put(c, contar);
// 				}

// 			}	

// 		}

// 		int size = altura * ancho;

// 		r = r / size;
// 		g = g / size;
// 		b = b / size;
// 		a = a / size;


// 		int max = 0;
// 		for (Color c : colors.keySet()) {  

// 			int count = colors.get(c);
// 			max = Math.max(max, count); 

// 		}


// 		double precisionActual = (double) max / size;

// 		return (precisionActual >= precision) ? new Color(r, g, b, a) : null;

// 	}
	
	public int altura() {
		return altura(raiz);
	}
	
	public int altura(Nodo nodo) {
		if(nodo==null)
			return 0;
		int h1=altura(nodo.getNodos()[0]);
		int h2=altura(nodo.getNodos()[1]);
		int h3=altura(nodo.getNodos()[2]);
		int h4=altura(nodo.getNodos()[3]);
		
		int max=(h1>h2)?h1:(h2>h3)?h2:(h3>h4)?h3:h4;
		return max +1;
		}

	public Nodo getRaiz() {
		return raiz;
	}

	public void setRaiz(Nodo raiz) {
		this.raiz = raiz;
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

	public double getPrecision() {
		return precision;
	}

	public void setPrecision(double precision) {
		this.precision = precision;
	}


private Color getNodeColor(Color image[][], int i, int j, int h, int w) {
	
	
	//lista de colores y la cant de veces que se repite 
	Map<Color, Integer> colores = new HashMap<>();
	
	float r = 0.0f;
	float g = 0.0f;
	float b = 0.0f;
	float a = 0.0f;
	
	
	//recorre toda la imagen 
	for(int k = 0; k < h; ++k) {
		
		for(int l = 0; l < w; ++l) {
			
		//	mira los colores de toda la imagen
			
			Color c = image[k + i][l + j];
			
			r = r + (c.getRed() / 255.0f);
			g = g + (c.getGreen() / 255.0f);
			b = b + (c.getBlue() / 255.0f);
			a = a + (c.getAlpha() / 255.0f);
			
			
			if(!colores.containsKey(c)) {//si no contiene el color, lo agrega al hash map
				colores.put(c, 1);
			}
			else {
				//si el color ya esta, retorna cuantas veces esta, y a suma 1a cant de veces uno mas
				
				int count = colores.get(c) + 1;
				colores.put(c, count);
			}
			
		}	
					
	}
	
	
	//el area de la imagen
	int area = h * w;
	//saca el promedio de cada color
	r = r / area;
	g = g / area;
	b = b / area;
	a = a / area;
	
	//segun la cant de veces que se repite un color, comprueba cual es el mas comun
	int max = 0;
	for (Color c : colores.keySet()) {  
		
		int count = colores.get(c);
		max = Math.max(max, count); 
		
	}
	
	//mira la relacion de la cantidad de veces que se repite con respecto al area de la imagen
	double precisionColor = (double) max / area;
	
	
	//si la razon del color mas repedito es igual o mayor de la que nos pide la imagen, devolvemos el color promedio, de lo contrario devolvemos nulo
	return (precisionColor >= precision) ? new Color(r, g, b, a) : null;
	
}
