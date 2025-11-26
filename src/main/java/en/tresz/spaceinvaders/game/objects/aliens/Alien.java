package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.objects.projectiles.AlienProjectile;
import en.tresz.spaceinvaders.util.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Alien class represents a generic alien.
 */
public abstract class Alien extends GameObject {
    protected BufferedImage alienImage;

    public static final int ALIEN_HEIGHT = 80;

    // counts how many times the alien has collided with the wall
    protected int collisionCount = 0;

    // after this number of collisions, the alien requests all aliens to move down
    protected int closingInTreshold = 6;

    protected int hitSpeed;

    // flag to request all aliens to move down
    protected boolean requestGlobalMoveDown = false;

    protected int shootingInterval = 500;

    /**
     * Copy constructor for creating a new Alien from an existing one.
     * 
     * @param alien the alien to copy from
     */
    protected Alien(Alien alien) {
        super(alien.position, alien.velocity);
        this.closingInTreshold = alien.closingInTreshold;
        this.width = alien.width;
        this.height = alien.height;
        this.hitSpeed = alien.hitSpeed;
    }

    /**
     * Constructs a new Alien with the specified parameters.
     * 
     * @param p        the initial position of the alien
     * @param v        the initial velocity of the alien
     * @param width    the width of the alien
     * @param hitSpeed the speed multiplier for movement and shooting
     */
    protected Alien(Vector2D p, Vector2D v, int width, int hitSpeed) {
        super(p, v);
        this.width = width;
        this.height = ALIEN_HEIGHT;
        velocity.setY(height);
        this.hitSpeed = hitSpeed;
    }

    /**
     * Updates the alien's position and handles wall collisions.
     * Increments collision count when hitting walls and requests global move down
     * when threshold is reached.
     * 
     * @param gamePanel the game panel for boundary checking
     */
    public void update(GamePanel gamePanel) {
        position.addX(velocity.getX());

        if ((position.getX() + getHalfWidth()) >= gamePanel.getWidth()) {
            position.setX(gamePanel.getWidth() - getHalfWidth()); // if the velocity is too high, prevent it going out
                                                                  // of bounds
            velocity.setX(-velocity.getX());
            collisionCount++;
        } else if ((position.getX() - getHalfWidth()) <= 0) {
            position.setX(0 + getHalfWidth()); // if the velocity is too high, prevent it going out of bounds
            velocity.setX(-velocity.getX());
            collisionCount++;
        }

        if (collisionCount == getClosingInTreshold()) {
            collisionCount = 0;
            requestGlobalMoveDown = true;
        }

    }

    /**
     * Attempts to shoot a projectile towards the player.
     * The shooting interval is controlled by hitSpeed and resets after each shot.
     * 
     * @param gamePanel the game panel to add the projectile to
     */
    public void shoot(GamePanel gamePanel) {
        if (shootingInterval > 0) {
            shootingInterval -= hitSpeed;
            return;
        }
        Vector2D projectilePosition = new Vector2D(position.getX(), position.getY() + getHalfHeight());
        AlienProjectile projectile = new AlienProjectile(projectilePosition);
        gamePanel.addGameObject(projectile);
        shootingInterval = 500;
    }

    /**
     * Draws the alien on the screen using its image.
     * 
     * @param g the graphics context to draw on
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(alienImage, position.centerX(width), position.centerY(height), width, height, null);
    }

    /**
     * Checks if the alien has reached the player's vertical position.
     * 
     * @param player the player to check against
     * @return true if the alien has reached the player's level, false otherwise
     */
    public boolean reachedBottom(Player player) {
        return getPosition().getY()
                + getHalfHeight() >= (player.getPosition().getY() - player.getHeight());
    }

    /**
     * Checks if the alien requests all aliens to move down, resetting the flag
     * after checking.
     * 
     * @return true if the alien requests all aliens to move down, false otherwise
     */
    public boolean isRequestGlobalMoveDown() {
        if (requestGlobalMoveDown) {
            requestGlobalMoveDown = false;
            return true;
        }
        return false;
    }

    /**
     * Gets the collision threshold before the alien requests a global move down.
     * 
     * @return the closing in threshold value
     */
    public int getClosingInTreshold() {
        return closingInTreshold;
    }

    /**
     * Sets the collision count for this alien.
     * 
     * @param count the new collision count value
     */
    public void setCollisionCount(int count) {
        collisionCount = count;
    }

    /**
     * Gets the hit speed multiplier for this alien.
     * 
     * @return the hit speed value
     */
    public int getHitSpeed() {
        return hitSpeed;
    }

    /**
     * Determines if a random event should occur based on a given percentage.
     * 
     * @param percentage the probability of the event occurring (0-100)
     * @return true if the random event occurs, false otherwise
     */
    public boolean chance(double percentage) {
        return Math.random() < (percentage / 100.0);
    }
}
