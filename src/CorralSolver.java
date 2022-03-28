import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CorralSolver {
    static boolean solved = false;
    public static void main (String [] args) {
        int[][] board = new int[3][3];
        board[0][0] = 4;
        board[0][1] = 3;
        board[0][2] = 3;
        board[1][0] = 0;
        board[1][1] = 0;
        board[1][2] = 0;
        board[2][0] = 0;
        board[2][1] = 0;
        board[2][2] = 0;

        CorralBoard b = new CorralBoard(board);

        System.out.println(bfs(b));

//    if (dfs(b) == null) {
//      System.out.println("no solution");
//    }
//    else {
//      System.out.println(b);
//    }
    }

    public static CorralBoard dfs(CorralBoard b) {
        for (int i = 0; i < b.getWidth(); i++) {
            for (int j = 0; j < b.getHeight(); j++) {
                if (b.getIndex(i, j) == 0 && !solved) {
                    b.setIndex(i, j, -1);
                    System.out.println(b.toString());
                    if (b.isSolved()) {
                        solved = true;
                        return b;
                    }
                    dfs(b);
                }
            }
        }

        return null;
    }

    public static CorralBoard bfs(CorralBoard b) {
        Queue<CorralBoard> boards = new LinkedList<>();
        ArrayList<CorralBoard> explored = new ArrayList<>();

        boards.add(b);

        if(b.isSolved()) {
            return b;
        }

        while(!boards.isEmpty()) {
            CorralBoard cb = boards.poll();

            for(int i = 0; i < b.getWidth(); i++) {
                for(int j = 0; j < b.getHeight(); j++) {
                    CorralBoard temp = new CorralBoard(cb.getBoard());

                    System.out.print((temp.getIndex(i, j) == 0) + " : ");
                    if(temp.getIndex(i, j) == 0) {
                        temp.setIndex(i, j, -1);
                    }

                    System.out.println(temp.equals(cb));
                    if(!explored.contains(temp)) {
                        System.out.println("It ajfbs");
                        if(temp.isSolved()) return temp;
                        else {
//                            if(temp.getIndex(i, j) == -1) temp.setIndex(i, j, 0);
                            boards.add(temp);
                        }
                    }
                    explored.add(cb);
                }
            }
        }
        return null;
    }
}
