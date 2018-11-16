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
     * @param defaultImageURL String image URL
     */
    public NodeButton(String defaultImageURL)  {
        this(null, new ImageIcon(defaultImageURL));
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
    private void stringToImageConverter(ImageIcon icon){
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

        if(bn.hasZombie() && bn.hasPlant()) {
            if (bn.getPlant() instanceof MoneyPlant) {
                this.stringToImageConverter(new ImageIcon(View.ZOMBIE_SUNFLOWER_IMAGE));
            } else {
                this.stringToImageConverter(new ImageIcon(View.ZOMBIE_PLANT_IMAGE));
            }
        } else if (bn.hasPlant()) {
                this.stringToImageConverter(new ImageIcon(bn.getPlant().getImgURL()));
        } else if (bn.hasZombie()) {
            this.stringToImageConverter(new ImageIcon(bn.getZombie().getImgURL()));
        } else {
            this.stringToImageConverter(defaultImage);
        }
    }
}
