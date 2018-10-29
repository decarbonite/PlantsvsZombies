/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 17 October, 2018
 */
public class Zombie extends NPC {
    private float walkingSpeed; // time that passed to go from one cell to the other
    private int scoreOnDeath;
    private static int counter = 0;

    public Zombie(String name, int health) {
        super(name+""+counter, health);
        counter++;
    }

    public Zombie(String name, int health, int attackPower) {
        super(name+""+counter, health, attackPower);
        counter++;
    }

    public Zombie(String name, int health, int attackPower, int points) {
        super(name+""+counter, health, attackPower);
        counter++;
    }

    @Override
    public void attack(NPC npc) {
        if (npc != null && npc.getHealth() > 0) {
            npc.setHealth(npc.getHealth() - this.getAttackPower());
        }
    }
}
