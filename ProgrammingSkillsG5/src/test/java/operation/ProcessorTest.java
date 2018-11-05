package test.java.operation;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

import main.java.input.Parser;
import main.java.world.*;
import main.java.operation.Processor;

public class ProcessorTest {
	
	private static Field old_landscape;
	private static Field new_landscape;
	
	@BeforeClass
	public static void initFields() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		old_landscape = Processor.class.getDeclaredField("old_landscape");
		old_landscape.setAccessible(true);	
		old_landscape.set(Landscape.class, initLandscape(5.0,0.5));
		new_landscape = Processor.class.getDeclaredField("new_landscape");
		new_landscape.setAccessible(true);	
		new_landscape.set(Landscape.class, initLandscape(5.0,0.5));
	}
	
	public static Landscape initLandscape(double hare, double puma) {
			
		/* birth_rate_hares = 0.08,
		 * predation_rate = 0.04,
		 * birth_rate_pumas_per_hare_eaten = 0.02,
		 * mortality_rate_pumas = 0.06,
		 * diffusion_rate_hares = 0.2,
		 * diffusion_rate_pumas = 0.2,
		 * size_time_step = 0.4 */
		
		Landscape landscape = new Landscape(0.08, 0.04, 0.02, 0.06, 0.2, 0.2, 0.4);
		
		landscape.currentGrid = new GridSquare[10][10];
		for(int i = 0; i < 10; i++) {
			for(int j = 0; j < 10; j++) {
				landscape.currentGrid[i][j] = new GridSquare(GridSquareType.Land);
				landscape.currentGrid[i][j].hares = hare;
				landscape.currentGrid[i][j].pumas = puma;
			}
		}
		
		return landscape;
		
	}
	
	public static double roundTwoDecimalPlaces(double input) {
		return BigDecimal.valueOf(input).setScale(3, RoundingMode.HALF_UP).doubleValue();
	}
	
	@Test 
	public void updatePumaNonNegative() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method updatePuma = Processor.class.getDeclaredMethod("updatePuma", int.class, int.class);
		updatePuma.setAccessible(true);	
		
		Landscape ls = (Landscape)old_landscape.get(old_landscape);
		ls.currentGrid[1][1].pumas = -1000.0;
		old_landscape.set(Landscape.class, ls);
		
		updatePuma.invoke(int.class,1,1);	
		
		ls = (Landscape)new_landscape.get(new_landscape);
		assertTrue(ls.currentGrid[1][1].pumas == 0.0);
		
		old_landscape.set(Landscape.class, initLandscape(5.0,0.5));
		
	}
	
	@Test 
	public void updateHareNonNegative() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method updateHare = Processor.class.getDeclaredMethod("updateHare", int.class, int.class);
		updateHare.setAccessible(true);	
		
		Landscape ls = (Landscape)old_landscape.get(old_landscape);
		ls.currentGrid[1][1].hares = -1000.0;
		old_landscape.set(Landscape.class, ls);
		
		updateHare.invoke(int.class,1,1);	
		
		ls = (Landscape)new_landscape.get(new_landscape);
		assertTrue(ls.currentGrid[1][1].hares == 0.0);
		
		old_landscape.set(Landscape.class, initLandscape(5.0,0.5));
	}
	
	@Test
	public void pumaLocalPopulationChangeArithmetic() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method pumaLocalPopulationChange = Processor.class.getDeclaredMethod("pumaLocalPopulationChange", int.class, int.class);
		pumaLocalPopulationChange.setAccessible(true);	
		
		pumaLocalPopulationChange.invoke(Object.class,1,1);
		
		double middle = (double)pumaLocalPopulationChange.invoke(double.class,1,1);
		double corner = (double)pumaLocalPopulationChange.invoke(double.class,0,0);
		double edge   = (double)pumaLocalPopulationChange.invoke(double.class,1,0);
		
		assertTrue(roundTwoDecimalPlaces(middle) == 0.02);
		assertTrue(roundTwoDecimalPlaces(corner) == 0.02);
		assertTrue(roundTwoDecimalPlaces(edge)   == 0.02);
		
	}
	
	@Test
	public void hareLocalPopulationChangeArithmetic() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method hareLocalPopulationChange = Processor.class.getDeclaredMethod("hareLocalPopulationChange", int.class, int.class);
		hareLocalPopulationChange.setAccessible(true);	
		
		hareLocalPopulationChange.invoke(Object.class,1,1);
		
		double middle = (double)hareLocalPopulationChange.invoke(double.class,1,1);
		double corner = (double)hareLocalPopulationChange.invoke(double.class,0,0);
		double edge   = (double)hareLocalPopulationChange.invoke(double.class,1,0);

		assertTrue(roundTwoDecimalPlaces(middle) == 0.30);
		assertTrue(roundTwoDecimalPlaces(corner) == 0.30);
		assertTrue(roundTwoDecimalPlaces(edge)   == 0.30);
		
	}
	
	@Test
	public void pumaNetMigrationArithmetic() throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		Method pumaNetMigration = Processor.class.getDeclaredMethod("pumaNetMigration", int.class, int.class);
		pumaNetMigration.setAccessible(true);
		
		double middle = (double)pumaNetMigration.invoke(double.class,1,1);
		double corner = (double)pumaNetMigration.invoke(double.class,0,0);
		double edge = (double)pumaNetMigration.invoke(double.class,1,0);
		
		assertTrue(roundTwoDecimalPlaces(middle) == 0.0);
		assertTrue(roundTwoDecimalPlaces(corner) == 0.0);
		assertTrue(roundTwoDecimalPlaces(edge)   == 0.0);
			
	}
	
	@Test
	public void hareNetMigrationArithmetic() throws NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		Method hareNetMigration = Processor.class.getDeclaredMethod("hareNetMigration", int.class, int.class);
		hareNetMigration.setAccessible(true);
		
		double middle = (double)hareNetMigration.invoke(double.class,1,1);
		double corner = (double)hareNetMigration.invoke(double.class,0,0);
		double edge   = (double)hareNetMigration.invoke(double.class,1,0);
		
		assertTrue(roundTwoDecimalPlaces(middle) == 0.0);
		assertTrue(roundTwoDecimalPlaces(corner) == 0.0);
		assertTrue(roundTwoDecimalPlaces(edge)   == 0.0);
			
	}

	@Test
	public void getSurroundingGridSquaresInputs() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException, ClassNotFoundException {
		
		Method method = Processor.class.getDeclaredMethod("getSurroundingGridSquares", int.class, int.class);
		method.setAccessible(true);
		
		ArrayList<GridSquareType> leftUpperCorner  = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {0,0});
		ArrayList<GridSquareType> rightUpperCorner = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {0,9});
		ArrayList<GridSquareType> leftLowerCorner  = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {9,0});
		ArrayList<GridSquareType> rightLowerCorner = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {9,9});
		
		assertEquals(leftUpperCorner.size(),2);
		assertEquals(rightUpperCorner.size(),2);
		assertEquals(leftLowerCorner.size(),2);
		assertEquals(rightLowerCorner.size(),2);
		
		ArrayList<GridSquareType> rightEdge  = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {4,9});
		ArrayList<GridSquareType> leftEdge   = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {4,0});
		ArrayList<GridSquareType> upperEdge  = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {0,4});
		ArrayList<GridSquareType> lowerEdge  = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {9,4});
		
		assertEquals(rightEdge.size(),3);
		assertEquals(leftEdge.size(),3);
		assertEquals(upperEdge.size(),3);
		assertEquals(lowerEdge.size(),3);
		
		ArrayList<GridSquareType> middle  = (ArrayList<GridSquareType>)method.invoke(Object.class, new Object[] {4,4});
		assertEquals(middle.size(),4);

	}

}
