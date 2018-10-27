/**
 * @author Dmytro Sytnik (VanArman)
 * @version 13 October, 2018
 */
public class XYPlant extends Plant {
    public XYPlant(String name, int health){
        super(name, health);
    }

    public XYPlant(String name, int health, int attackPower){
        super(name, health, attackPower);
    }

    @Override
    public void attack(NPC npc) {
        if (npc != null){
            npc.setHealth(npc.getHealth() - this.getAttackPower());
        }
    }

}
