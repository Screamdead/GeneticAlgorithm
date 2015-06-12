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
		hijos[0][random2] = temp1;
		
		temp1 = hijos[1][random1];
		hijos[1][random1] = hijos[1][random2];
		hijos[1][random2] = temp1;
		
		for (int i = 0; i < 2; i++) {
			for (int k = 0; k < 10; k++) {
				System.out.print(hijos[i][k] + "  ");
			}
			System.out.println();
		}
		
		return hijos;
	}
	
	/*
	 * Método de cruce Order Crossover
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] orderCrossover(int[]padre1, int[]padre2){
				
		// Declaración matriz de hijos
		
		int[][] hijos = new int [2][padre1.length];
		
		// Declaración vector de cada hijo
		
		int[] hijo1 = new int [padre1.length];
		int[] hijo2 = new int [padre2.length];
		
		Random random = new Random();
		
		// Obtener el tamaño del fenotipo1
		
		int tamano = padre1.length;
		
		// Selecciona dos números aleatorios para el punto de inicio y fin del corte
		
		int numero1 = random.nextInt(tamano);
		int numero2 = random.nextInt(tamano);
		
		// Hacer que el más pequeño sea el inicio y el más grande el final
		
		int inicio = Math.min(numero1, numero2);
		int fin = Math.max(numero1, numero2);
		
		// Inicializa el vector hijo1 en -1 en cada posición
		
		for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre1 en el hijo1
		
		for (int i = inicio; i <= fin; i++) {
			hijo1[i] = padre1[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre2copia = padre2.clone();
		
		// Borra el subtour escogido en el padre1 dentro del padre2copia
		
		for (int i = inicio; i <= fin; i++) {
			for (int j = 0; j < padre2copia.length; j++) {
				if (padre2copia[j] == padre1[i]){
					padre2copia[j] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo1 de izquierda a derecha
		
		int j = 0;
		for (int i = 0; i < hijo1.length; i++) {
			if(hijo1[i] == -1){
					if(padre2copia[j] != -1){
						hijo1[i] = padre2copia[j];
						j++;
					}
					else{
						j++;
						i--;
					}
			}
			else{
				hijo1[i] = hijo1[i];
			}
		}

		/*
		 * Pasos para crear el hijo2
		 */
		// Inicializa el vector hijo2 en -1 en cada posición
		
		for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
		
		// Selecciona y copia el subtour dentro del Padre2 en el hijo2
		
		for (int i = inicio; i <= fin; i++) {
			hijo2[i] = padre2[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre1copia = padre1.clone();
		
		// Borra el subtour escogido en el padre2 dentro del padre1copia
		
		for (int i = inicio; i <= fin; i++) {
			for (int y = 0; y < padre1copia.length; y++) {
				if (padre1copia[y] == padre2[i]){
					padre1copia[y] = -1;
				}
			}
		}
		
		// Ubica las ciudades en las posiciones libres del hijo2 de izquierda a derecha
		
		j = 0;
		for (int i = 0; i < hijo2.length; i++) {
			if(hijo2[i] == -1){
				if(padre1copia[j] != -1){
					hijo2[i] = padre1copia[j];
					j++;
				}
				else{
					j++;
					i--;
				}
			}
			else{
				hijo2[i] = hijo2[i];
			}
		}
				
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		
		return hijos;
	}
}
