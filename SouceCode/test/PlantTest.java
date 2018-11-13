import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class PlantTest {
    String pName = "Plant";
    int pHelth = 100;
    int pAttackPower = 20;
    String pImageURL = "imgUrl.png";

    @Test
    public void isAlive() {
        Plant p = new Plant(pName, pHelth, pAttackPower, pImageURL);
        Assert.assertEquals(true, p.isAlive());
        Assert.assertNotEquals(false, p.isAlive());

        p.setHealth(-2);
        Assert.assertEquals(false, p.isAlive());
        Assert.assertNotEquals(true, p.isAlive());
    }

    @Test
    public void getHealth() {
        Plant p = new Plant(pName, pHelth, pAttackPower, pImageURL);
        Assert.assertEquals(pHelth, p.getHealth());
        Assert.assertNotEquals(10, p.getHealth());

        p.setHealth(20);
        Assert.assertEquals(20, p.getHealth());
        Assert.assertNotEquals(10, p.getHealth());
    }

    @Test
    public void setHealth() {
        Plant p = new Plant(pName, pHelth, pAttackPower, pImageURL);
        p.setHealth(10);
        Assert.assertEquals(10, p.getHealth());
        Assert.assertNotEquals(100, p.getHealth());
    }

    @Test
    public void getAttackPower() {
        Plant p = new Plant(pName, pHelth, pAttackPower, pImageURL);

        Assert.assertEquals(pAttackPower, p.getAttackPower());
        Assert.assertNotEquals(10, p.getAttackPower());
    }

    @Test
    public void getImgURL() {
        Plant p = new Plant(pName, pHelth, pAttackPower, pImageURL);
        Assert.assertEquals(pImageURL, p.getImgURL());
    }
}