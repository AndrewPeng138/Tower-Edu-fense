import javax.swing.*;
import java.awt.*;

/**
 * Boss enemy that appears on the final wave
 */
public class PrayingMantis extends EnemyModel{
    private Image mantisImage;
    PrayingMantis(int startRow, int startCol){
        setCurrentRow(startRow);
        setCurrentCol(startCol);
        this.setHealth(1000);
        this.setDamage(10);
        this.mantisImage = new ImageIcon("Images/BugSprites/mantisPA.png").getImage();
    }

    @Override
    public boolean isMetal() {
        return false;
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
        g.drawImage(mantisImage, screenX, screenY, tileWidth, tileHeight, null);
    }
}
