import java.util.concurrent.TimeUnit;

/**
 * Main class that initializes the model, view and controller
 *
 * @author Ahmed Romih
 * @version 16, Nov 2018
 */

// TODO Implement level change
public class Game{
    private int currentLevel;
    private View v;
    private Board m;
    private Controller c;

    public Game() {
        ReadLevel rl = new ReadLevel();
        currentLevel = 0;
        this.v = new View();
        this.m = rl.readLevelFromXML(currentLevel);

        if(m == null){
            this.m = new Board(15, 0, 200);
        }

        this.c = new Controller(v, m);
    }

    public void runGame() {
        c.generateBoard();
        // A little delay before zombies start to appear
        try{
            TimeUnit.SECONDS.sleep(1);
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

    public void loaadLevel() {

    }

    public static void main(String[] args) {
        new Game().runGame();
    }

}
