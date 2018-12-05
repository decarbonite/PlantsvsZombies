import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Default view component for the MVC model.
 * This class builds GUI for the Plant vs Zombie game.
 *
 * Contains visual components such paths to the default images in the game.
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 21 November, 2018
 */
public class View extends JFrame {
    public static final int BOARD_ROWS = 5;
    public static final int BOARD_COLS = 9;
    private JPanel gridPanel;
    private JPanel selectPanel;
    private static NodeButton<BoardNode>[][] btn;
    private JButton shootFlowerButton;
    private JButton strongPlant;
    private JButton sunflowerButton;
    private JButton dblSunflowerButton;
    private JLabel scoreLabel;
    private JLabel moneyLabel;
    private JMenuItem undo;
    private JMenuItem redo;
    private JMenuItem load;
    private JMenuItem save;
    private JFrame frame;

    protected static final String PLANT_ICON       = "images/plant.png";
    protected static final String PLANT2_ICON      = "images/plant2.png";
    protected static final String SUNFLOWER_ICON   = "images/sunflower.png";
    protected static final String SUNFLOWER2_ICON  = "images/sunflower2.png";
    protected static final String GRASS_IMAGE      = "images/grassed.png";
    protected static final String PLANT_IMAGE      = "images/grassedPlant.png";
    protected static final String PLANT2_IMAGE     = "images/grassedPlant2.png";
    protected static final String SUNFLOWER_IMAGE  = "images/grassedSunflower.png";
    protected static final String SUNFLOWER2_IMAGE = "images/grassedSunflower2.png";
    protected static final String ZOMBIE_IMAGE     = "images/grassedZombie1.png";
    protected static final String ZOMBIE2_IMAGE    = "images/grassedZombie2.png";

