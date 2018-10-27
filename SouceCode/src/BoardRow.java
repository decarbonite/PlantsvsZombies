import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Dmytro Sytnik (VanArman)
 * @version 17 October, 2018
 */
public class BoardRow {
    private static int nZombiesSpawn;
    private List<BoardNode> row;

    public BoardRow(int zombies2Spawn) {
        nZombiesSpawn = zombies2Spawn;
        row = new ArrayList<BoardNode>(9);
        for(int i = 0; i < 9; i++) {
            row.add(new BoardNode());
        }
    }

    private void moveZombie() {
        for(int i = 0; i < row.size() - 1; i++){
            BoardNode current = row.get(i);
            BoardNode next = row.get(i+1);
            String printedLine = current.hasPlant() ? current.getPlantName() + "\t" : "false\t\t\t";
                   printedLine += next.hasZombie() ? next.getZombieName() + "\t" : "false\t\t\t";
            if(current.hasZombie() && !current.hasPlant()) {
                System.out.println(printedLine);
                System.out.println("\n\t\t***Zombies WON***\n");
                System.exit(0);
            } else if(current.hasPlant() && current.hasZombie()) {
                current.plantFightZombie();
                System.out.println(printedLine + "\t*FIGHT*");
            }

            if(next.hasZombie() && !current.hasZombie()) {
                current.addZombie(next.destroyZombie());
                printedLine += "\tMoved from [ " + (i+1) + " ] -> [ "+ i +" ]";
            }

            System.out.println(printedLine);
        }
    }

    private void generateNewZombie() {
        Random rand = new Random();
        if (nZombiesSpawn > 0) {
            if (rand.nextInt(5) % 3 == 0) {
                row.get(8).addZombie(new Zombie("Stiven", 100, 10));
                nZombiesSpawn--;
                System.out.println("Nomber of zombies to spawn: "+ nZombiesSpawn);
            }
        }
    }

    public void addPlant(int index, Plant plant){
        row.get(index).addPlant(plant);
    }

    private void fightPvsZ(){
        for(int i = 0; i < row.size() - 1; i++) {
            BoardNode plantFind = row.get(i);
            if(plantFind.hasPlant()) {
                for(int j = i; j < row.size() - 1; j++) {
                    BoardNode zombieFind = row.get(j);
                    if(plantFind == zombieFind && zombieFind.hasZombie()) {
//                        plantFind.plantFightZombie();
                        break;
                    } else if(plantFind != zombieFind && zombieFind.hasZombie()) {
                        zombieFind.addZombie(plantFind.plantFightZombie(zombieFind.destroyZombie()));
                        break;
                    }
                }
            }
        }
    }

    public void runRow() {
        generateNewZombie();
        fightPvsZ();
        moveZombie();
    }
}
