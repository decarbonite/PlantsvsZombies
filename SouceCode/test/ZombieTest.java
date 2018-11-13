import org.junit.Before;
import org.junit.Test;
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
        z = new Zombie("Zombie",100,20,10,"imgUrl.png");
    }

    @Test
    public void getPointsWhenDead() {
        assertEquals(10, z.getPointsWhenDead());
        assertNotEquals(20, z.getPointsWhenDead());
    }

    @Test
    public void isAlive() {
        assertTrue("Expected to return true.", z.isAlive());
        assertFalse("Expected to return false.", !z.isAlive());

        z.setHealth(-20);
        assertFalse("Expected to return false.", z.isAlive());
    }

    @Test
    public void getHealth() {
        assertEquals("Health expected to be 100.",100, z.getHealth());
    }

    @Test
    public void setHealth() {
        z.setHealth(20);
        assertNotEquals("Health expected to be 20.", 1, z.getHealth());
        assertEquals("Health expected to be 20.", 20, z.getHealth());
    }

    @Test
    public void getAttackPower() {
        assertEquals("Attack Power expected to be 20.",20, z.getAttackPower());
    }

    @Test
    public void getImgURL() {
        assertEquals("Image URL should be imgUrl.png.", "imgUrl.png", z.getImgURL());
    }
}