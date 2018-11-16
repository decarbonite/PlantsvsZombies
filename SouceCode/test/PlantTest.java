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
    public void testIsAlive() {
        assertTrue("Plant should be alive",p.isAlive());
        assertFalse("Plant should not be alive",!p.isAlive());

        p.setHealth(-2);
        assertFalse("Plant should not be alive", p.isAlive());
    }

    @Test
    public void testGetHealth() {

        assertEquals("Health expected to be 100.",100, p.getHealth());
        assertNotEquals(10, p.getHealth());

        p.setHealth(10);
        assertEquals("Health expected to be 10.",10, p.getHealth());
        assertNotEquals(20, p.getHealth());
    }

    @Test
    public void testSetHealth() {

        assertEquals("Health should be 100.",100, p.getHealth());
        p.setHealth(10);
        assertNotEquals("Health should be 10.",100, p.getHealth());
        assertEquals("Health should be 10.",10, p.getHealth());
    }

    @Test
    public void testGetAttackPower() {
        assertEquals("Attack power should be 20.",20, p.getAttackPower());
        assertNotEquals("Attack power should be 20.",10, p.getAttackPower());
    }


    @Test
    public void testGetImgURL() {
        assertEquals("Image URL should be imgURL.png", "imgUrl.png", p.getImgURL());
        assertNotEquals("Image URL should be imgURL.png", "imgUrl.jpg", p.getImgURL());
        assertNotEquals("Image URL should be imgURL.png", "imgURL.png", p.getImgURL());
    }
}