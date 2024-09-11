/**
 * A border tile that does nothing but exist,  bounding the grid
 */
public class BorderTile extends Tile {
    private String type = "border";

    /**
     * Standard getter
     * @return type of tile
     */
    @Override
    public String getType() {
        return type;
    }

}

