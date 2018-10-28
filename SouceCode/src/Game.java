/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Game {
    //game should have the main that initializes, model,controller,view later on

    public Game() { }

    public static void main(String[] args) {
        Board board = new Board(5, 9, 20);

        for (int i =0; i<20;i++){
            
            board.runBoard();
            System.out.println();
        }
    }
}
