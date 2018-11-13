import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class ZombieTest {
    String zName = "Zombie";
    int zHelth = 100;
    int zAttackPower = 20;
    int zScoreOnDeth = 10;
    String zImageURL = "imgUrl.png";

    @Test
    public void getPointsWhenDead() {
        Zombie z = new Zombie(zName, zHelth, zAttackPower, zScoreOnDeth, zImageURL);
        Assert.assertEquals(zScoreOnDeth, z.getPointsWhenDead());
        Assert.assertNotEquals(20, z.getPointsWhenDead());
    }

    @Test
    public void isAlive() {
        Zombie z = new Zombie(zName, zHelth, zAttackPower, zScoreOnDeth, zImageURL);
        Assert.assertEquals(true, z.isAlive());
        Assert.assertNotEquals(false, z.isAlive());

        z.setHealth(-20);
        Assert.assertEquals(false, z.isAlive());
        Assert.assertNotEquals(true, z.isAlive());

    }

    @Test
    public void getHealth() {
        Zombie z = new Zombie(zName, zHelth, zAttackPower, zScoreOnDeth, zImageURL);
        Assert.assertEquals(zHelth, z.getHealth());
    }

    @Test
    public void setHealth() {
        Zombie z = new Zombie(zName, zHelth, zAttackPower, zScoreOnDeth, zImageURL);
        z.setHealth(20);
        Assert.assertNotEquals(zHelth, z.getHealth());
        Assert.assertEquals(20, z.getHealth());
    }

    @Test
    public void getAttackPower() {
        Zombie z = new Zombie(zName, zHelth, zAttackPower, zScoreOnDeth, zImageURL);
        Assert.assertEquals(zAttackPower, z.getAttackPower());
    }

    @Test
    public void getImgURL() {
        Zombie z = new Zombie(zName, zHelth, zAttackPower, zScoreOnDeth, zImageURL);
        Assert.assertEquals(zImageURL, z.getImgURL());
    }
}