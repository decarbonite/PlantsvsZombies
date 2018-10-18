/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 17 October, 2018
 */
public class Zombie extends NPC {
    private float walkingSpeed; // time that passed to go from one cell to the other
    private int scoreOnDead;
    private static int counter = 0;

    public Zombie(String name, int health) {
        super(name+" "+counter, health);
        counter++;
    }

    public Zombie(String name, int health, int attackPower) {
        super(name+" "+counter, health, attackPower);
        counter++;
    }

    @Override
    public void attack(NPC npc) {
        npc.setHealth(npc.getHealth() - this.getAttackPower());
    }


    /*worry about later
    public void updateSpeed() {

    }*/
}
