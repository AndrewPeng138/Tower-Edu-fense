import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel {
    public Tile[][] locations;
    public Image backgroundImage;

    public MapPanel(Tile[][] locations, String backgroundImagePath) {
        this.locations = locations;

        // Load the background map image
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        setLayout(null);  // Use absolute positioning for placing buttons

        // Create buttons for each tile
        createTileButtons();
    }

    private void createTileButtons() {
        // Determine tile size based on the initial panel size
        int tileWidth = 800 / locations[0].length;  // Assume an initial width for simplicity
        int tileHeight = 600 / locations.length;    // Assume an initial height for simplicity

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                // Create a new JButton for each tile
                JButton tileButton = new JButton();
                tileButton.setBounds(j * tileWidth, i * tileHeight, tileWidth, tileHeight);  // Position the button
                tileButton.setOpaque(false);  // Make button background transparent
                tileButton.setContentAreaFilled(false);  // Remove background fill
                tileButton.setBorderPainted(false);  // Remove border
                tileButton.setFocusPainted(false);  // Remove focus indicator

                // Add action listener to handle click event
                int row = i, col = j;
                tileButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleTileClick(row, col);
                    }
                });

                // Add the button to the panel
                add(tileButton);
            }
        }
    }

    private void handleTileClick(int row, int col) {
        // Logic for what happens when a tile is clicked
        System.out.println("Tile clicked at: [" + row + ", " + col + "]");

        // functionality here to place a tower or perform another action
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

        // Recreate and reposition buttons in case of resizing
        createTileButtons();  // Recreate them with new sizes
    }
}
