public class EnemyController {
    private EnemyModel enemy;
    private Player player; // Reference to the player to interact with player's health

    public EnemyController(EnemyModel enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }

    public void setHealth(int value) {
        enemy.setHealth(value);
    }

    public void setSpeed(int value) {
        enemy.setSpeed(value);
    }

    public void setDamage(int value) {
        enemy.setDamage(value);
    }

    // Series of actions to take place once enemy reaches end of path
    public void reachEnd() {
        enemy.setHealth(0); // Enemy 'dies' or is removed from the game
        player.decreaseHealth(enemy.getDamage()); // Decrease player's health by the enemy's damage
    }

    public void update() {
        // Movement logic based on enemy's speed and pathfinding logic
        // Placeholder: move the enemy forward one tile for simplicity
        int nextX = enemy.getX() + 1; // Example movement logic
        int nextY = enemy.getY();
        enemy.move(nextX, nextY);
        if (hasReachedEnd(nextX, nextY)) {
            reachEnd();
        }
    }

    private boolean hasReachedEnd(int x, int y) {
        // Implement logic to determine if the current tile is the exit
        return false; // Placeholder: needs actual implementation
    }
}
