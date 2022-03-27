
public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = new int[3][3];
		board[0][0] = 4;
		board[0][1] = 3;
		board[0][2] = 3;
		board[1][0] = -1;
		board[1][1] = 0;
		board[1][2] = 0;
		board[2][0] = -1;
		board[2][1] = 0;
		board[2][2] = 0;
		
		CorralBoard b = new CorralBoard(board);

		System.out.println(b.toString());
		System.out.println(b.isSolved());
	}

}
