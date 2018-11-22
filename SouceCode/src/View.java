import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.KeyEvent;
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
    protected static final String ZOMBIE_IMAGE     = "images/grassedZombie.png";
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
        undo.addActionListener(new Controller(this));
        redo.addActionListener(new Controller(this));
        menu.add(undo);
        menu.add(redo);
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
        moneyLabel.setText(Integer.toString(Board.money));

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
        moneyLabel.setText(Integer.toString(Board.money));
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

    public JMenuItem getUndo() {
        return undo;
    }

    public JMenuItem getRedo() {
        return redo;
    }

    public JButton getStrongPlant() {
        return strongPlant;
    }

    public JButton getDblSunflowerButton() {
        return dblSunflowerButton;
    }
}
