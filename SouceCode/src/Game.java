import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.Timer;

/**
 * Main class that initializes the model, view and controller
 *
 * @author Ahmed Romih
 * @version 16, Nov 2018
 */

public class Game{
    private String playerName;
    private int currentLevel;
    private View v;
    private Board m;
    private Controller c;

    public Game() {
        currentLevel = 0;
        new InitialScreenController(new InitialScreen(this));
    }

    public void generateGame(Map<String, Integer> scope) {

        if(scope != null){
            this.m = new Board(scope, 0, 200);
        } else {
            ReadLevel rl = new ReadLevel();
            this.m = rl.readLevelFromXML(currentLevel);

            if(m == null) {
                m = new Board(15, 0, 200);
            }
        }

        this.v = new View();
        this.c = new Controller(v, m);
        c.generateBoard();

        runGame();
    }

    public void runGame() {
        // A little delay before zombies start to appear
        Timer timer = new Timer(3000, e -> {
            c.updateBoard();
            c.gameEnded();
            v.repaint();
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        timer.setInitialDelay(1000);
        timer.start();
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public static void main(String[] args) {
        new Game();
    }

}
