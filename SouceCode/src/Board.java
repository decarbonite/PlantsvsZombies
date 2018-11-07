import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    private static int boardRows;
    private static int boardColumns;
    protected static int score;
    protected static int money;
    private int zombiesToSpawn;
    private ArrayList<BoardRow> board;
    private JPanel gridPanel;
    private JPanel selectPanel;
    private JButton[][] label;
    private JButton shootflowerButton;
    private JButton sunflowerButton;
    private Image cursorIcon;
    private ImageIcon img22;
    private JFrame frame;



    private ActionListener listener;

    /**
     * Default constructor
     *
     * @param numberOfRows int number of rows
     * @param numberOfColumns int number of BoardNode instances in each row
     * @param zombiesToSpawn int number of zombies that would be randomly generated
     * @param score int initial score for the game (round)
     * @param money int initial amount of money
     */
    public Board(int numberOfRows, int numberOfColumns, int zombiesToSpawn, int score, int money) {
        boardRows = numberOfRows;
        boardColumns = numberOfColumns;
        this.score = score;
        this.money = money;
        this.zombiesToSpawn = zombiesToSpawn;
        this.board = new ArrayList<>(boardRows);

        for (int i = 0; i < boardRows; i++) {
            board.add(new BoardRow(boardColumns));
        }
    }

    public Board(){

        frame = new JFrame("Plants Vs Zombies");

        selectPanel = new JPanel(new GridLayout(1,5,2,2));

        gridPanel = new JPanel(new GridLayout(5,9,2,2));
        label = new JButton[5][9];

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

    public JButton[][] getLabel() {
        return label;
    }

    public JButton getShootflowerButton() {
        return shootflowerButton;
    }

    public JButton getSunflowerButton() {
        return sunflowerButton;
    }

    public ImageIcon getImg22() {
        return img22;
    }

    public void setImg22(ImageIcon img22) {
        this.img22 = img22;
    }

    /**
     * Move Zombies across board in each row
     */
    private void moveZombies() {
        for (BoardRow br : board) {
            br.moveZombie();
        }
    }

    /**
     * Simulates fight between Zombies and Plants on each row and if Plant kill Zombie add point to the player
     */
    private void fightPlantZombie() {
        for (BoardRow br : board) {
            this.score = br.fightPvsZ(this.score);
        }
    }

    /**
     * Rise money if at least one of the rows contains MoneyFlower (if more, values added)
     */
    private void riseMoney() {
        for (BoardRow br : board){
            this.money = br.generateMoney(this.money);
        }
    }

    /**
     * Main method that runs the Zombie generation, simulates fight method, moves zombies across board and prints board.
     */
    public void runBoard() {
        riseMoney();
        generateNewZombie();

        fightPlantZombie();
        moveZombies();
    }

    /**
     * Check is the game ends
     * @return boolean[], boolean[0] = true if game is end; false otherwise. boolean[1] = true Zombie won; false if Plants won
     */
    public boolean[] gameEnds() {
        boolean[] res = new boolean[2];
        if(board.get(0).ZOMBIE_WON){
            res[0] = true; res[1] = true;
            return res;
        }

        boolean zombieOnRow = false;

        for(BoardRow br : board) {
            if(br.hasZombie() && !zombieOnRow) {
                zombieOnRow = true;
                continue;
            }
        }

        if(zombiesToSpawn <= 0) {
            res[0] = !zombieOnRow; res[1] = false;
            return res;
        } else {
            res[0] = false; res[1] = false;
            return res;
        }
    }

    /**
     * Randomly generates zombies on the board across rows starting on the right of the board
     */
    private void generateNewZombie() {
        Random rand = new Random();

        if (zombiesToSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                int randRow = rand.nextInt(boardRows);
                if (!board.get(randRow).hasZombie(boardColumns - 1)) {
                    addZombie(randRow, boardColumns - 1, new Zombie("Zombie", 100, 10, 10));
                    //label[5][9] = new JLabel(new ImageIcon("./img.jpg"));
                    //panel.add(label[i][j]);
                    zombiesToSpawn--;
                }
            }
        }
    }

    /**
     * Adds Plant object on a specific coordinate
     * @param x int column index
     * @param y int row index
     * @param plant Plant object
     * @return boolean true if plant has been added; false - otherwise
     */
    public boolean addPlant(int x, int y, Plant plant) {
        if(money >= 50 && (x >= 0 && x < boardRows) && (y >= 0 && y < (boardColumns - 1))) {
            if (plant != null) {
                if(!board.get(x).hasPlant(y)) {
                    board.get(x).addPlant(y, plant);
                    money -= 50;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds Zombie object on a specific coordinate
     * @param x int row index
     * @param y int column index
     * @param zombie Zombie object
     */
    public void addZombie(int x, int y, Zombie zombie) {
        if (zombie != null) {
            board.get(x).addZombie(y, zombie);
        }
    }


    public void paintGrid(){
        Image img = new ImageIcon("./plant.png").getImage();
        Image newImg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(newImg);
        shootflowerButton = new JButton(icon);
        shootflowerButton.addActionListener(new Controller(this));
        selectPanel.add(shootflowerButton);


        img = new ImageIcon("./sunflower.png").getImage();
        newImg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        sunflowerButton = new JButton(icon);
        sunflowerButton.addActionListener(new Controller(this));
        selectPanel.add(sunflowerButton);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                label[i][j] = new JButton(new ImageIcon("./img.jpg"));
                label[i][j].putClientProperty("row", i);
                label[i][j].putClientProperty("column", j);
                label[i][j].addActionListener(new Controller(this));
                gridPanel.add(label[i][j]);
            }
        }
    }


/*
    @SuppressWarnings("Duplicates")
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == shootflowerButton){
            if (frame.getCursor().getType() != 0){
                frame.setCursor(DEFAULT_CURSOR);
            }else {
                frame.setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon("./plant.png").getImage(),
                        new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("./plant.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                img22 = new ImageIcon(icon);
            }
        }
        if(e.getSource() == sunflowerButton){
            if (frame.getCursor().getType() != 0){
                frame.setCursor(DEFAULT_CURSOR);
            }else {
                frame.setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon("./sunflower.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("./sunflower.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
                img22 = new ImageIcon(icon);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (e.getSource() == label[i][j]){
                    if (frame.getCursor().getType() != 0) {
                        label[i][j].setIcon(img22);
                    }
                }
            }
        }

    }*/
}
