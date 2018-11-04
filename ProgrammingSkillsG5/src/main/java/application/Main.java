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
    	Landscape landscape = new Landscape(1.0,1.0,1.0,1.0,1.0,1.0,1.0); 
    	
    	/* take the landscape and populate its landscape grid */
    	Parser.populateLandscapeGridfromFile(landscape,"sample.dat");
    	
    	/* print the landscape */
    	Processor.run(landscape, 500);
    	
    }
    
}
