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

    /**
     * Default constructor that start welcome screen
     */
    public Game() {
        currentLevel = 0;
        new InitialScreenController(new InitialScreen(this));
    }

    /**
     * Generate game from the specified scope
     * @param scope Map structure with the zombie types and amounts
     */
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

    /**
     * Run game with initial delay of 1s and 3s between steps
     */
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

    /**
     * Setting player name
     * @param playerName String player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public static void main(String[] args) {
        new Game();
    }

}
