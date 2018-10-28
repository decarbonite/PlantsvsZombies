import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 24 October, 2018
 */
public class BoardRow {
    private static int COLUMNS_ON_BOARD = 9;
    private ArrayList<BoardNode> nodes;

    public BoardRow(int numberOfColumns) {
        this.COLUMNS_ON_BOARD = numberOfColumns;
        nodes = new ArrayList<>(COLUMNS_ON_BOARD);

        for(int i = 0; i < COLUMNS_ON_BOARD; i++) {
            nodes.add(new BoardNode());
        }
    }

    public String toString() {
        String str = "";
        for(BoardNode bn : nodes){
            if(bn.hasZombie() && !bn.hasPlant()){
                str += String.format("| %15s |", bn.getZombie());
            } else if(bn.hasPlant() && !bn.hasZombie()) {
                str += String.format("| %15s |", bn.getPlant());
            } else if(bn.hasZombie() && bn.hasPlant()) {
                str += String.format("| %15s |", "Z("+ bn.getZombie().getHealth() +") vs P("+ bn.getPlant().getHealth() +")");
            } else {
                str += String.format("| %15s |", "");
            }
        }
        return str;
    }


    protected void moveZombie() {
        for (int i = 0; i < COLUMNS_ON_BOARD - 1; i++) {
            BoardNode current = nodes.get(i);
            BoardNode next = nodes.get(i + 1);

            if(current.hasZombie() && current.hasPlant()){
                continue;
            }

            if (i == 0 && current.hasZombie() && !current.hasPlant()) {
                System.out.println("\n\t\t***Zombies WON***\n");
                System.exit(0);
            }

            if (next.hasZombie() && !current.hasZombie() && !next.hasPlant()) {
                current.addZombie(next.destroyZombie());
            }
        }
    }

    protected void addZombie(int index, Zombie newZombie) {
        if(newZombie != null) {
            nodes.get(index).addZombie(newZombie);
        }
    }

    protected void addPlant(int index, Plant newPlant) {
        if(newPlant != null) {
            nodes.get(index).addPlant(newPlant);
        }
    }

    protected boolean hasZombie(int index) {
        if(index >= 0){
            return nodes.get(index).hasZombie();
        }
        return false;
    }

    protected boolean hasPlant(int index) {
        if(index >= 0){
            nodes.get(index).hasPlant();
        }
        return false;
    }

    protected void fightPvsZ() {
        for (int i = 0; i < COLUMNS_ON_BOARD; i++) {
            BoardNode plantFind = nodes.get(i);
            if (plantFind.hasPlant()) {
                for (int j = i; j < COLUMNS_ON_BOARD; j++) {
                    BoardNode zombieFind = nodes.get(j);
                    if (plantFind == zombieFind && plantFind.hasZombie()) {
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