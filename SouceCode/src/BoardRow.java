import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 17 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 24 October, 2018
 *
 */
public class BoardRow {
    public static final int ROWS_ON_BOARD = 5;
    public static final int COLUMNS_ON_BOARD = 9;

    private static int nZombiesSpawn;
    private List<ArrayList<BoardNode>> board;

    public BoardRow(int nZombies) {
        this.nZombiesSpawn = nZombies;
        board = new ArrayList<>(ROWS_ON_BOARD);

        for(int i = 0; i < ROWS_ON_BOARD; i++){
            ArrayList<BoardNode> row = new ArrayList<>(COLUMNS_ON_BOARD);
            for(int j = 0; j < COLUMNS_ON_BOARD; j++){
                row.add(new BoardNode());
            }
            board.add(row);
        }
    }

    public void printBoard() {
        String str = "";
        for (int i = 0; i < ROWS_ON_BOARD; i++) {
            for (int j = 0; j < COLUMNS_ON_BOARD; j++) {
                if (board.get(i).get(j).hasZombie()) {
                    str = String.format("%15s", board.get(i).get(j).getZombie());
                } else if (board.get(i).get(j).hasPlant() && !board.get(i).get(j).hasZombie()) {
                    str = String.format("%15s", board.get(i).get(j).getPlant());
                } else {
                    //empty
                    str += String.format("%15s", "XX");
                }
            }
            str += "\n";
        }
        System.out.println(str);
    }

    /*public void paintTextGrid(){
        for (int i = 0; i < board.size() ; i++) {
            System.out.println(board.get(i).toString());
        }
    }*/

    private void moveZombie() {
        for (int i = 0; i < ROWS_ON_BOARD; i++) {
            for (int j = 0; j < COLUMNS_ON_BOARD - 1; j++) {
                BoardNode current = board.get(i).get(j);
                BoardNode next = board.get(i).get(j + 1);
                if (j == 0 && current.hasZombie() && !current.hasPlant()) {
                    System.out.println("\n\t\t***Zombies WON***\n");
                    System.exit(0);
                } else if (current.hasPlant() && current.hasZombie()) {
                    current.plantFightZombie();
                }
                if (next.hasZombie()) {
                    current.addZombie(next.destroyZombie());
                }
            }
        }
    }

    private void generateNewZombie() {
        Random rand = new Random();
        if (nZombiesSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                addZombie(rand.nextInt(5), 8, new Zombie("Zombie", 100, 10));
                nZombiesSpawn--;
            }
        }
    }

    public void addPlant(int x, int y, Plant plant) {
        board.get(x).get(y).addPlant(plant);
    }

    //HOW TO ADD A ZOMBIE AT A SPECIFIC NODE IN BOARD WHEN BOARD IS OF TYPE BOARDROW
    public void addZombie(int x, int y, Zombie zombie) {
        board.get(x).get(y).addZombie(zombie);
    }

    private void fightPvsZ(){
        for(int k = 0; k < board.size(); k++) {
            for (int i = 0; i < COLUMNS_ON_BOARD; i++) {
                BoardNode plantFind = board.get(k).get(i);
                if (plantFind.hasPlant()) {
                    for (int j = i; j < COLUMNS_ON_BOARD; j++) {
                        BoardNode zombieFind = board.get(k).get(j);
                        if (plantFind == zombieFind && zombieFind.hasZombie()) {
                            plantFind.plantFightZombie();
                            break;
                        } else if (plantFind != zombieFind && zombieFind.hasZombie()) {
                            zombieFind.addZombie(plantFind.plantFightZombie(zombieFind.destroyZombie()));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void runRow() {
        generateNewZombie();
        fightPvsZ();
        printBoard();
        moveZombie();
    }
}