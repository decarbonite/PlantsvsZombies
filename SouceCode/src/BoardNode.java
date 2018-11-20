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
        this.plant = null;
        this.zombie = null;
    }


    /**
     * Remove Zombie instance iff exists
     * @return Zombie instance of current node before Zombie will be removed
     */
    public Zombie destroyZombie() {
        if (zombie != null) {
            Zombie z = zombie;
            zombie = null;
            return z;
        }
        return null;
    }

    /**
     * Adds Plant instance to the current node
     * @param plant new Plant
     * @return boolean true - if plant was added; false otherwise
     */
    public boolean addPlant(Plant plant) {
        if (this.plant == null) {
            this.plant = plant;
            return true;
        }
        return false;
    }

    /**
     * Simulates fight between Plant and Zombie in current node
     * @return int reward when plant kills zombie
     */
    public int plantFightZombie() {
        this.plant.attack(this.zombie);

        if (this.zombie.getHealth() <= 0) {
            int score = this.zombie.getPointsWhenDead();
            this.zombie = null;
            return score;
        }

        this.zombie.attack(this.plant);

        if (this.plant.getHealth() <= 0) {
            this.plant = null;
            return 0;
        }
        return 0;
    }

    /**
     * Simulates fight between current Plant instance and given Zombie instance
     * @param zombie Zombie object to fight with
     * @return Zombie iff not ded; otherwise Zombie with updated health
     */
    public Zombie plantFightZombie(Zombie zombie) {
        this.plant.attack(zombie);
        return zombie;
    }

    /**
     * Adds Zombie instance into the node
     * @param zombie Zombie object
     */
    public void addZombie(Zombie zombie) {
        this.zombie = zombie;
    }

    /**
     * Check if the current node contain Zombie object
     * @return boolean true - node contain zombie instance; false - otherwise
     */
    public boolean hasZombie() {
        if (zombie != null) {
            return true;
        }
        return false;
    }

    /**
     * Check if the current node contain Plant object
     * @return true - node contain plant instance; false - otherwise
     */
    public boolean hasPlant() {
        if (plant != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns plant instance if the current node
     * @return Plant instance of the plant
     */
    public Plant getPlant() {
        return this.plant;
    }

    /**
     * Returns MoneyPlant if exists in current node
     * @return MoneyPlant object of MoneyPlant
     */
    public MoneyPlant getMoneyPlant() {
        if(this.plant instanceof MoneyPlant) {
            return (MoneyPlant) this.plant;
        }
        return null;
    }
    /**
     * Returns zombie instance of the current node
     * @return Zombie instance of Zombie
     */
    public Zombie getZombie() {
        return this.zombie;
    }

    /**
     * Check if the plant stored in the current nod is MoneyPlant
     * @return boolean true when Plant stored in the node is MoneyPlant instance; false - otherwise
     */
    public boolean hasMoneyPlant() {
        if(this.plant instanceof MoneyPlant) {
            return true;
        }
        return false;
    }

    public Plant removePlant(){
        Plant p = this.plant;
        this.plant = null;
        return p;
    }
}
