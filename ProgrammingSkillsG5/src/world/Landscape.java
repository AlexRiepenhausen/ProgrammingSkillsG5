package world;

public class Landscape {
	
	/* is the rate at which hares are born*/
	public final double birth_rate_hares;
	
	/* is the rate at which pumas eat hares */
	public final double predation_rate;		
	
	/* is the birth rate of pumas per hare eaten */
	public final double birth_rate_pumas_per_hare_eaten;
	
	/* is the puma mortality rate */
	public final double mortality_rate_pumas;
	
	/* is the diffusion rate for hares */
	public final double diffusion_rate_hares;
	
	/* is the diffusion rate for pumas */
	public final double diffusion_rate_pumas;
	
	/* is the size of the time step */
	public final double size_time_step;
		
	/* 2D double array: @Hassan, simply replace double type with Object GridSquare later */
	public int currentGrid[][]; //land == 1; water == 0
	
	/* Constructor */
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
		
	}//Landscape
	
	/* returns length of the landscape in grid squares */
	public int getLandscapeLength() {
		return currentGrid.length;
	}	
	
	/* returns width of the landscape in grid squares */
	public int getLandscapeWidth() {
		return currentGrid[0].length;
	}
	
	/* prints the landscape onto the command line for debugging purposes */
	public void printLandscape() {	
		
		int length = getLandscapeLength();
		int width  = getLandscapeWidth();
		
		System.out.println("Length: " + length);
		System.out.println("Width:  " + width);
		
		for(int i = 0; i < length; i++) {				
			for(int j = 0; j < width; j++) {
				System.out.print(currentGrid[i][j] + " ");
			}		
			System.out.println("");
		} 
		
	}//printLandscape
	
}
