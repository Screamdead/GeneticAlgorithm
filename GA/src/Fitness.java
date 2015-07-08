import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Fitness {
	
	int[][] cost_matrix = null;
	
	
	public Fitness(String archivo, int ciudades){
		this.cost_matrix = new int[ciudades][ciudades];
		
		BufferedReader brBackground;
		String[] lineBackground;
		String line = "";
		
		try 
		{
			brBackground = new BufferedReader(new FileReader( archivo ));
			line = brBackground.readLine();
			
			int fila = 0;
			
			while(line != null) //TODO
			{
				lineBackground = line.split(" ");
				
				for(int i = 0; i < ciudades; i++)
				{
					cost_matrix[fila][i] = Integer.parseInt( lineBackground[i] );
				}
					
				fila++;
				line = brBackground.readLine();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}

		
	}
	
	public int calcularFitness(int[] fenotipo){
		int fitness = 0;
		for(int i = 0; i < fenotipo.length; i++){
			fitness += cost_matrix[fenotipo[i]][fenotipo[(i+1) % fenotipo.length]];
		}

		fitness += cost_matrix[fenotipo.length - 1][0];
		return fitness;
	}
		
}