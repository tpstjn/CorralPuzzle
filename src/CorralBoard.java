import java.util.Arrays;
import java.util.Random;

public class CorralBoard {
	private int width;
	private int height;
	private int[][] board; //0 signifies outside the corral, -1 signifies inside the corral.
	private boolean[][] visited; //mirrors the dimensions of board
	private int R[] = {0, -1, 0, 1}; //stores row incremental changes
	private int C[] = {1, 0, -1, 0}; //stores column incremental changes
	
	public CorralBoard(int w, int h) {
		width = w;
		height = h;
		visited = new boolean[width][height];
		board = new int[width][height];
	}
	
	public CorralBoard(int[][] temp) {
		width = temp.length;
		height = temp[0].length;
		board = temp;
		visited = new boolean[width][height];
	}

	/**
	 * Gives a 33% chance to fill each cell in the board with an integer between 2 and the maximum possible value for that board
	 * The chance will need to be changed
	 */
	public void fillBoard() {
		Random r = new Random();
		int chance;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				chance = r.nextInt(100) + 1;
				if (chance > 66) {
					board[i][j] = (r.nextInt((width + height) - 2) + 2);
				}
			}
		}
	}

	/**
	 * Returns board as a string in an ASCII grid
	 */
	public String toString() {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				b.append("| " + board[i][j] + " ");
				if(j == board[i].length - 1) {
					b.append("|");
				}
			}
			b.append("\n");
		}
		return b.toString();
	}
	
	/**
	 * resets the visited grid to all false
	 */
	public void resetVisited() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				visited[i][j] = false;
			}
		}
	}
	
	/**
	 * checks if the board is a valid solution
	 * @return true if it is a solution
	 */
	public boolean isSolved() {
		for (int i = 0; i < width; i++) {
			for (int  j = 0; j < height; j++) {
				if(board[i][j] == 0 || board[i][j] == -1) {
					continue;
				}

				if (!validCell(i,j)) {
					return false;
				}
			}
		}
		
		return isContained();
	}
	
	/**
	 * Helper method for isSolved()
	 * Checks to ensure that the correct amount of horizontally and vertically neighboring cells are -1;
	 * @param r row index of cell
	 * @param c column index of cell
	 * @return true if the cell is valid
	 */
	private boolean validCell(int r, int c) {
		int numNeighbors = 1;
		for (int i = 1; i < width - r; i++) {
			if (board[r + i][c] != 0) {
				numNeighbors++;
			}
			else {
				break;
			}
		}
		
		for (int i = 1; i < height - c; i++) {
			if (board[r][c + i] != 0) {
				numNeighbors++;
			}
			else {
				break;
			}
		}
		
		for (int i = r - 1; i > 0; i--) {
			if (board[r - i][c] != 0) {
				numNeighbors++;
			}
			else {
				break;
			}
		}
		
		for (int i = c; i > 0; i--) {
			if (board[r][c - i] != 0) {
				numNeighbors++;
			}
			else {
				break;
			}
		}
		
		if (numNeighbors == board[r][c]) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * checks to ensure that one continuous loop is maintained in the grid.
	 * @return true if there is only one continuous loop
	 */
	private boolean isContained() {
		int enclosedSpaces = 0;
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (board[i][j] != 0 && !visited[i][j]) {
					enclosed(i,j);
					
					enclosedSpaces++;
				}
			}
		}
		
		if (enclosedSpaces == 1) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Recursive method for isContained()
	 * checks if a cell is visited or a barrier, and then recursively checks all of that cell's neighbors
	 * @param r row index of cell
	 * @param c column index of cell
	 */
	private void enclosed(int r, int c) {
		
		visited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int xx = r + R[i];
			int yy = c + C[i];

			if (xx >=0 && xx < width && yy >= 0 && yy < height) {
				if (!visited[xx][yy] && board[xx][yy] != 0) {
					enclosed(xx, yy);
				}
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getIndex(int r, int c) {
		return board[r][c];
	}

	public int[][] getBoard() {
		return board;
	}

	public void setIndex(int r, int c, int value) {
		board[r][c] = value;
	}

	@Override
	public boolean equals(Object other) {

		if(other == this) {
			return true;
		}

		if(!(other instanceof CorralBoard)) {
			return false;
		}

		CorralBoard cb = (CorralBoard) other;

		return Arrays.deepEquals(cb.board, this.board);
	}
}
