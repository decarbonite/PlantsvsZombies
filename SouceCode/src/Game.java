/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Game {
    //game should have the main that initializes, model,controller,view later on

    public Game() { }

    public static void main(String[] args) {
        BoardRow board = new BoardRow(20);

        //board.initializeBoard();

        board.addPlant(0,0, new XYPlant("Plant 1", 100, 20));
        board.addPlant(1,0, new XYPlant("Plant 2", 100, 20));
        board.addPlant(2,0, new XYPlant("Plant 3", 100, 20));
        board.addPlant(3,0, new XYPlant("Plant 4", 100, 20));
        board.addPlant(4,0, new XYPlant("Plant 5", 100, 20));

        for (int i =0; i<200;i++){
            board.runRow();
            System.out.println();
        }
    }
}
