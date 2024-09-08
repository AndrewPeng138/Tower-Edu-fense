public class EnemyController {
    /**
     * Sets health of enemy
     * @param value health value to be set
     */
    public void setHealth(int value){
        // TODO: implement method
        enemy.health = value;
    }
    /**
     * Sets speed of enemy
     * @param value speed value to be set
     */
    public void setSpeed(int value){
        // TODO: implement method
        enemy.speed = value;
    }
    /**
     * Sets health of enemy
     * @param value health value to be set
     */
    public void setHealth(int value){
        // TODO: implement method
        enemy.damage = value;
    }

    /**
     * Series of action to take place once enemy reaches end of path
     * enemy disappears, user health decreases
     */
    public void reachEnd(){
        // TODO: implement method
        enemy.setHealth(0);
        player.decreaseUserHealth(value);
    }
}
