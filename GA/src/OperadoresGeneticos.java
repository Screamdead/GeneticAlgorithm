import java.util.*;

public class OperadoresGeneticos {
	
	public OperadoresGeneticos(){
		
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Operadores de Mutación
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------

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
		int temp = 0;
		
		while(random1 == random2){
			random2 = rnd.nextInt(genotipo2.length);
		}
		
		hijos[0] = genotipo1.clone();
		hijos[1] = genotipo2.clone();
		
		temp = hijos[0][random1];
		hijos[0][random1] = hijos[0][random2];
		hijos[0][random2] = temp;
		
		temp = hijos[1][random1];
		hijos[1][random1] = hijos[1][random2];
		hijos[1][random2] = temp;
		
		return hijos;
	}
	
	/*
	 * Método de mutación Inversion Mutation
	 * @param fenotipo
	 * @return fenotipo
	 */
	public int[][] inversionMutation(int[] genotipo1, int[] genotipo2){
		
		int[][] hijos = new int [2][10];
		int[] hijo1 = genotipo1.clone();
		int[] hijo2 = genotipo2.clone();
		// Seleccionar 2 puntos al azar
		Random rnd = new Random();
		int random1 = rnd.nextInt(genotipo1.length);
		int random2 = rnd.nextInt(genotipo2.length);
		// Asegurar que son distintos
		while(random1 == random2){
			random1 = rnd.nextInt(genotipo1.length);
			random2 = rnd.nextInt(genotipo2.length);
		}
		// Hacer que el más pequeño sea el p1 y el más grande p2
		int p1 = Math.min(random1, random2);
		int p2 = Math.max(random1, random2);
		// Invertir entre el mínimo y el máximo para el hijo1
		for (int i = 0; i < ((p2-p1+1)/2); i++) {
			int aux = hijo1[p1 + i];
			hijo1[p1 + i] = hijo1[p2 - i];
			hijo1[p2 - i] = aux;
		}
		// Invertir entre el mínimo y el máximo para el hijo2
		for (int i = 0; i < ((p2-p1+1)/2); i++) {
			int aux = hijo2[p1 + i];
			hijo2[p1 + i] = hijo2[p2 - i];
			hijo2[p2 - i] = aux;
		}
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		return hijos;
		
	}
	
