package operation;

import world.Landscape;

public class Processor 
{
	private Landscape old_Landscape;
	private Landscape new_Landscape;
	private int length;
	private int width;
	
	public Processor(int l, int w)
	{
		length = l;//read in the dimensions of the Landscape
		width = w;
	}
	public Landscape run(Landscape buffer_Landscape)
	{
		
		
		old_Landscape = buffer_Landscape;// duplicate the Landscape, in order to complete the updates which are self-dependent
		new_Landscape = buffer_Landscape;
		
		
		for (int i = 0; i <= length; i++)// update the populations or densities of both hare and puma in each grid square
		{
			for(int j = 0; j <= width; j++)
				{
					update_hare(i,j);
					update_puma(i,j);
				}
		}
		return new_Landscape;
	}
	
	private void update_hare(int i, int j)
	{
		double buffer_hare = old_Landscape.currentGrid[i][j].hares;// this buffer is not necessarily important, unless...
		
		new_Landscape.currentGrid[i][j].hares = buffer_hare 
												+ old_Landscape.size_time_step *
												(	
													old_Landscape.birth_rate_hares * buffer_hare
													- old_Landscape.predation_rate* buffer_hare * old_Landscape.currentGrid[i][j].pumas 
													+ old_Landscape.diffusion_rate_hares *
														(
															( 
																	old_Landscape.currentGrid[i-1][j].hares 
																	+ old_Landscape.currentGrid[i+1][j].hares 
																	+ old_Landscape.currentGrid[i][j-1].hares 
																	+ old_Landscape.currentGrid[i][j+1].hares
															)
															- old_Landscape.currentGrid[i][j].surrounding_Land * buffer_hare																
														)
												);
	}//population or density formula for hares
	
	
	private void update_puma(int i, int j)
	{
		double buffer_puma = old_Landscape.currentGrid[i][j].pumas;
		new_Landscape.currentGrid[i][j].pumas = buffer_puma
												+ old_Landscape.size_time_step*
												(	
													old_Landscape.birth_rate_pumas_per_hare_eaten * new_Landscape.currentGrid[i][j].hares * buffer_puma
													- old_Landscape.mortality_rate_pumas * buffer_puma 
													+ old_Landscape.diffusion_rate_pumas *
														(
															( 
																	old_Landscape.currentGrid[i-1][j].pumas 
																	+ old_Landscape.currentGrid[i+1][j].pumas 
																	+ old_Landscape.currentGrid[i][j-1].pumas 
																	+ old_Landscape.currentGrid[i][j+1].pumas
															)
															- old_Landscape.currentGrid[i][j].surrounding_Land * buffer_puma																
														)
												);
	}//population or density formula for pumas
	
	 
}
