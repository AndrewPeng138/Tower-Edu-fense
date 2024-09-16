import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cannon {
    // List to keep track of all active projectiles
    private List<Projectile> projectiles;
    private String direction;

    public Cannon(String direction) {
        this.direction = direction;  // Direction where the cannon is firing ("left", "right", "up", "down")
        this.projectiles = new ArrayList<>();
    }

    /**
     * Fires a projectile.
     * @param startX x coordinate where the cannon is located
     * @param startY y coordinate where the cannon is located
     */
    public void fire(int startX, int startY) {
        // Create a new projectile and add it to the list
        Projectile newProjectile = new Projectile("cannon", "Images/TowerSprites/CannonTower.png", 50, startX, startY, direction);
        projectiles.add(newProjectile);
    }

    /**
     * Updates the state of all projectiles (i.e., moves them).
     */
    public void updateProjectiles() {
        for (Projectile projectile : projectiles) {
            projectile.move();
        }
        // Optionally, remove projectiles that have gone off-screen
        projectiles.removeIf(this::isOffScreen);
    }

    /**
     * Draws all active projectiles.
     * @param g Graphics object for drawing
     */
    public void drawProjectiles(Graphics g) {
        for (Projectile projectile : projectiles) {
            projectile.draw(g);
        }
    }

    /**
     * Checks if a projectile is off the screen.
     */
    private boolean isOffScreen(Projectile projectile) {
        return projectile.getCurrentX() < 0 || projectile.getCurrentX() > 800 ||  // Adjust screen width
                projectile.getCurrentY() < 0 || projectile.getCurrentY() > 600;   // Adjust screen height
    }
}
