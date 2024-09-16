import javax.swing.*;
import java.awt.*;

/**
 * A projectile that moves in a straight line in a specified direction.
 */
public class Projectile {
    // Projectile type (e.g., "cannon")
    private String type = "cannon";
    // Image for the projectile
    private Image imageIcon;
    // Damage of the projectile, inherited from the tower
    private int damage;
    // Starting x location
    private int startX;
    // Starting y location
    private int startY;
    // Current x location
    private int currentX;
    // Current y location
    private int currentY;
    // Speed of the projectile
    private int speed = 5;
    // Direction of movement (could be "left", "right", "up", or "down")
    private String direction;

    /**
     * Constructs a Projectile that moves in a straight line.
     * @param type e.g. "cannon"
     * @param imagePath the image path passed in for the game icon
     * @param damage damage dealt by the projectile
     * @param startX x coordinate spawn location
     * @param startY y coordinate spawn location
     * @param direction the direction in which the projectile will move ("left", "right", "up", "down")
     */
    public Projectile(String type, String imagePath, int damage, int startX, int startY, String direction) {
        this.type = type;
        this.imageIcon = new ImageIcon(imagePath).getImage();  // Load image from the file path
        this.damage = damage;
        this.startX = startX;
        this.startY = startY;
        this.currentX = startX;
        this.currentY = startY;
        this.direction = direction;
    }

    /**
     * Moves the projectile in a straight line in the specified direction.
     */
    public void move() {
        switch (direction) {
            case "left":
                currentX -= speed;
                break;
            case "right":
                currentX += speed;
                break;
            case "up":
                currentY -= speed;
                break;
            case "down":
                currentY += speed;
                break;
        }
    }

    /**
     * Draws the projectile on the screen.
     * @param g the Graphics object for rendering the projectile.
     */
    public void draw(Graphics g) {
        g.drawImage(imageIcon, currentX, currentY, null);
    }

    // Getters and setters
    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Image getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(Image imageIcon) {
        this.imageIcon = imageIcon;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
