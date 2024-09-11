public class LandTile extends Tile {
    private String type = "land";

    public LandTile() {
        super("Images/TileSprites/land grass pixel art.png");
    }

    @Override
    public String getType() {
        return type;
    }

}
