public class Roach extends EnemyModel{
    Roach(){
        this.setHealth(50);
        this.setDamage(10);
    }

    @Override
    public boolean isMetal() {
        return false;
    }
}
