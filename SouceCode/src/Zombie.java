/**
 * This class is responsible for making zombies and making them attack
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 17 October, 2018
 */
public class Zombie extends NPC {
    private int scoreOnDeath;
    private static int counter = 0;

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     */
    public Zombie(String name, int health) {
        super(name+""+counter, health);
        counter++;
    }

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     */
    public Zombie(String name, int health, int attackPower) {
        super(name+""+counter, health, attackPower);
        counter++;
    }

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     * @param scoreOnDeath score added to the player when zombie is died
     */
    public Zombie(String name, int health, int attackPower, int scoreOnDeath) {
        super(name+""+counter, health, attackPower);
        this.scoreOnDeath = scoreOnDeath;
        counter++;
    }

    public int getPointsWhenDead() { return scoreOnDeath; }

    /*public Zombie(String name, int health, int attackPower, int points) {
        super(name+""+counter, health, attackPower);
        counter++;
    }*/

    /**
     * Allows the zombie to attack plants
     * @param npc The plant to attack
     */
    @Override
    public void attack(NPC npc) {
        if (npc != null && npc.getHealth() > 0) {
            npc.setHealth(npc.getHealth() - this.getAttackPower());
        }
    }
}
