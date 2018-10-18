/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
abstract class Beast {
    private int health;
    private String beastName;
    private float atacPower;
    private float atacSpeed;

    public Beast(String name, int health) {
        this.beastName = name;
        this.health = health;
    }

    /**
     * Returns true if the updated health is grater that zero and false otherwise
     *
     * @param damage
     * @return
     */
    public boolean updateHealth(int damage){
        this.health -= damage;
        if(this.health > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return beastName + " - " + health;
    }
}
