package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends GameObject {
    
    public static final int WIDTH = 50;
    public static final int HEIGHT = 73;

    private BufferedImage playerImage = ImageLoader.loadBufferedImage("/images/spaceship.png");

    private int currentVelocity = 0;

    public Player(Vector2D position, Vector2D velocity) {
        super(position, velocity);
    }

    public void update(GamePanel gamePanel) {
        if (position.getX() - WIDTH / 2 < 0)
            position.setX(0 + WIDTH / 2);
        if (position.getX() + WIDTH / 2 > gamePanel.getWidth())
            position.setX(gamePanel.getWidth() - WIDTH / 2);
        position.setX(position.getX() + currentVelocity);
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, position.centerX(WIDTH), position.centerY(HEIGHT), WIDTH, HEIGHT, null);
    }

    public void moveLeft() {
        currentVelocity = -velocity.getX();
    }

    public void moveRight() {
        currentVelocity = velocity.getX();
    }

    public void stop() {
        currentVelocity = 0;
    }

    /**
     * forrás: https://www.javatips.net/api/javax.swing.inputmap
     * 
     * @param gamePanel
     */
    public void playerMovement(GamePanel gamePanel) {
        // LEFT pressed
        gamePanel.getInputMap(
                GamePanel.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("pressed LEFT"), "leftPressed");
        gamePanel.getActionMap().put("leftPressed", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                moveLeft();
            }
        });

        // LEFT released
        gamePanel.getInputMap(
                GamePanel.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("released LEFT"), "leftReleased");
        gamePanel.getActionMap().put("leftReleased", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                stop();
            }
        });

        // RIGHT pressed
        gamePanel.getInputMap(
                GamePanel.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("pressed RIGHT"), "rightPressed");
        gamePanel.getActionMap().put("rightPressed", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                moveRight();
            }
        });

        // RIGHT released
        gamePanel.getInputMap(
                GamePanel.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");
        gamePanel.getActionMap().put("rightReleased", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                stop();
            }
        });
    }

}
