import java.util.ArrayList;

public abstract class Wave {
    private ArrayList<EnemyModel> enemyWave;

    /**
     * Returns next enemy wave
     * @return current wave that is stored inside the arrayList
     */
    public ArrayList<EnemyModel> getWave(){
            return this.enemyWave;
    }
    public void addEnemy(EnemyModel enemy){
        this.enemyWave.add(enemy);
    }
}
