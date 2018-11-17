import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 12 November, 2018
 */
public class BoardRowTest {
    private BoardRow br = null;
    private Zombie z = null;
    private Plant p = null;
    private int health = 100;
    private int aPower = 10;
    private int deathScore = 10;

    @Before
    public void setUp() {
        br = new BoardRow();
        z = new Zombie("Zombie", health,aPower, deathScore,new ImageIcon("pic.png"));
        p = new Plant("Plant", health, aPower, new ImageIcon("pic.png"));
    }

    @Test
    public void testAddZombie() {
        int index = 6;
        br.addZombie(index, z);
        for(int i = 0; i < View.BOARD_COLS; i++){
            if(i == index){
                assertTrue("Zombie is on cell index: "+i, br.hasZombie(i));
            } else {
                assertFalse("Zombie is not on cell index: " + i, br.hasZombie(i));
            }
        }
    }

    @Test
    public void testMoveZombie() {
        br.addZombie(4, z);
        br.moveZombie();
        assertFalse("Zombie moved from 4", br.hasZombie(4));
        assertTrue("Zombie now is on 3", br.hasZombie(3));
    }

    @Test
    public void testAddPlant() {
        int index = 2;
        br.addPlant(index, p);
        for(int i = 0; i < View.BOARD_COLS; i++){
            if(i == index){
                assertTrue("Plant is on cell index: "+i, br.hasPlant(i));
            } else {
                assertFalse("Plant is not on cell index: " + i, br.hasPlant(i));
            }
        }
    }

    @Test
    public void testHasZombie() {
        for(int i = 0; i < View.BOARD_COLS; i++){
            assertFalse("Zombie is not on cell index: " + i, br.hasZombie(i));
        }
    }

    @Test
    public void testHasPlant() {
        for(int i = 0; i < View.BOARD_COLS; i++){
            assertFalse("Plant is not on cell index: " + i, br.hasPlant(i));
        }
    }

    @Test
    public void testFightPvsZInOneCell() {
        int score = 0;
        br.addPlant(0, p);
        br.addZombie(0, z);
        br.fightPvsZ(score);
        assertTrue("Plant fight Zombie", z.getHealth() == health - aPower);
        assertTrue("Zombie fight Plant", p.getHealth() == health - aPower);
    }

    @Test
    public void testFightPvsZOnDistance() {
        int score = 0;
        br.addPlant(0, p);
        br.addZombie(4, z);
        br.fightPvsZ(score);

        assertTrue("Plant fight Zombie on distance. Should be 90.  Is: "+ z.getHealth(), z.getHealth() == health - aPower);
        assertTrue("Plant fight Zombie on distance. Should be 100. Is: "+ p.getHealth(), p.getHealth() == health);
    }

    @Test
    public void testGenerateMoney() {
        z.setHealth(aPower);
        br.addPlant(0, p);
        br.addZombie(4, z);

        int score = 0;
        score = br.fightPvsZ(score);
        assertTrue("Score raised for killing Zombie. Should be "+ deathScore +". Is: "+ score, score == deathScore);
    }

    @Test
    public void testGetRow() {
        ArrayList<BoardNode> rowReturn = br.getRow();
        for(int i = 0; i < View.BOARD_COLS; i++){
            assertTrue("Is BoardNode instance", rowReturn.get(i) instanceof BoardNode);
        }

        assertTrue("Number of columns in the row is matched", rowReturn.size() == View.BOARD_COLS

        );
    }
}