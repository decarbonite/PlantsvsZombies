import javax.swing.*;
import java.awt.*;

/**
 * Specifically designed JButton that can contain reference to the object and updates correspondingly to its changes
 * Does not have borders, margins and label
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 16 November, 2018
 */
public class NodeButton<E> extends JButton {
    private E internalObj;
    private ImageIcon defaultImage;

    /**
     * Default constructor that creates JButton with defined image
     * @param defaultImage ImageIcon image URL
     */
    public NodeButton(ImageIcon defaultImage)  {
        this(null, defaultImage);
    }

    /**
     * Default constructor that creates JButton with the background image and linked defined object to it
     * @param obj E linking object
     * @param icon ImageIcon object
     */
    private NodeButton(E obj, ImageIcon icon) {
        this.internalObj = obj;
        this.defaultImage = icon;
        stringToImageConverter(icon);
    }

    /**
     * Helper method to clear all styles and add image to it
     * @param icon ImageIcon object
     */
    public void stringToImageConverter(ImageIcon icon){
        this.setIcon(null);
        this.setIcon(icon);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setText(null);
    }

    /**
     * Link new object to the button
     * @param obj E object to link
     */
    public void setObject(E obj) {
        this.internalObj = obj;
    }

    /**
     * Get instance of the linked object
     * @return E object
     */
    public E getObject(){
        return this.internalObj;
    }

    /**
     * Updates the button based on the object (Works only if linked object is BoardNode)
     */
    public void update() {
        if(!(internalObj instanceof BoardNode)){
            return;
        }

        BoardNode bn = (BoardNode) internalObj;

        String path = "images/grassed";

        if(bn.hasPlant()) {
            switch (bn.getPlant().getName()) {
                case "Sunflower":
                    path += "Sunflower";
                    break;
                case "Sunflower2":
                    path += "Sunflower2";
                    break;
                case "Plant":
                    path += "Plant";
                    break;
                case "Plant2":
                    path += "Plant2";
                    break;
                default:
                    break;
            }
        }

        if(bn.hasZombie()) {
            switch (bn.getZombie().getName()) {
                case "Zombie":
                    path += "Zombie";
                    break;
                case "Zombie2":
                    path += "Zombie2";
                    break;
                default:
                    break;
            }
        }

        this.stringToImageConverter(new ImageIcon(this.getClass().getResource(path+".png")));

    }
}
