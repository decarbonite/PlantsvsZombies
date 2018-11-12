/**
 *
 * * NPC is a Non-Player Character which could be any kind of a plant or a zombie
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 17 October, 2018
 *
 */
public abstract class NPC {
    private String imgURL;
    private int health;
    private String name;
    private int attackPower;
    private int money;


    /**
     * Initializes a new NPC
     * @param imgURL url of the image for NPS's on the board
     */
    public NPC(String imgURL) {
        this.imgURL = imgURL;
    }

    /**
     * Initializes a new NPC
     * @param health NPC's health
     * @param imgURL url of the image for NPS's on the board
     */
    public NPC(int health, String imgURL) {
        this.health = health;
        this.imgURL = imgURL;
    }

    /**
     * Initializes a new NPC
     * @param health NPC's health
     * @param attackPower NPC's attack power
     * @param imgURL url of the image for NPS's on the board
     */
    public NPC(int health, int money, int attackPower, String imgURL) {
        this.health = health;
        this.money = money;
        this.attackPower = attackPower;
        this.imgURL = imgURL;
    }

    /**
     * Initializes a new NPC
     * @param health NPC's health
     * @param attackPower NPC's attack power
     * @param imgURL url of the image for NPS's on the board
     */
    public NPC(int health, int attackPower, String imgURL) {
        this.health = health;
        this.attackPower = attackPower;
        this.imgURL = imgURL;
    }

    /**
     * Returns true if the NPC's health is greater than 0, false otherwise
     *
     * @return boolean returns whether the NPC is alive or not
     */
    public boolean isAlive(){
        if (this.getHealth() > 0) {
            return true;
        }
        return false;
    }

    /**
     * Returns health of the current NPC's
     * @return int Returns NPC's health points
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets NPC's health points
     * @param health NPC's given health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return int Returns NPC's attack power
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Attacks npc, implemented in subclasses
     * @param npc NPC to be attacked
     */
    public abstract void attack(NPC npc);

    /**
     * Returns url to the image of NPC's
     * @return String url
     */
    public String getImgURL() {
        return imgURL;
    }

    /**
     * Forms String object with NPC's name and health
     * @return String Returns a string containing NPC's name and health
     */
    public String toString() {
        return name +" ("+health+")";
    }
}
