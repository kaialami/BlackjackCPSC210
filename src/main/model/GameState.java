package model;

// Current state of the blackjack game that stores a user's balance
public class GameState {
    private int userBalance;

    // EFFECTS: creates empty game state where user balance has not been stored yet
    //          -1 means unstored balance
    public GameState() {
        userBalance = -1;
    }

    // MODIFIES: this
    // EFFECTS: sets game state's stored user to given user
    public void setUserBalance(int userBalance) {
        this.userBalance = userBalance;
    }

    // EFFECTS: returns stored user object
    public int getUserBalance() {
        return userBalance;
    }
}
