package input;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

import world.GridSquare;
import world.Landscape;

public class Parser {
	
	public static Landscape createLandscapeFromCommandLine() {

		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the rate at which hares are born:       ");
		double birth_rate_hares = Double.parseDouble(scanner.next());
		
		System.out.print("Enter the rate at which pumas eat hares:      ");
		double predation_rate = Double.parseDouble(scanner.next());
		
		System.out.print("Enter the birth rate of pumas per hare eaten: ");
		double birth_rate_pumas_per_hare_eaten = Double.parseDouble(scanner.next());
		
		System.out.print("Enter the puma mortality rate:                ");
		double mortality_rate_pumas = Double.parseDouble(scanner.next());
		
		System.out.print("Enter the diffusion rate for hares:           ");
		double diffusion_rate_hares = Double.parseDouble(scanner.next());

		System.out.print("Enter the diffusion rate for pumas:           ");
		double diffusion_rate_pumas = Double.parseDouble(scanner.next());
		
		System.out.print("Enter the size of the time step:              ");
		double size_time_step = Double.parseDouble(scanner.next());
		
		//close scanner
		scanner.close();
		
		return new Landscape(birth_rate_hares,
							 predation_rate,
							 birth_rate_pumas_per_hare_eaten,
							 mortality_rate_pumas,
							 diffusion_rate_hares,
							 diffusion_rate_pumas,
							 size_time_step);
							 
	}
	 
	/* takes empty landscape and returns filled landscape */
	public static void populateLandscapeGridfromFile(Landscape landscape,
													 String file_name) {
		
		System.out.println("Reading file ...");
		
		try {
						
			File landscape_file   = new File("./resources/" + file_name);	
			FileReader f_read     = new FileReader(landscape_file);
			BufferedReader b_read = new BufferedReader(f_read);
					
			//the first line contains width and length of the landscape
			String firstline = b_read.readLine();
			
			//split the two elements of this line and assign them to width and length
			int width  = Integer.parseInt(firstline.split(" ")[0]);
			int length = Integer.parseInt(firstline.split(" ")[1]);
			
			//initialise landscape arrays
			landscape.currentGrid = new GridSquare[length][width];
			
			String nextline[];
			
			//read the rest of the files			
			for(int i = 0; i < length; i++) {
				
				nextline = b_read.readLine().split(" ");
				
				for(int j = 0; j < width; j++) {
					landscape.currentGrid[i][j] = GridSquare.generateGridSquareFromStr(nextline[j]);
				}
				
			}//for
			
			//close both readers
			b_read.close();
			f_read.close();
	        
		}
	    catch (FileNotFoundException e){
	    	e.printStackTrace();
	        System.out.println("Exception: couldn't find file");
	    } catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception: buffered reader couldn't read line");
		}
		
        System.out.println("Finished reading file");
		
	}//readFile

}
