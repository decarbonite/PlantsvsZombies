import javax.swing.*;
import java.awt.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 08 November, 2018
 */
public class View extends JFrame {
    public static final int BOARD_ROWS = 5;
    public static final int BOARD_COLS = 9;
    private JPanel gridPanel;
    private JPanel selectPanel;
    private static NodeButton<BoardNode>[][] btn;
    private NodeButton shootFlowerButton;
    private NodeButton sunflowerButton;
    private ImageIcon img;
    private JFrame frame;

    protected static final String PATH = System.getProperty("user.dir");
    protected static final String PLANT_ICON = PATH + "/images/plant.png";
    protected static final String SUNFLOWER_ICON = PATH + "/images/sunflower.png";
    public static final String GRASS_IMAGE = PATH +"/images/grass.png";
    public static final String PLANT_IMAGE = PATH +"/images/grassed_plant.png";
    public static final String SUNFLOWER_IMAGE = PATH +"/images/grassed_sunflower.png";
    public static final String ZOMBIE_IMAGE = PATH +"/images/grassed_zombie.png";

    public View() {
        frame = new JFrame("Plants Vs Zombies");

        selectPanel = new JPanel(new GridLayout(1,5,0,0));

        gridPanel = new JPanel(new GridLayout(BOARD_ROWS,BOARD_COLS,0,0));
        btn = new NodeButton[BOARD_ROWS][BOARD_COLS];

        paintGrid();

        selectPanel.setSize(100,100);

        frame.setSize(800, 500);

        frame.add(selectPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);//show gui in the middle of screen
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void paintGrid(){
        //System.out.println(PATH);
        shootFlowerButton = new NodeButton<>(new BoardNode(), View.PLANT_ICON);
        shootFlowerButton.addActionListener(new Controller(this));
        selectPanel.add(shootFlowerButton);

        sunflowerButton = new NodeButton<>(new BoardNode(), View.SUNFLOWER_ICON);
        sunflowerButton.addActionListener(new Controller(this));
        selectPanel.add(sunflowerButton);

        generateBoard();
    }

    public void generateBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                btn[i][j] = new NodeButton<>(new BoardNode(), new Plant());     //  Grass plant

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

    public static NodeButton<BoardNode>[][] getBtn() {
        return btn;
    }

    public NodeButton<BoardNode> getShootFlowerButton() {
        return shootFlowerButton;
    }

    public NodeButton<BoardNode> getSunflowerButton() {
        return sunflowerButton;
    }

    public ImageIcon getImg() {
        return img;
    }

    public void setImg(ImageIcon img) {
        this.img = img;
    }

}
