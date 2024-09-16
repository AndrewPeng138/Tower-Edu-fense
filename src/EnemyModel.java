import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class EnemyModel {
    private int currentRow;
    private int currentCol;
    private int health;
    private int damage;
    private int pathIndex = 0;  // Tracks the enemy's position along the path

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentRow(int value){ this.currentRow = value; }

    public void setCurrentCol(int value){ this.currentCol = value; }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public abstract boolean isMetal();

    /**
     * Move the enemy to the next tile in the predefined path.
     * @param mapModel Map the enemy is moving on
     */
    public void moveToNextEnemyTile(MapModel mapModel, MapPanel mapPanel, List<Tile> enemyPath) {
        if (mapModel == null) {
            throw new IllegalArgumentException("MapModel cannot be null");
        }
        if (pathIndex < enemyPath.size()) {
            // Get the next position in the path
            int row = enemyPath.get(pathIndex).getRow();
            int col = enemyPath.get(pathIndex).getRow();
            JButton button = mapPanel.getButton(row, col);
            Point location = button.getLocation();
            moveTo(location.x, location.y);
            pathIndex++;  // Increment the path index
        }
    }

    /**
     * Method moving enemy to a new tile
     * @param newRow row number of new tile
     * @param newCol column number of new tile
     */
    public void moveTo(int newRow, int newCol) {
        this.currentRow = newRow;
        this.currentCol = newCol;
    }

    /**
     * Abstract method for drawing the enemy on the board
     * @param g Graphics object
     * @param screenX X-coordinate on the screen
     * @param screenY Y-coordinate on the screen
     * @param tileWidth Width of the tile
     * @param tileHeight Height of the tile
     */
    public abstract void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight);

    public void decreaseHealth(int damage) {
        health = -1;
    }

}