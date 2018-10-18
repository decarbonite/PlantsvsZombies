/**
 * Board node is holding information about NPCs on it.
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class BoardNode {
    private Plant plant;
    private Zombie zombie;

    /**
     * Default constructor that creates empty node with a specified coordinates
     */
    public BoardNode() {
        plant = null;
        zombie = null;
    }

public BoardNode(Plant plant) {
        this.plant = plant;
    }

    public BoardNode(Zombie zombie) {
        this.zombie = zombie;
    }

    public BoardNode(Plant plant, Zombie zombie) {
        this.plant = plant;
        this.zombie = zombie;
    }

    public void destroyPlant(){
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

    public boolean addPlant(Plant plant) {
        if(this.plant != null) {
            this.plant = plant;
            return true;
        }

        return false;
    }

    public void addZombie(Zombie zombie) {
        this.zombie = zombie;
    }

    public boolean hasZombie() {
        if(zombie != null) {
            return true;
        }
        return false;
    }

    public boolean hasPlant() {
        if(plant != null) {
            return true;
        }
        return false;
    }

    public String getZombie() {
        return zombie.toString();
    }
}
