package ui.panels;

import ui.BlackjackPlayer;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {
    private static final int HEIGHT = 45;
    private static final int FONT_SIZE = 20;
    private static final Font FONT = new Font("Cambria", 0, FONT_SIZE);

    private static final String WELCOME = "Welcome to Blackjack! Start game?";

    private JLabel jlabel;

    public TextPanel() {
        setPreferredSize(new Dimension(BlackjackPlayer.WIDTH, HEIGHT));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        jlabel = new JLabel(WELCOME);
        jlabel.setFont(FONT);
        jlabel.setHorizontalAlignment(SwingConstants.LEFT);
        this.add(jlabel, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawText(g);
    }

    private void drawText(Graphics g) {
        g.drawString("hi", 0, 0);
    }


}
