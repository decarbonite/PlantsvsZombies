import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Creates board that contains BoardRows based on BoardNodes
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 09 November, 2018
 */
public class Board {

    private static int boardRows;
    private static int boardColumns;
    protected static int score;
    protected static int money;
    private int zombiesToSpawn;
    private int totalZombies;
    protected static ArrayList<BoardRow> board;

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
        totalZombies = zombiesToSpawn;
        this.board = new ArrayList<>(boardRows);

        for (int i = 0; i < boardRows; i++) {
            board.add(new BoardRow(boardColumns));
        }
    }

    /**
     * Move Zombies across board in each row
     */
    protected int[] getZombieLocation() {
        int[] location = new int[totalZombies * 2];

        for (int i = 0; i < location.length; i++) {
            location[i] = -1;
        }

        int y=0;

        for (int i = 0; i < boardRows; i++) {
            for (int j = 0; j < boardColumns; j++) {
                if (View.getBtn()[i][j].getIcon().toString().equals(View.ZOMBIE_IMAGE)){
                    location[y] = i;
                    location[y+1] = j;
                    y+=2;
                }
            }
        }
        return location;
    }

    /**
     * Checks if game ended
     */
    public boolean hasWon() {
        int[] arr = getZombieLocation();

        if (zombiesToSpawn == 0 && arr[0] == -1){
            return true;
        }
        return false;
    }

    public boolean hasLost(){
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
     * Rise money if at least one of the rows contains MoneyFlower (if more, values added)
     */
    private void riseMoney() {
        for (BoardRow br : board){
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
        riseMoney();
        //gameEnded();
        fightPlantZombie();
        moveZombies();
        generateZombieSpawn();
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
                    addZombie(randRow, boardColumns - 1, new Zombie("Zombie", 10, 10, 10, View.ZOMBIE_IMAGE));
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

    //some getters used for testing

    public static int getBoardRows() {
        return boardRows;
    }

    public static int getBoardColumns() {
        return boardColumns;
    }

    public static int getScore() {
        return score;
    }

    public static int getMoney() {
        return money;
    }

    public int getZombiesToSpawn() {
        return zombiesToSpawn;
    }

    public int getTotalZombies() {
        return totalZombies;
    }

    public static ArrayList<BoardRow> getBoard() {
        return board;
    }
}
