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

    public NPC(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public NPC(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    /**
     * Returns true if the updated health is greater than zero and false otherwise
     *
     * @param damage damage given to the NPC
     * @return returns a boolean, whether the NPC is alive or not
     */
    public boolean isAlive(int damage){
        if (this.getHealth() - damage > 0) {
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

    public abstract void attack(NPC npc);

    public String toString() {
        return name;
    }
}
