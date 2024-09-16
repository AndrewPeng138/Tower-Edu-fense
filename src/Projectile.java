import javax.swing.*;
import java.awt.*;

/**
 * A projectile that can be of the types that will seek out the first enemy in the enemyList
 */
public class Projectile {
    // Indicates projectile type and therefore functionality
    // Cannon by default
    private String type = "cannon";
    // Image for the projectile passed by the tower
    private Image imageIcon;
    // Damage of the projectile, inherited from the tower
    private int damage;
    // Starting x location
    private int startX;
    // Starting y location
    private int startY;
    // The enemy which the projectile is aiming for
    EnemyModel target;

    /**
     * Constructs
     * @param type e.g. "cannon"
     * @param imagePath the image path passed in for the game icon
     * @param damage varies based on tower type, inherited from tower type
     * @param startX x coordinate spawn location
     * @param startY y coordinate spawn location
     * @param target the enemy that the projectile will move towards
     */
    public Projectile (String type, String imagePath, int damage, int startX, int startY, EnemyModel target) {
        this.type = type;
        this.imageIcon = new ImageIcon(imagePath).getImage();  // Load image from the file path
        this.damage = damage;
        this.startX = startX;
        this.startY = startY;
        this.target = target;
    }
}
