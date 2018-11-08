import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Creates board that contains BoardRows based on BoardNodes
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 27 October, 2018
 */
public class Board extends JFrame{

    public static final int BOARD_ROWS = 5;
    public static final int BOARD_COLS = 9;
    protected static int score;
    protected static int money;
    private int zombiesToSpawn = 3;
    private JPanel gridPanel;
    private JPanel selectPanel;
    private JButton[][] btn;
    private JButton shootFlowerButton;
    private JButton sunflowerButton;
    private ImageIcon img;
    private JFrame frame;
    private static final ImageIcon PLANT_IMAGE = new ImageIcon("../images/plant.png");
    private static final ImageIcon SUNFLOWER_IMAGE = new ImageIcon("../images/sunflower.png");
    private static final ImageIcon ZOMBIE_IMAGE = new ImageIcon("../images/zombie.png");
    private static final ImageIcon GRASS_IMAGE = new ImageIcon("../images/grass.jpg");



    public Board(int zombiesToSpawn, int score, int money) {
        this.score = score;
        this.money = money;
        this.zombiesToSpawn = zombiesToSpawn;
        frame = new JFrame("Plants Vs Zombies");

        selectPanel = new JPanel(new GridLayout(1,5,2,2));

        gridPanel = new JPanel(new GridLayout(5,9,2,2));
        btn = new JButton[5][9];

        paintGrid();

        selectPanel.setSize(100,100);

        frame.setSize(1000, 600);

        frame.add(selectPanel, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);//show gui in the middle of screen
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton[][] getBtn() {
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

    @SuppressWarnings("Duplicates")
    public void moveZombie() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                if (btn[i][j].getIcon().toString().equals("../images/zombie.png")){
                    if (j != 0 && btn[i][j-1].getIcon().toString().equals("../images/grass.jpg")){
                        btn[i][j].setIcon(GRASS_IMAGE);
                        btn[i][j-1].setIcon(ZOMBIE_IMAGE);
                    }
                }
            }
        }
    }

/**
     * Simulates fight between Zombies and Plants on each row and if Plant kill Zombie add point to the player
     *//*

    private void fightPlantZombie() {
        for (BoardRow br : board) {
            this.score = br.fightPvsZ(this.score);
        }
    }
*/


    /**
     * Randomly generates zombies on the board across rows starting on the right of the board
     */
    public void addZombie() {
        if (zombiesToSpawn > 0) {
            Random rand = new Random();

            if (rand.nextInt(5) % 3 == 0) {

                int randRow = rand.nextInt(BOARD_ROWS);

                // Place zombie only if its a grass button
                if (btn[randRow][8].getIcon().toString().equals("../images/grass.jpg")){
                    btn[randRow][8].setIcon(ZOMBIE_IMAGE);
                    zombiesToSpawn--;
                }
            }
        }

    }

    public void paintGrid(){
        shootFlowerButton = new JButton(PLANT_IMAGE);
        shootFlowerButton.addActionListener(new Controller(this));
        selectPanel.add(shootFlowerButton);

        sunflowerButton = new JButton(SUNFLOWER_IMAGE);
        sunflowerButton.addActionListener(new Controller(this));
        selectPanel.add(sunflowerButton);

        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                btn[i][j] = new JButton(GRASS_IMAGE);
                btn[i][j].putClientProperty("row", i);
                btn[i][j].putClientProperty("column", j);
                btn[i][j].addActionListener(new Controller(this));
                gridPanel.add(btn[i][j]);
            }
        }
    }
}
