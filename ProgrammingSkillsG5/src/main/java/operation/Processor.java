package main.java.operation;

import java.util.ArrayList;
import java.util.List;

import main.java.world.*;

public class Processor 
{
	private static Landscape old_Landscape;
	private static Landscape new_Landscape;
	
	/**
	 * Runs the simulation from start to finish
	 * @param Landscape landscape - landscape object
	 */	
	public static void run(Landscape landscape, int total_time_steps){
		
		// duplicate the Landscape, in order to complete the updates which are self-dependent
		old_Landscape = landscape;
		new_Landscape = landscape;
		
		System.out.println("Simulation starts ...");
		
		// run the simulation the amount of time steps specified
		for (int k = 0; k < total_time_steps; k++) {
			
			//progressBar(k, total_time_steps);
			
			// update the populations or densities of both hare and puma in each grid square	
			for (int i = 0; i < landscape.getLandscapeLength(); i++) {
				for(int j = 0; j < landscape.getLandscapeWidth(); j++) {
					update_hare(i,j);
					update_puma(i,j);
				}
			}	
			
		}
		
		System.out.println("Simulation finished");
		
	}
	
	/**
	 * Prints a progress bar onto the screen
	 * @param Double step - current iteration
	 * @param Double total - total number of iterations
	 */
	private static void progressBar(double step, double total) {
		
		int percent_int = (int)(step*10/total);
		
		String progress_bar = "[";
		
		for(int i = 0; i <= 10; i++) {
			
			if(i > percent_int) {
				progress_bar += " ";
			}
			else {
				progress_bar += "#";
			}
			
		}
		
		progress_bar += "]";
		
		String output = String.format( "%.2f",step*100/total) + "% " + progress_bar + "\r";

		System.out.print(output);
		
	}
	
	/**
	 * This updates the number of hares for each time step
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 */
	private static void update_hare(int i, int j) {
		
		double hare = old_Landscape.currentGrid[i][j].hares;
		
		new_Landscape.currentGrid[i][j].hares = hare + old_Landscape.size_time_step * (
											  + hareLocalPopulationChange(i, j, hare) 
										      + hareNetMigration(i, j, hare));
													 	
	}

	/**
	 * This updates the number of pumas for each time step
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 */
	private static void update_puma(int i, int j) {
		
		double puma = old_Landscape.currentGrid[i][j].pumas;
		
		new_Landscape.currentGrid[i][j].hares = puma + old_Landscape.size_time_step * (
											  + pumaLocalPopulationChange(i, j, puma) 
											  + pumaNetMigration(i, j, puma));
		
	}
	
	/**
	 * Calculates number of hares born and number of hares eaten 
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @param Double hare - current number of hares in square
	 * @return Double - hares born minus hares eaten
	 */	
	private static double hareLocalPopulationChange(int i, int j, double hare) {
		
		double hares_born  = old_Landscape.birth_rate_hares * hare;
		double hares_eaten = old_Landscape.predation_rate   * old_Landscape.currentGrid[i][j].pumas * hare; 
		
		return hares_born - hares_eaten; 
		
	}
	
	/**
	 * Calculates number of hares moving into square and number of hares moving out of square
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @param Double hare - current number of hares in square
	 * @return Double - hare net migration times diffusion rate
	 */		
	private static double hareNetMigration(int i, int j, double hare) {
		
		double hare_immigration = 0.0;
		
		List<GridSquare> surroundingGrids = getSurroundingGridSquares(i, j);
		for(GridSquare g : surroundingGrids) {
			hare_immigration += g.hares;
		}
		
		double hare_emigration = old_Landscape.getNumberOfSurroundingLandSquares(i,j) * hare;
		
		return old_Landscape.diffusion_rate_hares * (hare_immigration - hare_emigration);
		
	}
	
	/**
	 * Calculates number of pumas born and number of pumas that died 
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @param Double puma - current number of pumas in square
	 * @return Double - pumas born minus pumas dead
	 */	
	private static double pumaLocalPopulationChange(int i, int j, double puma) {
		
		double pumas_born = old_Landscape.birth_rate_pumas_per_hare_eaten * new_Landscape.currentGrid[i][j].hares * puma;
		double pumas_dead = old_Landscape.mortality_rate_pumas * puma ;	
		
		return pumas_born - pumas_dead;
		
	}
	
	/**
	 * Calculates number of pumas moving into square and number of pumas moving out of square
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @param Double hare - current number of hares in square
	 * @return Double - puma net migration times diffusion rate
	 */		
	private static double pumaNetMigration(int i, int j, double puma) {
		
		double puma_immigration = 0.0;
		
		List<GridSquare> surroundingGrids = getSurroundingGridSquares(i, j);
		for(GridSquare g : surroundingGrids) {
			puma_immigration += g.pumas;
		}
	
		double puma_emigration = old_Landscape.getNumberOfSurroundingLandSquares(i,j) * puma;
		
		return old_Landscape.diffusion_rate_pumas * (puma_immigration - puma_emigration);
		
	}
	
	/**
	 * Returns a list of all existing GridSquares surrounding Gridsquare
	 * with coordinates i and j
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @return List<GridSquare> - list of surrounding GridSquares
	 */			
	private static List<GridSquare> getSurroundingGridSquares(int i, int j) {
		
		List<GridSquare> surroundings = new ArrayList<>();
		
		// if it does not overshoot on top of landscape
		if(!(i-1 < 0)) {
			surroundings.add(old_Landscape.currentGrid[i-1][j]);
		}
		
		// if it does not overshoot on bottom of landscape
		if(!(i + 1 > old_Landscape.getLandscapeLength() - 1)) {
			surroundings.add(old_Landscape.currentGrid[i+1][j]);
		}
		
		// if it does not overshoot to the left of landscape
		if(!(j - 1 < 0)) {
			surroundings.add(old_Landscape.currentGrid[i][j-1]);
		}	
		
		// if it does not overshoot on bottom of landscape
		if(!(j + 1 > old_Landscape.getLandscapeWidth() - 1)) {
			surroundings.add(old_Landscape.currentGrid[i][j+1]);
		}		
		
		return surroundings;
		
	}
	
}
