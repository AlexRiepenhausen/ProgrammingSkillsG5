package world;

/**
 * This enum represent the grid square type Water(0), Land(1)
 * @author Team
 *
 */
public enum GridSquareType {
	Water(0), Land(1);
	
	private int numVal;

	/**
	 * This will return an enum Water or Land
	 * @param 0 for Water, 1 for Land
	 */
	GridSquareType(int numVal) {
        this.numVal = numVal;
    }

	/**
	 * To get the value representation of the enum
	 * @return 0 for Water, 1 for Land
	 */
    public int getNumVal() {
        return numVal;
    } 
    
    /**
     * A helper method that return an enum based on the passed integer value
     * @param 0 for Water, 1 for Land
     * @return GridSquareType object
     */
    public static GridSquareType setValue(int value) {
    	switch (value) {
    		case 0: return GridSquareType.Water;
    		case 1: return GridSquareType.Land;
    		default:
    			return null;
    	}
    }
    
    /**
     * A helper method that return an enum based on the passed string value
     * @param "0" for Water, "1" for Land
     * @return GridSquareType object
     */
    public static GridSquareType setValue(String strValue) {
    	int value = Integer.parseInt(strValue);
    	switch (value) {
    		case 0: return GridSquareType.Water;
    		case 1: return GridSquareType.Land;
    		default:
    			return null;
    	}
    }
}
