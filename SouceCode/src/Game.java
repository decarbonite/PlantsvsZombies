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
        int choice=0;
        int plantX=0;
        int plantY=0;
        Board board = new Board(5, 10, 20, 0, 200);

        /*board.addPlant(0,0, new Plant("Plant", 200, 15));
        board.addPlant(1,0, new Plant("Plant", 200, 15));
        board.addPlant(2,0, new Plant("Plant", 200, 15));
        board.addPlant(3,0, new Plant("Plant", 200, 15));
        board.addPlant(4,0, new Plant("Plant", 200, 15));*/

        board.printBoard();
        Scanner scan = new Scanner(System.in);

        while(true) {
            boolean[] res = board.gameEnds();


            while (true) {
                System.out.println("1- Add a plant");
                System.out.println("2- Play with no plants for now");
                choice = scan.nextInt();
                if (choice == 1){
                    System.out.println("Enter X coordinate: ");
                    plantX = scan.nextInt();
                    System.out.println("Enter Y coordinate: ");
                    plantY = scan.nextInt();

                    if (plantX < 0 || plantX > 4 || plantY < 0 || plantY > 8) {
                        System.out.println("Coordinates are out of board, try again");
                    }else{
                        board.addPlant(plantX, plantY, new Plant("Plant", 200, 15));
                        break;
                    }
                }else {
                    break;
                }
            }
            if(!res[0]) {
                System.out.println("Player's Money: " + board.money+ "\t\tScore: "+ board.score);
                //board.generateNewPlant();
                board.runBoard();
            } else {
                System.out.println("Player's Money: " + board.money+ "\t\tScore: "+ board.score);
                board.printBoard();
                System.out.println(res[1] ? "\n\t\t***ZOMBIE WON***\n" : "\n\t\t***PLANTS WON***\n");
                return;
            }
        }
    }
}
