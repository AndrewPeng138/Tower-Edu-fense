import java.awt.*;

public abstract class EnemyModel {
    protected int currentRow;
    protected int currentCol;
    private int health;
    private int damage;

    // Method to be overridden by specific enemies if needed
    public void moveToNextEnemyTile(Tile[][] mapTiles) {
        // General movement logic here (like in the Roach example)
    }

    public void moveTo(int newRow, int newCol) {
        this.currentRow = newRow;
        this.currentCol = newCol;
    }

    public void moveToNextEnemyTile(MapModel mapModel) {

        // Check left, right, and down tiles
        String leftTile = mapModel.getTileType(currentRow, currentCol - 1);
        String rightTile = mapModel.getTileType(currentRow, currentCol + 1);
        String downTile = mapModel.getTileType(currentRow + 1, currentCol);

        // Move to an "enemy" tile if available
        if (downTile.equals("enemy")) {
            moveTo(currentRow + 1, currentCol);
        } else if (leftTile.equals("enemy")) {
            moveTo(currentRow, currentCol - 1);
        } else if (rightTile.equals("enemy")) {
            moveTo(currentRow, currentCol + 1);
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    // Add getters if needed
    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public abstract boolean isMetal();

    protected void paintComponent(Graphics g) {
    }
}
