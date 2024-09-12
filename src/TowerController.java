public class TowerController {

    /**
     * Gets x coordinate of tower
     * @return x coordinate of tower
     */

    /*public int getX(){
        // TODO: implement method
        // return this.x;
    }*/

    /**
     * Gets y coordinate of tower
     * @return y coordinate of tower
     */

    /*public int getY(){
        // TODO: implement method
        // return this.y;
    }*/

    public String getLocation(TowerProperties tower) {
        String location = tower.getX() + "," + tower.getY();
        return location;
    }


    /**
     * Sets location of tower on game board, should only be activated if the tower was selected
     * @param x x-coordinate to be set
     * @param y y-coordinate to be set
     */

    public void setLocation(int x, int y){
        // TODO: implement method
        // this.x = x;
        // this.y = y;
    }

    /**
     * Checks if tower is selected to be placed on game board
     * @return if button for tower is selected
     */

    /*public boolean isSelected(){
        // TODO: implement method
        // return jButton.isPressed();
    }*/

}
