import java.util.ArrayList;

public class Mortar extends TowerModel{
    Mortar(){
        this.setDamage(5);
    }

    /**
     * Basic attack of mortar tower
     * @param enemyWave wave of enemies that the tower attacks
     */
    @Override
    public void Fire(Wave enemyWave) {
        ArrayList<Enemy> waveList = enemyWave.getWave();
        for(Enemy e : waveList){
            e.decreaseHealth(this.getDamage());
        }
    }
}
