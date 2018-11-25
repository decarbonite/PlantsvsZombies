import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 21 November, 2018
 */
public class ViewTest {

    View view = null;

    @Before
    public void setUp(){
        view = new View();
    }


    @Test
    public void testView(){
        for (int i = 0; i < View.BOARD_ROWS; i++) {
            for (int j = 0; j < View.BOARD_COLS; j++) {
                assertTrue("Should be true", View.getBtn()[i][j].isDisplayable());
            }
        }
    }
}
