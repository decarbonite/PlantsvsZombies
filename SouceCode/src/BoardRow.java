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
            if(current.hasZombie() && !current.hasPlant()) {
                System.out.println("\t\t***Zombies WON***\n");
                System.exit(0);
            }
            if(next.hasZombie()) {
                current.addZombie(next.destroyZombie());
                System.out.println("Zombie "+current.getZombieName()+" Moved from [ " + (i+1) + " ] -> [ "+ i +" ]");
            }
        }
    }

    private void generateNewZombie() {
        Random rand = new Random();
        if(rand.nextInt(5) % 3 == 0) {
            row.get(8).addZombie(new Zombie("Stiven", 100));
        }
    }

    private void fightPlantVsZombie(){

    }

    public void runRow() {
        generateNewZombie();
        moveZombie();
    }
}
