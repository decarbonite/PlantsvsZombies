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
     */
    public Plant(String name, int health) {
        super(name, health);
    }


    /**
     * Initializes a new Plant
     * @param name Plant's name
     * @param health Plant's health
     * @param attackPower Plant's attack power
     */
    public Plant(String name, int health, int attackPower) {
        super(name+""+counter, health, attackPower);
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
