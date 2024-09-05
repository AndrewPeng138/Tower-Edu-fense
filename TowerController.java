public class TowerController {

    /**
     * Gets x coordinate of tower
     * @return x coordinate of tower
     */
    public String getLocation(TowerModel tower) {
        String location = tower.getX() + "," + tower.getY();
        return location;
    }

    /**
     * Sets location of tower on game board, should only be activated if the tower was selected
     * @param x x-coordinate to be set
     * @param y y-coordinate to be set
     */
    public void setLocation(TowerModel tower, int x, int y){
        if(this.isSelected(tower)){
        tower.setX(x);
        tower.setY(y);
        }
    }

    /**
     * Checks if tower is selected to be placed on game board
     * @return if button for tower is selected
     */
    public boolean isSelected(TowerModel tower){
        // TODO: need to get more information on how GUI will react to get action of once button is pushed
        return JButton.isPressed();
    }
}
