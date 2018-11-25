import javax.swing.*;

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
     * @param name Zombie's name
     * @param health Zombie's health
     * @param imgURL ImageIcon of the zombie on the board (GUI)
     */
    public Zombie(String name, int health, ImageIcon imgURL) {
        super(name, health, imgURL);
    }

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     * @param imgURL ImageIcon of the zombie on the board (GUI)
     */
    public Zombie(String name, int health, int attackPower, ImageIcon imgURL) {
        super(name, health, attackPower, imgURL);
    }

    /**
     * Initializes a new zombie
     * @param name Zombie's name
     * @param health Zombie's health
     * @param attackPower zombie's attack power
     * @param scoreOnDeath score added to the player when zombie is died
     * @param imgURL ImageIcon of the zombie on the board (GUI)
     */
    public Zombie(String name, int health, int attackPower, int scoreOnDeath, ImageIcon imgURL) {
        super(name, health, attackPower, imgURL);
        this.scoreOnDeath = scoreOnDeath;
    }

    /**
     * Returns amount of points that would be added to the game score when zombie died
     * @return int score when dead
     */
    public int getPointsOnDeath() { return scoreOnDeath; }

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
