import javax.swing.*;
import java.awt.*;


public class Gui extends JFrame {

    JPanel panel;
    JLabel[][] label;

    public Gui(){
        super("Plants vs Zombies");

        panel = new JPanel(new GridLayout(5,9,2,2));
        label = new JLabel[5][9];

        this.setLayout(new GridLayout());

        this.setSize(1000, 600);
        this.setLocationRelativeTo(null);//show gui in the middle of screen
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        paintGrid();
        this.add(panel);
        this.setVisible(true);
    }


    public void paintGrid(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                label[i][j] = new JLabel(new ImageIcon("grass.jpg"));
                panel.add(label[i][j]);
            }
        }
    }





    public static void main(String[] args) {
        new Gui();
    }
}
