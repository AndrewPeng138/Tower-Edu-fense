import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Represents a MetalRoach enemy in the game. This class extends {@link EnemyModel}
 * and provides specific properties and behaviors for the MetalRoach enemy, including
 * health, damage, and visual representation.
 */
public class MetalRoach extends EnemyModel {
    /** The image representing the MetalRoach enemy. */
    private Image metalroachImage;

    /**
     * Constructs a MetalRoach with the specified starting position.
     * Initializes the enemy with specific health, damage, and an image.
     *
     * @param startRow The starting row position of the MetalRoach.
     * @param startCol The starting column position of the MetalRoach.
     */
    MetalRoach(int startRow, int startCol) {
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(50);
        this.setDamage(10);
        // Load the image for MetalRoach
        this.metalroachImage = new ImageIcon("Images/BugSprites/metalroachPA.png").getImage();
    }

    /**
     * Indicates whether the enemy is of type Metal.
     *
     * @return true, as MetalRoach is a Metal type enemy.
     */
    @Override
    public boolean isMetal() {
        return true;
    }

    /**
     * Draws the MetalRoach enemy on the board.
     *
     * @param g The {@link Graphics} object used for drawing the enemy.
     * @param screenX The x-coordinate on the screen where the enemy should be drawn.
     * @param screenY The y-coordinate on the screen where the enemy should be drawn.
     * @param tileWidth The width of the tile, used for scaling the image.
     * @param tileHeight The height of the tile, used for scaling the image.
     */
    @Override
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(metalroachImage, screenX, screenY, tileWidth, tileHeight, null);
    }
}
