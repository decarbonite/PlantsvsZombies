import java.util.ArrayList;
import java.util.Random;

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
    private ArrayList<BoardNode> nodes;

    /**
     * Default constructor initializes a row of a board
     */
    public BoardRow() {
        nodes = new ArrayList<>(View.BOARD_COLS);

        for(int i = 0; i < View.BOARD_COLS; i++) {
            nodes.add(new BoardNode());
        }
    }


    /**
     * Moves zombies across plane (based on the number of node(s))
     * Assign ZOMBIE_WON to true if zombie stays on the first node (end of the board) and this node does not contain plant.
     */
    protected void moveZombie() {
        for (int i = 0; i < View.BOARD_COLS - 1; i++) {
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
     * @return boolean true if zombie exists in the specified position; false - otherwise
     */
    protected boolean hasZombie(int index) {
        if(index >= 0){
            return nodes.get(index).hasZombie();
        }
        return false;
    }

    /**
     * Check if the Zombie exists in the current row
     * @return boolean true if zombie exists in this row; false - otherwise
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
     * @return boolean true if plant exists in the specified position; false - otherwise
     */
    protected boolean hasPlant(int index) {
        if(index >= 0){
            return nodes.get(index).hasPlant();
        }
        return false;
    }

    /**
     * Simulates fighting between Plant and Zombie
     * @param score int current player score
     * @return int reward for the player when plant kills zombie
     */
    protected int fightPvsZ(int score) {
        for (int i = 0; i < View.BOARD_COLS; i++) {
            BoardNode plantFind = nodes.get(i);
            if (plantFind.hasPlant()) {
                for (int j = i; j < View.BOARD_COLS; j++) {
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

    /**
     * Money generator if on row contains MoneyFlower with 50% chance to gain money
     * @param money int amount of money player currently have
     * @return int amount of money player gain from MoneyFlower
     */
    protected int generateMoney(int money){
        for(BoardNode bn : nodes){
            if(bn.hasMoneyPlant()){
                Random rand = new Random();

                if(rand.nextInt(2) % 2 == 0) {
                    money += bn.getMoneyPlant().getMoney();
                    return money;
                }
            }
        }
        return money;
    }

    protected ArrayList<BoardNode> getRow() {
        if(nodes != null) {
            return nodes;
        } else {
            return null;
        }
    }
}