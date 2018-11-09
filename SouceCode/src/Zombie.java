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
     * @param imgURL image of the zombie on the board (GUI)
     */
    public Zombie(String name, int health, String imgURL) {
        super(name, health, imgURL);
    }

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     * @param imgURL image of the zombie on the board (GUI)
     */
    public Zombie(String name, int health, int attackPower, String imgURL) {
        super(name, health, attackPower, imgURL);
    }

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     * @param scoreOnDeath score added to the player when zombie is died
     * @param imgURL image of the zombie on the board (GUI)
     */
    public Zombie(String name, int health, int attackPower, int scoreOnDeath, String imgURL) {
        super(name, health, attackPower, imgURL);
        this.scoreOnDeath = scoreOnDeath;
    }

    public int getPointsWhenDead() { return scoreOnDeath; }

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
