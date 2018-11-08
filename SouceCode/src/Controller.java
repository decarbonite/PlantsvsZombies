import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import static java.awt.Cursor.DEFAULT_CURSOR;

@SuppressWarnings("Duplicates")
public class Controller implements ActionListener {

    private Board view;
    public Controller(Board view){
        this.view = view;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.getShootFlowerButton()){
            if (view.getFrame().getCursor().getType() != 0){
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            }else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon("../images/plant.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("../images/plant.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                view.setImg(new ImageIcon(icon));
                return;
            }
        }
        if(e.getSource() == view.getSunflowerButton()){
            if (view.getFrame().getCursor().getType() != 0){
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            }else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon("../images/sunflower.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("../images/sunflower.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                view.setImg(new ImageIcon(icon));
                return;
            }
        }

        JButton btn =  (JButton) e.getSource();
        int row = (int)btn.getClientProperty("row");
        int col = (int)btn.getClientProperty("column");

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
        Board b = new Board(4,200,200);
        new Controller(b);
        while(true) {
            try {
                TimeUnit.SECONDS.sleep(2);
                b.addZombie();
                TimeUnit.SECONDS.sleep(2);
                b.moveZombie();
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }



        }
    }
}
