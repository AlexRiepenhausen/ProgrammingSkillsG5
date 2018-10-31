package main.java.world;

/**
 * 
 * @author hassan
 *
 */
public class GridSquare {
	
	/** The Grid type Water(0) or Land(1) */
	GridSquareType gridSquareType;
	
	/** The density of hares (H) */
	public double hares;
	
	/** The density of pumas (P) */
	public double pumas;
	
	/**
	 * Generate a Grid Square object and if the square is Land 
	 * the densities of H and P will be randomly initialized in 
	 * the range 0.0 to 5.0 
	 * @param gridSquareType The type of the grid square Land(1) or Water(0)
	 */
	public GridSquare(GridSquareType gridSquareType) {
		this.gridSquareType = gridSquareType;
		if (gridSquareType == GridSquareType.Land) {
			this.hares = Math.random() * 5;
			this.pumas = Math.random() * 5;
		}			
	}
	
	/**
	 * A helper function that create a GridSquare object from integer value
	 * @param gridType Water = 0, Land = 1
	 * @return GridSquare new object
	 */
	static public GridSquare generateGridSquareFromInt(int gridType) {
		return new GridSquare(GridSquareType.setValue(gridType));
	}
	
	/**
	 * A helper function that create a GridSquare object from string value
	 * @param gridType Water = "0", Land = "1"
	 * @return GridSquare new object
	 */
	static public GridSquare generateGridSquareFromStr(String gridType) {
		return new GridSquare(GridSquareType.setValue(gridType));
	}
	
	/**
	 * A helper function that returns the type of the current GridSquare object
	 * @return GridSquareType - returns either Water(0) or Land(1)
	 */
	public GridSquareType getGridSquareType() {
		return this.gridSquareType;
	}
	
}

