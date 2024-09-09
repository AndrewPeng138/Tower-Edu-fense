/**
 * An enemy path tile that can also be an entrance or exit
 */
public class EnemyTile extends Tile {
    private String type = "enemy";
    boolean isEntrance;
    boolean isExit;
    @Override
    public String getType() {
        return type;
    }

    /**
     * Sets whether this tile is the entrance tile
     * @param choice true indicates this is an entrance tile
     */
    public void setEntrance(boolean choice) {
        isEntrance = choice;
    }

    /**
     * Determines whether this is an entrance tile
     * @return true if this is an entrance tile
     */
    public boolean getEntrance() {
        return isEntrance;
    }
    /**
     * Sets whether this tile is the exit tile
     * @param choice true indicates this is an exit tile
     */
    public void setExit(boolean choice) {
        isExit = choice;
    }

    /**
     * Determines whether this is an exit tile
     * @return true if this is an exit tile
     */
    public boolean getExit() {
        return isExit;
    }


}
