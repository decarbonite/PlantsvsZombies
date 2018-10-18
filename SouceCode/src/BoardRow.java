import java.util.ArrayList;
import java.util.List;

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
        BoardNode previous;
        for(int i = 1; i < row.size(); i++){
            previous = row.get(i-1);
            previous.addZombie(row.get(i).destroyZombie());
            System.out.println("Zombie Moved from [ " + i + " ] -> [ "+ (i-1) +" ]");
        }
    }

    private void generateNewZombie() {
        row.get(8).addZombie(new Zombie("Stiven", 100));
    }

    public void runRow() {
        generateNewZombie();
        moveZombie();
    }
}
