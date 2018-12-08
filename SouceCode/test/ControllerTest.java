import org.junit.Before;
import org.junit.Test;
import javax.swing.*;
import static junit.framework.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 21 November, 2018
 */
public class ControllerTest {

    private Controller controller = null;
    private Board model = null;
    private View view = null;

    @Before
    public void setUp() {
        view = new View("Player");
        model = new Board(0, 0, 100);

        controller = new Controller(view, model);
    }


    @Test
    public void testGenerateBoard() {
        for(int i = 0; i < view.BOARD_ROWS; i++) {
            for(int j = 0; j < view.BOARD_COLS; j++) {
                assertFalse("Board were not generated yet", view.getBtn()[i][j].getObject() != null);
            }
        }

        controller.generateBoard();

        for(int i = 0; i < view.BOARD_ROWS; i++) {
            for(int j = 0; j < view.BOARD_COLS; j++) {
                assertTrue("Board were generated", view.getBtn()[1][1].getObject() != null);
            }
        }
    }

    @Test
    public void testUpdateBoard() {
        controller.generateBoard();
        ImageIcon ii = new ImageIcon(this.getClass().getResource("images/grassedPlant.png"));
        assertFalse("No plant on the view", view.getBtn()[0][0].getIcon().toString().equals(ii));

        model.addPlant(0, 0, new Plant("Plant", 100, ii));
        assertFalse("No plant on the view", view.getBtn()[0][0].getIcon().toString().equals(ii));

        controller.updateBoard();
        assertTrue("There is a plant on the view", view.getBtn()[0][0].getIcon().toString().equalsIgnoreCase(ii.toString()));
    }
}
