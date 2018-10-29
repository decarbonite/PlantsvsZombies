import java.util.ArrayList;

/**
 * Holds instances of the BoardNodes and creates virtual row on the board.
 * Simulates moving and fighting between Plants and Zombies in current row.
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 24 October, 2018
 */
public class BoardRow {
    protected static boolean ZOMBIE_WON = false;
    private static int COLUMNS_ON_BOARD = 9;
    private ArrayList<BoardNode> nodes;

    /**
     * Default constructor
     * @param numberOfColumns int number of columns
     */
    public BoardRow(int numberOfColumns) {
        this.COLUMNS_ON_BOARD = numberOfColumns;
        nodes = new ArrayList<>(COLUMNS_ON_BOARD);

        for(int i = 0; i < COLUMNS_ON_BOARD; i++) {
            nodes.add(new BoardNode());
        }
    }

    /**
     * Returns String object of the row with plants and zombies on it (perforated)
     * @return String object of all NPCs of each node
     */
    @Override
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

    /**
     * Moves zombies across plane (based on the number of node(s))
     * Assign ZOMBIE_WON to true if zombie stays on the first node (end of the board) and this node does not contain plant.
     */
    protected void moveZombie() {
        for (int i = 0; i < COLUMNS_ON_BOARD - 1; i++) {
            BoardNode current = nodes.get(i);
            BoardNode next = nodes.get(i + 1);

            if(current.hasZombie() && current.hasPlant()){
                continue;
            }

            if (i == 0 && current.hasZombie() && !current.hasPlant()) {
                ZOMBIE_WON = true;
            }

            if (next.hasZombie() && !current.hasZombie() && !next.hasPlant()) {
                current.addZombie(next.destroyZombie());
            }
        }
    }

    /**
     * Adds Zombie object to the specific position
     * @param index int index where to add Zombie
     * @param newZombie Zombie object
     */
    protected void addZombie(int index, Zombie newZombie) {
        if(newZombie != null) {
            nodes.get(index).addZombie(newZombie);
        }
    }

    /**
     * Adds Plant object to the specific position
     * @param index int index where to add Plant
     * @param newPlant Plant object
     */
    protected void addPlant(int index, Plant newPlant) {
        if(newPlant != null) {
            nodes.get(index).addPlant(newPlant);
        }
    }

    /**
     * Check if the Zombie exists in a specific position
     * @param index position to check Zombie instance
     * @return true if zombie exists in the specified position; false - otherwise
     */
    protected boolean hasZombie(int index) {
        if(index >= 0){
            return nodes.get(index).hasZombie();
        }
        return false;
    }

    /**
     * Check if the Zombie exists in the current row
     * @return true if zombie exists in this row; false - otherwise
     */
    protected boolean hasZombie() {
        boolean containsZombie = false;
        for(BoardNode bn : nodes){
            if(bn.hasZombie() && !containsZombie) {
                containsZombie = true;
                continue;
            }
        }

        return containsZombie;
    }

    /**
     * Check if the Plant exists in a specific position
     * @param index position to check Plant instance
     * @return true if plant exists in the specified position; false - otherwise
     */
    protected boolean hasPlant(int index) {
        if(index >= 0){
            nodes.get(index).hasPlant();
        }
        return false;
    }

    /**
     * Simulates fighting between Plant and Zombie
     * @param score int current player score
     * @return int reward for the player when plant kills zombie
     */
    protected int fightPvsZ(int score) {
        for (int i = 0; i < COLUMNS_ON_BOARD; i++) {
            BoardNode plantFind = nodes.get(i);
            if (plantFind.hasPlant()) {
                for (int j = i; j < COLUMNS_ON_BOARD; j++) {
                    BoardNode zombieFind = nodes.get(j);
                    if (plantFind == zombieFind && plantFind.hasZombie()) {
                        score += plantFind.plantFightZombie();
                        break;
                    } else if (plantFind != zombieFind && zombieFind.hasZombie()) {
                        Zombie z = plantFind.plantFightZombie(zombieFind.destroyZombie());
                        if(z.getHealth() <= 0){
                            zombieFind.addZombie(null);
                            score += z.getPointsWhenDead();
                        } else {
                            zombieFind.addZombie(z);
                        }
                        break;
                    }
                }
            }
        }
        return score;
    }
}