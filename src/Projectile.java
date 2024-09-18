import javax.swing.*;
import java.awt.*;

public class Projectile {
    private String type;
    private Image imageIcon;
    private int damage;
    private int currentX;
    private int currentY;
    private int speed = 10;  // Adjust speed as needed
    private EnemyModel theTarget;
    private int hitThreshold = 15;  // Defines how close the projectile needs to be to hit the target
    private boolean active = true; // Tracks if the projectile is still active
    private GameView gameView;


    public Projectile(String type, String imagePath, int damage, int startX, int startY, EnemyModel theTarget, GameView gameView) {
        this.type = type;
        this.imageIcon = new ImageIcon(imagePath).getImage();  // Load image from the file path
        this.damage = damage;
        this.currentX = startX;
        this.currentY = startY;
        this.theTarget = theTarget;
        this.gameView = gameView;  // Store the reference to GameView
    }

    public void move() {
        // Deactivate projectile if target is null, dead, or inactive
        if (!active || theTarget == null || !theTarget.isAlive()) {
            active = false;  // Ensure projectile deactivates if the target is dead
            return;
        }

        // Calculate the current direction toward the target
        int targetX = theTarget.getPixelX();  // Get the target's current pixel position
        int targetY = theTarget.getPixelY();

        int dx = targetX - currentX;
        int dy = targetY - currentY;

        // Normalize movement
        double distance = Math.sqrt(dx * dx + dy * dy);
        if (distance > 0) {
            currentX += (dx / distance) * speed;  // Update X position
            currentY += (dy / distance) * speed;  // Update Y position
        }

        // Check if the projectile has reached the target
        if (hasHitTarget()) {
            hitTarget();
        }
    }


    public void draw(Graphics g) {
        if (!active) return; // Don't draw inactive projectiles

        if (imageIcon != null) {
            // Draw the image with a fixed size (width: 50, height: 50)
            g.drawImage(imageIcon, currentX, currentY, 50, 50, null);
        } else {
            // Fallback: draw a red rectangle if the image isn't loaded
            g.setColor(Color.RED);
            g.fillRect(currentX, currentY, 50, 50);  // Simple red square as a placeholder
        }
    }

    // Collision detection method
    public boolean hasHitTarget() {
        if (theTarget == null) return false;  // Check for null target

        int targetX = theTarget.getPixelX();
        int targetY = theTarget.getPixelY();

        // Check if the projectile is close enough to the target to count as a hit
        int dx = targetX - currentX;
        int dy = targetY - currentY;
        return Math.sqrt(dx * dx + dy * dy) <= hitThreshold;
    }

    // Handle what happens when the projectile hits the target
    private void hitTarget() {
        if (theTarget == null || !theTarget.isAlive()) {
            // If the target is null or already dead, deactivate the projectile
            active = false;
            return;
        }

        // Deal damage to the target
        theTarget.setHealth(theTarget.getHealth() - damage);

        // If the target's health drops to 0 or below, eliminate it
        if (!theTarget.isAlive()) {
            gameView.removeEnemy(theTarget);  // Remove the enemy from the game
        }

        // Deactivate the projectile once it has successfully hit the target
        active = false;
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
