/**
 *
 * * NPC is a Non-Player Character which could be any kind of a plant or a zombie
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 17 October, 2018
 *
 */
public abstract class NPC {

    private int health;
    private String name;
    private int attackPower;
    private int attackSpeed;
    private int pointsWhenDead;

    /**
     * Initializes a new NPC
     * @param name NPC's name
     * @param health NPC's health
     */
    public NPC(String name, int health) {
        this.name = name;
        this.health = health;
    }

    /**
     * Initializes a new NPC
     * @param name NPC's name
     * @param health NPC's health
     * @param attackPower NPC's attack power
     */
    public NPC(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    /*public NPC(String name, int health, int attackPower, int points) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.pointsWhenDead = points;
    }*/

    /**
     * Returns true if the NPC's health is greater than 0, false otherwise
     *
     * @return returns a boolean, whether the NPC is alive or not
     */
    public boolean isAlive(){
        if (this.getHealth() > 0) {
            return true;
        }
        return false;
    }

    /**
     * @return Returns NPC's health points
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets NPC's health points
     * @param health NPC's given health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return Returns NPC's attack power
     */
    public int getAttackPower() {
        return attackPower;
    }

    public int getPointsWhenDead() { return pointsWhenDead; }

    /**
     * Attacks npc, implemented in subclasses
     * @param npc NPC to be attacked
     */
    public abstract void attack(NPC npc);

    /**
     * @return Returns a string containing NPC's name and health
     */
    public String toString() {
        return name +" ("+health+")";
    }
}
