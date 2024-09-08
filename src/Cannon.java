import java.util.ArrayList;

public class Cannon extends TowerModel{
    Cannon(){
        this.setDamage(10);
    }
    /**
     * Basic attack of cannon tower
     * @param enemyWave wave of enemies that the tower attacks
     */
    @Override
    public void Fire(Wave enemyWave) {
        ArrayList<Enemy>  waveList = enemyWave.getWave();
        waveList.get(0).decreaseHealth(this.getDamage());
    }


}
