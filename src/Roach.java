import javax.swing.ImageIcon;
import java.awt.Image;

public class Roach extends EnemyModel {
    private Image roachImage;
    private int currentRow;
    private int currentCol;
    private int row;
    private int col;


    public Roach(int startRow, int startCol) {
        this.currentRow = startRow;
        this.currentCol = startCol;
        this.setHealth(50);
        this.setDamage(10);
        this.row = row;
        this.col = col;
        this.roachImage = new ImageIcon("Images/BugSprites/cockroachPA.png").getImage(); // Load Roach image
    }

    public Image getRoachImage() {
        return roachImage;
    }

    public int getCurrentRow() {
        return currentRow;
    }

    public int getCurrentCol() {
        return currentCol;
    }

    // Move the roach to a new tile
    public void moveTo(int newRow, int newCol) {
        this.currentRow = newRow;
        this.currentCol = newCol;
    }

    // Method to disappear when reaching the exit
    public void disappear() {
        System.out.println("Roach has exited and disappeared.");
    }

    @Override
    public boolean isMetal() {
        return false;
    }
}
