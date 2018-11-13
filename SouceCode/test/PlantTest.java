import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class PlantTest {

    private Plant p = null;

    @Before
    public void setUp() {
        p = new Plant("Plant",100,20,"imgUrl.png");
    }

    @Test
    public void isAlive() {
        assertTrue(p.isAlive());
        assertFalse(!p.isAlive());

        p.setHealth(-2);
        assertFalse(p.isAlive());
    }

    @Test
    public void getHealth() {

        assertEquals("Health expected to be 100.",100, p.getHealth());
        assertNotEquals(10, p.getHealth());

        p.setHealth(10);
        assertEquals("Health expected to be 10.",10, p.getHealth());
        assertNotEquals(20, p.getHealth());
    }

    @Test
    public void setHealth() {
        p.setHealth(10);
        assertEquals(10, p.getHealth());
        assertNotEquals(100, p.getHealth());
    }

    @Test
    public void getAttackPower() {
        assertEquals(20, p.getAttackPower());
        assertNotEquals(10, p.getAttackPower());
    }


    @Test
    public void getImgURL() {
        assertEquals("imgUrl.png", p.getImgURL());
    }
}