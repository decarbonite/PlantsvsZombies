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
        for (int i = 0; i < 5; i++) {
            board.add(new BoardRow(10));
        }
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

    public void addPlant(int x, int y, Plant plant){
        board.get(x).addPlant(y, plant);
    }

    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.addPlant(0,0, new XYPlant("Plant 1", 100, 20));
        newGame.addPlant(1,0, new XYPlant("Plant 2", 100, 70));
        newGame.addPlant(2,0, new XYPlant("Plant 3", 100, 70));
        newGame.addPlant(3,0, new XYPlant("Plant 4", 100, 70));
        newGame.addPlant(4,0, new XYPlant("Plant 5", 100, 70));

        for(int i = 0; i < 150; i++) {
            System.out.println("\t\tRound ["+ (i+1) +"]");
            newGame.updateBoard();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
