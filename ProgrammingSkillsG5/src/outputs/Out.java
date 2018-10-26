package outputs;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Random;
import java.util.UUID;

/**
 * 
 * @author Almas
 *
 */
public class Out {
	/**
	 * Method for printing average of H and P
	 * @param str
	 */
	public static void printAvgHandP(String str) {
		System.out.println(str);
	}
	public static void generatePlainPPMFile(int width, int height) throws Exception {
		
		File Directory = new File("./PPM_Files");
		if(!Directory.exists())Directory.mkdir();		
		String file = Directory+"/"+UUID.randomUUID().toString().toUpperCase()+".ppm";
		
		/*
		PrintWriter writer = new PrintWriter(file, "UTF-8");
		writer.println("Hello world");
		writer.close();
		*/
		/*
		try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));
            for(int i=0; i<width*height; i++){ 
                int r=new Random().nextInt(20); //System.out.println("red="r);
                int g=new Random().nextInt(20); //System.out.println("green="g);
                int b=new Random().nextInt(20); //System.out.println("blue="b);
                int avg=(r+g+b)/3; //greyscale pixels
                out.write(avg);
                out.write(avg);
                out.write(avg);
            }
            out.close();
        } catch (Exception ex) { System.err.println(ex); } 
   	*/
	}
	public static void printOutput(String str) {
		System.out.println(str);
	}
	
}
