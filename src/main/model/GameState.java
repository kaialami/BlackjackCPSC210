package model;

// Current state of the blackjack game that stores a user
public class GameState {
    private User user;

    // EFFECTS: creates empty game state
    public GameState() {}

    // MODIFIES: this
    // EFFECTS: sets game state's stored user to given user
    public void setUser(User user) {
        this.user = user;
    }

    // EFFECTS: returns stored user object
    public User getUser() {
        return user;
    }
}
