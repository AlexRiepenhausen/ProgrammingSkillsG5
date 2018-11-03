package main.java.outputs;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Ppm {

	final int width, height;
	final int [][][] image;

	/**
	 * Constructor for the objects. You need to set the width/height of the image to create.
	 *
	 * @param width    width
	 * @param height   height
	 */
	public Ppm(int width, int height)
	{
		this.width = width;
		this.height = height;

		image = new int[width][height][3];
	}

	/**
	 * Sets a pixel. Values can be in range 0...1024. If you use values bigger than
	 * 255 then you need to set scanMaxValue to true in writeFile.
	 *
	 * @param x  x
	 * @param y  y
	 * @param r  r
	 * @param g  g
	 * @param b  b
	 */
	public void setPixel(int x, int y, int r, int g, int b)
	{
		image[x][y][0] = r;
		image[x][y][1] = g;
		image[x][y][2] = b;
	}

	protected void writeLine(BufferedWriter out, String line) throws Exception
	{
		out.write(line, 0, line.length());
		out.newLine();
	}

	/**
	 * Writes a PPM-file to disk.
	 *
	 * @param fileName  File to write to.
	 * @param scanMaxValue  Set this to true if you use r/g/b values bigger than 255.
	 */
	public void writeFile(String fileName, boolean scanMaxValue) throws Exception
	{
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));

		writeLine(out, "P3");
		writeLine(out, "" + width + " " + height);

		if (scanMaxValue)
		{
			int maxVal = 0;

			for(int y=0; y<height; y++)
			{
				for(int x=0; x<width; x++)
				{
					maxVal = Math.max(maxVal, image[x][y][0]);
					maxVal = Math.max(maxVal, image[x][y][1]);
					maxVal = Math.max(maxVal, image[x][y][2]);
				}
			}

			writeLine(out, "" + maxVal);
		}
		else
		{
			writeLine(out, "255");
		}

		for(int y=0; y<height; y++)
		{
			for(int x=0; x<width; x++)
				writeLine(out, "" + image[x][y][0] + " " + image[x][y][1] + " " + image[x][y][2]);
		}

		out.close();
	}

}
