import java.awt.Color;
import java.io.Serializable;

public class Nodo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -663615096892163056L;
	
	private int x;
	private int y;
	private int altura;
	private int ancho;
	private Color color;
	
	private Nodo[] nodos;
	
	public Nodo(int x, int y, int altura, int ancho) {
		super();
		this.x = x;
		this.y = y;
		this.altura = altura;
		this.ancho = ancho;
		nodos=new Nodo[4];
	}

	public Nodo() {
		super();
	}
	
	public Color promedioHijos() {
		float r=0.0f;
		float g=0.0f;
		float b=0.0f;
		float a=0.0f;
		
		int div=0;
		
		for (int i = 0; i < nodos.length; i++) {
			
			if(nodos[i]!=null) {
				r=r+(nodos[i].getColor().getRed()/255.0f);
				b=b+(nodos[i].getColor().getBlue()/255.0f);
				g=g+(nodos[i].getColor().getGreen()/255.0f);
				a=a+(nodos[i].getColor().getAlpha()/255.0f);
				
				div++;
			}
			
		}
		
		r=r/div;
		b=b/div;
		g=g/div;
		a=a/div;
		
		return new Color(r,g,b,a);
		
		
	}
	public boolean isHoja() {
		return (nodos[0]==null && nodos[1]==null && nodos[2]==null && nodos[3]==null)?true:false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Nodo[] getNodos() {
		return nodos;
	}

	public void setNodos(Nodo[] nodos) {
		this.nodos = nodos;
	}
	
	
	
	
	
	
	
	
	

}
