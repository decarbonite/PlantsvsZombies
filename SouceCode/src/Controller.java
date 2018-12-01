import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import static java.awt.Cursor.DEFAULT_CURSOR;

/**
 * Connects model (Board) and visualisation to perform MVC model
 * Listen for the events from the View, agrigates it calls and send signal for modification into the model and view.
 *
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 24 November, 2018
 */

public class Controller implements ActionListener {
    private View view;
    private Board model;
    private static Stack<Plant> undoStack;          //stack with all plants added to board or redo-ed
    private static Stack<Plant> redoStack;          //stack of the plants that were undo-ed
    private static Stack<Integer> undoCoordinate;   //x,y position of plants being added or redo-ed
    private static Stack<Integer> redoCoordinate;   //x,y position of plants that were undo-ed

    /**
     * Default constructor that requires only View to be passed
     * Model creates automatically with 10 zombies to spawn, zero score and 200 in-game money
     *
     * @param v View object
     */
    public Controller(View v) {
        this(v, new Board(10, 0, 200));
    }

    /**
     * Default constructor that requires both Model and View objects in order make linkage between them.
     *
     * @param v View object
     * @param m Model (Board) object
     */
    public Controller(View v, Board m) {
        this.view = v;
        this.model = m;
        undoStack = new Stack<>();
        undoCoordinate = new Stack<>();
        redoStack = new Stack<>();
        redoCoordinate = new Stack<>();
    }

    /**
     * Sends command to the View object to generate initial board
     */
    public void generateBoard() {
        view.linkModelView(model.board);
    }

    /**
     * Sends command to the Model and View in order to synchronize changes in model and view
     */
    public void updateBoard() {
        model.runBoard();
        view.updateView();
    }

    /**
     * Check if the condition for ending game is reached
     */
    public void gameEnded() {
        if (model.hasWon()) {
            JOptionPane.showMessageDialog(null, "You Won!");
            System.exit(0);
        }
        if (model.hasLost()) {
            JOptionPane.showMessageDialog(null, "You Lost!");
            System.exit(0);
        }
    }

