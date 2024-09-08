public class EnemyModel {
    private int damage;
    private int health;
    private int speed;
    private int x; // Current x-coordinate on the map
    private int y; // Current y-coordinate on the map

    public EnemyModel() {
        this.health = health;
        this.damage = damage;
        this.speed = speed;
    }

    // Basic movement by enemy
    public void move(int nextX, int nextY) {
        this.x = nextX;
        this.y = nextY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = Math.max(0, health); // Ensure health does not go below zero
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPosition(int enemyX, int enemyY)
    {
    }
}
