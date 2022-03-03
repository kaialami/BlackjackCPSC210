package model;

// Current state of the blackjack game
// Stores user, dealer, deck, and their associated fields
public class GameState {
    private User user;
    private Dealer dealer;
    private Deck deck;


    // EFFECTS: creates game state with saved user, dealer, deck
    public GameState(User user, Dealer dealer, Deck deck) {
        this.user = user;
        this.dealer = dealer;
        this.deck = deck;
    }


    // MODIFIES: this
    // EFFECTS: sets user to given user
    public void setUser(User user) {
        this.user = user;
    }

    // MODIFIES: this
    // EFFECTS: sets dealer to given dealer
    public void setDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    // MODIFIES: this
    // EFFECTS: sets deck to given deck
    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    // Getters
    public User getUser() {
        return user;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }
}
