package inputTests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.junit.jupiter.api.Test;

import input.Parser;

class ParserTest {

	@Test
	public void landscapeParametersShouldBeDouble() {
		
		//https://stackoverflow.com/questions/6415728/junit-testing-with-simulated-user-input
		ByteArrayInputStream in = new ByteArrayInputStream("1.0".getBytes());
		System.setIn(in);
		
		Parser.createLandscapeFromCommandLine();
		
	}

}
