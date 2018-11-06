package test.java.input;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.sun.istack.internal.localization.NullLocalizable;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import main.java.input.Parser;
import main.java.operation.Processor;
import main.java.world.GridSquare;
import main.java.world.Landscape;
import main.java.world.GridSquareType;

/**
 * 
 * @author Alexey Riepenhausen
 *
 */
@RunWith(value = BlockJUnit4ClassRunner.class)
public class ParserTest {
	
	private static String[] maplines = new String[] {
			"1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 1 1", 
			"1 0 1 1 1 0 1 1 1 0 1 1 1 0 0 0 0 0 1 1",
			"1 0 1 0 1 0 1 1 1 0 1 0 1 0 0 0 0 0 0 0", 
			"1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 1 1 0", 
			"1 0 0 0 0 0 1 1 1 0 0 0 0 0 1 1 1 1 1 0", 
			"1 0 1 1 1 0 1 1 1 0 1 1 1 0 1 1 1 1 1 0", 
			"1 0 1 0 1 0 1 1 1 0 1 0 1 0 1 1 1 1 1 0", 
			"1 0 1 1 1 0 1 1 1 0 1 1 1 0 0 0 0 0 0 0", 
			"1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 1 1", 
			"1 0 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 1 1" 
	};
	
	private static int[][] sampleMap = new int[][] {
		 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,}, 
         {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1,},
		 {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0,}, 
		 {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,}, 
		 {1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0,}, 
		 {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0,},
		 {1, 0, 1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0,}, 
		 {1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0,}, 
		 {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,}, 
		 {1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1,}
	};
	
	@Test
	public void linesToGridConversionTest() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		
		//Takes maplines and converts them to Landscape.currentGrid[][]
		//This test verifies that the logical conversion process works
		
		Method linesToGrid = Parser.class.getDeclaredMethod("linesToGrid", Landscape.class, String[].class);
		linesToGrid.setAccessible(true);
		
		Landscape landscape   = new Landscape();
		landscape.currentGrid = new GridSquare[sampleMap.length][sampleMap[0].length];
		linesToGrid.invoke(Object.class,landscape, maplines);	
		for(int i = 0; i < sampleMap.length; i++) {
			for(int j = 0; j < sampleMap[0].length; j++) {
				GridSquareType tile = GridSquareType.Land;
				if(sampleMap[i][j] == 0) {tile = GridSquareType.Water;}
				assertEquals(landscape.currentGrid[i][j].gridSquareType, tile);		
			}
		}
		
	}
	
	@Test
	public void initBuffReadBufferedReadNotNull() throws FileNotFoundException, NoSuchMethodException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		
		Method initBuffRead = Parser.class.getDeclaredMethod("initBuffRead", String.class);
		initBuffRead.setAccessible(true);
		
		Field b_read = Parser.class.getDeclaredField("b_read");
		b_read.setAccessible(true);
		
		b_read.set(BufferedReader.class, null);	

		initBuffRead.invoke(String.class,"src/test/resources/sample_orig.dat");
		assertNotNull(b_read);
		
	}

	@Test(expected = InvocationTargetException.class)
	public void initBuffReadExceptionMessage() throws FileNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Method initBuffRead = Parser.class.getDeclaredMethod("initBuffRead", String.class);
		initBuffRead.setAccessible(true);
		initBuffRead.invoke(String.class,"WRONGFILENAME");
	} 
	
	@Test
	public void isDoubleDifferentValues() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method isDouble  = Parser.class.getDeclaredMethod("isDouble", String.class);	
		isDouble.setAccessible(true);
		
		assertTrue((boolean)isDouble.invoke(String.class,"1.0"));
		assertTrue((boolean)isDouble.invoke(String.class,"1"));
		assertFalse((boolean)isDouble.invoke(String.class,"string"));
		
	}

	@Test
	public void parseVariableFromCommandLineDouble() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		ByteArrayInputStream in = new ByteArrayInputStream("1.0".getBytes());
		System.setIn(in);		
	    Scanner mockScanner = new Scanner(in);	   
	    
		Method parseVariableFromCommandLine = 
				Parser.class.getDeclaredMethod("parseVariableFromCommandLine", String.class, Scanner.class);	
		parseVariableFromCommandLine.setAccessible(true);
		
		Method isDouble = Parser.class.getDeclaredMethod("isDouble", String.class);
		isDouble.setAccessible(true);
		
		String str_input = (String) parseVariableFromCommandLine.invoke(Object.class,"", mockScanner);		
		assertTrue((boolean)isDouble.invoke(String.class,str_input));	
	}
	
	@Test
	public void parseVariableFromCommandLineString() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {	
		
		ByteArrayInputStream in = new ByteArrayInputStream("string".getBytes());
		System.setIn(in);		
	    Scanner mockScanner = new Scanner(in);	    
	    
		Method parseVariableFromCommandLine = 
				Parser.class.getDeclaredMethod("parseVariableFromCommandLine", String.class, Scanner.class);	
		parseVariableFromCommandLine.setAccessible(true);
		
		Method isDouble = Parser.class.getDeclaredMethod("isDouble", String.class);
		isDouble.setAccessible(true);
		
		String str_input = (String) parseVariableFromCommandLine.invoke(Object.class,"", mockScanner);		
		assertFalse((boolean)isDouble.invoke(String.class,str_input));
	}

	@Test
	public void ParamsWithinBoundary() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {	
		
		Method withinBoundary  = Parser.class.getDeclaredMethod("withinBoundary", double.class);	
		withinBoundary.setAccessible(true);
		
		assertFalse((boolean)withinBoundary.invoke(Double.class,5.1));
		assertFalse((boolean)withinBoundary.invoke(Double.class,-0.1));
		assertTrue((boolean)withinBoundary.invoke(Double.class,0.0));	
		assertTrue((boolean)withinBoundary.invoke(Double.class,.1));	
		
	}
	
	@Test(expected = InvocationTargetException.class)
	public void getNextLineUnintitialisedBufferedReader() throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Method initBuffRead = Parser.class.getDeclaredMethod("initBuffRead", String.class);
		Method getNextLine  = Parser.class.getDeclaredMethod("getNextLine");
		
		initBuffRead.setAccessible(true);
		getNextLine.setAccessible(true);
		
		initBuffRead.invoke(String.class, "WRONGFILENAME");
		getNextLine.invoke(Object.class);
	
	}
	
	@Test
	public void getNextLineIntitialisedBufferedReader() throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		
		Method initBuffRead = Parser.class.getDeclaredMethod("initBuffRead", String.class);
		Method getNextLine  = Parser.class.getDeclaredMethod("getNextLine");
		
		initBuffRead.setAccessible(true);
		getNextLine.setAccessible(true);
		
		Field b_read = Parser.class.getDeclaredField("b_read");
		b_read.setAccessible(true);
		
		b_read.set(BufferedReader.class, null);		
		
		initBuffRead.invoke(String.class, "src/test/resources/sample_orig.dat");
		
		assertNotNull(getNextLine.invoke(Object.class));
		//assertTrue(Parser.getNextLine().getClass().isArray());
	}
	
	@Test
	public void populateLandscapeGridfromFileProperFile() {
		Landscape landscape = new Landscape();
		Parser.populateLandscapeGridfromFile(landscape,"src/test/resources/sample_orig.dat");
		assertNotNull(landscape.currentGrid);
	}
	
	@Test
	public void populateLandscapeGridfromFileWrongFileName() {
		Landscape landscape = new Landscape();
		Parser.populateLandscapeGridfromFile(landscape,"WRONGFILENAME");
		assertNull(landscape.currentGrid);
	}
	
	@Test
	public void populateLandscapeGridfromFileCheckResult() {
		Landscape landscape = new Landscape();
		Parser.populateLandscapeGridfromFile(landscape,"src/test/resources/sample_orig.dat");
		assertNotNull(landscape.currentGrid);	
		assertTrue(landscape.getLandscapeLength() > 0.0);
		assertTrue(landscape.getLandscapeWidth() > 0.0);
	}

}
