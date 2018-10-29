/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */

import java.io.*;

public class Player {
    private String playerName;
	private int money;
    private int score;
    //private static int totalRounds;

    public Player () {
		try {
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Enter Player Name\n");
			playerName = bufferedReader.readLine();
			setPlayerName(playerName);
			setMoney(100);
			System.out.println ("Player Name: " + getPlayerName()+ "/n Money: " + getMoney()+ "/n Score: " + getScore());
			System.out.print("Enter Number of Rounds to Play\n");
			try{
				//totalRounds = Integer.parseInt(bufferedReader.readLine());
			}catch(NumberFormatException nfe){
				System.err.println("Invalid Format!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Something went Wrong");
		}
		this.money = 100;
		this.score = 0;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getScore() {
		return score;
	}

	public void updateScore(int score) {
		this.score = score;
	}
}