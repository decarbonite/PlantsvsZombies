import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class ZombieTest {

    private Zombie z = null;

    @Before
    public void setUp() {
        z = new Zombie("Zombie",100,20,10,new ImageIcon("pic.png"));
    }

    @Test
    public void testGetPointsWhenDead() {
        assertEquals("Points should be 10", 10, z.getPointsWhenDead());
        assertNotEquals("Points should be 10", 20, z.getPointsWhenDead());
    }

    @Test
    public void testIsAlive() {
        assertTrue("Should return true.", z.isAlive());
        assertFalse("Should return false.", !z.isAlive());

        z.setHealth(-20);
        assertFalse("Should return false.", z.isAlive());
    }

    @Test
    public void testGetHealth() {
        assertEquals("Health should be 100.",100, z.getHealth());
    }

    @Test
    public void testSetHealth() {
        z.setHealth(20);
        assertNotEquals("Health expected to be 20.", 1, z.getHealth());
        assertEquals("Health expected to be 20.", 20, z.getHealth());
    }

    @Test
    public void testGetAttackPower() {
        assertEquals("Attack Power expected to be 20.",20, z.getAttackPower());
    }

    @Test
    public void testGetImgURL() {
        assertEquals("Image URL should be imgUrl.png.", "pic.png", z.getImgURL().toString());
    }
}