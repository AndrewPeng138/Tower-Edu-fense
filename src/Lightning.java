import java.util.ArrayList;

/**
 * Special tower that can do chain damage to two additional enemies
 * 30 damage
 * Faster firing speed
 * 3500 cost
 */
public class Lightning extends TowerModel{
    Lightning(){
        this.setDamage(2);
    }

    /**
     * Basic chain attack of lightning tower
     * @param enemyWave wave of enemies that the tower attacks
     */
    @Override
    public void Fire(Wave enemyWave) {
        ArrayList<EnemyModel> waveList = enemyWave.getWave();
        for(EnemyModel e : waveList){
          //  e.decreaseHealth(this.getDamage());
        }
    }
}
