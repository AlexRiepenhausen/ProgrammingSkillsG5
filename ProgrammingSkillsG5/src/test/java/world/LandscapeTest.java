package test.java.world;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.input.Parser;
import main.java.world.Landscape;

/**
 * This class test the functionality and the correctness of the Landscape class
 * @author Team
 *
 */
public class LandscapeTest {

	@Test
	public void testDefaultLandscapeParameters() {
		Landscape landscape = new Landscape();
		double birth_rate_hares = 0.08,
				 predation_rate = 0.04,
				 birth_rate_pumas_per_hare_eaten = 0.02,
				 mortality_rate_pumas = 0.06,
				 diffusion_rate_hares = 0.2,
				 diffusion_rate_pumas = 0.2,
				 size_time_step = 0.4;
		
		assertEquals("default birth_rate_hares not as expected", 
				birth_rate_hares, landscape.birth_rate_hares, 0.0001);
		assertEquals("default predation_rate not as expected", 
				predation_rate, landscape.predation_rate, 0.0001);
		assertEquals("default birth_rate_pumas_per_hare_eaten not as expected", 
				birth_rate_pumas_per_hare_eaten, landscape.birth_rate_pumas_per_hare_eaten, 0.0001);
		assertEquals("default mortality_rate_pumas not as expected", 
				mortality_rate_pumas, landscape.mortality_rate_pumas, 0.0001);
		assertEquals("default diffusion_rate_hares not as expected", 
				diffusion_rate_hares, landscape.diffusion_rate_hares, 0.0001);
		assertEquals("default diffusion_rate_pumas not as expected", 
				diffusion_rate_pumas, landscape.diffusion_rate_pumas, 0.0001);
		assertEquals("default size_time_step not as expected", 
				size_time_step, landscape.size_time_step, 0.0001);
	}
	
	@Test
	public void testLandscapeLengthAndWidth() {
		Landscape landscape = new Landscape();
		Parser.populateLandscapeGridfromFile(landscape,"sample.dat");
		assertNotNull(landscape.currentGrid);	
		assertEquals("getLandscapeLength (rows) not as expected",
				landscape.getLandscapeLength(), 10);
		assertEquals("getLandscapeWidth (columns) not as expected",
				landscape.getLandscapeWidth() , 20);
		
	}
	
	@Test
	public void testGetNumberOfSurroundingLand() {
		Landscape landscape = new Landscape();
		Parser.populateLandscapeGridfromFile(landscape,"sample.dat");
		assertNotNull(landscape.currentGrid);	
		
		assertEquals("getNumberOfSurroundingLandSquares at [1, 1] not as expected",
				landscape.getNumberOfSurroundingLandSquares(1, 1) , 2);
		assertEquals("getNumberOfSurroundingLandSquares at [5, 5] not as expected",
				landscape.getNumberOfSurroundingLandSquares(5, 5) , 2);
		
		// Test the corners of the landscape
		assertEquals("getNumberOfSurroundingLandSquares at [0, 0] not as expected",
				landscape.getNumberOfSurroundingLandSquares(0, 0) , 1);
		assertEquals("getNumberOfSurroundingLandSquares at [9, 0] not as expected",
				landscape.getNumberOfSurroundingLandSquares(9, 0) , 1);
		assertEquals("getNumberOfSurroundingLandSquares at [0, 19] not as expected",
				landscape.getNumberOfSurroundingLandSquares(0, 19) , 2);
		assertEquals("getNumberOfSurroundingLandSquares at [9, 19] not as expected",
				landscape.getNumberOfSurroundingLandSquares(9, 19) , 2);
		
	}

}
