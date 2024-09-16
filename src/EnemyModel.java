import java.awt.*;

public abstract class EnemyModel {
    protected int xPosition;
    protected int yPosition;
    protected MapModel mapModel;
    protected int health;
    protected int damage;
    protected int currentRow;
    protected int currentCol;

    // Constructor
    public EnemyModel(MapModel mapModel, int startX, int startY) {
        this.mapModel = mapModel;
        this.xPosition = startX;
        this.yPosition = startY;
        this.health = 0;
        this.damage = 0;
    }

    // Abstract method to get the image of the enemy
    public abstract Image getImage();

    // Method to move the enemy
    public void move(int newX, int newY) {
        if (newX >= 0 && newX < mapModel.getMapWidth() && newY >= 0 && newY < mapModel.getMapHeight()) {
            Tile targetTile = mapModel.getTile(newY, newX);
            if (canMoveToTile(targetTile)) {
                xPosition = newX;
                yPosition = newY;
            }
        }
    }

    // Method to check if the enemy can move to a specific tile
    private boolean canMoveToTile(Tile tile) {
        return tile instanceof EnemyTile;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

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

    public boolean isMetal() {
        return false; // Default implementation
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
    public abstract void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight);

}