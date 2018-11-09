import javax.swing.*;
import java.awt.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 08 November, 2018
 */
public class View extends JFrame {
    public static final int BOARD_ROWS = 5;
    public static final int BOARD_COLS = 9;
    private JPanel gridPanel;
    private JPanel selectPanel;
    private static JButton[][] btn;
    private JButton shootFlowerButton;
    private JButton sunflowerButton;
    private ImageIcon img;
    private JFrame frame;
    public static final String GRASS_IMAGE = new ImageIcon("./images/grass.jpg").toString();
    public static final String PLANT_IMAGE = new ImageIcon("./images/plant.png").toString();
    public static final String SUNFLOWER_IMAGE = new ImageIcon("./images/sunflower.png").toString();
    public static final String ZOMBIE_IMAGE = new ImageIcon("./images/zombie.png").toString();


    public View() {
        frame = new JFrame("Plants Vs Zombies");

        selectPanel = new JPanel(new GridLayout(1,5,2,2));

        gridPanel = new JPanel(new GridLayout(BOARD_ROWS,BOARD_COLS,2,2));
        btn = new JButton[BOARD_ROWS][BOARD_COLS];

        paintGrid();

        selectPanel.setSize(100,100);

        frame.setSize(1000, 600);

        frame.add(selectPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);//show gui in the middle of screen
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintGrid(){
        shootFlowerButton = new JButton(new ImageIcon(PLANT_IMAGE));
        shootFlowerButton.addActionListener(new Controller(this));
        selectPanel.add(shootFlowerButton);

        sunflowerButton = new JButton(new ImageIcon(SUNFLOWER_IMAGE));
        sunflowerButton.addActionListener(new Controller(this));
        selectPanel.add(sunflowerButton);

        generteBoard();
    }

    public void generteBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                btn[i][j] = new JButton(new ImageIcon(GRASS_IMAGE));

                btn[i][j].putClientProperty("row", i);
                btn[i][j].putClientProperty("column", j);
                btn[i][j].addActionListener(new Controller(this));

                gridPanel.add(btn[i][j]);
            }
        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public static JButton[][] getBtn() {
        return btn;
    }

    public JButton getShootFlowerButton() {
        return shootFlowerButton;
    }

    public JButton getSunflowerButton() {
        return sunflowerButton;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }
}
