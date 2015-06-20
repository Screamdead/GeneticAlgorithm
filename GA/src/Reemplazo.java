import java.util.*;

public class Reemplazo {
	
	public Reemplazo(){
		
	}
	
	/*
	 * Método Ruleta
	 * @param fenotipo1, fenotipo2
	 * @return Individuo
	 */
	public Individuo ruleta(Individuo individuo1, Individuo individuo2){
		
		//System.out.println("Ruleta " + "Individuo1 entró: " + individuo1.getGenotipo_string());
		//System.out.println("Ruleta " + "Individuo2 entró: " + individuo2.getGenotipo_string());
		double puntoquiebre = 1.0 - (individuo1.fitness / (double)(individuo1.fitness + individuo2.fitness));
		Random rnd = new Random();
		double aleatorio = rnd.nextDouble();
		//System.out.println("Fitness del individuo 1: " + individuo1.fitness + "\t Suma de fitness: " + (individuo1.fitness + individuo2.fitness));
		//System.out.println("Punto: " + puntoquiebre + "\t Aleatorio: " + aleatorio);
		
		if (aleatorio < puntoquiebre){
			//System.out.println("Ruleta " + "Individuo1: " + individuo1.getGenotipo_string());
			return individuo1;
		}
		else{
			//System.out.println("Ruleta " + "Individuo2: " + individuo2.getGenotipo_string());
			return individuo2;
		}
		
	}
	/*
	 * Método Steady State
	 * @param padre1, padre2, hijo1, hijo2
	 * @return siguienteGeneración
	 */ 
	public Individuo[] steadyState(Individuo padre1, Individuo padre2, Individuo hijo1, Individuo hijo2){
		
		Individuo [] siguienteGeneracion = new Individuo[2];
		
		if (padre1.fitness < padre2.fitness){
			if(hijo1.fitness < hijo2.fitness){
				// Mejor padre con mejor hijo
				siguienteGeneracion[0] = ruleta(padre1, hijo1);
				// Peor padre con peor hijo
				siguienteGeneracion[1] = ruleta(padre2, hijo2);
			}
			else{
				// Mejor padre con mejor hijo
				siguienteGeneracion[0] = ruleta(padre1, hijo2);
				// Peor padre con peor hijo
				siguienteGeneracion[1] = ruleta(padre2, hijo1);				
			}
		}
		else{
			if(hijo2.fitness < hijo1.fitness){
				// Mejor padre con mejor hijo
				siguienteGeneracion[0] = ruleta(padre2, hijo2);
				// Peor padre con peor hijo
				siguienteGeneracion[1] = ruleta(padre1, hijo1);
			}
			else{
				// Mejor padre con mejor hijo
				siguienteGeneracion[0] = ruleta(padre2, hijo1);
				// Peor padre con peor hijo
				siguienteGeneracion[1] = ruleta(padre1, hijo2);				
			}
		} 
		
		return siguienteGeneracion;
	}
}
