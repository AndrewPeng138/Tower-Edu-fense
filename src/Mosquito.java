import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Represents a Mosquito enemy in the game. This class extends {@link EnemyModel}
 * and provides specific properties and behaviors for the Mosquito enemy, including
 * its health, damage, and visual representation. Mosquitoes are characterized by their
 * lower health compared to other enemies and their frequent appearance in the game.
 */
public class Mosquito extends EnemyModel {
    /** The image representing the Mosquito enemy. */
    private Image mosquitoImage;

    /**
     * Constructs a Mosquito with the specified starting position.
     * Initializes the enemy with specific health, damage, and an image.
     *
     * @param startRow The starting row position of the Mosquito.
     * @param startCol The starting column position of the Mosquito.
     */
    Mosquito(int startRow, int startCol) {
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(20);
        this.setDamage(10);
        // Load the image for Mosquito
        this.mosquitoImage = new ImageIcon("Images/BugSprites/mosquitoPA.png").getImage();
    }

    /**
     * Indicates whether the enemy is of type Metal.
     *
     * @return false, as Mosquito is not a Metal type enemy.
     */
    @Override
    public boolean isMetal() {
        return false;
    }

    /**
     * Draws the Mosquito enemy on the board.
     *
     * @param g The {@link Graphics} object used for drawing the enemy.
     * @param screenX The x-coordinate on the screen where the enemy should be drawn.
     * @param screenY The y-coordinate on the screen where the enemy should be drawn.
     * @param tileWidth The width of the tile, used for scaling the image.
     * @param tileHeight The height of the tile, used for scaling the image.
     */
    @Override
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(mosquitoImage, screenX, screenY, tileWidth, tileHeight, null);
    }
}
