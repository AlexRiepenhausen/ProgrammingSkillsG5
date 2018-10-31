package test.java.input;

import org.junit.Test;

import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import main.java.input.Parser;

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
	public void linesToGridConversionTest() {
		
		//Takes maplines and converts them to Landscape.currentGrid[][]
		//This test verifies that the logical conversion process works
		
		Landscape landscape   = new Landscape();
		landscape.currentGrid = new GridSquare[sampleMap.length][sampleMap[0].length];
		Parser.linesToGrid(landscape, maplines);	
		for(int i = 0; i < sampleMap.length; i++) {
			for(int j = 0; j < sampleMap[0].length; j++) {
				GridSquareType tile = GridSquareType.Land;
				if(sampleMap[i][j] == 0) {tile = GridSquareType.Water;}
				assertEquals(landscape.currentGrid[i][j].getGridSquareType(), tile);		
			}
		}
		
	}
	
	@Test
	public void initBuffReadBufferedReadNotNull() throws FileNotFoundException {
		Parser.b_read = null;
		Parser.initBuffRead("sample.dat");
		assertNotNull(Parser.b_read);
	}

	@Test(expected = FileNotFoundException.class)
	public void initBuffReadExceptionMessage() throws FileNotFoundException {
		Parser.initBuffRead("WRONGFILENAME");
	} 
	
	@Test
	public void isDoubleDifferentValues() {
		assertTrue(Parser.isDouble("1.0"));
		assertTrue(Parser.isDouble("1"));
		assertFalse(Parser.isDouble("string"));
	}

	@Test
	public void parseVariableFromCommandLineDouble() {
		ByteArrayInputStream in = new ByteArrayInputStream("1.0".getBytes());
		System.setIn(in);		
	    Scanner mockScanner = new Scanner(in);	    
		String str_input = Parser.parseVariableFromCommandLine("", mockScanner);		
		assertTrue(Parser.isDouble(str_input));	
	}
	
	@Test
	public void parseVariableFromCommandLineString() {	
		ByteArrayInputStream in = new ByteArrayInputStream("string".getBytes());
		System.setIn(in);		
	    Scanner mockScanner = new Scanner(in);	    
		String str_input = Parser.parseVariableFromCommandLine("", mockScanner);
		assertFalse(Parser.isDouble(str_input));
	}

	@Test
	public void ParamsWithinBoundary() {	
		assertFalse(Parser.withinBoundary(5.1));
		assertFalse(Parser.withinBoundary(-0.1));
		assertTrue(Parser.withinBoundary(0.0));	
		assertTrue(Parser.withinBoundary(.1));	
	}
	
	@Test(expected = IOException.class)
	public void getNextLineUnintitialisedBufferedReader() throws IOException {
		Parser.initBuffRead("WRONGFILENAME");
		Parser.getNextLine();
	}
	
	@Test
	public void getNextLineIntitialisedBufferedReader() throws IOException {
		Parser.initBuffRead("sample.dat");
		assertNotNull(Parser.getNextLine());
		assertTrue(Parser.getNextLine().getClass().isArray());
	}
	
	@Test
	public void populateLandscapeGridfromFileProperFile() {
		Landscape landscape = new Landscape();
		Parser.populateLandscapeGridfromFile(landscape,"sample.dat");
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
		Parser.populateLandscapeGridfromFile(landscape,"sample.dat");
		assertNotNull(landscape.currentGrid);	
		assertTrue(landscape.getLandscapeLength() > 0.0);
		assertTrue(landscape.getLandscapeWidth() > 0.0);
	}

}
