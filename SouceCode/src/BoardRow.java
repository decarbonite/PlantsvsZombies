import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 17 October, 2018
 */
public class BoardRow {

    public List<BoardNode> row;
    private List<BoardRow> board;

    public BoardRow() {
        row = new ArrayList<>(9);
        board = new ArrayList<>(5);
        for(int i = 0; i < 9; i++) {
            row.add(new BoardNode());
        }
    }

    public void initializeBoard(){
        for(int i = 0; i < 5; i++) {
            board.add(new BoardRow());
        }
    }

    public String toString(){
        String str = "";
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i).hasZombie()) {
                str += row.get(i).getZombieName() + "\t";
            }else if(row.get(i).hasPlant() && !row.get(i).hasZombie()){
                str += row.get(i).getPlantName() + "\t";
            }else {
                //empty
                str += "XX\t";
            }
        }
        return str;
    }

    public void paintTextGrid(){
        for (int i = 0; i < board.size() ; i++) {
            System.out.println(board.get(i).toString());
        }
    }

    private void moveZombie() {
        //for(int j=0;j<5;j++){
        for(int i = 0; i < row.size() - 1; i++){
            BoardNode current = row.get(i);
            BoardNode next = row.get(i+1);
            if(current.hasZombie() && !current.hasPlant()) {
                System.out.println("\n\t\t***Zombies WON***\n");
                System.exit(0);
            } else if(current.hasPlant() && current.hasZombie()) {
                current.plantFightZombie();
            }
            if(next.hasZombie()) {
                current.addZombie(next.destroyZombie());
            }
        }
    }

    private void generateNewZombie() {
        Random rand = new Random();
        if(rand.nextInt(4) % 2 == 0) {
            //row.get(8).addZombie(new Zombie("Zombie", 100, 10), (int)rand.nextInt(5));
            addZombie(new Zombie("Zombie",100,10), 8, rand.nextInt(5));
        }
    }

    public void addPlant(int index, Plant plant){
        row.get(index).addPlant(plant);
    }
    public void addPlant(int x, int y, Plant plant){
        board.get(x).addPlant(y,plant);
    }

    //HOW TO ADD A ZOMBIE AT A SPECIFIC NODE IN BOARD WHEN BOARD IS OF TYPE BOARDROW
    public void addZombie(Zombie zombie, int x, int y) {
            board.get(y).addZombie(zombie, 8);
    }

    public void addZombie(Zombie zombie, int y) {
            row.get(y).addZombie(zombie);
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
        moveZombie();
    }

    public static void main(String[] args) {
        BoardRow board = new BoardRow();

        board.initializeBoard();

        board.addPlant(0,0, new XYPlant("Plant 1", 100, 70));
        board.addPlant(1,0, new XYPlant("Plant 2", 100, 70));
        board.addPlant(2,0, new XYPlant("Plant 3", 100, 70));
        board.addPlant(3,0, new XYPlant("Plant 4", 100, 70));
        board.addPlant(4,0, new XYPlant("Plant 5", 100, 70));

        for (int i =0; i<9;i++){
            board.runRow();
            board.paintTextGrid();
            System.out.println();
            System.out.println();

        }
    }
}
