import java.util.ArrayList;

/**
 *
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Game {
    private GameRound gameRound;
    private ArrayList<BoardRow> board;

    public Game() {
        board = new ArrayList<BoardRow>(5);
        for (int i = 0; i < 5; i++) {
            board.add(new BoardRow());
        }
        System.out.println(board.size());
    }

    public void updateBoard() {
        int i = 1;
        for(BoardRow br: board) {
            br.runRow();
            System.out.println("\t\tROW [ "+ i +" ]\n");
            i++;
        }
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.updateBoard();
    }
}