    private void undo() {
        if (undoStack.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nothing to undo");
            return;
        }

        undoStack.pop();
        int i = undoCoordinate.pop();      // button x location
        int j = undoCoordinate.pop();      // button y location
        redoCoordinate.push(j);
        redoCoordinate.push(i);

        redoStack.push(Board.getBoard().get(i).getRow().get(j).removePlant()); //remove from board and push to stack
        //decrease money by 50 on undo because everything cost 50 points
        Board.setMoney(Board.getMoney() + 50);

        //Next 2 lines are not necessary. It makes the visual update instant, However,
        // the model would update the view from the previous line of code
        // but it would wait 3 seconds because of the delay function
        view.getMoneyLabel().setText(Integer.toString(Board.getMoney()));
        View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.GRASS_IMAGE)));
    }

    private void redo() {
        if (redoStack.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nothing to redo");
            return;
        }

        Plant plant = redoStack.pop();
        int i = redoCoordinate.pop();   //x coordinate of plant on board
        int j = redoCoordinate.pop();   //y coordinate of plant on board

        BoardNode node = (BoardNode) View.getBtn()[i][j].getObject();
        // if doing redo on a cell that is full now, bring back what was popped from the stack as if nothing changed
        if(node.hasPlant() || node.hasZombie()){
            redoStack.push(plant);
            redoCoordinate.push(j);
            redoCoordinate.push(i);
            JOptionPane.showMessageDialog(null, "Cant redo because the cell is currently occupied");
            return;
        }

        undoStack.push(Board.getBoard().get(i).getRow().get(j).addPlant(plant));
        Board.setMoney(Board.getMoney() - 50);
        view.getMoneyLabel().setText(Integer.toString(Board.getMoney()));
        undoCoordinate.push(j);
        undoCoordinate.push(i);
        //to make the addition to GUI instant, could be removed,
        //but the GUI would wait for the model to update to automatically add the plant
        if (plant instanceof MoneyPlant) {
            if (plant.getName().equals("Sunflower")) {
                View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.SUNFLOWER_IMAGE)));
                return;
            } else if (plant.getName().equals("Sunflower2")) {
                View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.SUNFLOWER2_IMAGE)));
                return;
            }
        } else if (plant.getName().equals("Plant")) {
            View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.PLANT_IMAGE)));
            return;
        } else if (plant.getName().equals("Plant2")) {
            View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.PLANT2_IMAGE)));
            return;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.getShootFlowerButton()) {
            // if cursor image is a custom image(plant) and clicked on a plant, change back to default cursor
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            }
            // change cursor to plant image
            else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(this.getClass().getResource(View.PLANT_ICON)).getImage(),
                                new Point(0, 0), "plant"));
                return;
            }
        }

        if (e.getSource() == view.getStrongPlant()) {
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            } else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(this.getClass().getResource(View.PLANT2_ICON)).getImage(),
                                new Point(0, 0), "plant2"));
                return;
            }
        }

        if (e.getSource() == view.getDblSunflowerButton()) {
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            } else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(this.getClass().getResource(View.SUNFLOWER2_ICON)).getImage(),
                                new Point(0, 0), "sunflower2"));
                return;
            }
        }

        if (e.getSource() == view.getSunflowerButton()) {
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            } else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(this.getClass().getResource(View.SUNFLOWER_ICON)).getImage(),
                                new Point(0, 0), "sunflower"));
                return;
            }
        }

        //Undo clicked
        if (e.getSource() == view.getUndo()) {
            undo();
        }

        //Redo clicked
        if (e.getSource() == view.getRedo()) {
            redo();
        }

        if (e.getSource() == view.getSave()){
            model.saveGame();
        }

        if (e.getSource() == view.getLoad()){
            model.loadGame();
            view.linkModelView(Board.getBoard());
            view.updateView();
            //updateBoard();
        }

        //Placing Plants
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            int row = (int) btn.getClientProperty("row");
            int col = (int) btn.getClientProperty("column");

            if (view.getFrame().getCursor().getType() != 0) {
                //Only place it if cell is empty
                BoardNode bn = (BoardNode) View.getBtn()[row][col].getObject();
                if (Board.getMoney() < 50) {
                    JOptionPane.showMessageDialog(null, "Not Sufficient Money");
                    view.getFrame().setCursor(DEFAULT_CURSOR);
                    return;
                }else if (bn.hasPlant() || bn.hasZombie()) {
                    JOptionPane.showMessageDialog(null, "This cell is occupied");
                    view.getFrame().setCursor(DEFAULT_CURSOR);
                    return;
                }
                String toPlant = view.getFrame().getCursor().getName();
                //Add shooting plant
                if (toPlant.equals("plant")) {
                    undoCoordinate.push(col);
                    undoCoordinate.push(row);
                    undoStack.push(model.addPlant(row, col, new Plant("Plant", 100, 20, new ImageIcon(this.getClass().getResource(View.PLANT_IMAGE)))));
                }

                //Add sunflower to generate money
                else if (toPlant.equals("sunflower")) {
                    undoCoordinate.push(col);
                    undoCoordinate.push(row);
                    undoStack.push(model.addPlant(row, col, new MoneyPlant("Sunflower", 60, 15, new ImageIcon(this.getClass().getResource(View.SUNFLOWER_IMAGE)))));
                }

                else if (toPlant.equals("plant2")) {
                    undoCoordinate.push(col);
                    undoCoordinate.push(row);
                    undoStack.push(model.addPlant(row, col, new Plant("Plant2", 150, 30, new ImageIcon(this.getClass().getResource(View.PLANT2_IMAGE)))));
                }

                else if (toPlant.equals("sunflower2")) {
                    undoCoordinate.push(col);
                    undoCoordinate.push(row);
                    undoStack.push(model.addPlant(row, col, new MoneyPlant("Sunflower2", 100, 25, new ImageIcon(this.getClass().getResource(View.SUNFLOWER2_IMAGE)))));
                }
                //Change cursor back to default after placing the plant
                view.getFrame().setCursor(DEFAULT_CURSOR);
                view.updateView();
            }
        }
    }
}
