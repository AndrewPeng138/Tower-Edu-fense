public class BorderTile extends Tile {
    private String type = "border";


    public BorderTile() {
        super("Images/TileSprites/new border.png");
    }


    @Override
    public String getType() {
        return type;
    }
}
