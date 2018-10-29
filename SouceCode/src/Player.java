/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */

import java.io.*;

public class Player {
    private String playerName;
    private int money;
    private int score;
    private static int totalRounds;

    public Player () {
		try {
			TakeInputs();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went Wrong");
		}
		this.money = 100;
		this.score = 0;
	}

	public void updateScore() {
		//this.score =
	}

	public void Money() {
		
	}

	public void TakeInputs() throws IOException{
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter Player Name\n");
        playerName = bufferedReader.readLine();
        System.out.print("Enter Number of Rounds to Play\n");
        try{
            totalRounds = Integer.parseInt(bufferedReader.readLine());
        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        }
        
	}
	
	//main method for testing purpose
    public static void main (String[] args) {
    	Player aPlayer = new Player ();
	}
}