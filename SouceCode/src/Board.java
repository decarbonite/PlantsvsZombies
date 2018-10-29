import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Creates board that contains BoardRows based on BoardNodes
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 27 October, 2018
 */
public class Board {
    protected static boolean GAME_ENDS = false;
    private static int BOARD_ROWS;
    private static int BOARD_COLUMNS;
    private static int zombiesToSpawn;
    private ArrayList<BoardRow> board;

    /**
     * Default constructor
     *
     * @param numberOfRows int number of rows
     * @param numberOfColumns int number of BoardNode instances in each row
     * @param zombiesToSpawn int number of zombies that would be randomly generated
     */
    public Board(int numberOfRows, int numberOfColumns, int zombiesToSpawn) {
        BOARD_ROWS = numberOfRows;
        BOARD_COLUMNS = numberOfColumns;
        this.zombiesToSpawn = zombiesToSpawn;
        this.board = new ArrayList<BoardRow>(BOARD_ROWS);

        for(int i = 0; i < BOARD_ROWS; i++) {
            board.add(new BoardRow(BOARD_COLUMNS));
        }
    }

    /**
     * Console print of the board
     */
    protected void printBoard() {
        for(BoardRow br : board){
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(br);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
    }

    /**
     * Move Zombies across board in each row
     */
    private void moveZombies(){
        for(BoardRow br : board){
            br.moveZombie();
        }
    }

    /**
     * Simulates fight between Zombies and Plants on each row
     */
    private void fightPlantZombie() {
        for(BoardRow br : board){
            br.fightPvsZ();
        }
    }

    /**
     * Main method that runs the Zombie generation, simulates fight method, moves zombies across board and prints board.
     */
    public void runBoard() {
        generateNewZombie();
        printBoard();
        fightPlantZombie();
        moveZombies();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check is the game ends
     * @return boolean[], boolean[0] = true if game is end; false otherwise. boolean[1] = true Zombie won; false if Plants won
     */
    public boolean[] gameEnds() {
        boolean[] res = new boolean[2];
        if(board.get(0).ZOMBIE_WON){
            res[0] = true; res[1] = true;
            return res;
        }

        boolean zombieOnRow = false;

        for(BoardRow br : board) {
            if(br.hasZombie() && !zombieOnRow) {
                zombieOnRow = true;
                continue;
            }
        }

        if(zombiesToSpawn <= 0) {
            res[0] = !zombieOnRow; res[1] = false;
            return res;
        } else {
            res[0] = false; res[1] = false;
            return res;
        }
    }

    /**
     * Randomly generates zombies on the board across rows starting on the right of the board
     */
    private void generateNewZombie() {
        Random rand = new Random();

        if (zombiesToSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                int randRow = rand.nextInt(BOARD_ROWS);
                if (!board.get(randRow).hasZombie(BOARD_COLUMNS - 1)) {
                    addZombie(randRow, BOARD_COLUMNS - 1, new Zombie("Zombie", 100, 40, 10));
                    zombiesToSpawn--;
                }
            }
        }
    }

    /**
     * Adds Plant object on a specific coordinate
     * @param x int row index
     * @param y int column index
     * @param plant Plant object
     */
    public void addPlant(int x, int y, Plant plant) {
        if (plant != null) {
            board.get(x).addPlant(y, plant);
        }
    }

    /**
     * Adds Zombie object on a specific coordinate
     * @param x int row index
     * @param y int column index
     * @param zombie Zombie object
     */
    public void addZombie(int x, int y, Zombie zombie) {
        if (zombie != null) {
            board.get(x).addZombie(y, zombie);
        }
    }
}
