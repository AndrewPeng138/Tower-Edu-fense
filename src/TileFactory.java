/**
 * Tile factory which does nothing but create Tiles
 */
public class TileFactory {
    /**
     * Does nothing but returns tile objects
     * @param type determines whether land, water, or enemy tile
     */
    public Tile createTile (String type) {
        // Land tile
        if (type.equals("land")) {
            return new LandTile();
        }
        // Water tile
        if (type.equals("water")) {
            return new WaterTile();
        }
        // Enemy tile
        if (type.equals("enemy")){
            return new EnemyTile();
        }
        if (type.equals("border")) {
            return new BorderTile();
        }
        // WARNING! Returns border tile by default
        else {
            return new BorderTile();
        }
    }
}