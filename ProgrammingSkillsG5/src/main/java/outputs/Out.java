package main.java.outputs;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.UUID;

import main.java.world.Landscape;

/**
 * 
 * @author Almas
 *
 */
public class Out {
	
	protected static void writeLine(BufferedWriter out, String line) throws Exception
	{
		out.write(line, 0, line.length());
		out.newLine();
	}	
	protected static int[][][] initImage(Landscape land) {
		
	    int length = land.getLandscapeLength(); //width
		int width = land.getLandscapeWidth(); //height 
		int [][][] image = new int[length][width][3];
	
		for(int i=0; i<length; i++)
			for(int j=0; j<width; j++)
			{ 
				if (!land.currentGrid[i][j].isLand()) {
					image[i][j][0] = 0;
					image[i][j][1] = 0;
					image[i][j][2] = 255;
				}
				else {				
					int tempGreen = (int)((land.currentGrid[i][j].hares/5)*255);
					int tempRed = (int)((land.currentGrid[i][j].pumas/5)*255);
					image[i][j][0] = tempRed>255?255:tempRed;
					image[i][j][1] = tempGreen>255?255:tempGreen;
					image[i][j][2] = 0;
				}
			}
		return image;
	}
	
	private static String fileNameNumberFormat(int i) {
		
		String name = Integer.toString(i);
		while(name.length() < 5) {
			name = "0" + name;
		}
		
		return name;
		
	}
	
	public static void generatePlainPPMFile(Landscape landscape, int i) throws Exception {
				
		int[][][]image = initImage(landscape);
		int length = image.length;
		int width = image[0].length;
		
		File Directory = new File("./PPM_Files");
		if(!Directory.exists())Directory.mkdir();		
		String file = Directory+"/"+fileNameNumberFormat(i)+".ppm";
		
		BufferedWriter out = new BufferedWriter(new FileWriter(file));

		writeLine(out, "P3");
		writeLine(out, "" + length + " " + width);
		writeLine(out, "255");
		
		for(int y=0; y<width; y++)
		{
			for(int x=0; x<length; x++)
				writeLine(out, "" + image[x][y][0] + " " + image[x][y][1] + " " + image[x][y][2]);
		}

		out.close();
	}
	public static void createTempFile(int length, int width) throws Exception {
		
		PrintWriter out = new PrintWriter(new File("sample"+length+"x"+width+".dat"));
		out.println(width + " " +length);
		
		for(int i=0; i<length; i++) {			
			for(int j=0; j<width; j++) {
				if(j<i)
					out.print("1 ");
				else 
					out.print("0 ");	
			}	
			out.println();
		}	
		out.flush();
		out.close();
	}
	public static void printOutput(String str) {
		System.out.println(str);
	}
}
