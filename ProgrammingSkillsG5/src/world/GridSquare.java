package world;

public class GridSquare {
	
	/** The Grid type Water(0) or Land(1) */
	GridSquareType gridSquareType;
	
	/** The density of hares (H) */
	int hares;
	
	/** The density of pumas (P) */
	int pumas;
	
	/**
	 * Generate a Grid Square object and if the square is Land 
	 * the densities of H and P will be randomly initialized in 
	 * the range 0 to 5 
	 * @param gridSquareType The type of the grid square Land(1) or Water(0)
	 */
	public GridSquare(GridSquareType gridSquareType) {
		this.gridSquareType = gridSquareType;
		if (gridSquareType == GridSquareType.Land) {
			this.hares = (int) Math.round(Math.random() * 5);
			this.pumas = (int) Math.round(Math.random() * 5);
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
}

