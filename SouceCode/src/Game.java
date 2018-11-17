import java.util.concurrent.TimeUnit;

/**
 * Main class that initializes the model, view and controller
 *
 * @author Ahmed Romih
 * @version 16, Nov 2018
 */

public class Game{

    public static void main(String[] args) {
        Controller c = new Controller(new View(), new Board(10, 0, 200));
        c.generateBoard();

        try{
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        while (true) {
            try {
                c.updateBoard();
                c.gameEnded();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
