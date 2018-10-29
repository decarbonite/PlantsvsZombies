/**
 * This is a plant class that generates money
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class MoneyPlant extends Plant {
    public MoneyPlant(String name, int health){
        super(name, health);
    }

    @Override
    public void attack(NPC npc) {
        if (npc != null) {
            npc.setHealth(npc.getHealth() - this.getAttackPower());
        }
    }

    /*public void updateMoney(){

    }*/
}
