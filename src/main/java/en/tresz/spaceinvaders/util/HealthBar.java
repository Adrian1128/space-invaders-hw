package en.tresz.spaceinvaders.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A visual health bar component that displays health as heart images.
 */
public class HealthBar extends JComponent {

    private int maxHealth;
    private int currentHealth;

    private int heartWidth = 25;
    private int heartHeight = 22;
    private int spacing = 8;

    private transient BufferedImage heartImage;

    /**
     * Constructs a HealthBar with the specified maximum health.
     * 
     * @param maxHealth the maximum health value
     */
    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;

        heartImage = ImageLoader.loadBufferedImage("/images/hearth.png");

        setPreferredSize(new Dimension(300, 40));
        setOpaque(false);
    }

    /**
     * Paints the health bar by drawing heart images from right to left.
     * 
     * @param g the graphics context
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        int y = 10;

        // draw from RIGHT to LEFT so hearts disappear from the left
        for (int i = 0; i < currentHealth; i++) {

            int x = getWidth() - 10 - heartWidth
                    - i * (heartWidth + spacing);

            g2.drawImage(heartImage, x, y, heartWidth, heartHeight, null);
        }
    }

    /**
     * Reduces the current health by the specified amount.
     * 
     * @param amount the amount of damage to take
     */
    public void takeDamage(int amount) {
        currentHealth -= amount;
        if (currentHealth < 0)
            currentHealth = 0;
        repaint();
    }

    /**
     * Resets the health bar to maximum health.
     */
    public void reset() {
        currentHealth = maxHealth;
        repaint();
    }

    /**
     * Gets the current health value.
     * 
     * @return the current health
     */
    public int getCurrentHealth() {
        return currentHealth;
    }

    /**
     * Checks if the health has reached zero.
     * 
     * @return true if current health is zero or below
     */
    public boolean isDead() {
        return currentHealth <= 0;
    }
}
