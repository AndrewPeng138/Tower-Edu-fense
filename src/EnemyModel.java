public class EnemyModel {
    private int damage;
    private int health;
    private int speed;
    private int x;
    private int y;

    /**
     * basic movement by enemy
     */
    public void move(int nextX, int nextY) {
        this.x = nextX;
        this.y = nextY;
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
     * Setting position of enemy
     * @param enemyX x coordinate to be set
     * @param enemyY y coordinate to be set
     */
    public void setPosition(int enemyX, int enemyY){
        this.x = enemyX;
        this.y = enemyY;
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
    public void setDamage(int value){
        this.damage = value;
    }

    /**
     * Decrease health taken from damage by certain amount
     * @param value amount of health enemy lost
     */
    public void decreaseHealth(int value){
        this.health -= value;
    }
}
