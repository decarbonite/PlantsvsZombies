import java.util.Queue;

/**
 * Board node is holding information about beasts on it.
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class BoardNode {
    private int coordinateX, coordinateY;
    private Plant plant;
    private Zombie zombie;

    /**
     * Default constructor that creates empty node with a specified coordinates
     */
    public BoardNode() {
        plant = null;
        zombie = null;
    }

    public BoardNode(int x, int y, Plant plant) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.plant = plant;
//        this.zombies = null;
    }

    public BoardNode(int x, int y, Zombie zombie) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.plant = null;
//        this.zombies.add(zombie);
    }

    public BoardNode(int x, int y, Plant plant, Zombie zombie) {
        this.coordinateX = x;
        this.coordinateY = y;
        this.plant = plant;
//        this.zombies.add(zombie);
    }

    public void destroyFlower(){
        if(plant != null){
            if(!plant.updateHealth(0)) {
                plant = null;
            }
        }
    }


    public Zombie destroyZombie() {
        if(zombie != null) {
            Zombie z = zombie;
            zombie = null;
            return z;
        }

        return null;
    }

    public boolean addFlover(Plant plant) {
        if(this.plant != null) {
            this.plant = plant;
            return true;
        }

        return false;
    }

    public void addZombie(Zombie zombie) {
        this.zombie = zombie;
    }

//    public boolean hasZombie() {
//        if(zombies.size() == 0) {
//            return false;
//        }
//        return true;
//    }
}
