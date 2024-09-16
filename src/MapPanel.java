import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapPanel extends JPanel {
    public Tile[][] locations;
    public Image backgroundImage;
    public Tower[][] placedTowers; // Store the placed towers
    private GameView gameView;
    private JButton[][] tileButtons;


    public MapPanel(Tile[][] locations, String backgroundImagePath, GameView gameView) {
        this.locations = locations;
        this.gameView = gameView;
        placedTowers = new Tower[locations.length][locations[0].length]; // Initialize the placedTowers array

        // Load the background map image
        backgroundImage = new ImageIcon(backgroundImagePath).getImage();
        setLayout(null);  // Use absolute positioning for placing buttons

        // Create buttons for each tile
        createTileButtons();
    }

    private void createTileButtons() {
        int tileWidth = 47;
        int tileHeight = 47;

        // Initialize the button array with the size of 'locations'
        tileButtons = new JButton[locations.length][locations[0].length];

        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations[i].length; j++) {
                // Create a new JButton for each tile
                JButton tileButton = new JButton();
                tileButton.setBounds(j * tileWidth, i * tileHeight, tileWidth, tileHeight);
                tileButton.setOpaque(false);
                tileButton.setContentAreaFilled(false);
                tileButton.setBorderPainted(false);
                tileButton.setFocusPainted(false);

                // Store the button in the array
                tileButtons[i][j] = tileButton;

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
        try {
            Tile tile = locations[row][col];
            Tower selectedTower = gameView.getSelectedTower(); // Get the selected tower

            if (selectedTower == null) {
                System.out.println("No tower selected.");  // Debug statement
                throw new IllegalStateException("No tower is currently selected.");
            }

            System.out.println("Clicked tile at row: " + row + ", col: " + col);
            System.out.println("Selected Tower: " + selectedTower.getName());

            // Handle different tile types
            if (tile.getName().equals("enemy")) {
                throw new IllegalArgumentException("You can't place a tower on the enemy path.");
            } else if (tile.getName().equals("border")) {
                throw new IllegalArgumentException("You can't place a tower on the border.");
            } else if (tile.getName().equals("water") && !selectedTower.getName().equals("Boat Tower")) {
                throw new IllegalArgumentException("Only Boat Towers can be placed on water.");
            }

            // Place the tower on the map using GameView's method
            System.out.println("Calling gameView.placeTowerOnTile...");
            gameView.placeTowerOnTile(row, col);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error placing tower: " + e.getMessage());  // Debug error message
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
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

                // Draw any placed tower on the tile
                Tower tower = placedTowers[i][j];
                if (tower != null) {
                    Image towerImage = tower.getTowerImage(); // Get the tower's image
                    g.drawImage(towerImage, j * tileWidth, i * tileHeight, tileWidth, tileHeight, this); // Draw the tower
                }
            }
        }
    }

    public void placeTower(int row, int col, Tower tower) {
        placedTowers[row][col] = tower;  // Place the tower in the specified tile
        repaint();  // Redraw the map to include the new tower
    }
    public JButton getButton(int x, int y){
        return tileButtons[x][y];
    }

}