	/*
	 * Método de mutación Insercion Mutation
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] insercionMutation(int[]genotipo1, int[]genotipo2){
		int[][] hijos = new int [2][10];
		int[] hijo1 = genotipo1.clone();
		int[] hijo2 = genotipo2.clone();
		// Seleccionar 2 puntos al azar
		Random rnd = new Random();
		int random1 = rnd.nextInt(genotipo1.length);
		int random2 = rnd.nextInt(genotipo2.length);
		// Asegurar que son distintos
		while(random1 == random2){
			random1 = rnd.nextInt(genotipo1.length);
			random2 = rnd.nextInt(genotipo2.length);
		}
		// Hacer que el más pequeño sea el p1 y el más grande p2
		int p1 = Math.min(random1, random2);
		int p2 = Math.max(random1, random2);
		// Desplaza los elementos desde p2 hasta p1 en una posición para el hijo1			
		int x = p1, j = p2;
		while (j != x) {
			int aux = hijo1[j];
			hijo1[j] = hijo1[j - 1];
			hijo1[j - 1] = aux;
			j--;
		}
		// Desplaza los elementos desde p2 hasta p1 en una posición para el hijo2
		while (p2 != p1) {
			int temp = hijo2[p2];
			hijo2[p2] = hijo2[p2 - 1];
			hijo2[p2 - 1] = temp;
			p2--;
		}
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		return hijos;
	}
	
	/*// TODO Auto-generated method stu
	 * Método de mutación Shift Mutation
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] shiftMutation(int[]genotipo1, int[]genotipo2){

		int[][] hijos = new int [2][10];
		int[] hijo1 = genotipo1.clone();
		int[] hijo2 = genotipo2.clone();
		// Seleccionar 2 puntos al azar
		Random rnd = new Random();
		int random1 = rnd.nextInt(genotipo1.length);
		int random2 = rnd.nextInt(genotipo2.length);
		// Asegurar que son distintos
		while(random1 == random2){
			random1 = rnd.nextInt(genotipo1.length);
			random2 = rnd.nextInt(genotipo2.length);
		}
		// Hacer que el más pequeño sea el p1 y el más grande p2
		int p1 = Math.min(random1, random2);
		int p2 = Math.max(random1, random2);
		// Desplazar posiciones para el hijo1
		for (int i = 0; i < p1; i++) {
			for (int j = 0; j < p2; j++) {
				int aux = hijo1[j];
				hijo1[j] = hijo1[j + 1];
				hijo1[j + 1] = aux;
			}
		}
		// Desplazar posiciones para el hijo2
		for (int i = 0; i < p1; i++) {
			for (int j = 0; j < p2; j++) {
				int aux = hijo2[j];
				hijo2[j] = hijo2[j + 1];
				hijo2[j + 1] = aux;
			}
		}
		hijos[0] = hijo1;
		hijos[1] = hijo2;
		return hijos;
	}
	
	/* Auto-generated method stu
	 * Método de mutación Shift Mutation
	 * @param padre1 y padre2
	 * @return hijos
	 */
	 
	public int[][] heuristicMutation(Fitness fitness, int[]genotipo1, int[]genotipo2){
		Random rnd = new Random();
		int[][] hijos = new int [2][10];
		int[] hijo1 = genotipo1.clone();
		int[] hijotemp1 = hijo1.clone();
		int[] hijo2 = genotipo2.clone();
		int[] hijotemp2 = hijo2.clone();
		int[] padre1 = genotipo1.clone();
		int[] padre2 = genotipo2.clone();
		int[] aux = new int[3];
		int[][] permutacion = new int[6][3]; 
		int maxfitnessp1 = fitness.calcularFitness(padre1);
		int maxfitnessp2 = fitness.calcularFitness(padre2);
		int inicio = rnd.nextInt(padre1.length);
		int medio = rnd.nextInt(padre1.length);
		while (medio == inicio) {
			medio = rnd.nextInt(padre1.length); 
		}
		int fin = rnd.nextInt(padre1.length);
		while ((fin == inicio) || (fin == medio)) {
			fin = rnd.nextInt(padre1.length);
		}
		// Proceso de padre1 para generar el hijo1
		aux[0] = padre1[inicio];
		aux[1] = padre1[medio];
		aux[2] = padre1[fin];
		permutacion[0] = aux;
		for (int i = 1; i < permutacion.length; i++) {
			switch (i) {
			case 1:
				permutacion[i][0] = permutacion[i - 1][0];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][1];
				continue;
			case 2:
				permutacion[i][0] = permutacion[i - 1][1];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][0];
				continue;
			case 3:
				permutacion[i][0] = permutacion[i - 1][0];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][1];
				continue;
			case 4:
				permutacion[i][0] = permutacion[i - 1][2];
				permutacion[i][1] = permutacion[i - 1][1];
				permutacion[i][2] = permutacion[i - 1][0];
				continue;
			case 5:
				permutacion[i][0] = permutacion[i - 1][0];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][1];
				continue;
			}
		}
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < padre1.length; j++) {
				if (j == inicio) {
					hijo1[j] = permutacion[i + 1][0];
				}
				if (j == medio) {
					hijo1[j] = permutacion[i + 1][1];
				}
				if (j == fin) {
					hijo1[j] = permutacion[i + 1][2];
				}
				if ((j != inicio) && (j != medio) && (j != fin)) {
					hijo1[j] = padre1[j];
				}
			}
			int temfitness = fitness.calcularFitness(hijo1);
			if (temfitness < maxfitnessp1) {
				maxfitnessp1 = temfitness;
				hijotemp1 = hijo1.clone();	
			}
		}
		
		// Proceso de padre2 para generar el hijo2
		aux[0] = padre2[inicio];
		aux[1] = padre2[medio];
		aux[2] = padre2[fin];
		permutacion[0] = aux;
		for (int i = 1; i < permutacion.length; i++) {
			switch (i) {
			case 1:
				permutacion[i][0] = permutacion[i - 1][0];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][1];
				continue;
			case 2:
				permutacion[i][0] = permutacion[i - 1][1];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][0];
				continue;
			case 3:
				permutacion[i][0] = permutacion[i - 1][0];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][1];
				continue;
			case 4:
				permutacion[i][0] = permutacion[i - 1][2];
				permutacion[i][1] = permutacion[i - 1][1];
				permutacion[i][2] = permutacion[i - 1][0];
				continue;
			case 5:
				permutacion[i][0] = permutacion[i - 1][0];
				permutacion[i][1] = permutacion[i - 1][2];
				permutacion[i][2] = permutacion[i - 1][1];
				continue;
			}
		}	
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < padre2.length; j++) {
				if (j == inicio) {
					hijo2[j] = permutacion[i + 1][0];
				}
				if (j == medio) {
					hijo2[j] = permutacion[i + 1][1];
				}
				if (j == fin) {
					hijo2[j] = permutacion[i + 1][2];
				}
				if ((j != inicio) && (j != medio) && (j != fin)) {
					hijo2[j] = padre2[j];
				}
			}
			int temfitness = fitness.calcularFitness(hijo2);
			if (temfitness < maxfitnessp2) {
				maxfitnessp2 = temfitness;
				hijotemp2 = hijo2.clone();
			}
		}
		
		hijos[0] = hijotemp1;
		hijos[1] = hijotemp2;
		
		return hijos;
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------
// Operadores de Cruce
//-------------------------------------------------------------------------------------------------------------------------------------
	
	/*
	 * Método de cruce Order Crossover(OX)
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
	
	/*
	 * Método de cruce Order preserving one-point crossover
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] OrderOnePointCrossover(int[]padre1, int[]padre2){
		
		// Declaración matriz de hijos
		
		int[][] hijos = new int [2][padre1.length];
				
		// Declaración vector de cada hijo
				
		int[] hijo1 = new int [padre1.length];
		int[] hijo2 = new int [padre2.length];
				
		Random random = new Random();
				
		// Obtener el tamaño del fenotipo1
				
		int tamano = padre1.length;
				
		// Selecciona dos números aleatorios para el punto de inicio y fin del corte
				
		int numero = random.nextInt(tamano);
				
		// Inicializa el vector hijo1 en -1 en cada posición
				
		for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
				
		// Selecciona y copia el subtour dentro del Padre1 en el hijo1
				
		for (int i = 0; i <= numero; i++) {
			hijo1[i] = padre1[i];
		}
				
		// Copia de un vector temporal para el padre2
				
		int[] padre2copia = padre2.clone();
				
		// Borra el subtour escogido en el padre1 dentro del padre2copia
				
		for (int i = 0; i <= numero; i++) {
			for (int j = 0; j < padre2copia.length; j++) {
				if (padre2copia[j] == padre1[i]) {
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
		
		for (int i = 0; i <= numero; i++) {
			hijo2[i] = padre2[i];
		}
		
		// Copia de un vector temporal para el padre2
		
		int[] padre1copia = padre1.clone();
		
		// Borra el subtour escogido en el padre2 dentro del padre1copia
		
		for (int i = 0; i <= numero; i++) {
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
	
	/*
	 * Método de cruce Partially Mapped Crossover (PMX)
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] PMX(int[]padre1, int[]padre2){
		// referencia: https://www.ceng.metu.edu.tr/~ucoluk/research/publications/tspnew.pdf
		int[][] hijos = new int [2][10];
		int[] hijo1 = new int[padre1.length];
		int[] hijo2 = new int[padre2.length];
		int[] padre1clone = padre1.clone();
		int[] padre2clone = padre2.clone();
		
		// Seleccionar 2 puntos al azar
		Random rnd = new Random();
		int random1 = rnd.nextInt(padre1.length);
		int random2 = rnd.nextInt(padre2.length);
		
		// Asegurar que son distintos
		while(random1 == random2){
			random1 = rnd.nextInt(padre1.length);
			random2 = rnd.nextInt(padre2.length);
		}
		
		// Hacer que el más pequeño sea el p1 y el más grande p2
		int p1 = 0;//Math.min(random1, random2);
		int p2 = 2;//Math.max(random1, random2);
		
		// Inicializa el vector hijo1 en -1 en cada posición
	    for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
	    
	    // Inicializa el vector hijo2 en -1 en cada posición
	    for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
	    
		// Copiar el segmento seleccionado del padre1 en el hijo2
		for (int i = p1; i <= p2; i++) {
			hijo2[i] = padre1clone[i];
		}
		
		// Copiar el segmento seleccionado del padre2 en el hijo1
		for (int i = p1; i <= p2; i++) {
			hijo1[i] = padre2clone[i];
		}
		
		// Proceso para generar el hijo1
		for (int i = p1; i <= p2; i++) {
			int elemento = padre2[i];
			for (int j = 0; j < padre1.length; j++) {
				if (padre1[j] == elemento) {
					int aux = padre1[i];
					padre1[i] = padre1[j];
					padre1[j] = aux;
					break;
				}
			}
		}
		
		// Completa el hijo1 
		for (int i = 0; i < p1; i++) {
			hijo1[i] = padre1[i];
		}
		for (int i = p2; i < hijo1.length; i++) {
			hijo1[i] = padre1[i];
		}
		
		// Proceso para generar el hijo2
		for (int i = p1; i <= p2; i++) {
			int elemento = padre1clone[i];
			for (int j = 0; j < padre2.length; j++) {
				if (padre2[j] == elemento) {
					int aux = padre2[i];
					padre2[i] = padre2[j];
					padre2[j] = aux;
					break;
				}
			}
		}
		
		// Completa el hijo1
		for (int i = 0; i < p1; i++) {
			hijo2[i] = padre2[i];
		}
		for (int i = p2; i < hijo1.length; i++) {
			hijo2[i] = padre2[i];
		}
		
		hijos[0] = hijo1;
		hijos[1] = hijo2;

		return hijos;
	}


	/*
	 * Método de cruce Cycle Crossover (CX)
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public int[][] CX(int[]padre1, int[]padre2){
		int[][] hijos = new int [2][10];
		int[] hijo1 = new int[padre1.length];
		int[] hijo2 = new int[padre2.length];
		
		// Creados para generar el hijo1 sin alterar los padres originales
		int[] padre1clone = padre1.clone();
		int[] padre2clone = padre2.clone();
		
		// Creados para generar el hijo2 sin alterar los padres originales
		int[] padre1copia = padre1.clone();
		int[] padre2copia = padre2.clone();
		
 		// Inicializa el vector hijo1 en -1 en cada posición
	    for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
	    
	    // Inicializa el vector hijo2 en -1 en cada posición
	    for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
	    
	    // Asigna a la primera posición del hijo1 el valor del padre1clone en la primera posición
		hijo1[0] = padre1clone[0];
		
		// Elemento es la variable que contiene el valor del padre1clone en la primera posición el cual se buscará en el padre2clone para empezar el ciclo y crear el hijo1
		int elemento = padre1clone[0];
		padre2clone[0] = -1;
		for (int i = 0; i < padre1clone.length; i++) {
			if ((padre2clone[i] == elemento) && (elemento != padre2clone[0])) {
				elemento = padre1clone[i];
				hijo1[i] = padre1clone[i];
				padre1clone[i] = -1;
				padre2clone[i] = -1;
				i = 0;
			}
		}
		
		// Se completa el hijo1 con los genes del padre2clone que no estan en el hijo1
		for (int i = 0; i < padre2clone.length; i++) {
			if (padre2clone[i] != -1) {
				hijo1[i] = padre2clone[i];
			}
		}
		
		// Asigna a la primera posición del hijo2 el valor del padre2copia en la primera posición
		hijo2[0] = padre2copia[0];
		
		// Elemento es la variable que contiene el valor del padre2copia en la primera posición el cual se buscará en el padre1copia para empezar el ciclo y crear el hijo2
		elemento = padre2copia[0];
		padre1copia[0] = -1;
		for (int i = 0; i < padre2copia.length; i++) {
			if ((padre1copia[i] == elemento) && (elemento != padre1copia[0])) {
				elemento = padre2copia[i];
				hijo2[i] = padre2copia[i];
				padre2copia[i] = -1;
				padre1copia[i] = -1;
				i = 0;
			}
		}
		
		// Se completa el hijo2 con los genes del padre1copia que no estan en el hijo2
		for (int i = 0; i < padre1copia.length; i++) {
			if (padre1copia[i] != -1) {
				hijo2[i] = padre1copia[i];
			}
		}
		
		hijos[0] = hijo1;
		hijos[1] = hijo2;

		return hijos;
	}

	/*
	 * Método de cruce Order Based Crossover (OBX)
	 * @param padre1 y padre2
	 * @return hijos
	 */
	public static int[][] OrderBasedCrossover(int[]padre1, int[]padre2){
		Random rnd = new Random(); 
		int[][] hijos = new int [2][10];
		int[] hijo1 = new int[padre1.length];
		int[] hijo2 = new int[padre2.length];
		
		// Creados para generar el hijo1 sin alterar los padres originales
		int[] padre1clone = padre1.clone();
		int[] padre2clone = padre2.clone();
		
		// Se genera una máscara binaria aleatoriamente
		int[] mask = new int[padre1.length];

		for (int i = 0; i < mask.length; i++) {
			boolean bool =  rnd.nextBoolean();
			if (bool) {
				mask[i] = 1;
			}
			else {
				mask[i] = 0;
			}
		}
		
		// Inicializa el vector hijo1 en -1 en cada posición
	    for (int i = 0; i < hijo1.length; i++) {
			hijo1[i] = -1;
		}
	    
	    // Inicializa el vector hijo2 en -1 en cada posición
	    for (int i = 0; i < hijo2.length; i++) {
			hijo2[i] = -1;
		}
	    
	    // Aquellas posiciones donde aparece el número uno, indica que el hijo uno heredará dicho gen del padre uno y el segundo hijo lo heredará del segundo padre
	    for (int i = 0; i < mask.length; i++) {
			if (mask[i] == 1) {
				hijo1[i] = padre1clone[i];
				hijo2[i] = padre2clone[i];
			}
		}
	    
	    // Se pone en -1 en el padre2 lo que está en el hijo1
	    for (int i = 0; i < hijo1.length; i++) {
			for (int j = 0; j < padre2clone.length; j++) {
				if (padre2clone[j] == hijo1[i]) {
					padre2clone[j] = -1;
					break;
				}
			}
		}
	    
	    // Se pone en -1 en el padre1 lo que está en el hijo2
	    for (int i = 0; i < hijo2.length; i++) {
			for (int j = 0; j < padre1clone.length; j++) {
				if (padre1clone[j] == hijo2[i]) {
					padre1clone[j] = -1;
					break;
				}
			}
		}
	    
	    // Se completa el hijo1 con los genes que no tiene del padre2
	    int temp = 0;
	    for (int i = 0; i < padre2clone.length; i++) {
			if (padre2clone[i] != -1) {
				int aux = padre2clone[i];
				for (int j = temp; j < hijo1.length; j++) {
					if (hijo1[j] == -1) {
						hijo1[j] = aux;
						temp = j;
						break;
					}
				}
			}
		}
	    
	    // Se completa el hijo2 con los genes que no tiene del padre1
	    temp = 0;
	    for (int i = 0; i < padre1clone.length; i++) {
			if (padre1clone[i] != -1) {
				int aux = padre1clone[i];
				for (int j = temp; j < hijo2.length; j++) {
					if (hijo2[j] == -1) {
						hijo2[j] = aux;
						temp = j;
						break;
					}
				}
			}
		}
	    
	    hijos[0] = hijo1;
	    hijos[1] = hijo2;

		return hijos;
	}
	
	
}
