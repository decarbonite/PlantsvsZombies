/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 17 October, 2018
 *
 *
 * NPC is a Non-Player Character which could be any kind of a plant or a zombie
 *
 */
public abstract class NPC {

    private int health;
    private String name;
    private int attackPower;
    private int attackSpeed;
    private int pointsWhenDead;

    public NPC(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public NPC(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public NPC(String name, int health, int attackPower, int points) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.pointsWhenDead = points;
    }

    /**
     * Returns true if the updated health is greater than zero and false otherwise
     *
     * @return returns a boolean, whether the NPC is alive or not
     */
    public boolean isAlive(){
        if (this.getHealth() > 0) {
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackPower() {
        return attackPower;
    }

    public int getPointsWhenDead() { return pointsWhenDead; }

    public abstract void attack(NPC npc);

    public String toString() {
        return name +" ("+health+")";
    }
}
