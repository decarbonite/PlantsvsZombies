import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

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
//        for (int i = 0; i < 5; i++) {
            board.add(new BoardRow());
//        }
    }

    public void updateBoard() {
        int i = 1;
        for(BoardRow br: board) {
            System.out.println("\t\tROW [ "+ i +" ]");
            br.runRow();
            i++;
            System.out.println("\n**********************************************\n");
        }
    }

    public static void main(String[] args) {
        Game newGame = new Game();

        for(int i = 0; i < 20; i++) {
            System.out.println("\t\tRound ["+ (i+1) +"]");
            newGame.updateBoard();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
