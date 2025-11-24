package en.tresz.spaceinvaders.game.aliens;

import en.tresz.spaceinvaders.game.GameObject;
import en.tresz.spaceinvaders.util.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The Alien class represents a generic alien, that does not shoot, only moves.
 */
public abstract class Alien extends GameObject {
    protected BufferedImage alienImage;

    // default sizes
    protected int width = 80;

    // static because every alien moves down by it's height, and every alien is the
    // same height
    protected static final int HEIGHT = 80;

    // counts how many times the alien has collided with the wall
    private int collisionCount = 0;

    // number of wall collisions before requesting to move down
    private int closingInTreshold = 4;

    // flag to request all aliens to move down
    private boolean requestGlobalMoveDown = false;

    /**
     * Constructor for the Alien class, sets the y velocity to the height of the
     * alien.
     * 
     * @param p                 the position vector
     * @param v                 the velocity vector
     * @param closingInTreshold the number of wall collisions before moving down
     */
    protected Alien(Vector2D p, Vector2D v, int width, int closingInTreshold) {
        super(p, v);
        this.closingInTreshold = closingInTreshold;
        this.width = width;
        velocity.setY(HEIGHT);
    }

    /**
     * Updates the alien's position and handles wall collisions.
     */
    public void update(int canvasWidth, int canvasHeight) {
        position.addX(velocity.getX());

        if ((position.getX() + getHalfWidth()) >= canvasWidth) {
            position.setX(canvasWidth - getHalfWidth()); // if the velocity is too high, prevent it going out of bounds
            velocity.setX(-velocity.getX());
            collisionCount++;
        } else if ((position.getX() - getHalfWidth()) <= 0) {
            position.setX(getHalfWidth()); // if the velocity is too high, prevent it going out of bounds
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
    protected void draw(Graphics g) {
        g.drawImage(alienImage, position.centerX(width), position.centerY(HEIGHT), width, HEIGHT, null);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return HEIGHT;
    }

    /**
     * Gets half the width of the alien.
     * 
     * @return
     */
    public int getHalfWidth() {
        return width / 2;
    }

    /**
     * Gets half the height of the alien.
     * 
     * @return
     */
    public int getHalfHeight() {
        return HEIGHT / 2;
    }

    public int getClosingInTreshold() {
        return closingInTreshold;
    }

    public void setCollisionCount(int count) {
        collisionCount = count;
    }

}
