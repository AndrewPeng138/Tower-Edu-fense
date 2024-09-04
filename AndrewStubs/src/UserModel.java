public class UserModel {
    private int currency;
    private int health;

    /**
     * Decreases player currency by set amount
     * @param value amount to decrease player currency by
     */
    public void decreaseUserCurrency(int value){
        // TODO: implement method
        this.currency -= value;
    }

    /**
     * Increases player currency by set amount
     * @param value amount to increase player currency by
     */
    public void increaseUserCurrency(int value){
        // TODO: implement method
        this.currency += value;
    }

    /**
     * Decreases player health by set amount
     * @param value amount to decrease player health by
     */
    public void decreaseUserHealth(int value){
        // TODO: implement method
        this.health -= value;
    }
}
