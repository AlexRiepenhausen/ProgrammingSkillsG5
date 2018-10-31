package test.java.world;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.java.world.GridSquare;
import main.java.world.GridSquareType;

/**
 * This class test the functionality and the correctness of the GridSquare class
 * @author Team
 *
 */
public class GridSquareTest {

	/**
	 * Test random density of Hares (H) and Pumas (P) range from 0 to 5.0
	 */
	@Test
	public void testCreateGirdWithRandomDensity() {		
		GridSquare gridSquare = new GridSquare(GridSquareType.Land);
		assertTrue("The density of hares is not in the range",
				gridSquare.hares >= 0 && gridSquare.hares <= 5);
		assertTrue("The density of pumas is not in the range",
				gridSquare.pumas >= 0 && gridSquare.pumas <= 5);
	}
	
	/**
	 * Test that water does not have density of Hares (H) and Pumas (P) 
	 */
	@Test
	public void testWaterGridDensities() {		
		GridSquare gridSquare = new GridSquare(GridSquareType.Water);
		assertEquals("Hares density in water not as expected", 
				0.0, gridSquare.hares, 0.0001);
		assertEquals("Pumas density in water not as expected", 
				0.0, gridSquare.pumas, 0.0001);
	}
	
	@Test
	public void testCreateGridSquareFromInt() {
		GridSquare landGrid = GridSquare.generateGridSquareFromInt(1);
		assertTrue("The grid type is not as expected", landGrid.isLand()); 
		
		landGrid = GridSquare.generateGridSquareFromInt(0);
		assertTrue("The grid type is not as expected", !landGrid.isLand());
	}
	
	@Test
	public void testCreateGridSquareFromString() {
		GridSquare landGrid = GridSquare.generateGridSquareFromStr("1");
		assertTrue("The grid type is not as expected", landGrid.isLand()); 
		
		landGrid = GridSquare.generateGridSquareFromStr("0");
		assertTrue("The grid type is not as expected", !landGrid.isLand());
	}
}
