import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cannon {
    // List to keep track of all active projectiles
    private List<Projectile> projectiles;
    private EnemyModel theTarget;
    private int xLoc;
    private int yLoc;
    private long lastFiredTime;  // Keep track of the last time the cannon fired
    private long fireCooldown = 500;  // 1000 ms (1 second) cooldown between shots
    private boolean hasFiredOnce = false;  // Flag to check if the cannon has fired once


    public Cannon(int xLoc, int yLoc) {
        this.projectiles = new ArrayList<>();
        this.xLoc = xLoc;
        this.yLoc = yLoc;
        this.lastFiredTime = System.currentTimeMillis();  // Initialize the last fired time to now
    }

    /**
     * Fires a projectile.
     * @param target the target we're firing at
     */
    public void fire(EnemyModel target) {
        long currentTime = System.currentTimeMillis();
        // Check if enough time has passed since the last shot
        if (!hasFiredOnce || currentTime - lastFiredTime >= fireCooldown) {
            // Ensure the target is not null
            if (target != null) {
                Projectile newProjectile = new Projectile("default", "Images/TowerSprites/DefaultProjectile.png", 50, this.xLoc, this.yLoc, target);
                projectiles.add(newProjectile);
            }
            // Update the last fired time
            lastFiredTime = currentTime;
            hasFiredOnce = true;
        }
    }


    /**
     * Updates the state of all projectiles (i.e., moves them).
     */
    public void updateProjectiles() {
        // Move projectiles and remove inactive ones
        projectiles.removeIf(projectile -> !projectile.isActive());

        for (Projectile projectile : projectiles) {
            projectile.move();  // Move each projectile
        }

        // Remove off-screen projectiles only if necessary
        projectiles.removeIf(this::isOffScreen);

        // Print out the size of the projectiles list
    }


    /**
     * Draws all active projectiles.
     * @param g Graphics object for drawing
     */
    public void drawProjectiles(Graphics g) {
        for (Projectile projectile : projectiles) {
            projectile.draw(g);  // This should trigger the draw method for each projectile
        }
    }


    /**
     * Checks if a projectile is off the screen.
     */
    private boolean isOffScreen(Projectile projectile) {
        return projectile.getCurrentX() < 0 || projectile.getCurrentX() > 800 ||  // Adjust screen width
                projectile.getCurrentY() < 0 || projectile.getCurrentY() > 600;   // Adjust screen height
    }

    public int getyLoc() {
        return yLoc;
    }

    public int getxLoc() {
        return xLoc;
    }
    // Method to return the list of projectiles
    public List<Projectile> getProjectiles() {
        return projectiles;
    }
}