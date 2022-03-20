package ui.panels;

import model.*;
import ui.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;

// Modelled after CPSC 210's SpaceInvaders app
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
public class GamePanel extends JPanel {
    public static final int HEIGHT = 540;
    public static final Color BG_COLOR = new Color(24, 24, 24);
    public static final int CARD_WIDTH = 90;
    public static final int CARD_HEIGHT = 120;
    public static final Color CARD_COLOR = Color.white;

    private Deck deck;
    private User user;
    private Dealer dealer;

    // EFFECTS: constructs game panel using given game state
    public GamePanel(GameState gs) {
        setPreferredSize(new Dimension(BlackjackPlayer.WIDTH, HEIGHT));
        setBackground(BG_COLOR);
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
        drawDeck(g);
        drawHands(g);
    }

    // MODIFIES: g
    // EFFECTS: draws deck onto g
    private void drawDeck(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(CARD_COLOR);
        g.fillRect(BlackjackPlayer.WIDTH - 150, HEIGHT / 2 - 60, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(savedCol);
    }

    private void drawHands(Graphics g) {
        drawPlayer(g, user);
        drawPlayer(g, dealer);
    }

    // MODIFIES: g
    // EFFECTS: draws user hand onto g
    private void drawPlayer(Graphics g, Player player) {
        Hand hand = player.getHand();
        for (int i = 0; i < hand.getSize(); i++) {
            drawCard(g, hand.getCards().get(i), i, player);
        }
    }

    // MODIFIES: g
    // EFFECTS: draws card on player's side, x pos based on index, onto g
    private void drawCard(Graphics g, Card card, int index, Player player) {
        int ypos = 0;
        if (player.equals(user)) {
            ypos = HEIGHT - CARD_HEIGHT - 50;
        } else if (player.equals(dealer)) {
            ypos = 50;
        }
        int xpos = CARD_WIDTH * index + index * 20 + 50;
        Color savedCol = g.getColor();
        g.setColor(CARD_COLOR);
        g.fillRect(xpos, ypos, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(savedCol);
    }

}
