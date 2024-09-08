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
        // Fills the entire grid with borders initially
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                BorderTile borderTile = (BorderTile) theFactory.createTile("border");
                locations[i][j] = borderTile;
            }
        }
        // EASY MAP
        if (mapType.equals("easy")) {
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
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy");
            entranceTile.setEntrance(true);
            locations[0][2] = entranceTile;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy");
            exitTile.setExit(true);
            locations[16][14] = exitTile;

            // Stock tiles to be placed anywhere
            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy");
            LandTile landTile = (LandTile) theFactory.createTile("land");
            WaterTile waterTile = (WaterTile) theFactory.createTile("water");
            // First row of map (Mostly water)
            locations[1][1] = waterTile;
            locations[1][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[1][x] = waterTile;
            }
            // Second row of map (dashed land row)
            locations[2][1] = waterTile;
            locations[2][2] = enemyTile;
            locations[2][3] = waterTile;
            locations[2][4] = landTile;
            locations[2][5] = landTile;
            locations[2][6] = waterTile;
            locations[2][7] = landTile;
            locations[2][8] = landTile;
            locations[2][9] = waterTile;
            locations[2][10] = landTile;
            locations[2][11] = landTile;
            locations[2][12] = waterTile;
            // little land
            locations[2][13] = landTile;
            locations[2][14] = waterTile;
            locations[2][15] = waterTile;
            // Third row of map (Mostly water)
            locations[3][1] = waterTile;
            locations[3][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[3][x] = waterTile;
            }
            // Fourth row of map (Mostly enemy)
            locations[4][1] = waterTile;
            for (int x = 2; x < 15; x++) {
                locations[4][x] = enemyTile;
            }
            locations[4][15] = waterTile;
            // Fifth row of map (Mostly water)
            for (int x = 1; x < 14; x++) {
                locations[5][x] = waterTile;
            }
            locations[5][14] = enemyTile;
            locations[5][15] = waterTile;
            // Sixth row of map (Big dashed land row 1)
            locations[6][1] = waterTile;
            locations[6][2] = waterTile;
            locations[6][3] = landTile;
            locations[6][4] = landTile;
            locations[6][5] = waterTile;
            locations[6][6] = landTile;
            locations[6][7] = landTile;
            locations[6][8] = waterTile;
            // big land
            locations[6][9] = landTile;
            locations[6][10] = landTile;
            locations[6][11] = landTile;
            locations[6][12] = landTile;
            locations[6][13] = waterTile;
            locations[6][14] = enemyTile;
            locations[6][15] = waterTile;
            // Seventh row of map (Big dashed land row 2)
            locations[7][1] = waterTile;
            locations[7][2] = waterTile;
            locations[7][3] = landTile;
            locations[7][4] = landTile;
            locations[7][5] = waterTile;
            locations[7][6] = landTile;
            locations[7][7] = landTile;
            locations[7][8] = waterTile;
            // big land
            locations[7][9] = landTile;
            locations[7][10] = landTile;
            locations[7][11] = landTile;
            locations[7][12] = landTile;
            locations[7][13] = waterTile;
            locations[7][14] = enemyTile;
            locations[7][15] = waterTile;
            // Eighth row of map (Mostly water)
            for (int x = 1; x < 14; x++) {
                locations[8][x] = waterTile;
            }
            locations[8][14] = enemyTile;
            locations[8][15] = waterTile;
            // Ninth row of map (Mostly enemy)
            locations[9][1] = waterTile;
            for (int x = 2; x < 15; x++) {
                locations[9][x] = enemyTile;
            }
            locations[9][15] = waterTile;
            // Tenth row of map (Mostly water)
            locations[10][1] = waterTile;
            locations[10][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[10][x] = waterTile;
            }
            // Eleventh row of map (Big dashed land row 1)
            locations[11][1] = waterTile;
            locations[11][2] = enemyTile;
            locations[11][3] = waterTile;
            locations[11][4] = landTile;
            locations[11][5] = landTile;
            locations[11][6] = waterTile;
            locations[11][7] = landTile;
            locations[11][8] = landTile;
            locations[11][9] = landTile;
            locations[11][10] = waterTile;
            locations[11][11] = landTile;
            locations[11][12] = landTile;
            locations[11][13] = landTile;
            locations[11][14] = waterTile;
            locations[11][15] = waterTile;
            // Twelfth row of map (Big dashed land row 2)
            locations[12][1] = waterTile;
            locations[12][2] = enemyTile;
            locations[12][3] = waterTile;
            locations[12][4] = landTile;
            locations[12][5] = landTile;
            locations[12][6] = waterTile;
            locations[12][7] = landTile;
            locations[12][8] = landTile;
            locations[12][9] = landTile;
            locations[12][10] = waterTile;
            locations[12][11] = landTile;
            locations[12][12] = landTile;
            locations[12][13] = landTile;
            locations[12][14] = waterTile;
            locations[12][15] = waterTile;
            // Thirteenth row of map (Mostly water)
            locations[13][1] = waterTile;
            locations[13][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[13][x] = waterTile;
            }
            // Fourteenth row of map (Mostly enemy)
            locations[14][1] = waterTile;
            for (int x = 2; x < 15; x++) {
                locations[14][x] = enemyTile;
            }
            locations[14][15] = waterTile;
            // Fifteenth row of map (Mostly water)
            for (int x = 1; x < 14; x++) {
                locations[15][x] = waterTile;
            }
            locations[15][14] = enemyTile;
            locations[15][15] = waterTile;

        } // End of Normal Map if statement
        // HARD MAP
        if (mapType.equals("hard")) {
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy");
            entranceTile.setEntrance(true);
            locations[0][6] = entranceTile;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy");
            exitTile.setExit(true);
            locations[16][10] = exitTile;

            // Stock tiles to be placed anywhere
            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy");
            LandTile landTile = (LandTile) theFactory.createTile("land");
            WaterTile waterTile = (WaterTile) theFactory.createTile("water");
            // First row of map (Mostly water)
            for (int x = 1; x < 6; x++) {
                locations[1][x] = waterTile;
            }
            locations[1][6] = enemyTile;
            for (int x = 7; x < 16; x++) {
                locations[1][x] = waterTile;
            }
            // Second row of map (Mostly water)
            for (int x = 1; x < 6; x++) {
                locations[2][x] = waterTile;
            }
            locations[2][6] = enemyTile;
            for (int x = 7; x < 16; x++) {
                locations[2][x] = waterTile;
            }
            // Third row of map (Big island row 1)
            for (int x = 1; x < 6; x++) {
                locations[3][x] = waterTile;
            }
            locations[3][6] = enemyTile;
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[3][x] = waterTile;
            }
            // big island
            for (int x = 10; x < 14; x++) {
                locations[3][x] = landTile;
            }
            locations[3][14] = waterTile;
            locations[3][15] = waterTile;
            // Fourth row of map (Big island row 2)
            locations[4][1] = waterTile;
            for (int x = 2; x < 7; x++) {
                locations[4][x] = enemyTile;
            }
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[4][x] = waterTile;
            }
            // big island
            for (int x = 10; x < 14; x++) {
                locations[4][x] = landTile;
            }
            locations[4][14] = waterTile;
            locations[4][15] = waterTile;
            // Fifth row of map (Big island row 3)
            locations[5][1] = waterTile;
            locations[5][2] = enemyTile;
            for (int x = 3; x < 10; x++) {
                locations[5][x] = waterTile;
            }
            // big island
            for (int x = 10; x < 14; x++) {
                locations[5][x] = landTile;
            }
            locations[5][14] = waterTile;
            locations[5][15] = waterTile;
            // Sixth row of map (Little island row 1)
            locations[6][1] = waterTile;
            locations[6][2] = enemyTile;
            locations[6][3] = waterTile;
            // little island
            for (int x = 4; x < 7; x++) {
                locations[6][x] = landTile;
            }
            for (int x = 7; x < 16; x++) {
                locations[6][x] = waterTile;
            }
            // Seventh row of map (Little island row 2)
            locations[7][1] = waterTile;
            locations[7][2] = enemyTile;
            locations[7][3] = waterTile;
            // little island
            for (int x = 4; x < 7; x++) {
                locations[7][x] = landTile;
            }
            for (int x = 7; x < 16; x++) {
                locations[7][x] = waterTile;
            }
            // Eighth row of map (Mostly water)
            locations[8][1] = waterTile;
            locations[8][2] = enemyTile;
            for (int x = 3; x < 16; x++) {
                locations[8][x] = waterTile;
            }
            // Ninth row of map (Mostly enemy)
            locations[9][1] = waterTile;
            for (int x = 2; x < 15; x++) {
                locations[9][x] = enemyTile;
            }
            locations[9][15] = waterTile;
            // Tenth row of map (Mostly water)
            for (int x =1; x < 14; x++) {
                locations[10][x] = waterTile;
            }
            locations[10][14] = enemyTile;
            locations[10][15] = waterTile;
            // Eleventh row of map (Little island row 1)
            for (int x = 1; x < 10; x++) {
                locations[11][x] = waterTile;
            }
            for (int x = 10; x < 13; x++) {
                locations[11][x] = landTile;
            }
            locations[11][13] = waterTile;
            locations[11][14] = enemyTile;
            locations[11][15] = waterTile;
            // Twelfth row of map (Double island row)
            locations[12][1] = waterTile;
            locations[12][2] = waterTile;
            for (int x = 3; x < 7; x++) {
                locations[12][x] = landTile;
            }
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[12][x] = waterTile;
            }
            for (int x = 10; x < 13; x++) {
                locations[12][x] = landTile;
            }
            locations[12][13] = waterTile;
            locations[12][14] = enemyTile;
            locations[12][15] = waterTile;
            // Thirteenth row of map (Big island row 2)
            locations[13][1] = waterTile;
            locations[13][2] = waterTile;
            for (int x = 3; x < 7; x++) {
                locations[13][x] = landTile;
            }
            for (int x = 7; x < 14; x++) {
                locations[13][x] = waterTile;
            }
            locations[13][14] = enemyTile;
            locations[13][15] = waterTile;
            // Fourteenth row of map (Big island row 3);
            locations[14][1] = waterTile;
            locations[14][2] = waterTile;
            for (int x = 3; x < 7; x++) {
                locations[14][x] = landTile;
            }
            // 3 wide blue line in middle
            for (int x = 7; x < 10; x++) {
                locations[14][x] = waterTile;
            }
            for (int x = 10; x < 15; x++) {
                locations[14][x] = enemyTile;
            }
            locations[14][15] = waterTile;
            // Fifteenth row of map (Mostly water)
            for (int x = 1; x < 10; x++) {
                locations[15][x] = waterTile;
            }
            locations[15][10] = enemyTile;
            for (int x = 11; x < 16; x++) {
                locations[15][x] = waterTile;
            }
        } // End of Hard Map if statement
        // EXTREME MAP
        if (mapType.equals("extreme")) {
            // Sets entrance
            EnemyTile entranceTile = (EnemyTile) theFactory.createTile("enemy");
            entranceTile.setEntrance(true);
            locations[0][8] = entranceTile;
            // Sets exit
            EnemyTile exitTile = (EnemyTile) theFactory.createTile("enemy");
            exitTile.setExit(true);
            locations[16][8] = exitTile;

            // Stock tiles to be placed anywhere
            EnemyTile enemyTile = (EnemyTile) theFactory.createTile("enemy");
            LandTile landTile = (LandTile) theFactory.createTile("land");
            WaterTile waterTile = (WaterTile) theFactory.createTile("water");
            // Fills the play area with water
            for (int x = 1; x < 16; x++) {
                for (int y = 1; y < 16; y++) {
                    locations[x][y] = waterTile;
                }
            }
            // Draws the enemy path strip down column 8
            for (int y = 1; y < 16; y++) {
                locations[y][8] = enemyTile;
            }
            // Manually adds islands
            // Big island
            locations[2][11] = landTile;
            locations[2][12] = landTile;
            locations[2][13] = landTile;
            locations[3][11] = landTile;
            locations[3][12] = landTile;
            locations[3][13] = landTile;
            // Top left little island
            locations[3][4] = landTile;
            locations[3][5] = landTile;
            locations[4][4] = landTile;
            locations[4][5] = landTile;
            // Tiniest island
            locations[8][11] = landTile;
            // Bottom left little island;
            locations[12][3] = landTile;
            locations[12][4] = landTile;
            locations[13][3] = landTile;
            locations[13][4] = landTile;
            // Bottom right tiny island
            locations[14][12] = landTile;
            locations[14][13] = landTile;
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
     * Returns the Tile at the specified location
     * @param index1 2d array locations[index1][]
     * @param index2 2d array locations[][index2]
     * @return Tile at locations[index1][index2]
     */
    public Tile getTileAt(int index1, int index2) {
        return locations[index1][index2];
    }

    /**
     * Prints all locations in the map model for design and testing purposes
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
