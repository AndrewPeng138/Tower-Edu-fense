public class BorderTile extends Tile {
    private String type = "border";

    public BorderTile() {
        super("Images/TileSprites/possible border.jpg");
    }

    @Override
    public String getType() {
        return type;
    }
}
