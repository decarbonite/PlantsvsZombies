/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 26 October, 2018
 */

public class Plant extends NPC {


    /**
     * Initializes a new Grass Plant
     */
    public Plant() {
        super(View.GRASS_IMAGE);
    }

    /**
     * Initializes a new Plant
     * @param health Plant's health
     * @param attackPower Plant's attack power
     */
    public Plant(int health, int attackPower) {
        super(health, attackPower, View.PLANT_IMAGE);
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
