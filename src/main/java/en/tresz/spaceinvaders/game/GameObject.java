package en.tresz.spaceinvaders.game;

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
     * Updates the object.
     * 
     * @param canvasWidth  width of the canvas where the object is drawn
     * @param canvasHeight height of the canvas where the object is drawn
     */
    public abstract void update(GamePanel gamePanel);

    /**
     * Draws the object.
     */
    protected abstract void draw(Graphics g);

    public boolean intersects(GameObject other) {
        return !(position.getX() + getHalfWidth() < other.position.getX() - other.getHalfWidth()
                || position.getX() - getHalfWidth() > other.position.getX() + other.getHalfWidth()
                || position.getY() + getHalfHeight() < other.position.getY() - other.getHalfHeight()
                || position.getY() - getHalfHeight() > other.position.getY() + other.getHalfHeight());
    }

    public Vector2D getPosition() {
        return position;
    }

    public Vector2D getVelocity() {
        return velocity;
    }

    public void setPosition(Vector2D p) {
        this.position = p;
    }

    public void setVelocity(Vector2D v) {
        this.velocity = v;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getHalfWidth() {
        return width / 2;
    }

    public int getHalfHeight() {
        return height / 2;
    }
}
