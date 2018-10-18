import java.util.Queue;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
abstract class Plant extends Beast {
    private Queue<Zombie> zombieQueue;
    private int cost;
    public Plant(String name, int health) {
        super(name, health);
    }

}
