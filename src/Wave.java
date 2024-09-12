import java.util.ArrayList;

/**
 * A wave has a specific array list of enemies inside it that is assigned
 * based on which wave counter number it is constructed with
 */
public class Wave {
    // Can be waves 1-20
    private int waveCounter;
    private ArrayList<EnemyModel> waveList;

    /**
     * Constructs a wave object that contains the correct wave that the game is on
     * @param waveCounter which wave, 1-20
     */
    public Wave(int waveCounter) {
        this.waveCounter = waveCounter;
        // Wave counter determines what enemies are placed into a wave
        if (this.waveCounter == 1) {
            // 3 Roaches
            // 150 combined total health
            //waveList.add(new Roach());
            //waveList.add(new Roach());
            //waveList.add(new Roach());
        }
        if (this.waveCounter == 2 ) {
            // 4 Roaches
            // 200 combined total health
            for (int i = 0; i < 4; i++) {
                //waveList.add(new Roach());
            }
        }
        if (this.waveCounter == 3) {
            // 6 roaches
            // 300 combined total health
            for (int i = 0; i < 6; i++) {
                //waveList.add(new Roach());
            }
        }
        if (this.waveCounter == 4) {
            // 4 roaches
            // 1 beetle
            // 300 combined total health
            for (int i = 0; i < 4; i++) {
                //waveList.add(new Roach());
            }
            waveList.add(new Beetle());
        }
        if (this.waveCounter == 5) {
            // 4 beetles
            // 400 combined total health
            for (int i = 0; i < 4; i++) {
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 6) {
            // 3 roaches
            // 3 beetles (every other)
            // 450 combined total health
            for (int i = 0; i < 3; i++) {
                //waveList.add(new Roach());
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 7) {
            // 4 roaches
            // 4 beetles (every other)
            // 600 combined total health
            for (int i = 0; i < 4; i++) {
                //waveList.add(new Roach());
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 8) {
            // 2 roaches
            // 6 beetles
            // 700 combined total health
            //waveList.add(new Roach());
            //waveList.add(new Roach());
            for (int i = 0; i < 6; i++) {
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 9) {
            // 8 beetles
            // 800 combined total health
            for (int i = 0; i < 8; i++) {
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 10) {
            // 45 mosquitos
            // 900 combined total health
            for (int i = 0; i < 45; i++) {
                waveList.add(new Mosquito());
            }
        }
        if (this.waveCounter == 11) {
            // 35 mosquitos
            // 10 roaches
            // 1200 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new Mosquito());
                //waveList.add(new Roach());
            }
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito());
            }
        }
        if (waveCounter == 12) {
            // 35 mosquitos
            // 5 roaches
            // 5 beetles
            // 1450 combined total health
        for (int i = 0; i < 5; i++) {
            waveList.add(new Mosquito());
            //waveList.add(new Roach());
            waveList.add(new Beetle());
        }
        for (int i = 0; i < 30; i++) {
            waveList.add(new Mosquito());
        }
        }
        if (this.waveCounter == 13) {
            // 16 beetles
            // 1600 combined total health
            for (int i = 0; i < 16; i++) {
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 14) {
            // 50 mosquitos
            // 10 roaches
            // 5 beetles
            // 2000 combined total health
            for (int i = 0; i < 50; i++) {
                waveList.add(new Mosquito());
            }
            for (int i = 0; i < 10; i++) {
               // waveList.add(new Roach());
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Beetle());
            }
        }
        if (this.waveCounter == 15) {
            // 10 metal roaches
            // 500 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new MetalRoach());
            }
        }
        if (this.waveCounter == 16) {
            // 10 metal roaches
            // 10 roaches
            // 50 mosquitos
            // 2000 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new MetalRoach());
                //waveList.add(new Roach());
            }
            for (int i = 0; i < 50; i++) {
                waveList.add(new Mosquito());
            }
        }
        if (this.waveCounter == 17) {
            // 10 metal roaches
            // 10 beetles
            // 10 roaches
            // 25 mosquitos
            // 2500 combined total health
            for (int i = 0; i < 10; i++) {
                waveList.add(new MetalRoach());
                waveList.add(new Beetle());
                //waveList.add(new Roach());
            }
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito());
            }
        }
        if (this.waveCounter == 18) {
            // 25 mosquitos
            // 5 beetles
            // 10 roaches
            // 5 metal roaches
            // 5 beetles
            // 10 roaches
            // 5 metal roaches
            // 3000 combined total health
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito());
            }
            // Does this twice
            for (int x = 0; x < 2; x++) {
                for (int i = 0; i < 5; i++) {
                    waveList.add(new Beetle());
                }
                for (int i = 0; i < 10; i++) {
                    //waveList.add(new Roach());
                }
                for (int i = 0; i < 5; i++) {
                    waveList.add(new MetalRoach());
                }
            }
        }
        if (this.waveCounter == 19) {
            // 25 mosquitos
            // 15 beetles
            // 15 roaches
            // 15 metal roaches
            // 3500 combined total health
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito());
            }
            for (int i = 0; i < 15; i++) {
                waveList.add(new Beetle());
                //waveList.add(new Roach());
                waveList.add(new MetalRoach());
            }
        }
        if (this.waveCounter == 20) {
            // 25 mosquitos
            // 5 roaches
            // 5 metal roaches
            // 5 beetles
            // 1 praying mantis
            // 5 roaches
            // 5 metal roaches
            // 5 beetles
            // 25 mosquitos
            // 4000 combined total health
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito());
            }
            for (int i = 0; i < 5; i++) {
                //waveList.add(new Roach());
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new MetalRoach());
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Beetle());
            }
            waveList.add(new PrayingMantis());
            for (int i = 0; i < 5; i++) {
                //waveList.add(new Roach());
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new MetalRoach());
            }
            for (int i = 0; i < 5; i++) {
                waveList.add(new Beetle());
            }
            for (int i = 0; i < 25; i++) {
                waveList.add(new Mosquito());
            }
        }
    }

    public ArrayList<EnemyModel> getWave(){
        return waveList;
    }

}
