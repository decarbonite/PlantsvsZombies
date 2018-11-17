import javax.swing.*;

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
    private ImageIcon imgURL;
    private int health;
    private String name;
    private int attackPower;

    /**
     * Initializes a new NPC
     * @param name NPC's name
     * @param health NPC's health
     * @param imgURL ImageIcon url of the image for NPS's on the board
     */
    public NPC(String name, int health, ImageIcon imgURL) {
        this.name = name;
        this.health = health;
        this.imgURL = imgURL;
    }

    /**
     * Initializes a new NPC
     * @param name NPC's name
     * @param health NPC's health
     * @param attackPower NPC's attack power
     * @param imgURL ImageIcon url of the image for NPS's on the board
     */
    public NPC(String name, int health, int attackPower, ImageIcon imgURL) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.imgURL = imgURL;
    }

    /**
     * Returns true if the NPC's health is greater than 0, false otherwise
     * @return boolean returns true if NPC alive; false - otherwise
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
     * @return ImageIcon url
     */
    public ImageIcon getImgURL() {
        return imgURL;
    }
}
