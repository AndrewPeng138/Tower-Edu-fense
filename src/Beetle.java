public class Beetle extends EnemyModel{
    Beetle(){
        this.setHealth(100);
        this.setDamage(10);
    }

    @Override
    public boolean isMetal() {
        return false;
    }
}
