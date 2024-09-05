/**
 * A border tile that does nothing but exist
 */
public class BorderTile extends Tile {
    private String type = "border";
    @Override
    public String getType() {
        return type;
    }
}
