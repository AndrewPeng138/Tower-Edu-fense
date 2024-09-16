/**
 * Interface representing the model of a tower in the game.
 * Defines the behavior that any tower model should implement.
 */
public interface TowerModelI {

    /**
     * Fires the tower at the given wave of enemies.
     * The exact behavior of the firing mechanism is determined by the implementing class.
     *
     * @param enemyWave The wave of enemies that the tower will target.
     */
    void Fire(Wave enemyWave);
}
