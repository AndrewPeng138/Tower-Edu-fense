import javax.swing.border.Border;

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
        // Instantiates a new Tile Factory to manufacture our necessary tiles
        TileFactory theFactory = new TileFactory();

        // EASY MAP
        if (mapType.equals("easy")) {

            // Fills the entire grid with borders initially
            for (int i = 0; i < locations.length; i++) {
                for (int j = 0; j < locations.length; j++) {
                    BorderTile borderTile = (BorderTile) theFactory.createTile("border");
                    locations[i][j] = borderTile;
                }
            }
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy");
            entranceTile.setEntrance(true);
            locations[0][14] = entranceTile;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy");
            exitTile.setExit(true);
            locations[16][2] = exitTile;

            // Stock tiles to be placed anywhere
            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy");
            LandTile landTile = (LandTile) theFactory.createTile("land");
            // First row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[1][x] = landTile;
            }
            locations[1][14] = enemyTile;
            locations[1][15] = landTile;
            // Second row of map (Mostly enemy)
            locations[2][1] = landTile;
            for (int x = 2; x < 15; x++) {
                locations[2][x] = enemyTile;
            }
            locations[2][15] = landTile;
            // Third row of map (Mostly land)
            locations[3][1] = landTile;
            locations[3][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[3][x] = landTile;
            }
            // Fourth row of map (Mostly enemy)
            locations[4][1] = landTile;
            for (int x = 2; x < 15; x++) {
                locations[4][x] = enemyTile;
            }
            locations[4][15] = landTile;
            // Fifth row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[5][x] = landTile;
            }
            locations[5][14] = enemyTile;
            locations[5][15] = landTile;
            // Sixth row of map (Mostly enemy)
            locations[6][1] = landTile;
            for (int x = 2; x < 15; x++) {
                locations[6][x] = enemyTile;
            }
            locations[6][15] = landTile;
            // Seventh row of map (Mostly land)
            locations[7][1] = landTile;
            locations[7][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[7][x] = landTile;
            }
            // Eighth row of map (Mostly enemy)
            locations[8][1] = landTile;
            for (int x = 2; x < 15; x++) {
                locations[8][x] = enemyTile;
            }
            locations[8][15] = landTile;
            // Ninth row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[9][x] = landTile;
            }
            locations[9][14] = enemyTile;
            locations[9][15] = landTile;
            // Tenth row of map (Mostly enemy)
            locations[10][1] = landTile;
            for (int x = 2; x < 15; x++) {
                locations[10][x] = enemyTile;
            }
            locations[10][15] = landTile;
            // Eleventh row of map (Mostly land)
            locations[11][1] = landTile;
            locations[11][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[11][x] = landTile;
            }
            // Twelfth row of map (Mostly enemy)
            for (int x = 1; x < 14; x++) {
                locations[12][x] = landTile;
            }
            locations[12][14] = enemyTile;
            locations[12][15] = landTile;
            // Thirteenth row of map (Mostly land)
            for (int x = 1; x < 14; x++) {
                locations[13][x] = landTile;
            }
            locations[13][14] = enemyTile;
            locations[13][15] = landTile;
            // Fourteenth row of map (Mostly enemy)
            locations[14][1] = landTile;
            for (int x = 2; x < 15; x++) {
                locations[14][x] = enemyTile;
            }
            locations[14][15] = landTile;
            // Fifteenth row of map (Mostly land)
            locations[15][1] = landTile;
            locations[15][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[15][x] = landTile;
            }

        } // End of Easy Map if statement


        // NORMAL MAP
        if (mapType.equals("normal")) {

        } // End of Normal Map if statement
        // HARD MAP
        if (mapType.equals("hard")) {

        } // End of Hard Map if statement
        // EXTREME MAP
        if (mapType.equals("extreme")) {

        } // End of EXTREME Map if statement

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