    /**
     * Default constructor that initialized window with board,
     * barr with the score and current amount of money, and control buttons to place plants into the board.
     */
    public View() {
        frame = new JFrame("Plants Vs Zombies");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Undo/Redo");
        undo = new JMenuItem("Undo");
        redo = new JMenuItem("Redo");
        JMenu menu2 = new JMenu("Menu");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        load.addActionListener(new Controller(this));
        save.addActionListener(new Controller(this));
        undo.addActionListener(new Controller(this));
        redo.addActionListener(new Controller(this));
        menu2.add(save);
        menu2.add(load);
        menu.add(undo);
        menu.add(redo);
        menuBar.add(menu2);
        menuBar.add(menu);

        JPanel statsPanel = new JPanel(new GridLayout(1,4,0,0));
        selectPanel = new JPanel(new GridLayout(1,5,0,0));
        gridPanel = new JPanel(new GridLayout(BOARD_ROWS,BOARD_COLS,0,1));

        btn = new NodeButton[BOARD_ROWS][BOARD_COLS];

        scoreLabel = new JLabel("0");
        moneyLabel = new JLabel("0");
        statsPanel.add(new JLabel("Score: "));
        statsPanel.add(scoreLabel);
        statsPanel.add(new JLabel("Money: "));
        statsPanel.add(moneyLabel);

        selectPanel.setMaximumSize(new Dimension(100,100));

        frame.setSize(925, 600);
        paintGrid();

        JPanel gJP = new JPanel(new  BorderLayout());
        gJP.add(statsPanel, BorderLayout.NORTH);
        gJP.add(selectPanel, BorderLayout.CENTER);

        frame.setJMenuBar(menuBar);
        frame.add(gJP, BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);      //show gui in the middle of screen
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Generate control buttons to place plants into the board and board itself
     */
    public void paintGrid(){
        shootFlowerButton = new JButton(new ImageIcon(this.getClass().getResource(PLANT_ICON)));
        shootFlowerButton.setName("Plant");
        shootFlowerButton.addActionListener(new Controller(this));
        selectPanel.add(shootFlowerButton);

        strongPlant = new JButton(new ImageIcon(this.getClass().getResource(PLANT2_ICON)));
        strongPlant.setName("Strong Plant");
        strongPlant.addActionListener(new Controller(this));
        selectPanel.add(strongPlant);

        dblSunflowerButton = new JButton(new ImageIcon(this.getClass().getResource(SUNFLOWER2_ICON)));
        dblSunflowerButton.setName("Double Sunflower");
        dblSunflowerButton.addActionListener(new Controller(this));
        selectPanel.add(dblSunflowerButton);

        sunflowerButton = new JButton(new ImageIcon(this.getClass().getResource(SUNFLOWER_ICON)));
        sunflowerButton.setName("Sunflower");
        sunflowerButton.addActionListener(new Controller(this));
        selectPanel.add(sunflowerButton);

        generateBoard();
    }

    /**
     * Helper method that generates board out of the array of NodeButtons
     */
    public void generateBoard() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                btn[i][j] = new NodeButton<>(new ImageIcon(this.getClass().getResource(GRASS_IMAGE)));

                btn[i][j].putClientProperty("row", i);              //to save i and j for that specific button,
                btn[i][j].putClientProperty("column", j);           // helps when dealing with actions in controller
                btn[i][j].addActionListener(new Controller(this));

                gridPanel.add(btn[i][j]);
            }
        }
    }

    /**
     * Link each button on the board with the corresponding BoardNode object in the model
     * @param b ArrayList of BoardRows - board itself
     */
    public void linkModelView(ArrayList<BoardRow> b) {
        scoreLabel.setText(Integer.toString(Board.score));
        moneyLabel.setText(Integer.toString(Board.getMoney()));

        for (int i = 0; i < BOARD_ROWS; i++) {
            ArrayList<BoardNode> br = b.get(i).getRow();
            for (int j = 0; j < BOARD_COLS; j++) {
                btn[i][j].setObject(br.get(j));
            }
        }
    }

    /**
     * Synchronizes model and view by calling internal update method of the NodeButton
     */
    public void updateView() {
        for (int i = 0; i < BOARD_ROWS; i++) {
            for (int j = 0; j < BOARD_COLS; j++) {
                btn[i][j].update();
            }
        }

        scoreLabel.setText(Integer.toString(Board.score));
        moneyLabel.setText(Integer.toString(Board.getMoney()));
    }

    /**
     * Return main frame of the window
     * @return JFrame main frame
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Return two dimensional array of buttons
     * @return array of NodeButtons
     */
    public static NodeButton[][] getBtn() {
        return btn;
    }

    /**
     * Return object of shooting plant button from the control panel
     * @return JButton of shooting plant
     */
    public JButton getShootFlowerButton() {
        return shootFlowerButton;
    }

    /**
     * Return object of sunflower plant (one that generates money) button from the control panel
     * @return JButton of sunflower plant
     */
    public JButton getSunflowerButton() {
        return sunflowerButton;
    }

    /**
     * Returns the instance of the Undo button in the main menu
     * @return JMenuItem returns the undo Menu item
     */
    public JMenuItem getUndo() {
        return undo;
    }

    /**
     * Returns the instance of the Redo button in the main menu
     * @return JMenuItem returns the redo Menu item
     */
    public JMenuItem getRedo() {
        return redo;
    }

    /**
     * Returns the instance of the strong plant from the select panel
     * @return JButton instance of strong plant button
     */
    public JButton getStrongPlant() {
        return strongPlant;
    }

    /**
     * Returns the instance of the double sunflower plant from the select panel
     * @return JButton instance of double sunflower plant button
     */
    public JButton getDblSunflowerButton() {
        return dblSunflowerButton;
    }

    /**
     * Return the instance of the label that contains information about monue and score that the player has
     * @return JLabel returns the money label which contains the amount of money the player has
     */
    public JLabel getMoneyLabel() {
        return moneyLabel;
    }

    public JMenuItem getLoad() {
        return load;
    }

    public JMenuItem getSave() {
        return save;
    }
}
