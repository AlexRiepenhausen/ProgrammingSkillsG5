package world;

public enum GridSquareType {
	Water(0), Land(1);
	
	private int numVal;

	GridSquareType(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    } 
    
    public static GridSquareType setValue(int value) {
    	switch (value) {
    		case 0: return GridSquareType.Water;
    		case 1: return GridSquareType.Land;
    		default:
    			return null;
    	}
    }
    
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
