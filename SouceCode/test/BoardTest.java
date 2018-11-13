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
        board = new Board(3,4,5,100,200);
    }

    @Test
    public void testSize() {
        assertEquals("Size of board should be 3.", 3, board.getBoard().size());
    }

    @Test
    public void testRows() {
        assertEquals("Number of rows should be 3.", 3, board.getBoardRows());
    }

    @Test
    public void testColumns() {
        assertEquals("Number of columns should be 4.", 4, board.getBoardColumns());
        board = new Board(5,5,6,200,200);
        assertEquals("Number of columns should be 5.", 5, board.getBoardColumns());
    }

    @Test
    public void testGetMoney(){
        assertEquals("Money should be equal to 200", 200, board.getMoney());
        board = new Board(5,5,0,200,50);
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