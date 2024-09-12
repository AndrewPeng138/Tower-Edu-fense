import java.awt.*;

public abstract class EnemyModel {
    private int currentRow;
    private int currentCol;
    private int health;
    private int damage;

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    public void setCurrentRow(int value){this.currentRow = value;}

    public void setCurrentCol(int value){this.currentCol = value;}

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
     * Move the enemy to a new tile if it's an enemy tile
     * @param mapModel Map the enemy is moving on
     */
    public void moveToNextEnemyTile(MapModel mapModel) {
        if (mapModel == null) {
            throw new IllegalArgumentException("MapModel cannot be null");
        }

        // Get current position
        int row = getCurrentRow();
        int col = getCurrentCol();

        // Check the tiles in order: down, left, right
        if (isEnemyTile(mapModel, row + 1, col)) {
            moveTo(row + 1, col); // Move down
        } else if (isEnemyTile(mapModel, row, col - 1)) {
            moveTo(row, col - 1); // Move left
        } else if (isEnemyTile(mapModel, row, col + 1)) {
            moveTo(row, col + 1); // Move right
        }
    }

    /**
     * Utility method to check if a tile is an enemy tile
     * @param mapModel Map that tile is on
     * @param row row of tile
     * @param col column of tile
     * @return true if tile is an enemy tile, false if other tile type
     */
    private boolean isEnemyTile(MapModel mapModel, int row, int col) {
        // Check if the position is within bounds
        String tileType = mapModel.getTileType(row, col);
        return "enemy".equals(tileType);  // Return true if the tile is an "enemy" tile
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
     * Abstract method drawing the enemy on the board
     * @param g
     * @param screenX
     * @param screenY
     * @param tileWidth
     * @param tileHeight
     */
    public abstract void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight);
}
