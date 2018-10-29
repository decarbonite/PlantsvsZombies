import java.util.Scanner;

/**
 * Starting point of the Plant vs Zombie game
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 *
 * @author Ahmed Romih (decarbonite)
 * @version 27 October, 2019
 */
public class Game {
    /**
     * Entering point of the game
     * @param args String[] - not used in this game
     */
    public static void main(String[] args) {
        int numberOfRows = 5;
        int numberOfColumns = 10;

        Player newPlayer = new Player();

        int choice=0;
        int plantX=0;
        int plantY=0;

        Board board = new Board(numberOfRows, numberOfColumns, 20, newPlayer.getScore(), newPlayer.getMoney());

        while(true) {
            boolean[] res = board.gameEnds();
            Scanner scan = new Scanner(System.in);

            while (true) {
                System.out.println(" 1  - Add a plant");
                System.out.println(" 2  - Add Sun Flower (to generate money)");
                System.out.println(" 3  - Play with no plants for now");
                choice = scan.nextInt();
                if (choice == 1 || choice == 2){
                    System.out.println("Enter row position (1 -> "+ (numberOfRows) +"): ");
                    plantX = scan.nextInt() - 1;
                    System.out.println("Enter column position (1 -> "+ (numberOfColumns - 1) +"): ");
                    plantY = scan.nextInt() - 1;

                    if(choice == 1 && board.addPlant(plantX, plantY, new Plant("Plant", 200, 15))){
                        break;
                    } else if (choice == 2 && board.addPlant(plantX, plantY, new MoneyPlant("SunFlower", 75, 25))){
                        break;
                    } else {
                        System.out.println("Cannot add plant into this cell");
                    }
                } else {
                    break;
                }
            }

            if(!res[0]) {
                newPlayer.setMoney(board.money);
                newPlayer.updateScore(board.score);
                System.out.println("Player: "+ newPlayer.getPlayerName() +"\t\tMoney: "+ newPlayer.getMoney() +"\t\tScore: "+ newPlayer.getScore());

                board.runBoard();
            } else {
                System.out.println(res[1] ? "\t\t\t\t***ZOMBIE WON***\n" : "\t\t\t\t***PLANTS WON***\n");
                return;
            }
        }

    }
}
