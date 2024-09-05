/**
 * Stores data for the gameMap
 * Construct a game map by making a MapModel object and pass in the map type to auto construct it
 */

public class MapModel {
    // 2D array contain all the map tiles
    private Tile[][] locations = new Tile[17][17];

    /**
     * Constructor for new maps, keep in mind [y][x] coordinates
     * @param mapType easy, normal, hard, or extreme map
     */
    public MapModel(String mapType) {
        TileFactory theFactory = new TileFactory();
        EnemyTile normalEnemy = (EnemyTile) theFactory.createTile("enemy");

        // EASY MAP
        if (mapType.equals("easy")) {
            for (int i = 0; i < locations.length; i++) {
                for (int j = 0; j < locations.length; j++) {
                    Tile borderTile = theFactory.createTile("border");
                    locations[i][j] = borderTile;
                }
            }
        }
        // NORMAL MAP
        if (mapType.equals("normal")) {

        }
        // HARD MAP
        if (mapType.equals("hard")) {

        }
        // EXTREME MAP
        if (mapType.equals("extreme")) {

        }

    }

    /**
     * Essential method that provides the game board once it is correctly constructed
     * @return the 2D Tile array of the game board
     */
    public Tile[][] getLocations() {
        return locations;
    }

    /**
     * Prints all locaions in the map model for design and testing purposes
     */
    public void printLocations() {
        for (int i = 0; i < locations.length; i++) {
            for (int j =0; j < locations.length; j++) {
                // prints the type at the location bounded by | and |
                System.out.print("|"+ locations[i][j].getType() + "|");
            }
            System.out.println();
        }
    }


}
