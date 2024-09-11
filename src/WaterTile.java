public class WaterTile extends Tile {
    private String type = "water";

    public WaterTile() {
        super("Images/TileSprites/water pixel art.png");
    }

    @Override
    public String getType() {
        return type;
    }
}
