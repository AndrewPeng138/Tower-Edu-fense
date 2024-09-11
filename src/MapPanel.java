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

        // Draw the background image scaled to the new, smaller size
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Determine tile size based on the new panel dimensions
        int tileWidth = getWidth() / locations[0].length;
        int tileHeight = getHeight() / locations.length;

        // Loop through the locations array and draw each tile
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                Tile tile = locations[i][j];
                if (tile != null) {
                    // Set different colors for different tile types
                    if (tile instanceof LandTile) {
                        g.setColor(Color.GREEN);
                    } else if (tile instanceof EnemyTile) {
                        g.setColor(Color.RED);

                    }
                    else if (tile instanceof WaterTile) {
                        g.setColor(Color.BLUE);
                    }
                    else if (tile instanceof BorderTile) {
                        g.setColor(Color.WHITE);
                    }
                    else {
                        g.setColor(Color.GRAY); // Tiles that haven't been set
                    }

                    // Draw the tile as a smaller rectangle based on the adjusted tile size
                    g.fillRect(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                }
            }
        }
    }
}
