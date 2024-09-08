public class EnemyModel {
    private int damage;
    private int health;
    private int speed;
    private int x;
    private int y;

    /**
     * basic movement by enemy
     */
    public void move(){
        // TODO: this method needs to be more defined by the tile class or another way we need to find the next tile
        this.position() = nextTile;
    }
    /**
     * Gets x coordinate of enemy
     * @return x coordinate of enemy
     */
    public int getX(){
        return this.x;
    }
    /**
     * Gets y coordinate of enemy
     * @return y coordinate of enemy
     */
    public int getY(){
        return this.y;
    }

    /**
     * Set the x coordinate of enemy
     * @param value x coordinate to be set
     */
    public void setX(int value){
        this.x = value;
    }
    /**
     * Set the y coordinate of enemy
     * @param value y coordinate to be set
     */
    public void setY(int value){
        this.y = value;
    }
    /**
     * Sets health of enemy
     * @param value health value to be set
     */
    public void setHealth(int value){
        this.health = value;
    }
    /**
     * Sets speed of enemy
     * @param value speed value to be set
     */
    public void setSpeed(int value){
        this.speed = value;
    }
    /**
     * Sets health of enemy
     * @param value health value to be set
     */
    public void setHealth(int value){
        this.damage = value;
    }
}
