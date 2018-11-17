import javax.swing.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 16 Nov, 2018
 */

public class Plant extends NPC {

    /**
     * Initializes a new Plant
     * @param name Plant's name
     * @param health Plant's health
     * @param imgURL String image of the plant on the board (GUI)
     */
    public Plant(String name, int health, ImageIcon imgURL) {
        super(name, health, imgURL);
    }


    /**
     * Initializes a new Plant
     * @param name Plant's name
     * @param health Plant's health
     * @param attackPower Plant's attack power
     * @param imgURL String image of the plant on the board (GUI)
     */
    public Plant(String name, int health, int attackPower, ImageIcon imgURL) {
        super(name, health, attackPower, imgURL);
    }

    /**
     * Simulates fight between Plant and given NPC (Zombie)
     * @param npc NPC to be attacked
     */
    @Override
    public void attack(NPC npc) {
        if (npc != null && npc.getHealth() > 0) {
            npc.setHealth(npc.getHealth() - this.getAttackPower());
        }
    }
}
