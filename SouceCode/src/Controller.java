import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import static java.awt.Cursor.DEFAULT_CURSOR;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 08 November, 2018
 */
public class Controller implements ActionListener {
    private View view;
    private Board model;

    public Controller(View v) {
        this.view = v;
        this.model = new Board(v.BOARD_ROWS, v.BOARD_COLS, 5, 100, 100);
    }

    public Controller(View v, Board m) {
        this.view = v;
        this.model = m;
    }

    public void generateZombie() {
        int[] coordinates = model.generateZombieSpawn();
        if (coordinates != null) {
            if (view.getBtn()[coordinates[1]][coordinates[0]].getIcon().toString().equals(View.GRASS_IMAGE)) {
                view.getBtn()[coordinates[1]][coordinates[0]].setIcon(new ImageIcon(View.ZOMBIE_IMAGE));
            }
        }
    }

    public void moveZombie() {

        int[] coordinates = model.getZombieLocation();

        if (coordinates != null && coordinates[1] != 0 && view.getBtn()[coordinates[0]][coordinates[1] -1].getIcon().toString().equals(View.GRASS_IMAGE)) {
            view.getBtn()[coordinates[0]][coordinates[1]].setIcon(new ImageIcon(View.GRASS_IMAGE));
            view.getBtn()[coordinates[0]][coordinates[1]-1].setIcon(new ImageIcon(View.ZOMBIE_IMAGE));
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
                        createCustomCursor(new ImageIcon(View.PLANT_IMAGE).getImage(),
                                new Point(0, 0), "custom cursor"));
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
                        createCustomCursor(new ImageIcon(View.SUNFLOWER_IMAGE).getImage(),
                                new Point(0, 0), "custom cursor"));
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
            if (!view.getBtn()[row][col].getIcon().toString().equals(View.GRASS_IMAGE)) {
                JOptionPane.showMessageDialog(null, "This cell is occupied");
                return;
            }
            view.getBtn()[row][col].setIcon(view.getImg());
        }
    }

    public static void main(String[] args) {
        Controller c = new Controller(new View());
        while (true) {
            try {
                c.generateZombie();
                TimeUnit.SECONDS.sleep(2);
                c.moveZombie();

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
