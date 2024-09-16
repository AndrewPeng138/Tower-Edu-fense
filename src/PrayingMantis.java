import javax.swing.*;
import java.awt.*;

/**
 * Represents a Praying Mantis boss enemy that appears on the final wave of the game.
 * This class extends {@link EnemyModel} and provides specific properties and behaviors
 * for the Praying Mantis, including its high health and damage values, as well as its
 * visual representation.
 */
public class PrayingMantis extends EnemyModel {
    /** The image representing the Praying Mantis enemy. */
    private Image mantisImage;

    /**
     * Constructs a Praying Mantis with the specified starting position.
     * Initializes the enemy with high health, specific damage, and an image.
     *
     * @param startRow The starting row position of the Praying Mantis.
     * @param startCol The starting column position of the Praying Mantis.
     */
    PrayingMantis(int startRow, int startCol) {
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(1000);
        this.setDamage(10);
        // Load the image for Praying Mantis
        this.mantisImage = new ImageIcon("Images/BugSprites/mantisPA.png").getImage();
    }

    /**
     * Indicates whether the enemy is of type Metal.
     *
     * @return false, as Praying Mantis is not a Metal type enemy.
     */
    @Override
    public boolean isMetal() {
        return false;
    }

    /**
     * Draws the Praying Mantis enemy on the board.
     *
     * @param g The {@link Graphics} object used for drawing the enemy.
     * @param screenX The x-coordinate on the screen where the enemy should be drawn.
     * @param screenY The y-coordinate on the screen where the enemy should be drawn.
     * @param tileWidth The width of the tile, used for scaling the image.
     * @param tileHeight The height of the tile, used for scaling the image.
     */
    @Override
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(mantisImage, screenX, screenY, tileWidth, tileHeight, null);
    }
}
