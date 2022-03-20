package ui.panels;

import model.Dealer;
import model.Deck;
import model.GameState;
import model.User;
import ui.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;

// Modelled after CPSC 210's SpaceInvaders app
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
public class GamePanel extends JPanel {
    public static final int HEIGHT = 500;
    public static final Color COLOR = new Color(24, 24, 24);

    private Deck deck;
    private User user;
    private Dealer dealer;

    // EFFECTS: constructs game panel using given game state
    public GamePanel(GameState gs) {
        setPreferredSize(new Dimension(BlackjackPlayer.WIDTH, HEIGHT));
        setBackground(COLOR);
        deck = gs.getDeck();
        user = gs.getUser();
        dealer = gs.getDealer();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
    }

    //MODIFIES: g
    // EFFECTS: draws game onto g
    private void drawGame(Graphics g) {
        drawDeck();
        drawUser();
        drawDealer();
    }

    private void drawDeck() {
    }

    private void drawUser(){

    }

    private void drawDealer() {
    }
}
