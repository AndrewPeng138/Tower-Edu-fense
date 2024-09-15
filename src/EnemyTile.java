public class EnemyTile extends Tile {
    private String type = "enemy";

    public EnemyTile(int row, int col, boolean isEntrance, boolean isExit) {
        super("Images/TileSprites/enemypath pixel art.png", row, col, true, isExit); // EnemyTile always has isEnemyTile = true
        this.setEntrance(isEntrance); // Use the setter to mark the tile as an entrance
    }

    @Override
    public String getType() {
        return type;
    }
}
