package main.java.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import main.java.world.GridSquare;
import main.java.world.Landscape;

public class Parser {
	
	public static BufferedReader b_read;
	
	/**
	 * Instantiates global variable b_read of type BufferedReader
	 * @param  String - file name of the file in question without path
	 */	
	public static void initBuffRead(String file_name) throws FileNotFoundException {
		
		System.out.println("Reading file ...");
		File landscape_file = new File("src/main/resources/" + file_name);	
		FileReader f_read   = new FileReader(landscape_file);
		
		b_read = new BufferedReader(f_read);	
		System.out.println("Finished reading file into buffered reader");
	
	}
	
	/**
	 * @return Landscape - creates a new Landscape object with parameters from command line
	 */	
	public static Landscape createLandscapeFromCommandLine() {

		String[] str_inputs = new String[7];		
		double[] dbl_inputs = new double[7];		
		
		String[] prompts = {"Enter the rate at which hares are born:       ",
							"Enter the rate at which pumas eat hares:      ",
							"Enter the birth rate of pumas per hare eaten: ",
							"Enter the puma mortality rate:                ",
							"Enter the diffusion rate for hares:           ",
							"Enter the diffusion rate for pumas:           ",
							"Enter the size of the time step:              "};
		
		Scanner scanner = new Scanner(System.in);
		
		for(int i = 0; i < 7; i++) {
			
			str_inputs[i] = parseVariableFromCommandLine(prompts[i], scanner);
			
			while(!isDouble(str_inputs[i])) {
				System.out.println("Your input is not a number. Please specifiy value again");
				str_inputs[i] = parseVariableFromCommandLine(prompts[i], scanner);
			}
			
			dbl_inputs[i] = Double.parseDouble(str_inputs[i]);
			
			if(!withinBoundary(dbl_inputs[i])) {
				System.out.println("Wrong value (e.g. negative or too big). Please specifiy value again");
				i--; //go back one step
			}
			
		}
		
		scanner.close();
		
		return new Landscape(dbl_inputs[0],
							 dbl_inputs[1],
							 dbl_inputs[2],
							 dbl_inputs[3],
							 dbl_inputs[4],
							 dbl_inputs[5],
							 dbl_inputs[6]);
							 
	}
	
	/**
	 * @param String  - command line prompt
	 * @param Scanner - current scanner taking user response after the prompt
	 * @return String - the command line input after the prompt in form of a string
	 */	
	public static String parseVariableFromCommandLine(String prompt, Scanner scanner) {
		System.out.print(prompt);
		return scanner.next();
	}
	
	/**
	 * @param String   - user input from command line
	 * @return Boolean - true if said input can be safely converted to a double, false otherwise
	 */	
	public static boolean isDouble(String input) {
		
        try {
            double number = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
	}

	/**
	 * @param Double   - map parameter in question
	 * @return Boolean - true if within specified boundary, false otherwise
	 */	
	public static boolean withinBoundary(double val) {
		
		if(val < 0.0) {return false;}
		else if(val > 5.0) {return false;}	
		return true;
		
	}
	
	/**
	 * A function that reads a file containing the grid design of the landscape
	 * and populates the supplied landscape object with a 2D grid
	 * @param Landscape - a landscape object without its LandscapeGrid set yet
	 * @param String    - file containing grid information for landscape
	 */		 
	public static void populateLandscapeGridfromFile(Landscape landscape,
													 String file_name) {

		//the first line contains width and length of the landscape
		String[] firstline;		
		
		try {initBuffRead(file_name);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}
				
		try {firstline = getNextLine();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		//split the two elements of this line and assign them to width and length
		int width  = Integer.parseInt(firstline[0]);
		int length = Integer.parseInt(firstline[1]);
		
		//initialise landscape arrays
		landscape.currentGrid = new GridSquare[length][width];
	
		linesToGrid(landscape, length, width);
		
	}//readFile
	
	/**
	 * Takes a landscape object and populates its 2D gridSquare array from BufferedReader
	 * @param Landscape - a landscape object with an already existing LandscapeGrid
	 * @param Int       - length of the landscape grid
	 * @param Int       - width of the landscape grid
	 */			
	public static void linesToGrid(Landscape landscape, int length, int width) {
		
		String nextline[];
		
		for(int i = 0; i < length; i++) {
			
			try {
				nextline = getNextLine();
			} catch (IOException e) {
				System.out.println("Exception: Could not read next line from BufferedReader");
				e.printStackTrace();
				return;
			}

			for(int j = 0; j < width; j++) {
				landscape.currentGrid[i][j] = GridSquare.generateGridSquareFromStr(nextline[j]);
			}
			
		}
		
	}
	
	/**
	 * Takes a landscape object and populates its 2D gridSquare array from a String array containing lines
	 * @param Landscape - a landscape object with an already existing LandscapeGrid
	 * @param String[]  - String array containing lines
	 */			
	public static void linesToGrid(Landscape landscape, String[] lines) {
		
		String[] nextline;
		
		for(int i = 0; i < lines.length; i++) {
			
			nextline = lines[i].split(" ");

			for(int j = 0; j < nextline.length; j++) {
				landscape.currentGrid[i][j] = GridSquare.generateGridSquareFromStr(nextline[j]);
			}
			
		}
		
		System.out.println("Finished reading file");
		
	}
	
	/**
	 * @return next line of BuffereReader in form of String array with blank space set as delimiter
	 */			
	public static String[] getNextLine() throws IOException{
		return b_read.readLine().split(" ");
	}
	
}
