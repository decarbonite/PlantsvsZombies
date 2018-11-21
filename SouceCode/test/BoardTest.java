
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static junit.framework.Assert.*;


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
    public void testAddPlant() {
        assertNull("Plant should not be added.",board.addPlant(-1,4, new Plant("p", 1,new ImageIcon("pic.png"))));
        assertNull("Plant should not be added.",board.addPlant(-1,-4, new Plant("p", 1,new ImageIcon("pic.png"))));
        assertNull("Plant should not be added.",board.addPlant(1,-4, new Plant("p", 1,new ImageIcon("pic.png"))));
        assertNull("Plant should not be added.",board.addPlant(2,9, new Plant("p", 1,new ImageIcon("pic.png"))));
        assertNull("Plant should not be added.",board.addPlant(10,4, new Plant("p", 1,new ImageIcon("pic.png"))));

        board.addPlant(2,4, new Plant("p", 1,new ImageIcon("pic.png")));
        assertTrue("Plant should be added", Board.getBoard().get(2).getRow().get(4).hasPlant());
    }

    @Test
    public void testAddZombie() {

        assertFalse("Zombie should not be added.",board.addZombie(-1,4, new Zombie("z", 1,new ImageIcon("pic.png"))));
        assertFalse("Zombie should not be added.",board.addZombie(-1,-4, new Zombie("z", 1,new ImageIcon("pic.png"))));
        assertFalse("Zombie should not be added.",board.addZombie(1,-4, new Zombie("z", 1,new ImageIcon("pic.png"))));
        assertFalse("Zombie should not be added.",board.addZombie(2,9, new Zombie("z", 1,new ImageIcon("pic.png"))));
        assertFalse("Zombie should not be added.",board.addZombie(10,4, new Zombie("z", 1,new ImageIcon("pic.png"))));

        assertTrue("Zombie should be added", board.addZombie(2,8, new Zombie("z", 1,new ImageIcon("pic.png"))));
    }
}
