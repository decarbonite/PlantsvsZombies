/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 26 October, 2018
 */

public class Plant extends NPC {
    private static int counter = 0;

    /**
     * Initializes a new Plant
     * @param name Plant's name
     * @param health Plant's health
     * @param imgURL String image of the plant on the board (GUI)
     */
    public Plant(String name, int health, String imgURL) {
        super(name, health, imgURL);
    }


    /**
     * Initializes a new Plant
     * @param name Plant's name
     * @param health Plant's health
     * @param attackPower Plant's attack power
     * @param imgURL String image of the plant on the board (GUI)
     */
    public Plant(String name, int health, int attackPower, String imgURL) {
        super(name+""+counter, health, attackPower, imgURL);
        counter++;
    }

    /**
     * Simulates fight between Plant and given NPC (Zombie)
     * @param npc NPC to be attacked
     */
    @Override
    public void attack(NPC npc) {
        if (npc != null){
            npc.setHealth(npc.getHealth() - this.getAttackPower());
        }
    }
}
