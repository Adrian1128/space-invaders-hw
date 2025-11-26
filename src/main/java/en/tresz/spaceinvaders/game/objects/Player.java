package en.tresz.spaceinvaders.game.objects;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.projectiles.PlayerProjectile;
import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

/**
 * Represents the player's spaceship controlled by keyboard input.
 */
public class Player extends GameObject {

    private BufferedImage playerImage = ImageLoader.loadBufferedImage("/images/spaceship.png");

    private BufferedImage playerNormalImage = ImageLoader.loadBufferedImage("/images/spaceship.png");
    private BufferedImage playerHitImage = ImageLoader.loadBufferedImage("/images/spaceship-hit.png");

    private int currentVelocity = 0;

    private int hitSpeed;

    private int shootingInterval = 500;

    private int health;

    /**
     * Constructs a Player with specified parameters.
     * 
     * @param position the initial position
     * @param velocity the movement velocity
     * @param hitSpeed the shooting speed multiplier
     * @param health   the initial health
     */
    public Player(Vector2D position, Vector2D velocity, int hitSpeed, int health) {
        super(position, velocity);
        this.hitSpeed = hitSpeed;
        this.health = health;
        width = 50;
        height = 73;
    }

    /**
     * Updates the player position and enforces boundary constraints.
     * 
     * @param gamePanel the game panel for boundary checking
     */
    public void update(GamePanel gamePanel) {
        if (position.getX() - width / 2 < 0)
            position.setX(0 + width / 2);
        if (position.getX() + width / 2 > gamePanel.getWidth())
            position.setX(gamePanel.getWidth() - width / 2);
        position.setX(position.getX() + currentVelocity);
    }

    /**
     * Draws the player spaceship.
     * 
     * @param g the graphics context
     */
    public void draw(Graphics g) {
        g.drawImage(playerImage, position.centerX(width), position.centerY(height), width, height, null);
    }

    /**
     * Draws the player spaceship, showing hit state if applicable.
     * 
     * @param g     the graphics context
     * @param isHit
     */
    public void draw(Graphics g, boolean isHit) {
        if (isHit) {
            g.drawImage(playerHitImage, position.centerX(width), position.centerY(height), width, height, null);
        } else {
            g.drawImage(playerNormalImage, position.centerX(width), position.centerY(height), width, height, null);
        }
    }

    /**
     * Attempts to shoot a projectile upwards.
     * 
     * @param gamePanel the game panel to add the projectile to
     */
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

    /**
     * Moves the player left.
     */
    public void moveLeft() {
        currentVelocity = -velocity.getX();
    }

    /**
     * Moves the player right.
     */
    public void moveRight() {
        currentVelocity = velocity.getX();
    }

    /**
     * Stops the player movement.
     */
    public void stop() {
        currentVelocity = 0;
    }

    /**
     * Sets up keyboard input bindings for player movement using arrow keys.
     * Source: https://www.javatips.net/api/javax.swing.inputmap
     * 
     * @param gamePanel the game panel to register input bindings on
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

    /**
     * Gets the player's current health.
     * 
     * @return the health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the player's health.
     * 
     * @param health the new health value
     */
    public void setHealth(int health) {
        this.health = health;
    }
}