package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;

/**
 * The base class for all game objects.
 */
public abstract class GameObject {
    protected Vector2D position;
    protected Vector2D velocity;

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
    public abstract void update(int canvasWidth, int canvasHeight);

    /**
     * Draws the object.
     */
    protected abstract void draw(Graphics g);

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
}
