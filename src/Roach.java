import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Roach extends EnemyModel {
    private Image roachImage;  // To hold the roach's image
    private int currentRow;
    private int currentCol;


    // Constructor
    public Roach(MapModel mapModel, int startRow, int startCol) {
        super(mapModel, startRow, startCol);  // Correct constructor call
        this.currentRow = startRow;
        this.currentCol = startCol;
        this.roachImage = new ImageIcon("Images/BugSprites/cockroachPA.png").getImage();
        System.out.println("Roach starting at: (" + currentRow + ", " + currentCol + ")");
    }


    // Getters
    public Image getRoachImage() {
        return roachImage;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    // Move the roach to a new tile if it's an enemy tile
    public void moveToNextEnemyTile(MapModel mapModel) {
        if (mapModel == null) {
            throw new IllegalArgumentException("MapModel cannot be null");
        }

        // Get current position
        int row = currentRow;
        int col = currentCol;

        // Check the tiles in order: down, left, right
        if (isEnemyTile(mapModel, row + 1, col)) {
            moveTo(row + 1, col); // Move down
        } else if (isEnemyTile(mapModel, row, col - 1)) {
            moveTo(row, col - 1); // Move left
        } else if (isEnemyTile(mapModel, row, col + 1)) {
            moveTo(row, col + 1); // Move right
        }
    }

    @Override
    public boolean isMetal() {
        return false;
    }

    // Utility method to check if a tile is an enemy tile
    private boolean isEnemyTile(MapModel mapModel, int row, int col) {
        // Check if the position is within bounds
        String tileType = mapModel.getTileType(row, col);
        return "enemy".equals(tileType);  // Return true if the tile is an "enemy" tile
    }

    // Update current position
    public void moveTo(int newRow, int newCol) {
        this.currentRow = newRow;
        this.currentCol = newCol;
    }

    // Draw the roach on the screen
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(roachImage, screenX, screenY, tileWidth, tileHeight, null);
    }

    public Image getImage() {
        return roachImage;
    }
}
