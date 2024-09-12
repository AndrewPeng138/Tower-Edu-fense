//import java.util.ArrayList;
/**
 * Special tower that can hit all enemies on the grid
 * 5 damage
 * Slow firing speed
 * 3000 cost
 */
public class Mortar extends TowerModel{
    Mortar(){
        this.setDamage(2);
    }

    /**
     * Basic attack of mortar tower
     * @param enemyWave wave of enemies that the tower attacks
     */
    @Override
    public void Fire(Wave enemyWave) {
       // ArrayList<EnemyModel> waveList = enemyWave.getWave();
       // for(EnemyModel e : waveList){
          //  e.decreaseHealth(this.getDamage());
        }
    }
//}
