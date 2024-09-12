import java.util.ArrayList;

/**
 * Default tower
 * 10 damage
 * Standard firing speed
 * 500 cost
 */
public class Cannon extends TowerProperties implements TowerModelI{
    Cannon(){
        this.setDamage(10);
        this.setCost(500);
    }

    /**
     * Basic attack of cannon tower
     * @param enemyWave wave of enemies that the tower attacks
     */
    @Override
    public void Fire(Wave enemyWave) {
        ArrayList<EnemyModel>  waveList = enemyWave.getWave();
       // waveList.get(0).decreaseHealth(this.getDamage());
    }


}
