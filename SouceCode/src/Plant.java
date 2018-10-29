/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */

abstract class Plant extends NPC {
    private int cost;

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
        super(name, health, attackPower);
    }
}
