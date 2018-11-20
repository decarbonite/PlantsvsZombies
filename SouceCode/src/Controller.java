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
 * @version 08 November, 2018
 */
@SuppressWarnings("Duplicates")
public class Controller implements ActionListener {
    private View view;
    private Board model;
    private static Stack<Plant> undoStack;          //stack with all plants added to board or redo-ed
    private static Stack<Plant> redoStack;          //stack of the plants that were undo-ed
    private static Stack<Integer> undoCoordinate;   //x,y position of plants being added or redo-ed
    private static Stack<Integer> redoCoordinate;   //x,y position of plants being that were undo-ed

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

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.getShootFlowerButton()) {
            if (view.getFrame().getCursor().getType() != 0) {
                view.getFrame().setCursor(DEFAULT_CURSOR);
                return;
            } else {
                view.getFrame().setCursor(Toolkit.getDefaultToolkit().
                        createCustomCursor(new ImageIcon(this.getClass().getResource(View.PLANT_ICON)).getImage(),
                                new Point(0, 0), "plant"));
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
            //to make the removal instant, could be removed,
            //but it would wait for the model to update to automatically remove the plant
            View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.GRASS_IMAGE)));

        }

        //Redo clicked
        if (e.getSource() == view.getRedo()) {
            if (redoStack.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nothing to redo");
                return;
            }

            Plant plant = redoStack.pop();
            int i = redoCoordinate.pop();
            int j = redoCoordinate.pop();

            undoStack.push(Board.getBoard().get(i).getRow().get(j).addPlant(plant));
            //undoStack.push("Sunflower");
            undoCoordinate.push(j);
            undoCoordinate.push(i);
            //to make the addition to GUI instant, could be removed,
            //but the GUI would wait for the model to update to automatically add the plant
            if (plant instanceof MoneyPlant) {
                View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.SUNFLOWER_IMAGE)));
                return;
            }
            View.getBtn()[i][j].stringToImageConverter(new ImageIcon(this.getClass().getResource(View.PLANT_IMAGE)));
        }

        //Placing Plants
        if (e.getSource() instanceof JButton) {
            JButton btn = (JButton) e.getSource();
            int row = (int) btn.getClientProperty("row");
            int col = (int) btn.getClientProperty("column");

            if (view.getFrame().getCursor().getType() != 0) {
                //Only place it if cell is empty
                BoardNode bn = (BoardNode) View.getBtn()[row][col].getObject();
                if (bn.hasPlant() || bn.hasZombie()) {
                    JOptionPane.showMessageDialog(null, "This cell is occupied");
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
                if (toPlant.equals("sunflower")) {
                    undoCoordinate.push(col);
                    undoCoordinate.push(row);
                    undoStack.push(model.addPlant(row, col, new MoneyPlant("Sunflower", 60, 25, new ImageIcon(this.getClass().getResource(View.SUNFLOWER_IMAGE)))));
                }

                //Change cursor back to default
                if (view.getFrame().getCursor().getType() != 0) {
                    view.getFrame().setCursor(DEFAULT_CURSOR);
                }
                view.updateView();
            }
        }

    }
}
