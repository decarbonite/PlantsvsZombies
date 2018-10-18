/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Zombie extends NPC {
    private float walkingSpeed; // time that passed to go from one cell to the other
    private int scoreOnDead;

    public Zombie(String name, int health) {
        super(name, health);
    }


    /*worry about later
    public void updateSpeed() {

    }*/
}
