package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.projectiles.PlayerProjectile;
import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class Player extends GameObject {

    private BufferedImage playerImage = ImageLoader.loadBufferedImage("/images/spaceship.png");

    private int currentVelocity = 0;

    private int hitSpeed;

    private int shootingInterval = 500;

    private int health;

    public Player(Vector2D position, Vector2D velocity, int hitSpeed, int health) {
        super(position, velocity);
        this.hitSpeed = hitSpeed;
        this.health = health;
        width = 50;
        height = 73;
    }

    public void update(GamePanel gamePanel) {
        if (position.getX() - width / 2 < 0)
            position.setX(0 + width / 2);
        if (position.getX() + width / 2 > gamePanel.getWidth())
            position.setX(gamePanel.getWidth() - width / 2);
        position.setX(position.getX() + currentVelocity);
    }

    public void draw(Graphics g) {
        g.drawImage(playerImage, position.centerX(width), position.centerY(height), width, height, null);
    }

    public void shoot(GamePanel gamePanel) {
        if (shootingInterval > 0) {
            shootingInterval -= hitSpeed;
            return;
        }

        Vector2D projectilePosition = new Vector2D(position.getX(), position.getY() - getHalfHeight());
        PlayerProjectile projectile = new PlayerProjectile(projectilePosition);
        gamePanel.addGameObject(projectile);
        shootingInterval = 500;
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
                JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("pressed LEFT"), "leftPressed");
        gamePanel.getActionMap().put("leftPressed", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                moveLeft();
            }
        });

        // LEFT released
        gamePanel.getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("released LEFT"), "leftReleased");
        gamePanel.getActionMap().put("leftReleased", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                stop();
            }
        });

        // RIGHT pressed
        gamePanel.getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("pressed RIGHT"), "rightPressed");
        gamePanel.getActionMap().put("rightPressed", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                moveRight();
            }
        });

        // RIGHT released
        gamePanel.getInputMap(
                JComponent.WHEN_IN_FOCUSED_WINDOW)
                .put(javax.swing.KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");
        gamePanel.getActionMap().put("rightReleased", new javax.swing.AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                stop();
            }
        });
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}