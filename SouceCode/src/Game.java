import java.util.Map;
import javax.swing.*;

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
    private boolean storyMode;

    /**
     * Default constructor that start welcome screen
     */
    public Game() {
        this.storyMode = false;
        currentLevel = 0;
        new InitialScreenController(new InitialScreen(this));
    }

    /**
     * Generate game from the specified scope
     * @param scope Map structure with the zombie types and amounts
     */
    protected void generateGame(Map<String, Integer> scope) {

        if(scope != null){
            this.m = new Board(scope, 0, 200);
        } else {
            this.storyMode = true;
            ReadLevel rl = new ReadLevel();
            this.m = rl.readLevelFromXML(currentLevel, 0);

            if(m == null) {
                m = new Board(15, 0, 200);
            }
        }

        this.v = new View(playerName);
        this.c = new Controller(v, m);
        c.generateBoard();

        runGame();
    }

    /**
     * Run game with initial delay of 1s and 3s between steps
     */
    private void runGame() {
        Timer timer = new Timer(3000, e -> {
            if(c.gameEnded() == 1 && storyMode){
                currentLevel++;
                ReadLevel rl = new ReadLevel();
                m = rl.readLevelFromXML(currentLevel, m.score);
                v.getFrame().dispose();

                if(m == null) {
                    JOptionPane.showMessageDialog(v.getFrame(), "You Won!");
                    System.exit(0);
                }

                v = new View(playerName);
                c = new Controller(v, m);
                c.generateBoard();
            }

            c.updateBoard();
            v.repaint();
        });

        timer.setRepeats(true);
        timer.setCoalesce(true);
        // A little delay before zombies start to appear
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
