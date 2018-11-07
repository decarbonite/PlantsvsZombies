import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.awt.Cursor.DEFAULT_CURSOR;

@SuppressWarnings("Duplicates")
public class Controller implements ActionListener {

    private Board view;
    public Controller(Board view){
        this.view = view;
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == view.getShootflowerButton()){
            if (view.getFrame().getCursor().getType() != 0){
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            }else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon("./plant.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("./plant.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                view.setImg22(new ImageIcon(icon));
                return;
            }
        }
        if(e.getSource() == view.getSunflowerButton()){
            if (view.getFrame().getCursor().getType() != 0){
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            }else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon("./sunflower.png").getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon("./sunflower.png").getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                view.setImg22(new ImageIcon(icon));
                return;
            }
        }

        JButton btn =  (JButton) e.getSource();
        int row = (int)btn.getClientProperty("row");
        int col = (int)btn.getClientProperty("column");
        if (view.getFrame().getCursor().getType() != 0) {
            view.getLabel()[row][col].setIcon(view.getImg22());
        }



}
    public static void main(String[] args) {
        new Controller(new Board());
    }
}
