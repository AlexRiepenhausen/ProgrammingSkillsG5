package test.java.outputs;

import static org.junit.Assert.*;
import java.io.File;
import org.junit.Test;
import main.java.input.Parser;
import main.java.outputs.*;
import main.java.world.Landscape;

public class OutTest {

	@Test
	public void generatePlainPPMFileTest() throws Exception {
		int i=0;
		Landscape landscape = new Landscape(); 
    	Parser.populateLandscapeGridfromFile(landscape,"src/test/resources/sample.dat");
		
    	Out.generatePlainPPMFile(landscape, i);
		File file = new File("./PPM_Files/"+Out.fileNameNumberFormat(i)+".ppm");
		
		assertTrue(file.exists());
	}

}
