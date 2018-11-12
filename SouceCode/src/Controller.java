import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.VolatileImage;
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
        this.model = new Board(View.BOARD_ROWS, View.BOARD_COLS, 5, 100, 100);
    }

    public Controller(View v, Board m) {
        this.view = v;
        this.model = m;
    }

    public void generateZombie() {
        int[] coordinates = model.generateZombieSpawn();
        if (coordinates != null) {
            if (View.getBtn()[coordinates[0]][coordinates[1]].getIcon().toString().equals(View.GRASS_IMAGE)) {
                View.getBtn()[coordinates[0]][coordinates[1]] = new NodeButton<>(new BoardNode(), new Zombie(50,20));
            }
        }
    }

    public void moveZombie() {

        int[] coordinates = model.getZombieLocation();
        for (int i = 0; i < coordinates.length-1; i+=2) {
            if (coordinates[i+1] == -1) continue;   //no zombie there
            if (coordinates[i+1] != 0 && View.getBtn()[coordinates[i]][coordinates[i+1] -1].getIcon().toString().equals(View.GRASS_IMAGE)) {
                View.getBtn()[coordinates[i]][coordinates[i+1]].setImage(new ImageIcon(View.GRASS_IMAGE));
                View.getBtn()[coordinates[i]][coordinates[i+1]-1] = new NodeButton<>(new BoardNode(),
                        new Zombie(View.getBtn()[coordinates[i]][coordinates[i+1]].getZombie().getHealth(),
                                View.getBtn()[coordinates[i]][coordinates[i+1]].getZombie().getAttackPower()));
            }
        }

    }

    public void gameOver(){
        if (model.hasLost()){
            JOptionPane.showMessageDialog(null, "You Lost");
            System.exit(0);
        }
    }


    //ignore until we fix zombies showing on board using NodeButton
    public void fight(){
        int[] zombieLocation = model.getZombieLocation();
        int[] plantLocation = model.getPlantLocation();

        for (int i = 0; i < zombieLocation.length; i+=2) {
            if (zombieLocation[i] == -1 || plantLocation[i] == -1) continue;
            if (zombieLocation[i] == plantLocation[i]){
                for (int j = 1; j < zombieLocation.length; j+=2) {
                    //zombie is far from plant, so plant is only fighting
                    if (zombieLocation[j] - plantLocation[j] > 1){
                        View.getBtn()[plantLocation[i]][plantLocation[j]].getPlant().attack(
                                View.getBtn()[zombieLocation[i]][zombieLocation[j]].getZombie());
                    }
                    //zombie and plant fighting
                    else if (zombieLocation[j] - plantLocation[j] == 1){
                        View.getBtn()[plantLocation[i]][plantLocation[j]].getPlant().attack(
                                View.getBtn()[zombieLocation[i]][zombieLocation[j]].getZombie());

                        View.getBtn()[zombieLocation[i]][zombieLocation[j]].getZombie().attack(
                                View.getBtn()[plantLocation[i]][plantLocation[j]].getPlant());
                    }

                    if (!View.getBtn()[plantLocation[i]][plantLocation[j]].getPlant().isAlive()){
                        View.getBtn()[plantLocation[i]][plantLocation[j]].setImage(new ImageIcon(View.GRASS_IMAGE));
                    }
                    if (!View.getBtn()[zombieLocation[i]][zombieLocation[j]].getZombie().isAlive()){
                        View.getBtn()[zombieLocation[i]][zombieLocation[j]].setImage(new ImageIcon(View.GRASS_IMAGE));
                    }
                }
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
                        createCustomCursor(new ImageIcon(View.PLANT_ICON).getImage(),
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
                        createCustomCursor(new ImageIcon(View.SUNFLOWER_ICON).getImage(),
                                new Point(0, 0), "custom cursor"));
                Image icon = new ImageIcon(View.SUNFLOWER_IMAGE).getImage();
                view.setImg(new ImageIcon(icon));
                return;
            }
        }

        NodeButton btn = (NodeButton) e.getSource();
        int row = (int) btn.getClientProperty("row");
        int col = (int) btn.getClientProperty("column");

        if (view.getFrame().getCursor().getType() != 0) {
            //Only place it if cell is empty
            if (!View.getBtn()[row][col].getIcon().toString().equals(View.GRASS_IMAGE)) {
                JOptionPane.showMessageDialog(null, "This cell is occupied");
                return;
            }
            View.getBtn()[row][col] = new NodeButton<>(new BoardNode(), new Plant(100,30));
        }
    }

    // used to test:- places zombie at position 4,4 - if u try to place a plant there it says occupied,
    // so there is a zombie but picture doesnt show
    //this methodology works when doing it in the view in the generateBoard method.. but not here
    public void aha(){
        View.getBtn()[4][4] = new NodeButton<>(new BoardNode(), new Zombie(500,20));
    }

    public static void main(String[] args) {
        Controller c = new Controller(new View());
        while (true) {
            try {
                //c.aha();
                c.generateZombie();
                TimeUnit.SECONDS.sleep(2);
                c.fight();
                c.moveZombie();
                c.gameOver();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
