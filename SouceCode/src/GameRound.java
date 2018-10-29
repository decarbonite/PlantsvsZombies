/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
import java.lang.Math;

public class GameRound {
    private int baseZombies = 10;
    private int multiplier;
    private int currentRound = 0;
    private static boolean roundEnds = false;
    private int max, min, score, zombies2Spawn;
    private int totalRounds = 10;
    
    public GameRound(){
    	nextRound();
    }
    
    public void nextRound () {
    	this.currentRound = currentRound + 1;
    	createMultiplier ((int)(max * score * 0.01),(int)(min * score * 0.01));
    	round();
    }
    
    private void round() {
		// TODO Auto-generated method stub
    	zombies2Spawn = ((multiplier * baseZombies * currentRound)/(currentRound * 5));
    	if (roundEnds == true && totalRounds > 0) {
    		this.totalRounds --;
    		nextRound();
    	}
    	else if (roundEnds == true && totalRounds == 0) {
    		System.out.println("GAME HAS ENDED");
    	}
    		/*To DO:
    		 * 		Determine when and how the round ends
    		 * 		Determine when and how the next round starts
    		 */
	}

	public void createMultiplier (int max, int min) {
    	int range = max - min + 1;
    	this.multiplier = (int) (Math.random() * range) + min;
    }
    
    //main method for testing purpose
    public static void main (String[] args) {	
    	
	}
}
