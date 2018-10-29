/**
 * Starting point of the Plant vs Zombie game
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Game {
    /**
     * Entering point of the game
     * @param args String[] - not used in this game
     */
    public static void main(String[] args) {
        Board board = new Board(5, 10, 20, 0, 200);

        board.addPlant(0,0, new XYPlant("Plant", 200, 15));
        board.addPlant(1,0, new XYPlant("Plant", 200, 15));
        board.addPlant(2,0, new XYPlant("Plant", 200, 15));
        board.addPlant(3,0, new XYPlant("Plant", 200, 15));
        board.addPlant(4,0, new XYPlant("Plant", 200, 15));

        board.printBoard();
        while(true) {
            boolean[] res = board.gameEnds();
            if(!res[0]) {
                System.out.println("Player's Money: " + board.money+ "\t\tScore: "+ board.score);
                board.generateNewPlant();
                board.runBoard();
            } else {
                System.out.println("Player's Money: " + board.money+ "\t\tScore: "+ board.score);
                board.printBoard();
                System.out.println(res[1] ? "\n\t\t***ZOMBIE WON***\n" : "\n\t\t***PLANTS WON***\n");
                return;
            }
        }
    }
}
