package nineTileAStarSolution;

import java.util.Arrays;

public class NineTile {
    
	private int[][] nineTile;
    private int blankX;
    private int blankY;
    
    public NineTile(int[][] nineTile) {
    	this.nineTile = new int[3][3];
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			if(nineTile[i][j] == 0) {
    				blankX = j;
    				blankY = i;
    			}
    			this.nineTile[i][j] = nineTile[i][j];
    		}
    	}
    }
    
    public int[][] getNineTile() {
    	return nineTile;
    }
    
    public boolean isGoalState() {
    	boolean isGoal = true;
    	int current = 1;
    	for(int i = 0; i < 3 && isGoal; i++) {
    		for(int j = 0; j < 3 && isGoal; j++) {
    			if(nineTile[i][j] != current) {
    				isGoal = false;
    			} else {
    				current = (current + 1) % 9;
    			}
    		}
    	}
    	return isGoal;
    }
    
    public int findManhattanDistance() {
    	int manhattanDistance = 0;
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			int originalX = 0, originalY = 0;
    			if(nineTile[i][j] == 0) {
    				originalY = 2;
    				originalX = 2;
    			} else {
    				originalY = (nineTile[i][j] - 1) / 3;
    				originalX = (nineTile[i][j] - 1) % 3;
    			}
    			manhattanDistance += Math.abs(j - originalX) + Math.abs(i - originalY);
    		}
    	}
    	return manhattanDistance;
    }
    
    public boolean moveBlankTop() {
    	boolean validMove = true;
    	if(blankY > 0) {
    		nineTile[blankY][blankX] = nineTile[blankY - 1][blankX];
    		nineTile[blankY - 1][blankX] = 0;
    		blankY--;
    	} else {
    		validMove = false;
    	}
    	return validMove;
    }
    
    public boolean moveBlankBottom() {
    	boolean validMove = true;
    	if(blankY < 2) {
    		nineTile[blankY][blankX] = nineTile[blankY + 1][blankX];
    		nineTile[blankY + 1][blankX] = 0;
    		blankY++;
    	} else {
    		validMove = false;
    	}
    	return validMove;
    }
    
    public boolean moveBlankLeft() {
    	boolean validMove = true;
    	if(blankX > 0) {
    		nineTile[blankY][blankX] = nineTile[blankY][blankX - 1];
    		nineTile[blankY][blankX - 1] = 0;
    		blankX--;
    	} else {
    		validMove = false;
    	}
    	return validMove;
    }
    
    public boolean moveBlankRight() {
    	boolean validMove = true;
    	if(blankX < 2) {
    		nineTile[blankY][blankX] = nineTile[blankY][blankX + 1];
    		nineTile[blankY][blankX + 1] = 0;
    		blankX++;
    	} else {
    		validMove = false;
    	}
    	return validMove;
    }
    
    public void printNineTile() {
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			System.out.print(nineTile[i][j] + " ");
    		}
    		System.out.println();
    	}
    }
    
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(nineTile);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NineTile other = (NineTile) obj;
		if (!Arrays.deepEquals(nineTile, other.nineTile))
			return false;
		return true;
	}

	public boolean isSolvable() {
    	boolean[] found = new boolean[9];
    	int inversions = 0;
    	boolean retVal;
    	for(int i = 0; i < 9; i++) {
    		found[i] = false;
    	}
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			if(nineTile[i][j] != 0) {
    				for(int k = 1; k < nineTile[i][j]; k++) {
    					if(!found[k]) {
    						inversions++;
    					}
    				}
    			}
    			found[nineTile[i][j]] = true;
    		}
    	}
    	if(inversions % 2 == 0) {
    		retVal = true;
    	} else {
    		retVal = false;
    	}
    	return retVal;
    }
	
	public int getBlankX() {
		return blankX;
	}
	
	public int getBlankY() {
		return blankY;
	}
}
