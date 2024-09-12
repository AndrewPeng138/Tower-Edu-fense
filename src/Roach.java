import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Roach extends EnemyModel {
    private Image roachImage;  // To hold the roach's image

    /**
     * Default constructor of roach
     * @param startRow starting row of roach on the map
     * @param startCol starting column of roach on the map
     */
    public Roach(int startRow, int startCol) {
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(50);   // Set initial health
        this.setDamage(10);   // Set initial damage
        this.roachImage = new ImageIcon("Images/BugSprites/cockroachPA.png").getImage();
        System.out.println("Roach starting at: (" + getCurrentRow() + ", " + getCurrentCol() + ")");
    }

    public Image getRoachImage() {
        return roachImage;
    }

    @Override
    public boolean isMetal() {
        return false;
    }

    @Override
    // Draw the roach on the screen
    public void draw(Graphics g, int screenX, int screenY, int tileWidth, int tileHeight) {
        g.drawImage(roachImage, screenX, screenY, tileWidth, tileHeight, null);
    }
}
