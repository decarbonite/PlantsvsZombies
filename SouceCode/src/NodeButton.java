import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 09 November, 2018
 */
public class NodeButton<E> extends JButton {
    //private static final String PATH = System.getProperty("user.dir") + "/src/images";
    private E internalObj;


    private Plant plant;
    private Zombie zombie;
    private ImageIcon icon;

    public NodeButton(E obj) {
        this.internalObj = obj;
    }

    public NodeButton(E obj, String defaultImageURL) {
        this(obj, new ImageIcon(defaultImageURL));
    }

    private NodeButton(E obj, ImageIcon icon) {
        this.internalObj = obj;
        stringToImageConverter(icon);
    }


    public NodeButton(E obj, Plant plant) {
        this.internalObj = obj;
        this.plant = plant;
        this.icon = new ImageIcon(plant.getImgURL());
        stringToImageConverter(icon);
    }

    public NodeButton(E obj, Zombie zombie) {
        this.internalObj = obj;
        this.zombie = zombie;
        this.icon = new ImageIcon(zombie.getImgURL());
        stringToImageConverter(icon);
    }

    private void stringToImageConverter(ImageIcon icon){
        this.setIcon(icon);
        this.setMargin(new Insets(0, 0, 0, 0));
        this.setIconTextGap(0);
        this.setBorderPainted(false);
        this.setBorder(null);
        this.setText(null);
        this.setSize(icon.getImage().getWidth(null), icon.getImage().getHeight(null));
    }

    //public E getObject(){
        //return this.internalObj;
    //}

    public void setImage(String imageURL) {
        this.setImage(new ImageIcon(imageURL));
    }

    public void setImage(ImageIcon imageIcon) {
        stringToImageConverter(imageIcon);
    }

    public Plant getPlant() {
        return plant;
    }

    public Zombie getZombie() {
        return zombie;
    }
}
