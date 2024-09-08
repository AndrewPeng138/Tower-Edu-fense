
import org.junit.Test;
import org.junit.Assert;


public class MapModelTest {
    MapModel easyMap = new MapModel("easy");
    MapModel normalMap = new MapModel("normal");
    MapModel hardMap = new MapModel("hard");
    MapModel extremeMap = new MapModel("extreme");
    // Matches easyMap
    Tile[][] testTile = new Tile[17][17];
    @Test
    public void MapTests() {
        // Should pass
        extremeMap.printLocations();
    }
}
