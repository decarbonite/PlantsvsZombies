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

    private ArrayList<BoardNode> row;
    private ArrayList<BoardNode> row2;
    private ArrayList<BoardNode> row3;
    private ArrayList<BoardNode> row4;
    private ArrayList<BoardNode> row5;
    private List<ArrayList<BoardNode>> board;

    public BoardRow() {
        row = new ArrayList<>(9);
        row2 = new ArrayList<>(9);
        row3 = new ArrayList<>(9);
        row4 = new ArrayList<>(9);
        row5 = new ArrayList<>(9);
        board = new ArrayList<>(5);
        for (int i = 0; i < 9; i++) {
            row.add(new BoardNode());
            row2.add(new BoardNode());
            row3.add(new BoardNode());
            row4.add(new BoardNode());
            row5.add(new BoardNode());
        }
        board.add(row);
        board.add(row2);
        board.add(row3);
        board.add(row4);
        board.add(row5);
    }

    /*public void initializeBoard(){
        for(int i = 0; i < 5; i++) {
            board.add(new BoardNode());
        }
    }*/

    public void printBoard() {
        String str = "";
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < 9; j++) {
                if (board.get(i).get(j).hasZombie()) {
                    str += board.get(i).get(j).getZombieName() + "\t";
                } else if (board.get(i).get(j).hasPlant() && !board.get(i).get(j).hasZombie()) {
                    str += board.get(i).get(j).getPlantName() + "\t";
                } else {
                    //empty
                    str += "XX\t";
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
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < 8; j++) {
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
        if (rand.nextInt(4) % 2 == 0) {
            addZombie(rand.nextInt(5), 8, new Zombie("Zombie", 100, 10));
        }
    }

    public void addPlant(int x, int y, Plant plant) {
        board.get(x).get(y).addPlant(plant);
    }

    //HOW TO ADD A ZOMBIE AT A SPECIFIC NODE IN BOARD WHEN BOARD IS OF TYPE BOARDROW
    public void addZombie(int x, int y, Zombie zombie) {
        board.get(x).get(y).addZombie(zombie);
    }

//    private void fightPvsZ(){
//        for(int i = 0; i < row.size() - 1; i++) {
//            BoardNode current = row.get(i);
//            if(current.hasPlant()) {
//                for(int j = i; j < row.size() - 1; j++) {
//                    BoardNode next = row.get(j);
//                    if(current == next && next.hasZombie()){
//                        current.plantFightZombie();
//                    }
//                }
//            }
//        }
//    }

    public void runRow() {
        generateNewZombie();
//        fightPvsZ();
        printBoard();
        moveZombie();
    }
}