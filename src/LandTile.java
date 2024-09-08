/**
 * A land tile that towers can be placed on
 */
public class LandTile extends Tile {
    private String type = "land";
    @Override
    public String getType() {
        return type;
    }
}
