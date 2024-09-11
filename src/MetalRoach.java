/**
 * A standard roach, except it can only be damaged by fire tower
 */
public class MetalRoach extends EnemyModel{
    MetalRoach(){
        this.setHealth(50);
        this.setDamage(10);
        this.isMetal = true;
    }

}
