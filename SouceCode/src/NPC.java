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
    private float attackPower;
    private float attackSpeed;

    public NPC(String name, int health) {
        this.name = name;
        this.health = health;
    }

    /**
     * Returns true if the updated health is greater than zero and false otherwise
     *
     * @param damage
     * @return
     */
    public boolean updateHealth(int damage){
        if (this.getHealth() - damage > 0) {
            return true;
        }
        return false;
    }

    public int getHealth() {
        return health;
    }

    public float getAttackPower() {
        return attackPower;
    }

    public void setAttackPower(float attackPower) {
        this.attackPower = attackPower;
    }


    public String toString() {
        return name + " - " + health;
    }
}
