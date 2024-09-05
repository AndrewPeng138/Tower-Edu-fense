public abstract class TowerModel {
    private int damage;
    private String SpecialEffect;
    private int cost;
    // No longer having range limits on towers
    //private int range;
    private int x;
    private int y;

    /**
     * Basic attack from tower onto enemies (different for each type of tower)
     */
    abstract public void Fire();
    /**
     * Gets x coordinate of tower
     * @return x coordinate of tower
     */
    public int getX(){
        return this.x;
    }
    /**
     * Gets y coordinate of tower
     * @return y coordinate of tower
     */
    public int getY(){
        return this.y;
    }

    /**
     * Set the x coordinate of tower
     * @param value x coordinate to be set
     */
    public void setX(int value){
        this.x = value;
    }
    /**
     * Set the y coordinate of tower
     * @param value y coordinate to be set
     */
    public void setY(int value){
        this.y = value;
    }
}
