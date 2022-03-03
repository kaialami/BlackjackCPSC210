package model;

// Current state of the blackjack game that stores a user and deck object
public class GameState {
    private User user;
    private Deck deck;

    // EFFECTS: creates empty game state
    public GameState() {}

    // MODIFIES: this
    // EFFECTS: sets game state's stored user to given user
    public void setUser(User user) {
        this.user = user;
    }

    // MODIFIES: this
    // EFFECTS: sets game state's stored deck to given deck
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // EFFECTS: returns stored user object
    public User getUser() {
        return user;
    }

    // EFFECTS: returns stored deck object
    public Deck getDeck() {
        return deck;
    }
}
