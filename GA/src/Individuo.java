
public class Individuo {
	 
	int[] fenotipo;
	int[] genotipo;
	int fitness;
	
	/*
	 * Constructor Individuo
	 */
	public Individuo(int[] fenotipo, int fitness){
		this.fenotipo = fenotipo;
		this.fitness = fitness;
		this.genotipo = fenotipo;
	}

	/**
	 * @return the fenotipo
	 */
	public int[] getFenotipo() {
		return fenotipo;
	}

	/**
	 * @param fenotipo the fenotipo to set
	 */
	public void setFenotipo(int[] fenotipo) {
		this.fenotipo = fenotipo;
	}

	/**
	 * @return the genotipo
	 */
	public int[] getGenotipo() {
		return genotipo;
	} 

	/**
	 * @param genotipo the genotipo to set
	 */
	public void setGenotipo(int[] genotipo) {
		this.genotipo = genotipo;
	}

	/**
	 * @return the fitness
	 */
	public int getFitness() {
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}	
	
	/**
	 * Imprimir individuo
	 */
	public String getGenotipo_string() {
		String temporal = "";
		for (int i = 0; i < genotipo.length; i++) {
			temporal += genotipo[i] + " ";
		}
		return temporal;
	}
	
}
