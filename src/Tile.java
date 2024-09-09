/**
<<<<<<< HEAD
 * An individual square on the 15x15 game map
 */
public abstract class Tile {
    public abstract String getType();

public abstract class Tile2 {
    private boolean isExit = false;  // Default to not an exit

    /**
     * Returns the type of tile as a string.
     * This method must be implemented by all concrete tile classes.
     *
     * @return a string representing the type of the tile
     */
    public abstract String getType();

    /**
     * Determines whether this tile is designated as an exit.
     *
     * @return true if this tile is an exit, false otherwise
     */
    public boolean getExit() {
        return isExit;
    }

    /**
     * Sets this tile as an exit. This should only be set based on the map design
     * where exits are required, such as the end of a path where enemies exit.
     *
     * @param exit a boolean where true sets the tile as an exit
     */
    public void setExit(boolean exit) {
        this.isExit = exit;
    }

}}
