import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @author Ahmed Romih (decarbonite)
 * @version 12 November, 2018
 */
public class MoneyPlantTest {
    MoneyPlant mp = null;

    @Before
    public void setUp() {
        mp = new MoneyPlant("Plant",200,20,"imgUrl.png");
    }

    @Test
    public void getMoney() {
        assertEquals("Money should be 20.", 20, mp.getMoney());
        assertNotEquals(10, mp.getMoney());
    }

    @Test
    public void isAlive() {
        assertEquals(true, mp.isAlive());
        assertNotEquals(false, mp.isAlive());

        mp.setHealth(-2);
        Assert.assertEquals(false, mp.isAlive());
        Assert.assertNotEquals(true, mp.isAlive());
    }

    @Test
    public void getHealth() {
        assertEquals("Health should be 200.", 200, mp.getHealth());
        assertNotEquals("Health should be 200.",100, mp.getHealth());

        mp.setHealth(20);
        assertEquals("Health should be 20.",20, mp.getHealth());
        assertNotEquals("Health should be 20.", 10, mp.getHealth());
    }

    @Test
    public void setHealth() {
        mp.setHealth(5);
        assertEquals("Health should be 5.", 5, mp.getHealth());
        assertNotEquals("Health should be 5.",100, mp.getHealth());
    }

    @Test
    public void getAttackPower() {
        assertEquals(0, mp.getAttackPower());
        assertNotEquals(10, mp.getAttackPower());
    }

    @Test
    public void getImgURL() {
        assertEquals("Image URL should be imgUrl.png.","imgUrl.png", mp.getImgURL());
    }
}