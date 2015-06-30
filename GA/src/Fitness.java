
public class Fitness {
	
	static int[][] cost_matrix = {
			{0, 29, 86, 46, 68, 52, 72, 42, 51, 55, 29},
			{29, 0, 55, 46, 42, 43, 43, 23, 23, 31, 41},
			{86, 55, 0, 68, 46, 55, 23, 43, 41, 29, 79},
			{46, 46, 68, 0, 82, 15, 72, 31, 62, 42, 21},
			{68, 42, 46, 82, 0, 82, 15, 72, 31, 62, 82},
			{52, 43, 55, 82, 15, 0, 15, 72, 31, 62, 33},
			{72, 43, 23, 72, 15, 15, 0, 31, 62, 42, 77},
			{42, 23, 43, 31, 72, 72, 31, 0, 82, 15, 37},
			{51, 23, 41, 62, 31, 31, 62, 82, 0, 42, 62},
			{55, 31, 29, 42, 62, 62, 42, 15, 42, 0, 51},
			{29, 41, 79, 21, 82, 33, 77, 37, 62, 51, 0}
			};
	  
	public Fitness(){
		
	}
	
	public int calcularFitness(int[] fenotipo){
		int fitness = 0;
		for(int i = 0; i < (fenotipo.length - 1); i++){
			fitness += cost_matrix[fenotipo[i]][fenotipo[i+1]];
		}
		fitness += cost_matrix[fenotipo.length - 1][0];
		return fitness;
	}
		
}