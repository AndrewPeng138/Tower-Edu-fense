public class LandTile extends Tile {
    private String type = "land";

    public LandTile(int row, int col) {
        super("Images/TileSprites/land grass pixel art.png", row, col, false, false); // Set isEnemyTile and isExit to false
    }

    @Override
    public String getType() {
        return type;
    }
}
