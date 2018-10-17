/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Zombie extends Beast {
    private float walkingSpeed; // time that passed to go from one cell to the other
    private int scoreOnDead;

    public Zombie(String name, int health) {
        super(name, health);
    }

    public void updateSpeed() {

    }
}
