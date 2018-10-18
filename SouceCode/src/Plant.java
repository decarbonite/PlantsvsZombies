/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */

abstract class Plant extends NPC {
    private int cost;

    public Plant(String name, int health) {
        super(name, health);
    }

    public Plant(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }
}
