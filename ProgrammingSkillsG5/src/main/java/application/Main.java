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
	
	public static final String CONFIG_ARGS = "-config";
	public static final String FILE_ARGS = "-file";
	
	/**
	 * CONFIG_FILE structure 
	 * =====================
	 * input_file:src/main/resources/islands.dat
	 * birth_rate_hares:0.08
	 * predation_rate:0.04
	 * birth_rate_pumas_per_hare_eaten:0.02
	 * mortality_rate_pumas:0.06
	 * diffusion_rate_hares:0.2
	 * diffusion_rate_pumas:0.2
	 * size_time_step:0.4
	 * 
	 * =====================
	 *  
	 * @param args -config CONFIG_FILE or -file INPUT_FILE
	 * @throws Exception
	 */
    public static void main(String[] args) throws Exception {
    	Landscape landscape = null;
    	String configFile = ""; 
    	if (args.length == 1) {
    		throw new IllegalArgumentException("Invalid Arguments!");    	
    	} else if (args.length > 1) {
    		if (args[0].equalsIgnoreCase(CONFIG_ARGS)) {
    			/* if the config file specified read the configuration */ 
    			configFile = args[1];
    			landscape = Parser.createLandscapefromConfigFile(configFile);
    			
    		} else if (args[0].equalsIgnoreCase(FILE_ARGS)) {
    			/* if the input file specified run with the default parameters */
    			landscape = new Landscape(); 
    			Parser.populateLandscapeGridfromFile(landscape,args[1]);
    		}
    		
    	} else {
    		/* if no argument specified initalise new landscape 
    		 * with parameters from command line */
        	landscape = Parser.createLandscapeFromCommandLine();	
    	}
    	
    	if (landscape == null) {
    		System.err.println("The application terminated unsuccessfully");
    		return;
    	}
    		
    	/* Alternative: initialise new landscape manually (testing purposes) */
    	//Landscape landscape = new Landscape(0.08,0.04,0.001,0.5,0.2,0.2,0.4); 
    		
    	long start = System.currentTimeMillis();
    	
    	for (int i=0; i<50; i++) {
    		Processor.run(landscape, 10);
			Processor.printAnimalAverages(i);
    		Out.generatePlainPPMFile(landscape, i);
    	}
    	
        long finish = System.currentTimeMillis();
        
        double seconds = (double)(finish-start)/1000.0;
        		
        System.out.format("Total time taken: %.2f seconds\n", seconds);
    	
    }    
}
