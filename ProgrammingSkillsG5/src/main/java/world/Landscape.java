	package main.java.world;

/**
 * This class represent the landscape with all the rates values
 * @author Team
 *
 */
public class Landscape {
	
	/** (r) is the rate at which hares are born */
	public final double birth_rate_hares;
	
	/** (a) is the rate at which pumas eat hares */
	public final double predation_rate;		
	
	/** (b) is the birth rate of pumas per hare eaten */
	public final double birth_rate_pumas_per_hare_eaten;
	
	/** (m) is the puma mortality rate  */
	public final double mortality_rate_pumas;
	
	/** (k) is the diffusion rate for hares */
	public final double diffusion_rate_hares;
	
	/** (l) is the diffusion rate for pumas */
	public final double diffusion_rate_pumas;
	
	/** (delta t) is the size of the time step */
	public final double size_time_step;
		
	/** 2D array GridSquare later */
	public GridSquare currentGrid[][]; //land == 1; water == 0
	
	/**
	 * Generate a landscape with initial parameters
	 * 
	 * @param birth_rate_hares (r)
	 * @param predation_rate (a)
	 * @param birth_rate_pumas_per_hare_eaten (b)
	 * @param mortality_rate_pumas (m)
	 * @param diffusion_rate_hares (k)
	 * @param diffusion_rate_pumas (l)
	 * @param size_time_step (delta t)
	 */
	public Landscape(double birth_rate_hares,
					 double predation_rate,
					 double birth_rate_pumas_per_hare_eaten,
					 double mortality_rate_pumas,
					 double diffusion_rate_hares,
					 double diffusion_rate_pumas,
					 double size_time_step) {
				
		/* scalar parameters */
		this.birth_rate_hares                 = birth_rate_hares;
		this.predation_rate                   = predation_rate;		
		this.birth_rate_pumas_per_hare_eaten  = birth_rate_pumas_per_hare_eaten;
		this.mortality_rate_pumas             = mortality_rate_pumas;
		this.diffusion_rate_hares             = diffusion_rate_hares;
		this.diffusion_rate_pumas             = diffusion_rate_pumas;
		this.size_time_step                   = size_time_step;
		
	}
	
	/**
	 * Default constructor will set these default parameters:
	 * 
	 * birth_rate_hares = 0.08,
	 * predation_rate = 0.04,
	 * birth_rate_pumas_per_hare_eaten = 0.02,
	 * mortality_rate_pumas = 0.06,
	 * diffusion_rate_hares = 0.2,
	 * diffusion_rate_pumas = 0.2,
	 * size_time_step = 0.4		
	 */
	public Landscape() {			
		this( /* birth_rate_hares */ 0.08,
			/* predation_rate */ 0.04,
			/* birth_rate_pumas_per_hare_eaten */ 0.02,
			/* mortality_rate_pumas */ 0.06,
			/* diffusion_rate_hares */ 0.2,
			/* diffusion_rate_pumas */ 0.2,
			/* size_time_step */ 0.4);		
	}
	
	/**
	 * @return length (rows) of the landscape in grid squares
	 */
	public int getLandscapeLength() {
		return currentGrid.length;
	}	
	
	/** 
	 * @return width (columns) of the landscape in grid squares 
	 */
	public int getLandscapeWidth() {
		
		return currentGrid[0].length;
	}
	
	
	/** prints the landscape onto the command line for debugging purposes */
	public void printLandscape() {	
		
		int length = getLandscapeLength();
		int width  = getLandscapeWidth();
		
		System.out.println("Length: " + length);
		System.out.println("Width:  " + width);
		
		System.out.println("Grid Types:  ");
		
		for(int i = 0; i < length; i++) {				
			for(int j = 0; j < width; j++) {
				System.out.print(currentGrid[i][j].gridSquareType.getNumVal() + " ");
			}		
			System.out.println("");
		} 		

		System.out.println("H density:  ");
		for(int i = 0; i < length; i++) {				
			for(int j = 0; j < width; j++) {
				System.out.print(currentGrid[i][j].hares + " ");
			}		
			System.out.println("");
		} 
		
		System.out.println("P density:  ");
		for(int i = 0; i < length; i++) {				
			for(int j = 0; j < width; j++) {
				System.out.print(currentGrid[i][j].pumas + " ");
			}		
			System.out.println("");
		} 


	}
	
	/**
	 * This method will return the number of the surrounding lands of a specific location
	 * @param x axis location 
	 * @param y axis location
	 * @return the total number of the surrounding lands
	 */
	public int getNumberOfSurroundingLandSquares(int x, int y) {
		int totalNum = 0;
		if (x > 0)
			totalNum += this.currentGrid[x-1][y].isLand() ? 1 : 0;
		if (x < this.currentGrid.length - 1)
			totalNum += this.currentGrid[x+1][y].isLand() ? 1 : 0;
		if (y > 0)
			totalNum += this.currentGrid[x][y-1].isLand() ? 1 : 0;
		if (y < this.currentGrid[0].length - 1)
			totalNum += this.currentGrid[x][y+1].isLand() ? 1 : 0;
		
		return totalNum;
	}
	
}
