import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import static java.awt.Cursor.DEFAULT_CURSOR;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 08 November, 2018
 */
public class Controller implements ActionListener {
    private View view;
    private Board model;

    public Controller(View v) {
        this.view = v;
        this.model = new Board(v.BOARD_ROWS, v.BOARD_COLS, 5, 100, 500000);
//        this.generateBoard();
    }

    public Controller(View v, Board m) {
        this.view = v;
        this.model = m;
    }

    public void generateBoard() {
        view.linkModelView(model.board);
    }

    public void updateBoard() {
//        view.linkModelView(model.board);
        model.runBoard();
        view.updateView();
    }


//    public void generateZombie() {
//        int[] coordinates = model.generateZombieSpawn();
//        if (coordinates != null) {
//            if (view.getBtn()[coordinates[1]][coordinates[0]].getIcon().toString().equals(View.GRASS_IMAGE)) {
//                view.getBtn()[coordinates[1]][coordinates[0]].setIcon(new ImageIcon(View.ZOMBIE_IMAGE));
//            }
//        }
//    }

//    public void moveZombie() {
//
//        int[] coordinates = model.getZombieLocation();
//        for (int i = 0; i < coordinates.length-1; i+=2) {
//            if (coordinates != null && coordinates[i+1] != 0 && view.getBtn()[coordinates[i]][coordinates[i+1] -1].getIcon().toString().equals(View.GRASS_IMAGE)) {
//                view.getBtn()[coordinates[i]][coordinates[i+1]].setIcon(new ImageIcon(View.GRASS_IMAGE));
//                view.getBtn()[coordinates[i]][coordinates[i+1]-1].setIcon(new ImageIcon(View.ZOMBIE_IMAGE));
//            }
//        }
//
//    }


    public void gameEnded(){
        if (model.hasWon()) {
            JOptionPane.showMessageDialog(null, "You Won!");
            System.exit(0);
        }
        if (model.hasLost()){
            JOptionPane.showMessageDialog(null, "You Lost!");
            System.exit(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.getShootFlowerButton()) {
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            } else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(View.PLANT_ICON).getImage(),
                                new Point(0, 0), "plant"));
                Image icon = new ImageIcon(View.PLANT_IMAGE).getImage();
                view.setImg(new ImageIcon(icon));
                return;
            }
        }
        if (e.getSource() == view.getSunflowerButton()) {
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            } else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(View.SUNFLOWER_ICON).getImage(),
                                new Point(0, 0), "sunflower"));
                Image icon = new ImageIcon(View.SUNFLOWER_IMAGE).getImage();
                view.setImg(new ImageIcon(icon));
                return;
            }
        }

        JButton btn = (JButton) e.getSource();
        int row = (int) btn.getClientProperty("row");
        int col = (int) btn.getClientProperty("column");

        if (view.getFrame().getCursor().getType() != 0) {
            //Only place it if cell is empty
            BoardNode bn = (BoardNode) view.getBtn()[row][col].getObject();
            if (bn.hasPlant() || bn.hasZombie()) {
                JOptionPane.showMessageDialog(null, "This cell is occupied");
                return;
            }
            String toPlant = view.getFrame().getCursor().getName();
            if(toPlant.equals("plant")) {
                model.addPlant(row, col, new Plant("Plant", 100, 5, View.PLANT_IMAGE));
            }

            if(toPlant.equals("sunflower")) {
                model.addPlant(row, col, new MoneyPlant("Sunflower", 100, 25,  View.SUNFLOWER_IMAGE));
            }

            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
            }
            view.updateView();
        }
    }

    public static void main(String[] args) {
        Controller c = new Controller(new View());
        c.generateBoard();
        while (true) {
            try {
                c.updateBoard();
                c.gameEnded();
//                c.generateZombie();
                TimeUnit.SECONDS.sleep(3);
//                c.moveZombie();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
