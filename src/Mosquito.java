/**
 * A lower health enemy that appears more often
 */
public class Mosquito extends EnemyModel{
    Mosquito(){
        this.setHealth(20);
        this.setDamage(10);
    }

    @Override
    public boolean isMetal() {
        return false;
    }
}
