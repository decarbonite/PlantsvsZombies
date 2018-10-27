/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Game {
    //game should have the main that initializes, model,controller,view later on

    public Game() { }

    public static void main(String[] args) {
        BoardRow board = new BoardRow(20);

        board.addPlant(0,0, new XYPlant("Plant1", 100, 10));
        board.addPlant(1,0, new XYPlant("Plant2", 100, 10));
        board.addPlant(2,0, new XYPlant("Plant3", 100, 10));
        board.addPlant(3,0, new XYPlant("Plant4", 100, 10));
        board.addPlant(4,0, new XYPlant("Plant5", 100, 10));

        for (int i =0; i<200;i++){
            board.runRow();
            System.out.println();
        }
    }
}
