import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class MetalRoach extends EnemyModel{
    private Image metalroachImage;
    MetalRoach(int startRow, int startCol){
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(50);
        this.setDamage(10);
        // Add functionality for immunity
        this.metalroachImage = new ImageIcon("Images/BugSprites/metalroachPA.png").getImage();
    }

    @Override
    public boolean isMetal() {
        return true;
    }

    /**
     * Method moving enemy to a new tile
     *
     * @param newRow row number of new tile
     * @param newCol column number of new tile
     */
    @Override
    public void moveTo(int newRow, int newCol) {
        currentRow  = newRow;
        currentCol = newCol;
    }

    /**
     * Abstract method drawing the enemy on the board
     *
     * @param g
     * @param screenX
     * @param screenY
     * @param tileWidth
     * @param tileHeight
     */
    @Override
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(metalroachImage, screenX, screenY, tileWidth, tileHeight, null);
    }
}