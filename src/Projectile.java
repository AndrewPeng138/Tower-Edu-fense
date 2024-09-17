
import javax.swing.*;
import java.awt.*;

public class Projectile {
    private String type;
    private Image imageIcon;
    private int damage;
    private int currentX;
    private int currentY;
    private int speed = 100;  // Adjust speed as needed
    private EnemyModel theTarget;
    private int hitThreshold = 15;  // Defines how close the projectile needs to be to hit the target
    private boolean active = true; // Tracks if the projectile is still active

    public Projectile(String type, String imagePath, int damage, int startX, int startY, EnemyModel theTarget) {
        this.type = type;
        this.imageIcon = new ImageIcon(imagePath).getImage();  // Load image from the file path
        this.damage = damage;
        this.currentX = startX;
        this.currentY = startY;
        this.theTarget = theTarget;

    }

    public void move() {
        if (!active || theTarget == null) return; // No target, don't move

        // Calculate the direction toward the target
        int targetX = theTarget.getCurrentCol();
        int targetY = theTarget.getCurrentRow();

        // Simple linear movement
        int dx = targetX - currentX;
        int dy = targetY - currentY;

        // Normalize movement
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            currentX += (dx / distance) * speed;
            currentY += (dy / distance) * speed;
        }

    }

    public void draw(Graphics g) {
        if (!active) return; // Don't draw inactive projectiles
        if (imageIcon != null) {
            // Draw the image with a fixed size (width: 20, height: 20)
            g.drawImage(imageIcon, currentX, currentY, 20, 20, null);
        } else {
            // Fallback: draw a red rectangle if the image isn't loaded
            g.setColor(Color.RED);
            g.fillRect(currentX, currentY, 10, 10);  // Simple red square as a placeholder
        }

        // Check if the projectile has hit the target
        if (hasHitTarget()) {
            System.out.println("Hit detected in hasHitTarget()");
            hitTarget();  // Call the method to handle hitting the target
        }

    }

    // Collision detection method
    public boolean hasHitTarget() {
        int targetX = theTarget.getPixelX();
        int targetY = theTarget.getPixelY();

        // Check if the projectile is within a certain threshold distance from the target
        int dx = targetX - currentX;
        int dy = targetY - currentY;
        return Math.sqrt(dx * dx + dy * dy) <= hitThreshold;
    }

    // Handle what happens when the projectile hits the target
    private void hitTarget() {
        System.out.println("Bug hit! Dealing " + damage + " damage.");
        System.out.println("Target health before: " + theTarget.getHealth());
        theTarget.setHealth(theTarget.getHealth() - damage);  // Reduce enemy's health
        System.out.println("Target health after: " + theTarget.getHealth());
        // The projectile can be removed after hitting, but that logic will be handled by the cannon class
        active = false;  // Mark the projectile as inactive
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }
    public boolean isActive() {
        return active;
    }
}
