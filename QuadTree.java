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
