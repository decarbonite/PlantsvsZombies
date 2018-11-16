import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 12 November, 2018
 */
public class BoardTest {
    private static Board board = null;

    @Before
    public void setUp() {
        board = new Board(5,100,200);
    }

    @Test
    public void testSize() {
        assertEquals("Number of board rows should be 5.", 5, board.getBoard().size());
    }

    @Test
    public void testGetMoney(){
        assertEquals("Money should be equal to 200", 200, board.getMoney());
        board = new Board(0,200,50);
        assertEquals("Money should be equal to 50", 50, board.getMoney());
    }

    @Test
    public void runBoard() {
    }

    @Test
    public void generateZombieSpawn() {

    }

    @Test
    public void addPlant() {
    }

    @Test
    public void addZombie() {
    }
}