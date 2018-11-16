import java.util.ArrayList;
import java.util.Random;

/**
 * Creates board that contains BoardRows based on BoardNodes
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 16 November, 2018
 */
public class Board {

    protected static int score;
    protected static int money;
    private int zombiesToSpawn;
    private int totalZombies;
    protected static ArrayList<BoardRow> board;

    /**
     * Default constructor Initializes model for 5x9 board
     *
     * @param zombiesToSpawn int number of zombies that would be randomly generated
     * @param score          int initial score for the game (round)
     * @param money          int initial amount of money
     */
    public Board(int zombiesToSpawn, int score, int money) {
        this.score = score;
        this.money = money;
        this.zombiesToSpawn = zombiesToSpawn;
        this.totalZombies = zombiesToSpawn;
        this.board = new ArrayList<>(View.BOARD_ROWS);

        for (int i = 0; i < View.BOARD_ROWS; i++) {
            board.add(new BoardRow());
        }
    }

    /**
     * Gets all zombies locations on the board
     */
    protected int[] getZombieLocation() {
        int[] location = new int[totalZombies * 2];

        for (int i = 0; i < location.length; i++) {
            location[i] = -1;
        }

        int y = 0;

        for (int i = 0; i < View.BOARD_ROWS; i++) {
            for (int j = 0; j < View.BOARD_COLS; j++) {
                if (board.get(i).hasZombie(j)){
                    location[y] = i;
                    location[y + 1] = j;
                    y += 2;
                }
            }
        }
        return location;
    }

    /**
     * Checks if player won the game
     * @return returns a boolean, true for win
     *
     */
    public boolean hasWon() {
        int[] arr = getZombieLocation();

        if (zombiesToSpawn == 0 && arr[0] == -1){
            return true;
        }
        return false;
    }

    /**
     * Checks if player lost the game
     * @return returns a boolean, true for loss
     */
    public boolean hasLost() {
        int[] arr = getZombieLocation();
        for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Simulates fight between Zombies and Plants on each row and if Plant kill Zombie add point to the player
     */
    private void fightPlantZombie() {
        for (BoardRow br : board) {
            this.score = br.fightPvsZ(this.score);
        }
    }

    /**
     * Increment money if at least one of the rows contains MoneyFlower (if more, values added)
     */
    private void incrementMoney() {
        for (BoardRow br : board) {
            this.money = br.generateMoney(this.money);
        }
    }

    /**
     * Move Zombies across board in each row
     */
    private void moveZombies() {
        for (BoardRow br : board) {
            br.moveZombie();
        }
    }

    /**
     * Main method that runs the Zombie generation, simulates fight method, moves zombies across board and prints board.
     */
    public void runBoard() {
        incrementMoney();
        fightPlantZombie();
        moveZombies();
        generateZombieSpawn();
    }

    /**
     * Randomly generates zombies' spawn location on the board
     */
    protected int[] generateZombieSpawn() {
        Random rand = new Random();
        if (zombiesToSpawn > 0) {
            if (rand.nextInt(2) == 0) {     //  50/50 chance to spawn a zombie
                int randRow = rand.nextInt(View.BOARD_ROWS);
                if (!board.get(randRow).hasZombie(View.BOARD_COLS - 1)) {
                    addZombie(randRow, View.BOARD_COLS - 1, new Zombie("Zombie", 10, 10, 10, View.ZOMBIE_IMAGE));
                    zombiesToSpawn--;
                    return new int[]{(View.BOARD_COLS - 1), randRow};
                }
            }
        }
        return null;
    }

    /**
     * Adds Plant object on a specific coordinate
     *
     * @param x     int column index
     * @param y     int row index
     * @param plant Plant object
     * @return boolean true if plant has been added; false - otherwise
     */
    public boolean addPlant(int x, int y, Plant plant) {
        if (money >= 50 && (x >= 0 && x < View.BOARD_COLS) && (y >= 0 && y < (View.BOARD_COLS - 1))) {
            if (plant != null) {
                if (!board.get(x).hasPlant(y)) {
                    board.get(x).addPlant(y, plant);
                    money -= 50;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds Zombie object on a specific coordinate
     *
     * @param x      int row index
     * @param y      int column index
     * @param zombie Zombie object
     */
    public void addZombie(int x, int y, Zombie zombie) {
        if (zombie != null) {
            board.get(x).addZombie(y, zombie);
        }
    }

    //some getters used for testing

    public static int getMoney() {
        return money;
    }

    public static ArrayList<BoardRow> getBoard() {
        return board;
    }
}
