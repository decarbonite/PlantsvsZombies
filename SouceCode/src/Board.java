import java.util.ArrayList;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 27 October, 2018
 */
public class Board {
    private static int boardRows;
    private static int boardColumns;

    private int money;

    private int zombiesToSpawn;

    private ArrayList<BoardRow> board;

    public Board(int numberOfRows, int numberOfColumns, int zombiesToSpawn) {
        money = 200;
        boardRows = numberOfRows;
        boardColumns = numberOfColumns;
        this.zombiesToSpawn = zombiesToSpawn;
        this.board = new ArrayList<>(boardRows);

        for (int i = 0; i < boardRows; i++) {
            board.add(new BoardRow(boardColumns));
        }
    }

    private void printBoard() {
        System.out.println("Player's Money: " + money);
        for (BoardRow br : board) {
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(br);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n");
    }

    private void moveZombies() {
        for (BoardRow br : board) {
            br.moveZombie();
        }
    }

    private void fightPlantZombie() {
        for (BoardRow br : board) {
            br.fightPvsZ();
        }
    }

    public void runBoard() {
        generateNewPlant();
        generateNewZombie();
        printBoard();
        fightPlantZombie();
        moveZombies();
    }


    private void generateNewZombie() {
        Random rand = new Random();

        if (zombiesToSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                int randRow = rand.nextInt(boardRows);
                if (!board.get(randRow).hasZombie(boardColumns - 1)) {
                    addZombie(randRow, boardColumns - 1, new Zombie("Zombie", 100, 40));
                    zombiesToSpawn--;
                }
            }
        }
    }

    private void generateNewPlant() {
        Random rand = new Random();

        if (money >= 50 && rand.nextInt(5) % 3 == 0) {
            int randRow = rand.nextInt(boardRows);
            int randCol = rand.nextInt(boardColumns);
            if (!board.get(randRow).hasZombie(boardColumns - 1)) {
                addPlant(randRow, randCol, new XYPlant("Plant", 100, 40));
                money -= 50;
            }
        }

    }

    public void addPlant(int x, int y, Plant plant) {
        if (plant != null) {
            board.get(x).addPlant(y, plant);
        }
    }

    //HOW TO ADD A ZOMBIE AT A SPECIFIC NODE IN BOARD WHEN BOARD IS OF TYPE BOARDROW
    public void addZombie(int x, int y, Zombie zombie) {
        if (zombie != null) {
            board.get(x).addZombie(y, zombie);
        }
    }
}
