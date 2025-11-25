package en.tresz.spaceinvaders.util;

import javax.swing.*;
import java.awt.*;

// TODO: Replace rectangles with hearts
public class HealthBar extends JComponent {

    private int maxHealth;
    private int currentHealth;

    private int heartWidth = 20;
    private int heartHeight = 20;
    private int spacing = 5;

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;

        setPreferredSize(new Dimension(300, 40));
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int y = 10;

        // draw from RIGHT to LEFT so hearts disappear from the left
        for (int i = 0; i < currentHealth; i++) {

            int x = getWidth() - 10 - heartWidth
                    - i * (heartWidth + spacing);

            g2.setColor(Color.RED);
            g2.fillRect(x, y, heartWidth, heartHeight);
        }
    }

    public void takeDamage(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0)
            currentHealth = 0;
        repaint();
    }

    public void reset() {
        currentHealth = maxHealth;
        repaint();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public boolean isDead() {
        return currentHealth <= 0;
    }
}
