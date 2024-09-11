import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {
    private Tile[][] locations;
    private Image backgroundImage;

    public MapPanel(Tile[][] locations, String backgroundImagePath) {
        this.locations = locations;
        // Load the background map image
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image scaled to the panel size
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Determine tile size based on the panel dimensions
        int tileWidth = getWidth() / locations[0].length;
        int tileHeight = getHeight() / locations.length;

        // Loop through the locations array and draw each tile
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Tile tile = locations[i][j];
                if (tile != null) {
                    // Get the tile's image
                    Image tileImage = tile.getTileImage();

                    // Draw the tile image, scaling it to the tile size
                    g.drawImage(tileImage, j * tileWidth, i * tileHeight, tileWidth, tileHeight, this);
                }
            }
        }
    }
}
