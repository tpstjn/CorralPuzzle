import java.util.Random;

public class CorralBoard {
	private int width;
	private int height;
	private int[][] board;
	boolean[][] visited;
	
	
	boolean enclosed = false;
	int R[] = {0, -1, 0, 1};
	int C[] = {1, 0, -1, 0};
	
	public CorralBoard(int w, int h) {
		visited = new boolean[width][height];
		width = w;
		height = h;
		board = new int[w][h];
	}

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
	
	public boolean isSolved() {
		boolean solution = false;
		for (int i = 0; i < width; i++) {
			for (int  j = 0; j < height; j++) {
				if(board[i][j] == 0 || board[i][j] == -1) {
					continue;
				}

				if (!validCell(i,j)) {
					return solution;
				}
			}
		}
		
		return solution;
	}
	
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
	
	private boolean isEnclosed() {
		boolean valid = false;
		boolean exitLoop = false;
		int r = 0;
		int c = 0;
		for (int i = 0; i < width; i++) {
			for (int  j = 0; j < height; j++) {
				visited[i][j] = true;
				if (board[i][j] != 0) {
					exitLoop = true;
					r = i;
					c = j;
					break;
				}
			}
			if (exitLoop) {
				break;
			}
		}
		
		
		
		return valid;
	}
	
	private void enclosed(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int xx = r + R[i];
			int yy = c + C[i];

			if (xx >=0 && xx < width && yy >= 0 && yy < height) {
				if (!visited[xx][yy] && board[xx][yy] == -1) {
					enclosed(xx, yy);
				}
			}
		}
	}
}
