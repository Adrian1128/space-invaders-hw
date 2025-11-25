package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.projectiles.AlienProjectile;
import en.tresz.spaceinvaders.util.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Alien class represents a generic alien, that does not shoot, only moves.
 */
public abstract class Alien extends GameObject {
    protected BufferedImage alienImage;

    // counts how many times the alien has collided with the wall
    private int collisionCount = 0;

    // number of wall collisions before requesting to move down
    private int closingInTreshold = 6;

    private int hitSpeed;

    // flag to request all aliens to move down
    private boolean requestGlobalMoveDown = false;

    private int shootingInterval = 500;

    protected Alien(Alien alien) {
        super(alien.position, alien.velocity);
        this.closingInTreshold = alien.closingInTreshold;
        this.width = alien.width;
        this.height = alien.height;
        this.hitSpeed = alien.hitSpeed;
    }

    protected Alien(Vector2D p, Vector2D v, int width, int hitSpeed) {
        super(p, v);
        this.width = width;
        this.height = 80;
        velocity.setY(height);
        this.hitSpeed = hitSpeed;
    }

    /**
     * Updates the alien's position and handles wall collisions.
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
     * Draws the alien.
     */
    @Override
    public void draw(Graphics g) {
        g.drawImage(alienImage, position.centerX(width), position.centerY(height), width, height, null);
    }

    public boolean reachedBottom(Player player) {
        return getPosition().getY()
                + getHalfHeight() >= (player.getPosition().getY() - player.getHeight());
    }

    /**
     * Checks if the alien requests all aliens to move down.
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

    public int getClosingInTreshold() {
        return closingInTreshold;
    }

    public void setCollisionCount(int count) {
        collisionCount = count;
    }

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

    public int getHitSpeed() {
        return hitSpeed;
    }
}
