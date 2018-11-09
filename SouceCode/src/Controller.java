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

    public void gameStep() {
        int[] coordinates = model.generateNewZombie();
        if(coordinates != null) {
            if (view.getBtn()[coordinates[1]][coordinates[0]].getIcon().toString().equals("../images/grass.jpg")){
                    view.getBtn()[coordinates[1]][coordinates[0]].setIcon(new ImageIcon(View.ZOMBIE_IMAGE));
            }
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
                        createCustomCursor(new ImageIcon("../images/plant.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("../images/plant.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
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
                        createCustomCursor(new ImageIcon("../images/sunflower.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("../images/sunflower.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                view.setImg(new ImageIcon(icon));
                return;
            }
        }

        JButton btn = (JButton) e.getSource();
        int row = (int) btn.getClientProperty("row");
        int col = (int) btn.getClientProperty("column");

        if (view.getFrame().getCursor().getType() != 0) {
            //Only place it if cell is empty
            if (!view.getBtn()[row][col].getIcon().toString().equals("../images/grass.jpg")) {
                JOptionPane.showMessageDialog(null, "This cell is occupied");
                return;
            }
            view.getBtn()[row][col].setIcon(view.getImg());
        }
    }

    public static void main(String[] args) {
        Controller c = new Controller(new View());
        while(true) {
            try {
                c.gameStep();
                TimeUnit.SECONDS.sleep(2);

            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
