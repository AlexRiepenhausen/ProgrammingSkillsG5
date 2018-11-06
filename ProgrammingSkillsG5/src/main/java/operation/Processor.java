package main.java.operation;

import java.util.ArrayList;
import java.util.List;

import main.java.world.GridSquare;
import main.java.world.Landscape;

public class Processor  {
	
	private static Landscape old_landscape;
	private static Landscape new_landscape;
	
	private static double total_hares;
	private static double total_pumas;
	private static double average_hares;
	private static double average_pumas;
	
	/**
	 * Runs the simulation from start to finish
	 * @param Landscape landscape - landscape object
	 */	
	public static void run(Landscape landscape, int total_time_steps){
		
		// duplicate the Landscape, in order to complete the updates which are self-dependent
		old_landscape = landscape;
		new_landscape = landscape;

		// run the simulation the amount of time steps specified
		for (int k = 0; k < total_time_steps; k++) {
			
			total_hares   = 0.0;
			total_pumas   = 0.0;
			average_hares = 0.0;
			average_pumas = 0.0;
			
			// update the populations or densities of both hare and puma in each grid square	
			for (int i = 0; i < landscape.getLandscapeLength(); i++) {
				for(int j = 0; j < landscape.getLandscapeWidth(); j++) {
					updateHare(i,j);
					updatePuma(i,j);
				}
			}	
			
			//update
			old_landscape = new_landscape;
			
			calculateAverageHares();
			calculateAveragePumas();
			
		}		
	}
	
	public static void printAnimalAverages(int step) {
		if(step == 0) {
			System.out.println("|Average Hare|Average Puma");
		}
		System.out.format("| %10.2f | %10.2f \n",average_hares,average_pumas);
	}
	
	/**
	 * This updates the number of hares for each time step
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 */
	private static void updateHare(int i, int j) {
			
		new_landscape.currentGrid[i][j].hares = old_landscape.currentGrid[i][j].hares 
											  + old_landscape.size_time_step * (
											  + hareLocalPopulationChange(i, j) 
										      + hareNetMigration(i, j));
		
		//hares cannot become negative
		if(new_landscape.currentGrid[i][j].hares < 0) {
			new_landscape.currentGrid[i][j].hares = 0.0;
		}
		
		total_hares += new_landscape.currentGrid[i][j].hares;
													 	
	}

	/**
	 * This updates the number of pumas for each time step
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 */
	private static void updatePuma(int i, int j) {
		
		new_landscape.currentGrid[i][j].pumas = old_landscape.currentGrid[i][j].pumas 
											  + old_landscape.size_time_step * (
											  + pumaLocalPopulationChange(i, j) 
											  + pumaNetMigration(i, j));
		
		//pumas cannot become negative
		if(new_landscape.currentGrid[i][j].pumas < 0) {
			new_landscape.currentGrid[i][j].pumas = 0.0;
		}
		
		total_pumas += new_landscape.currentGrid[i][j].pumas;
		
	}
	
	/**
	 * Calculates the average number of hares on the landscape 
	 * (divided by total number of Land GridSquares)
	 */
	private static void calculateAverageHares() {
		int num_land_grids = old_landscape.getNumberOfLandGridSquares();
		average_hares = (double)total_hares/(double)num_land_grids;
	}
	
	/**
	 * Calculates the average number of pumas on the landscape 
	 * (divided by total number of Land GridSquares)
	 */
	private static void calculateAveragePumas() {
		int num_land_grids = old_landscape.getNumberOfLandGridSquares();
		average_pumas = (double)total_pumas/(double)num_land_grids;
	}
	
	/**
	 * Calculates number of hares born and number of hares eaten 
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @return Double - hares born minus hares eaten
	 */	
	private static double hareLocalPopulationChange(int i, int j) {
		
		double hares_born  = old_landscape.birth_rate_hares 
				           * old_landscape.currentGrid[i][j].hares;
		
		double hares_eaten = old_landscape.predation_rate 
						   * old_landscape.currentGrid[i][j].pumas 
						   * old_landscape.currentGrid[i][j].hares; 
		
		return hares_born - hares_eaten; 
		
	}
	
	/**
	 * Calculates number of hares moving into square and number of hares moving out of square
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @return Double - hare net migration times diffusion rate
	 */		
	private static double hareNetMigration(int i, int j) {
		
		double hare_immigration = 0.0;
		
		List<GridSquare> surroundingGrids = getSurroundingGridSquares(i, j);
		for(GridSquare g : surroundingGrids) {
			hare_immigration += g.hares;
		}
		
		double hare_emigration = old_landscape.getNumberOfSurroundingLandSquares(i,j)
				               * old_landscape.currentGrid[i][j].hares;
		
		return old_landscape.diffusion_rate_hares * (hare_immigration - hare_emigration);
		
	}
	
	/**
	 * Calculates number of pumas born and number of pumas that died 
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @return Double - pumas born minus pumas dead
	 */	
	private static double pumaLocalPopulationChange(int i, int j) {
		
		double pumas_born = old_landscape.birth_rate_pumas_per_hare_eaten 
						  * old_landscape.currentGrid[i][j].hares 
						  * old_landscape.currentGrid[i][j].pumas;
		
		double pumas_dead = old_landscape.mortality_rate_pumas 
						  * old_landscape.currentGrid[i][j].pumas;	
		
		return pumas_born - pumas_dead;
		
	}
	
	/**
	 * Calculates number of pumas moving into square and number of pumas moving out of square
	 * and returns net change for square
	 * @param Integer i - current landscape row
	 * @param Integer j - current landscape column
	 * @return Double - puma net migration times diffusion rate
	 */		
	private static double pumaNetMigration(int i, int j) {
		
		double puma_immigration = 0.0;
		
		List<GridSquare> surroundingGrids = getSurroundingGridSquares(i, j);
		for(GridSquare g : surroundingGrids) {
			puma_immigration += g.pumas;
		}
	
		double puma_emigration = old_landscape.getNumberOfSurroundingLandSquares(i,j) 
							   * old_landscape.currentGrid[i][j].pumas;
		
		return old_landscape.diffusion_rate_pumas * (puma_immigration - puma_emigration);
		
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
			surroundings.add(old_landscape.currentGrid[i-1][j]);
		}
		
		// if it does not overshoot on bottom of landscape
		if(!(i + 1 > old_landscape.getLandscapeLength() - 1)) {
			surroundings.add(old_landscape.currentGrid[i+1][j]);
		}
		
		// if it does not overshoot to the left of landscape
		if(!(j - 1 < 0)) {
			surroundings.add(old_landscape.currentGrid[i][j-1]);
		}	
		
		// if it does not overshoot on bottom of landscape
		if(!(j + 1 > old_landscape.getLandscapeWidth() - 1)) {
			surroundings.add(old_landscape.currentGrid[i][j+1]);
		}		
		
		return surroundings;
		
	}
	
}
