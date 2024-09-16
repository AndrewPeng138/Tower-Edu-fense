import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Represents a roach enemy in the game.
 * This class handles the roach's image, position, and basic enemy attributes such as health and damage.
 */
public class Roach extends EnemyModel {
    /** Image representing the roach. */
    private Image roachImage;

    /** The current row position of the roach on the map. */
    private int currentRow;

    /** The current column position of the roach on the map. */
    private int currentCol;

    /**
     * Constructs a Roach object with the specified starting position.
     * Initializes the roach's health, damage, and loads its image.
     *
     * @param startRow The initial row position of the roach.
     * @param startCol The initial column position of the roach.
     */
    public Roach(int startRow, int startCol) {
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(50);   // Set initial health
        this.setDamage(10);   // Set initial damage
        this.roachImage = new ImageIcon("Images/BugSprites/cockroachPA.png").getImage();
    }

    /**
     * Gets the image representing the roach.
     *
     * @return The image of the roach.
     */
    public Image getRoachImage() {
        return roachImage;
    }

    /**
     * Gets the current row position of the roach.
     *
     * @return The row position of the roach.
     */
    public int getCurrentRow() {
        return currentRow;
    }

    /**
     * Gets the current column position of the roach.
     *
     * @return The column position of the roach.
     */
    public int getCurrentCol() {
        return currentCol;
    }

    @Override
    /**
     * Indicates whether the roach is a metal enemy.
     *
     * @return false, as this roach is not a metal enemy.
     */
    public boolean isMetal() {
        return false;
    }

    /**
     * Checks if the specified tile on the map is an enemy tile.
     *
     * @param mapModel The map model containing tile information.
     * @param row The row position of the tile to check.
     * @param col The column position of the tile to check.
     * @return true if the tile is an "enemy" tile, false otherwise.
     */
    private boolean isEnemyTile(MapModel mapModel, int row, int col) {
        // Check if the position is within bounds
        String tileType = mapModel.getTileType(row, col);
        return "enemy".equals(tileType);  // Return true if the tile is an "enemy" tile
    }

    /**
     * Draws the roach on the screen at the specified location.
     *
     * @param g The graphics context to use for drawing.
     * @param screenX The x-coordinate on the screen where the roach should be drawn.
     * @param screenY The y-coordinate on the screen where the roach should be drawn.
     * @param tileWidth The width of the tile on the screen.
     * @param tileHeight The height of the tile on the screen.
     */
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(roachImage, screenX, screenY, tileWidth, tileHeight, null);
    }

    /**
     * Gets the image representing the roach.
     *
     * @return The image of the roach.
     */
    public Image getImage() {
        return roachImage;
    }
}
