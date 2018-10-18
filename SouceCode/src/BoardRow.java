import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 17 October, 2018
 */
public class BoardRow {

    private List<BoardNode> row;

    public BoardRow() {
        row = new ArrayList<BoardNode>(9);
        for(int i = 0; i < 9; i++) {
            row.add(new BoardNode());
        }
    }

    private void moveZombie() {
        for(int i = 0; i < row.size() - 1; i++){
            BoardNode current = row.get(i);
            BoardNode next = row.get(i+1);
            String printedLine = current.hasPlant() ? current.getPlantName() + "\t" : "false\t\t\t";
                   printedLine += current.hasZombie() ? current.getZombieName() + "\t" : "false\t\t\t";
            if(current.hasZombie() && !current.hasPlant()) {
                System.out.println("\n\t\t***Zombies WON***\n");
                System.exit(0);
            } else if(current.hasPlant() && current.hasZombie()) {
                current.plantFightZombie();
            }

            if(next.hasZombie()) {
                current.addZombie(next.destroyZombie());
                printedLine += "\tMoved from [ " + (i+1) + " ] -> [ "+ i +" ]";
            }

            System.out.println(printedLine);
        }
    }

    private void generateNewZombie() {
        Random rand = new Random();
        if(rand.nextInt(5) % 3 == 0) {
            row.get(8).addZombie(new Zombie("Stiven", 100, 10));
        }
    }

    public void addPlant(int index, Plant plant){
        row.get(index).addPlant(plant);
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
}
