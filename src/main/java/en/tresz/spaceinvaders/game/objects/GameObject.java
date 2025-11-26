package en.tresz.spaceinvaders.game.objects;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;

/**
 * The base class for all game objects.
 */
public abstract class GameObject {
    protected Vector2D position;
    protected Vector2D velocity;

    protected int width;
    protected int height;

    /**
     * Constructor for the GameObject class.
     * 
     * @param p the position vector
     * @param v the velocity vector
     */
    protected GameObject(Vector2D p, Vector2D v) {
        this.position = p;
        this.velocity = v;
    }

    /**
     * Updates the object state.
     * 
     * @param gamePanel the game panel
     */
    public abstract void update(GamePanel gamePanel);

    /**
     * Draws the object.
     */
    public abstract void draw(Graphics g);

    /**
     * Checks if this object intersects with another object.
     * 
     * @param other the other game object to check
     * @return true if the objects intersect
     */
    public boolean intersects(GameObject other) {
        return !(position.getX() + getHalfWidth() < other.position.getX() - other.getHalfWidth()
                || position.getX() - getHalfWidth() > other.position.getX() + other.getHalfWidth()
                || position.getY() + getHalfHeight() < other.position.getY() - other.getHalfHeight()
                || position.getY() - getHalfHeight() > other.position.getY() + other.getHalfHeight());
    }

    /**
     * Gets the position vector.
     * 
     * @return the position
     */
    public Vector2D getPosition() {
        return position;
    }

    /**
     * Gets the velocity vector.
     * 
     * @return the velocity
     */
    public Vector2D getVelocity() {
        return velocity;
    }

    /**
     * Sets the position vector.
     * 
     * @param p the new position
     */
    public void setPosition(Vector2D p) {
        this.position = p;
    }

    /**
     * Sets the velocity vector.
     * 
     * @param v the new velocity
     */
    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    /**
     * Gets the width of the object.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the object.
     * 
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets half the width (used for collision detection, and drawing).
     * 
     * @return half the width
     */
    public int getHalfWidth() {
        return width / 2;
    }

    /**
     * Gets half the height (used for collision detection, and drawing).
     * 
     * @return half the height
     */
    public int getHalfHeight() {
        return height / 2;
    }
}
