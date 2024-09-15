public class BorderTile extends Tile {
    private String type = "border";

    public BorderTile(int row, int col) {
        super("Images/TileSprites/new border.png", row, col, false, false); // Set isEnemyTile and isExit to false
    }

    @Override
    public String getType() {
        return type;
    }
}
