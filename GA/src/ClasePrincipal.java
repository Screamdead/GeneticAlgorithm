import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClasePrincipal {

	int cantidadIteraciones = 5000;
	int numeroNodos;
	int tamanoPoblacion = 10000;
	Vector<Individuo> poblacion = new Vector<Individuo>();
	Fitness fitness = null;
	Reemplazo reemplazo = new Reemplazo();
	OperadoresGeneticos operador = new OperadoresGeneticos();
	
	public ClasePrincipal(int ciudades, String archivo) {
		
		fitness = new Fitness(archivo, ciudades);
		this.numeroNodos = ciudades;
		
		this.inicializarPoblacion();
	}
	
	public static void main(String[] args) {
		int ciudades = Integer.parseInt( args[0] );
		String archivo = args[1];
		String salida = args[2];
		
		for(int i = 0; i < 30; i++)
		{
			System.out.println("Experiment " + (i + 1));
			System.gc();
			ClasePrincipal principal = new ClasePrincipal(ciudades, archivo);
			principal.generacionIndividuos();
			principal.mejorIndividuo(salida);
			principal = null;
		}
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
				
				if(random < 0.5){
					hijos_genotipo = operador.orderCrossover(padre1.getFenotipo(), padre2.getFenotipo());
				}
				else{
					hijos_genotipo = operador.inversionMutation(padre1.getGenotipo(), padre2.getGenotipo());
				}
				
				hijos[0] = new Individuo(hijos_genotipo[0], fitness.calcularFitness(hijos_genotipo[0]));
				hijos[1] = new Individuo(hijos_genotipo[1], fitness.calcularFitness(hijos_genotipo[1]));
				
				Individuo[] ganadores = reemplazo.steadyState(padre1, padre2, hijos[0], hijos[1]);
				
				siguienteGeneracion.add(ganadores[0]);
				siguienteGeneracion.add(ganadores[1]);				
				
			}
			
			this.poblacion = (Vector<Individuo>) siguienteGeneracion.clone();
			
		}	
	}
	
	public void mejorIndividuo(String output){
		
		int indice = 0;
		int mejorFitness = poblacion.get(0).getFitness();
		
		for (int i = 1; i < tamanoPoblacion; i++) {
			if (poblacion.get(i).getFitness() < mejorFitness){
				mejorFitness = poblacion.get(i).getFitness();
				indice = i;
			}
		}
		
		//File output creation
    	File o = new File(output);
    	
    	//Create output file
    	FileWriter fw;
        if (!o.exists()) 
        {
            try 
            {
                o.createNewFile();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(ClasePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("\n\nFile " + o);
                System.exit(0);
            }
        }
        BufferedWriter bw = null;
        
        try 
    	{
    		fw = new FileWriter(o.getAbsoluteFile(), true);
    		bw = new BufferedWriter(fw);
    		
    		bw.write( "\n\nFenotipo del mejor individuo: \t" + poblacion.get(indice).getGenotipo_string() );
    		bw.write( "\nFitness del mejor individuo: \t" + poblacion.get(indice).getFitness() );
        	
    		
			bw.flush();
		} 
    	catch (IOException e) 
    	{
			e.printStackTrace();
		}
	}
}