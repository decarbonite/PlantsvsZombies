/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
abstract class Beast {
    private int health;
    private short positionX;
    private short positionY;
    private String beastName;
    private float atacPower;
    private float atacSpeed;

    public Beast() {

    }

    /**
     * Returns true if the updated health is grater that zero and false otherwise
     *
     * @param damage
     * @return
     */
    public boolean updateHealth(int damage){

        return true;
    }
}
