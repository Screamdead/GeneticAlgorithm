import java.util.*;

public class OperadoresGeneticos {
	
	public OperadoresGeneticos(){
		
	}
	
	/*
	 * Método de mutación swapMutation
	 * @param fenotipo
	 * @return fenotipo
	 */
	public int[][] swapMutation(int[] genotipo1, int[] genotipo2){
		
		int[][] hijos = new int [2][10];
		Random rnd = new Random();
		int random1 = rnd.nextInt(genotipo1.length);
		int random2 = rnd.nextInt(genotipo2.length);
		int temp1 = 0;
		
		hijos[0] = genotipo1;
		hijos[1] = genotipo2;
		
		temp1 = hijos[0][random1];
		hijos[0][random1] = hijos[0][random2];
		random2 = temp1;
		
		temp1 = hijos[1][random1];
		hijos[1][random2] = hijos[1][random1];
		random2 = temp1;
		
		return hijos;
	}
	
	/*
	 * Método de cruce Order Crossover
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] orderCrossover(int[] fenotipo1, int[] fenotipo2){
		
		// Declaración matriz de hijos
		
		int[][] Hijos = new int [2][10];
			
		// Declaración de ArrayList temporales
		
		ArrayList<Integer> padretempo1 = new ArrayList<Integer>();
		ArrayList<Integer> padretempo2 = new ArrayList<Integer>();
		ArrayList<Integer> hijotempo1 = new ArrayList<Integer>();
		ArrayList<Integer> hijotempo2 = new ArrayList<Integer>();
			
		//Pasa los valores de los fenotipos a ArrayList
			
		for(int i = 0; i < fenotipo1.length; i++){
			padretempo1.add(fenotipo1[i]);
		}
		
		for(int i = 0; i < fenotipo2.length; i++){
			padretempo2.add(fenotipo2[i]);
		}
		
		Random random = new Random();
			
		// Obtener el tamaño del fenotipo1
			
		int tamano = fenotipo1.length;
			
		// Selecciona dos números aleatorios para el punto de inicio y fin del corte

		int numero1 = random.nextInt(tamano - 1);
		int numero2 = random.nextInt(tamano);
			
		// Hacer que el más pequeño sea el inicio y el más grande el final
			
		int inicio = Math.min(numero1, numero2);
		int fin = Math.max(numero1, numero2);
			
		// Agrega el contenido del padre 1 entre los puntos de inicio y fin a los hijos
			
		hijotempo1.addAll(padretempo1.subList(inicio, fin));
		hijotempo2.addAll(padretempo2.subList(inicio, fin));
			
		// Iterar sobre cada ciudad en las rutas de los padres
			
		int ciudadActualIndex = 0;
		int ciudadActualPadre1 = 0;
		int ciudadActualPadre2 = 0;
		
		for (int j = 0; j < tamano; j++){
				
			// Obtener el índice de la ciudad actual
		
			ciudadActualIndex = (fin + j) % tamano;
				
			// Obtener la ciudad en el índice actual en cada uno de los dos recorridos de padres
				
			ciudadActualPadre1 = padretempo1.get(ciudadActualIndex);
			ciudadActualPadre2 = padretempo2.get(ciudadActualIndex);
				
			// Si el hijo 1 no contiene ya la ciudad actual agregarla
				
			if (!hijotempo1.contains(ciudadActualPadre2)){
				hijotempo1.add(ciudadActualPadre2);				
			}
			
			// Si el hijo 2 no contiene ya la ciudad actual agregarla
				
			if (!hijotempo2.contains(ciudadActualPadre1)){
				hijotempo2.add(ciudadActualPadre1);				
			}			
		}
			
		// Girar las listas por lo que la división original se encuentra en el mismo lugar que en las giras de los padres
			
		Collections.rotate(hijotempo1, inicio);
		Collections.rotate(hijotempo2, inicio);
			
		// Declaración de los hijos
			
		int[] hijo1 = new int[tamano];
		int[] hijo2 = new int[tamano];
			
		// Guardar las listas de hijos temporales en los arreglos hijo1 e hijo2
			
		for(int i = 0; i < hijotempo1.size(); i++){
			hijo1[i] = hijotempo1.get(i);
		}
			
		for(int i = 0; i < hijotempo2.size(); i++){
			hijo2[i] = hijotempo2.get(i);
		}
		
		// Guardando hijo1 e hijo2 en la matriz Hijos
		
		for (int i = 0; i < hijo1.length; i++) {
			Hijos[0][i] = hijo1[i];
			Hijos[1][i] = hijo2[i];
		}
		return Hijos;
	}
}
