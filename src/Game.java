public class Game
{
    private MapModel map;
    private EnemyModel enemy;
    private int enemyX = 0;  // Start position X
    private int enemyY = 14; // Start position Y (entrance position based on your map setup)

    public Game() {
        map = new MapModel("easy");
        enemy = new EnemyModel();
        // Initialize the enemy at the starting position
        enemy.setPosition(enemyX, enemyY);
    }

    public void startGame() {
        while (!isAtExit(enemyX, enemyY)) {
            moveEnemy();
            try {
                Thread.sleep(1000); // Wait a second to see the movement
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Enemy has reached the exit!");
    }

    private void moveEnemy() {
        // Assuming the enemy moves vertically down the path
        if (enemyY < 16 && map.getLocations()[enemyX][enemyY + 1] instanceof EnemyTile) {
            enemyY++;
        } else if (enemyX < 16) {
            enemyX++;
        }
        System.out.println("Enemy moved to: " + enemyX + ", " + enemyY);
    }

    private boolean isAtExit(int x, int y) {
        return map.getLocations()[x][y].getExit();
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }
}
