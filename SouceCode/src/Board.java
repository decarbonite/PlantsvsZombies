import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

/**
 * Generates board that contains BoardRows based on BoardNodes
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 16 November, 2018
 */
public class Board implements Serializable{
    Map<String, Integer> zombieScope = null;
    protected static int score;
    protected static int money;
    private static int zombiesToSpawn;
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
     * Default constructor with level builder functionality that initializes model for 5x9 board
     *
     * @param zombieScope Map where key is name of the zombie, value - number of zombies of this type to spawn
     * @param score          int initial score for the game (round)
     * @param money          int initial amount of money
     */
    public Board(Map<String, Integer> zombieScope, int score, int money) {
        this.score = score;
        this.money = money;
        this.zombieScope = zombieScope;

        int zombiesToSpawn = 0;
        for(String key : zombieScope.keySet().toArray(new String[0])) {
            int temp = Integer.valueOf(zombieScope.get(key));
            zombiesToSpawn += temp;
        }


        this.zombiesToSpawn = zombiesToSpawn;
        this.totalZombies = zombiesToSpawn;
        this.board = new ArrayList<>(View.BOARD_ROWS);

        for (int i = 0; i < View.BOARD_ROWS; i++) {
            board.add(new BoardRow());
        }
    }

    /**
     * Gets all zombies locations on the board.
     * Even indices of the array represent x location of zombies, odd are y; [1,2,5,4] means zombie at (1,2) and another at (5,4)
     *
     * @return int array of generated zombie locations
     */
    protected int[] getZombieLocation() {
        int[] location = new int[totalZombies * 2];

        for (int i = 0; i < location.length; i++) {
            location[i] = -1;
        }

        int y = 0;

        for (int i = 0; i < View.BOARD_ROWS; i++) {
            for (int j = 0; j < View.BOARD_COLS; j++) {
                if (!board.get(i).hasPlant(j) && board.get(i).hasZombie(j)) {
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
     *
     * @return returns a boolean, true for win
     */
    public boolean hasWon() {
        int[] arr = getZombieLocation();

        if (zombiesToSpawn == 0 && arr[0] == -1) {
            return true;
        }
        return false;
    }

    /**
     * Checks if player lost the game
     *
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
    protected void generateZombieSpawn() {

        Random rand = new Random();
        if (zombiesToSpawn > 0) {
            if (rand.nextInt(2) == 0) {     //  50/50 chance to spawn a zombie
                int randRow = rand.nextInt(View.BOARD_ROWS);
                if (!board.get(randRow).hasZombie(View.BOARD_COLS - 1)) {
                    if(!zombieScope.isEmpty()){
                        String[] keys = zombieScope.keySet().toArray(new String[0]);
                        for(String key : keys) {
                            if (rand.nextInt(2) == 0 && zombieScope.get(key) > 0) {
                                int multiplier = Integer.valueOf(String.valueOf(key.toCharArray()[key.length()-1]));
                                addZombie(randRow, View.BOARD_COLS - 1, new Zombie(key, multiplier * 100, multiplier * (1/2) * 10, multiplier * 10, new ImageIcon(this.getClass().getResource((View.ZOMBIE_IMAGE)))));
                                zombieScope.put(key, (zombieScope.get(key) - 1));
                                zombiesToSpawn--;
                                break;
                            }
                        }
                    } else {
                        if (rand.nextInt(2) == 0) {
                            addZombie(randRow, View.BOARD_COLS - 1, new Zombie("Zombie1", 100, 20, 10, new ImageIcon(this.getClass().getResource((View.ZOMBIE_IMAGE)))));
                        } else {
                            addZombie(randRow, View.BOARD_COLS - 1, new Zombie("Zombie2", 200, 15, 20, new ImageIcon(this.getClass().getResource((View.ZOMBIE2_IMAGE)))));
                        }
                        zombiesToSpawn--;
                    }
                }
            }
        }
    }

    /**
     * Adds Plant object on a specific coordinate
     *
     * @param x     int column index
     * @param y     int row index
     * @param plant Plant object
     * @return Plant returns the plant if it was added, null otherwise
     */
    public Plant addPlant(int x, int y, Plant plant) {
        if (money >= 50 && (x >= 0 && x < View.BOARD_COLS) && (y >= 0 && y < (View.BOARD_COLS))) {
            if (plant != null) {
                if (!board.get(x).hasPlant(y)) {
                    board.get(x).addPlant(y, plant);
                    money -= 50;
                    return plant;
                }
            }
        }
        return null;
    }

    /**
     * Adds Zombie object on a specific coordinate
     *
     * @param x      int row index
     * @param y      int column index
     * @param zombie Zombie object
     * @return boolean true if zombie added; false - otherwise
     */
    public boolean addZombie(int x, int y, Zombie zombie) {
        if (zombie != null && x >= 0 && x < 5 && y == 8) {
            board.get(x).addZombie(y, zombie);
            return true;
        }
        return false;
    }

    /**
     * Saves current state of the game
     */
    public void saveGame() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Save.txt"));
            out.writeObject(board);
            out.writeObject(Board.getMoney());
            out.writeObject(Board.score);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads last saved instance of the game
     */
    public void loadGame() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Save.txt"));
            board = (ArrayList<BoardRow>) in.readObject();
            money = (int) in.readObject();
            score = (int) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return amount of money player currently have
     *
     * @return int amount of money
     */
    public static int getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the player gets
     *
     * @param money amount of money to set
     */
    public static void setMoney(int money) {
        Board.money = money;
    }

    /**
     * Return whole board rows generated for a particular board
     *
     * @return ArrayList of BoardRows objects
     */
    public static ArrayList<BoardRow> getBoard() {
        return board;
    }
}
