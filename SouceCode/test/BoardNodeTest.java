import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 12 November, 2018
 */
public class BoardNodeTest {

    private BoardNode node = null;

    @Before
    public void setUp(){
        node = new BoardNode();
    }


    @Test
    public void testDestroyZombie() {
        assertNull(node.destroyZombie());
        node.addZombie(new Zombie("z", 1,2,"pic.png"));
        assertNotNull(node.destroyZombie());
    }

    @Test
    public void testAddPlant() {
        assertNull(node.getPlant());
        node.addPlant(new Plant("z", 1,2,"pic.png"));
        assertNotNull(node.getPlant());
    }

    @Test
    public void testPlantFightZombie() {
        node.addZombie(new Zombie("z", 50, 10, "pic.png"));
        node.addPlant(new Plant("p", 50, 10, "pic.png"));

        assertEquals("Zombie's health should be 50.",50, node.getZombie().getHealth());
        assertEquals("Plant's health should be 50.",50, node.getPlant().getHealth());

        node.plantFightZombie();    //fight, decrease health by 10 for both objects

        assertEquals("Zombie's health should be 40.",40, node.getZombie().getHealth());
        assertEquals("Plant's health should be 40.",40, node.getPlant().getHealth());

        assertNotEquals("Zombie's health should be 40.",50, node.getZombie().getHealth());
        assertNotEquals("Plant's health should be 40.",50, node.getPlant().getHealth());

        Zombie z = new Zombie("z", 100, 10, "pic.png");
        node.addZombie(z);
        node.plantFightZombie(z);     //fight, decrease zombie's health by 10

        assertEquals("Zombie's health should be 90.", 90, node.getZombie().getHealth());

        //Plant's health unchanged
        assertEquals("Plant's health should be 40.", 40, node.getPlant().getHealth());
    }


    @Test
    public void testAddZombie() {
        assertNull("Zombie should be null.", node.getZombie());
        node.addZombie(new Zombie("z", 1,2,"pic.png"));
        assertNotNull("Zombie should not be null.", node.getZombie());
    }

    @Test
    public void testHasZombie() {
        assertFalse("Should be false.", node.hasZombie());
        node.addZombie(new Zombie("z", 100, 10, "pic.png"));
        assertTrue("Should be true.", node.hasZombie());
    }

    @Test
    public void testHasPlant() {
        assertFalse("Should be false.", node.hasPlant());
        node.addPlant(new Plant("p", 100, 10, "pic.png"));
        assertTrue("Should be true.", node.hasPlant());
    }

    @Test
    public void testGetPlant() {
        assertNull("Should be null.", node.getPlant());
        node.addPlant(new Plant("p",1,"pic.png"));
        assertNotNull("Should not be null.", node.getPlant());
    }

    @Test
    public void testGetMoneyPlant() {
        assertNull("Should be null.", node.getMoneyPlant());
        node.addPlant(new MoneyPlant("Money Plant",1,2,"pic.png"));
        assertNotNull("Should not be null.", node.getMoneyPlant());
    }

    @Test
    public void testGetZombie() {
        assertNull("Should be null.", node.getZombie());
        node.addZombie(new Zombie("z",1,"pic.png"));
        assertNotNull("Should not be null.", node.getZombie());
    }

    @Test
    public void testHasMoneyPlant() {
        assertFalse("Should be false.", node.hasMoneyPlant());
        node.addPlant(new MoneyPlant("Money Plant",1,2,"pic.png"));
        assertTrue("Should not be true.", node.hasMoneyPlant());
        assertTrue("Should not be true.", node.hasPlant());
    }
}