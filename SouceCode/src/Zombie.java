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

    /**
     * Initializes a new zombie
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     */
    public Zombie(int health, int attackPower) {
        super(health, attackPower, View.ZOMBIE_IMAGE);
    }

    /**
     * Initializes a new zombie
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     * @param scoreOnDeath score added to the player when zombie is died
     */
    public Zombie(int health, int attackPower, int scoreOnDeath) {
        super(health, attackPower, View.ZOMBIE_IMAGE);
        this.scoreOnDeath = scoreOnDeath;
    }

    public int getScoreOnDeath() { return scoreOnDeath; }

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
