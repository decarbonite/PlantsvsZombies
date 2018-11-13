import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class MoneyPlantTest {
    String mpName = "Plant";
    int mpHelth = 100;
    int mpMoney = 20;
    String mpImageURL = "imgUrl.png";

    @Test
    public void getMoney() {
        MoneyPlant mp = new MoneyPlant(mpName, mpHelth, mpMoney, mpImageURL);

        Assert.assertEquals(mpMoney, mp.getMoney());
        Assert.assertNotEquals(10, mp.getMoney());
    }

    @Test
    public void isAlive() {
        MoneyPlant mp = new MoneyPlant(mpName, mpHelth, mpMoney, mpImageURL);
        Assert.assertEquals(true, mp.isAlive());
        Assert.assertNotEquals(false, mp.isAlive());

        mp.setHealth(-2);
        Assert.assertEquals(false, mp.isAlive());
        Assert.assertNotEquals(true, mp.isAlive());
    }

    @Test
    public void getHealth() {
        MoneyPlant mp = new MoneyPlant(mpName, mpHelth, mpMoney, mpImageURL);
        Assert.assertEquals(mpHelth, mp.getHealth());
        Assert.assertNotEquals(10, mp.getHealth());

        mp.setHealth(20);
        Assert.assertEquals(20, mp.getHealth());
        Assert.assertNotEquals(10, mp.getHealth());
    }

    @Test
    public void setHealth() {
        MoneyPlant mp = new MoneyPlant(mpName, mpHelth, mpMoney, mpImageURL);
        mp.setHealth(10);
        Assert.assertEquals(10, mp.getHealth());
        Assert.assertNotEquals(100, mp.getHealth());
    }

    @Test
    public void getAttackPower() {
        MoneyPlant mp = new MoneyPlant(mpName, mpHelth, mpMoney, mpImageURL);
        Assert.assertEquals(0, mp.getAttackPower());
        Assert.assertNotEquals(10, mp.getAttackPower());
    }

    @Test
    public void getImgURL() {
        MoneyPlant mp = new MoneyPlant(mpName, mpHelth, mpMoney, mpImageURL);
        Assert.assertEquals(mpImageURL, mp.getImgURL());
    }
}