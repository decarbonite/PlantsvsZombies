import java.util.ArrayList;
import java.util.Random;

/**
 * Creates board that contains BoardRows based on BoardNodes
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 27 October, 2018
 */
public class Board {
    private static int boardRows;
    private static int boardColumns;
    protected static int score;
    protected static int money;
    private int zombiesToSpawn;
    private ArrayList<BoardRow> board;
    private View view;

    /**
     * Default constructor
     *
     * @param numberOfRows int number of rows
     * @param numberOfColumns int number of BoardNode instances in each row
     * @param zombiesToSpawn int number of zombies that would be randomly generated
     * @param score int initial score for the game (round)
     * @param money int initial amount of money
     */
    public Board(int numberOfRows, int numberOfColumns, int zombiesToSpawn, int score, int money) {
        boardRows = numberOfRows;
        boardColumns = numberOfColumns;
        this.score = score;
        this.money = money;
        this.zombiesToSpawn = zombiesToSpawn;
        this.board = new ArrayList<>(boardRows);

        for (int i = 0; i < boardRows; i++) {
            board.add(new BoardRow(boardColumns));
        }
    }

    /**
     * Move Zombies across board in each row
     */
    protected int[] getZombieLocation() {
        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if (View.getBtn()[i][j].getIcon().toString().equals(View.ZOMBIE_IMAGE)){
                    return new int[]{i,j};
                }
            }
        }
        return null;
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
     * Rise money if at least one of the rows contains MoneyFlower (if more, values added)
     */
    private void riseMoney() {
        for (BoardRow br : board){
            this.money = br.generateMoney(this.money);
        }
    }

    /**
     * Main method that runs the Zombie generation, simulates fight method, moves zombies across board and prints board.
     */
    public void runBoard() {
        riseMoney();
        fightPlantZombie();
        //moveZombies();
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
     * Randomly generates zombies' spawn location on the board
     */
    protected int[] generateZombieSpawn() {
        Random rand = new Random();

        if (zombiesToSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                int randRow = rand.nextInt(boardRows);
                if (!board.get(randRow).hasZombie(boardColumns - 1)) {
                    //addZombie(randRow, boardColumns - 1, new Zombie("Zombie", 100, 10, 10, View.ZOMBIE_IMAGE));
                    zombiesToSpawn--;
                    return new int[]{(boardColumns - 1), randRow};
                }
            }
        }
        return null;
    }

    /**
     * Adds Plant object on a specific coordinate
     * @param x int column index
     * @param y int row index
     * @param plant Plant object
     * @return boolean true if plant has been added; false - otherwise
     */
    public boolean addPlant(int x, int y, Plant plant) {
        if(money >= 50 && (x >= 0 && x < boardRows) && (y >= 0 && y < (boardColumns - 1))) {
            if (plant != null) {
                if(!board.get(x).hasPlant(y)) {
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
