public class WaterTile extends Tile {
    private String type = "water";

    public WaterTile(int row, int col) {
        super("Images/TileSprites/water pixel art.png", row, col, false, false); // Pass relevant parameters to the Tile constructor
    }

    @Override
    public String getType() {
        return type;
    }
}
