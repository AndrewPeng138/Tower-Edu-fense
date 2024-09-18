import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Flame implements TowerModelI {
    // List to keep track of all active projectiles
    private List<Projectile> projectiles;
    private EnemyModel theTarget;
    private int xLoc;
    private int yLoc;
    private long lastFiredTime;  // Keep track of the last time the cannon fired
    private long fireCooldown = 500;  // 1000 ms (1 second) cooldown between shots
    private boolean hasFiredOnce = false;  // Flag to check if the cannon has fired once


    public Flame(int xLoc, int yLoc) {
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
        // Create a copy of the projectiles list
        List<Projectile> projectilesCopy = new ArrayList<>(projectiles);

        // Move projectiles and remove inactive ones
        projectilesCopy.removeIf(projectile -> !projectile.isActive());

        for (Projectile projectile : projectilesCopy) {
            projectile.move();  // Move each projectile
        }

        // Remove off-screen projectiles only if necessary
        projectilesCopy.removeIf(this::isOffScreen);

        // Replace the original list with the updated copy after modifications
        projectiles = projectilesCopy;
    }


    /**
     * Draws all active projectiles.
     * @param g Graphics object for drawing
     */
    public void drawProjectiles(Graphics g) {
        // Create a copy of the projectiles list
        List<Projectile> projectilesCopy = new ArrayList<>(projectiles);

        // Iterate over the copy to draw the projectiles
        for (Projectile projectile : projectilesCopy) {
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