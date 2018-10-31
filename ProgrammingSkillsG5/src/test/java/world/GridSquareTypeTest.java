package test.java.world;

import static org.junit.Assert.*;

import org.junit.Test;

import main.java.world.GridSquareType;

/**
 * This class test the functionality and the correctness of the GridSquareType enum
 * @author Team
 *
 */
public class GridSquareTypeTest {

	@Test
	public void testCreateGridSquareTypeFromInt() {
		GridSquareType landGrid = GridSquareType.setValue(1);
		GridSquareType waterGrid = GridSquareType.setValue(0);
		
		assertEquals("Create Land GridSquareType enum from int not as expected", 
				GridSquareType.Land, landGrid);
		assertEquals("Create Water GridSquareType enum from int not as expected", 
				GridSquareType.Water, waterGrid);
	}
	
	@Test
	public void testCreateGridSquareTypeFromString() {
		GridSquareType landGrid = GridSquareType.setValue("1");
		GridSquareType waterGrid = GridSquareType.setValue("0");
		
		assertEquals("Create Land GridSquareType enum from int not as expected", 
				GridSquareType.Land, landGrid);
		assertEquals("Create Water GridSquareType enum from int not as expected", 
				GridSquareType.Water, waterGrid);
	}

}
