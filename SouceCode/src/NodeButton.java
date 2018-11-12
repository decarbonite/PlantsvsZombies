import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 09 November, 2018
 */
public class NodeButton<E> extends JButton {
    private E internalObj;
    private ImageIcon defaultImage;

    public NodeButton() {
        this(null, "");
    }

    public NodeButton(String defaultImageURL)  {
        this(null, new ImageIcon(defaultImageURL));
    }

    public NodeButton(E obj) {
        this.internalObj = obj;
    }

    public NodeButton(E obj, String defaultImageURL) {
        this(obj, new ImageIcon(defaultImageURL));
    }

    private NodeButton(E obj, ImageIcon icon) {
        this.internalObj = obj;
        this.defaultImage = icon;
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

    public void setObject(E obj) {
        this.internalObj = obj;
    }

    public E getObject(){
        return this.internalObj;
    }

    public void setImage(String imageURL) {
        this.setImage(new ImageIcon(imageURL));
    }

    public void setImage(ImageIcon imageIcon) {
        stringToImageConverter(imageIcon);
    }

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
