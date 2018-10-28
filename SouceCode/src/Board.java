import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 27 October, 2018
 */
public class Board {
    private static int BOARD_ROWS;
    private static int BOARD_COLUMNS;

    private int zombiesToSpawn;

    private ArrayList<BoardRow> board;

    public Board(int numberOfRows, int numberOfColumns, int zombiesToSpawn) {
        BOARD_ROWS = numberOfRows;
        BOARD_COLUMNS = numberOfColumns;
        this.zombiesToSpawn = zombiesToSpawn;
        this.board = new ArrayList<BoardRow>(BOARD_ROWS);

        for(int i = 0; i < BOARD_ROWS; i++) {
            board.add(new BoardRow(BOARD_COLUMNS));
        }
    }

    private void printBoard() {
        for(BoardRow br : board){
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println(br);
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\n");
    }

    private void moveZombies(){
        for(BoardRow br : board){
            br.moveZombie();
        }
    }

    private void fightPlantZombie() {
        for(BoardRow br : board){
            br.fightPvsZ();
        }
    }

    public void runBoard() {
        generateNewZombie();
        printBoard();
        fightPlantZombie();
        moveZombies();
    }


    private void generateNewZombie() {
        Random rand = new Random();

        if (zombiesToSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                int randRow = rand.nextInt(BOARD_ROWS);
                if (!board.get(randRow).hasZombie(BOARD_COLUMNS - 1)) {
                    addZombie(randRow, BOARD_COLUMNS - 1, new Zombie("Zombie", 100, 40));
                    zombiesToSpawn--;
                }
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
