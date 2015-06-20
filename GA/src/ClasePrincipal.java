import java.util.*;

public class ClasePrincipal {

	int cantidadIteraciones = 100;
	int numeroNodos = 11;
	int tamanoPoblacion = 100;
	Vector<Individuo> poblacion = new Vector<Individuo>();
	Fitness fitness = new Fitness();
	Reemplazo reemplazo = new Reemplazo();
	OperadoresGeneticos operador = new OperadoresGeneticos();
	
	public ClasePrincipal(){
		this.inicializarPoblacion();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClasePrincipal principal = new ClasePrincipal();
		principal.generacionIndividuos();
		principal.mejorIndividuo();
	}
	
	public int [] generarPermutacionAleatoria(int [] genotipo){
				
		Random rnd = new Random();
		int temp = 0;
		
		for (int i = 0; i < numeroNodos; i++) {
			genotipo[i] = i;
		}
		
		for (int i = 0; i < (genotipo.length - 1); i++) {
			int random1 = rnd.nextInt(numeroNodos);
			int random2 = rnd.nextInt(numeroNodos);
			temp = genotipo[random1]; 
			genotipo[random1] = genotipo[random2];
			genotipo[random2] = temp;		
		}
		
		for (int i = 0; i < genotipo.length; i++) {
			System.out.print(genotipo[i] + "  ");
		}
		
		System.out.println();
		return genotipo;
	}

	public void inicializarPoblacion(){
		for (int i = 0; i < tamanoPoblacion; i++) {
			
			int[]genotipo = new int[numeroNodos];
			genotipo = generarPermutacionAleatoria(genotipo);
			poblacion.addElement(new Individuo(genotipo, fitness.calcularFitness(genotipo)));
			
		}
	}
	
	public Individuo torneo(){
		
		Reemplazo ruleta = new Reemplazo();
		Random random = new Random();
		
		int aleatorio1 = random.nextInt(tamanoPoblacion);
		int aleatorio2 = random.nextInt(tamanoPoblacion);
		int aleatorio3 = random.nextInt(tamanoPoblacion);
		int aleatorio4 = random.nextInt(tamanoPoblacion);
		
		System.out.println("\nAleatorio 1: " + aleatorio1 + " Tamaño de la poblacion: " + poblacion.size() );
		Individuo individuo1 = poblacion.get(aleatorio1);
		Individuo individuo2 = poblacion.get(aleatorio2);
		Individuo individuo3 = poblacion.get(aleatorio3);
		Individuo individuo4 = poblacion.get(aleatorio4);
		
		Individuo individuoGanador = ruleta.ruleta(ruleta.ruleta(individuo1, individuo2),ruleta.ruleta(individuo3, individuo4));
	
		return individuoGanador;
	}
	
	@SuppressWarnings("unchecked")
	public void generacionIndividuos(){
		
		Random rand = new Random();
		double random = 0;
		
		Vector<Individuo> siguienteGeneracion = new Vector<Individuo>();
		
		for (int i = 0; i < cantidadIteraciones; i++) {
			
			siguienteGeneracion.clear();
			
			for (int j = 0; j < (tamanoPoblacion / 2); j++) {
				
				Individuo padre1 = torneo();
				Individuo padre2 = torneo();
				int[][] hijos_genotipo = null;
				Individuo [] hijos = new Individuo[2];
				random = rand.nextDouble();
				
				System.out.println("Padre 1: " + padre1.getGenotipo_string() + "\tPadre 2: "  + padre2.getGenotipo_string() );
				
				if(random < 0.5){
					hijos_genotipo = operador.orderCrossover(padre1.getFenotipo(), padre2.getFenotipo());
					System.out.println("Cross-Over");
				}
				else{
					hijos_genotipo = operador.swapMutation(padre1.getGenotipo(), padre2.getGenotipo());
					System.out.println("Mutation");
				}
				
				hijos[0] = new Individuo(hijos_genotipo[0], fitness.calcularFitness(hijos_genotipo[0]));
				hijos[1] = new Individuo(hijos_genotipo[1], fitness.calcularFitness(hijos_genotipo[1]));
				
				System.out.println("Hijo 1: " + hijos[0].getGenotipo_string() + "\tHijo 2: "  + hijos[1].getGenotipo_string());
				
				Individuo[] ganadores = reemplazo.steadyState(padre1, padre2, hijos[0], hijos[1]);
				
				System.out.println("Ganador 1: " + ganadores[0].getGenotipo_string() + "\tGanador 2: " + ganadores[1].getGenotipo_string());

				//System.out.println("Individuo 1: " + hijos[0].getGenotipo_string() + "\t Individuo 2: "  + hijos[1].getGenotipo_string());
				
				siguienteGeneracion.add(ganadores[0]);
				siguienteGeneracion.add(ganadores[1]);				
				
			}
			
			this.poblacion = (Vector<Individuo>) siguienteGeneracion.clone();
			
		}	
	}
	
	public void mejorIndividuo(){
		
		int indice = 0;
		int mejorFitness = poblacion.get(0).getFitness();
		
		for (int i = 1; i < tamanoPoblacion; i++) {
			if (poblacion.get(i).getFitness() < mejorFitness){
				mejorFitness = poblacion.get(i).getFitness();
				indice = i;
			}
		}
		
		System.out.println();
		System.out.println("Fenotipo del mejor individuo: \t" + poblacion.get(indice).getGenotipo_string());
		System.out.println("Fitness del mejor individuo: \t" + poblacion.get(indice).getFitness());
		
	}
}
