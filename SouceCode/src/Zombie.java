/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Zombie extends NPC {
    private float walkingSpeed; // time that passed to go from one cell to the other
    private int scoreOnDead;
    private static int counter = 0;

    public Zombie(String name, int health) {
        super(name+" "+counter, health);
        counter++;
    }


    /*worry about later
    public void updateSpeed() {

    }*/
}
