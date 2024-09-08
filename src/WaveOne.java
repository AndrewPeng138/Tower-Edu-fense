public class WaveOne extends Wave{
    /**
     * Creating first wave with 5 mini enemies and 3 giant enemies
     */
    WaveOne(){
        for(int i = 0; i<5; i++){
            Mini miniEnemy = new Mini();
            this.addEnemy(miniEnemy);
        }
        for(int i = 0; i<3; i++){
            Giant giantEnemy = new Giant();
            this.addEnemy(giantEnemy);
        }
    }
}
