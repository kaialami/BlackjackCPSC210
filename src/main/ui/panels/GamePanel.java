package ui.panels;

import model.*;
import ui.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;

// Modelled after CPSC 210's SpaceInvaders app
// https://github.students.cs.ubc.ca/CPSC210/B02-SpaceInvadersBase.git
public class GamePanel extends JPanel {
    public static final int HEIGHT = 540;
    public static final Color BG_COLOR = new Color(51, 51, 51);
    public static final int CARD_WIDTH = 90;
    public static final int CARD_HEIGHT = 120;
    public static final Color CARD_COLOR = Color.white;
    public static final Font FONT = new Font("Serif", Font.PLAIN, 22);
    public static final Color RED = new Color(180, 0, 0);

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
        g.setFont(FONT);
        drawGame(g);
    }

    //MODIFIES: g
    // EFFECTS: draws game onto g
    private void drawGame(Graphics g) {
        drawDeck(g);
        drawHands(g);
        drawBetBalance(g);
    }

    // MODIFIES: g
    // EFFECTS: draws user bet and balance onto g
    private void drawBetBalance(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(Color.white);
        g.drawString("BET: " + user.getBet() + "       BALANCE: " + user.getBalance(),
                20, HEIGHT - g.getFont().getSize());
    }

    // MODIFIES: g
    // EFFECTS: draws deck onto g
    private void drawDeck(Graphics g) {
        int xpos = BlackjackPlayer.WIDTH - 150;
        int ypos = HEIGHT / 2 - 70;
        int cardYPos = ypos + CARD_HEIGHT / 2;
        Color savedCol = g.getColor();
        g.setColor(CARD_COLOR);
        g.fillRect(xpos, ypos, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(Color.BLACK);
        g.drawString("♠", xpos + 8, cardYPos);
        g.setColor(RED);
        g.drawString("◆", xpos + 25, cardYPos);
        g.setColor(Color.BLACK);
        g.drawString("♣", xpos + CARD_WIDTH - 40, cardYPos);
        g.setColor(RED);
        g.drawString("♥", xpos + CARD_WIDTH - 20, cardYPos);
        g.setColor(savedCol);
    }

    // MODIFIES: g
    // EFFECTS: draws user and dealer hands onto g
    private void drawHands(Graphics g) {
        drawPlayer(g, user);
        drawPlayer(g, dealer);
    }

    // MODIFIES: g
    // EFFECTS: draws player hand onto g
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
            ypos = HEIGHT - CARD_HEIGHT - 60;
        } else if (player.equals(dealer)) {
            ypos = 30;
        }
        int xpos = CARD_WIDTH * index + index * 20 + 50;
        String cardText = generateCardText(card, player, index);
        Color cardFontColor = getCardColor(card);
        Color savedCol = g.getColor();
        g.setColor(CARD_COLOR);
        g.fillRect(xpos, ypos, CARD_WIDTH, CARD_HEIGHT);
        g.setColor(cardFontColor);
        g.drawString(cardText, xpos + CARD_WIDTH / 2 - 16, ypos + CARD_HEIGHT / 2 + 5);
        g.setColor(savedCol);
    }

    // EFFECTS: returns red/black depending on card suit
    private Color getCardColor(Card card) {
        int suit = card.getSuit();
        if (suit == 0 || suit == 3) {
            return Color.black;
        } else {
            return RED;
        }
    }

    // EFFECTS: helper method to generate card text
    private String generateCardText(Card card, Player player, int index) {
        String cardText;
        if (player.equals(dealer) && index == 0 && !dealer.isTurn()) {
            cardText = "  ?";
        } else {
            cardText = chooseSuit(card) + " " + chooseValue(card);
        }
        return cardText;
    }

    // EFFECTS: returns card value as string
    private String chooseValue(Card card) {
        String valueString;
        int value = card.getValue();
        if (value == 1) {
            valueString = "A";
        } else if (value == 11) {
            valueString = "J";
        } else if (value == 12) {
            valueString = "Q";
        } else if (value == 13) {
            valueString = "K";
        } else {
            if (value == 10) {
                valueString = Integer.toString(value);
            } else {
                valueString = value + " ";
            }
        }
        return valueString;
    }

    // EFFECTS: returns card suit as string;
    private String chooseSuit(Card card) {
        String suitString;
        int suit = card.getSuit();
        if (suit == 0) {
            suitString = "♣";
        } else if (suit == 1) {
            suitString = "◆";
        } else if (suit == 2) {
            suitString = "♥";
        } else {
            suitString = "♠";
        }
        return suitString;
    }
}
