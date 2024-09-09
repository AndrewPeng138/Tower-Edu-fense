import org.junit.Assert;
import org.junit.Test;

public class EnemyTileTest {





    @Test
    public void EnemyTileTests() {
        TileFactory theFactory = new TileFactory();
        EnemyTile normalEnemy = (EnemyTile) theFactory.createTile("enemy");

        EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy");
        entranceTile.setEntrance(true);
        EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy");
        exitTile.setExit(true);
        // Should pass
        Assert.assertEquals(true, entranceTile.getEntrance());
        // Should pass
        Assert.assertEquals(true, exitTile.getExit());
        // Should pass
        Assert.assertEquals(false, normalEnemy.getEntrance());

    }
}