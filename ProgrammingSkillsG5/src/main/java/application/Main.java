/**
 * The main java file from which the software will be run
 * 
 */

package main.java.application;
import main.java.input.Parser;
import main.java.outputs.Out;
import main.java.world.Landscape;
import main.java.operation.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	
    	/* initalise new landscape with parameters from command line */
    	//Landscape landscape = Parser.createLandscapeFromCommandLine();
    	
    	/* Alternative: initialise new landscape manually (testing purposes) */
    	Landscape landscape = new Landscape(); 
    	
    	/* take the landscape and populate its landscape grid */
    	Parser.populateLandscapeGridfromFile(landscape,"islands.dat");
    	    	
    	/* print the landscape */
    	//landscape.printLandscape();
    
    	

    	
		Out.printOutput("start");
    	
    	/* print the landscape */
    	
    	for (int i=0; i<50; i++) {
    		Processor.run(landscape, 10);    		
    		Out.generatePlainPPMFile(landscape, i);
    	}	


    	/* print the landscape */
    	//Processor.run(landscape, 500);
    	
		
		//Out.generatePlainPPMFile(landscape);
		//Out.createTempFile(500, 400);
		
    	Out.printOutput("finish");
    	
    	

    	
    }
    
}
