/**
 * A water tile that only boat tiles can be placed on
 */
public class WaterTile extends Tile {
    private String type = "water";
    @Override
    public String getType() {
        return type;
    }
}