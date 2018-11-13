import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class BoardNodeTest {

    private BoardNode node = null;
    @Before
    public void setUp(){
        node = new BoardNode();
    }
    @Test
    public void destroyZombie() {
        assertNull(node.destroyZombie());
        node.addZombie(new Zombie("z", 1,2,"2"));
        assertNotNull(node.destroyZombie());
    }

    @Test
    public void addPlant() {
        assertNull(node.getPlant());
        node.addPlant(new Plant("z", 1,2,"2"));
        assertNotNull(node.getPlant());
    }

    @Test
    public void plantFightZombie() {
    }

    @Test
    public void plantFightZombie1() {
    }

    @Test
    public void addZombie() {
        assertNull(node.getZombie());
        node.addZombie(new Zombie("z", 1,2,"2"));
        assertNotNull(node.getZombie());
    }

    @Test
    public void hasZombie() {
    }

    @Test
    public void hasPlant() {
    }

    @Test
    public void getPlant() {
    }

    @Test
    public void getMoneyPlant() {
    }

    @Test
    public void getZombie() {
    }

    @Test
    public void hasMoneyPlant() {
    }
}