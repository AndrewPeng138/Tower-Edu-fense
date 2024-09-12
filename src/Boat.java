import java.util.ArrayList;

/**
 * Default tower except can be placed in water
 * 10 damage
 * Standard firing speed
 * 1500 cost
 */
public class Boat extends TowerProperties implements TowerModelI{
    Boat(){
        this.setDamage(10);
        this.setCost(1500);
    }
    /**
     * Basic attack of boat tower
     * @param enemyWave wave of enemies that the tower attacks
     */
    @Override
    public void Fire(Wave enemyWave) {
        ArrayList<EnemyModel>  waveList = enemyWave.getWave();
       // waveList.get(0).decreaseHealth(this.getDamage());
    }


}
