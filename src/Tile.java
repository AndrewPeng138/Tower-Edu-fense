import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * An individual square on the 17x17 game map
 */
public abstract class Tile {
    private Image tileImage;

    // Constructor that accepts the image path
    public Tile(String imagePath) {
        this.tileImage = new ImageIcon(imagePath).getImage();
    }

    // Get the type of the tile (remains abstract)
    public abstract String getType();

    // Get the image for this tile
    public Image getTileImage() {
        return tileImage;
    }
    private boolean isExit;  // Flag to indicate if the tile is an exit


}
