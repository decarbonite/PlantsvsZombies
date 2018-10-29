/**
 * Starting point of the Plant vs Zombie game
 *
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class Game {
    /**
     * Entering point of the game
     * @param args String[] - not used in this game
     */
    public static void main(String[] args) {
        Player newPlayer = new Player();
        Board board = new Board(5, 10, 1, newPlayer.getScore(), newPlayer.getMoney());

        board.addPlant(0,0, new XYPlant("Plant", 200, 15));
        board.addPlant(1,0, new XYPlant("Plant", 200, 15));
        board.addPlant(2,0, new XYPlant("Plant", 200, 15));
        board.addPlant(3,0, new XYPlant("Plant", 200, 15));
        board.addPlant(4,0, new XYPlant("Plant", 200, 15));

        while(true) {
            boolean[] res = board.gameEnds();

            board.generateNewPlant();
            newPlayer.setMoney(board.money);
            newPlayer.updateScore(board.score);

            if(!res[0]) {
                System.out.println("Player: "+ newPlayer.getPlayerName() +"\t\tMoney: "+ newPlayer.getMoney() +"\t\tScore: "+ newPlayer.getScore());
                board.runBoard();

                newPlayer.setMoney(board.money);
                newPlayer.updateScore(board.score);
            } else {
                System.out.println(res[1] ? "\t\t\t\t***ZOMBIE WON***\n" : "\t\t\t\t***PLANTS WON***\n");
                return;
            }
        }

    }
}
