package en.tresz.spaceinvaders.util;

/**
 * A simple 2D vector class for positioning.
 */
public class Vector2D {

    private int x;
    private int y;

    /**
     * Constructor for the Vector2D class.
     * 
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Centers the x coordinate based on the given width.
     * 
     * @param width the width to center around
     * @return the centered x coordinate
     */
    public int centerX(int width) {
        return this.x - (width / 2);
    }

    /**
     * Centers the y coordinate based on the given height.
     * 
     * @param height the height to center around
     * @return the centered y coordinate
     */
    public int centerY(int height) {
        return this.y - (height / 2);
    }

    /**
     * Adds another vector to this vector.
     * 
     * @param v the vector to add
     */
    public void add(Vector2D v) {
        this.x += v.x;
        this.y += v.y;
    }

    /**
     * Adds to the x coordinate.
     * 
     * @param x the value to add to x
     */
    public void addX(int x) {
        this.x += x;
    }

    /**
     * Adds to the y coordinate.
     * 
     * @param y the value to add to y
     */
    public void addY(int y) {
        this.y += y;
    }

    /**
     * Subtracts another vector from this vector.
     * 
     * @param v the vector to subtract
     */
    public void subtract(Vector2D v) {
        this.x -= v.x;
        this.y -= v.y;
    }

    /**
     * Subtracts from the x coordinate.
     * 
     * @param x the value to subtract from x
     */
    public void subtractX(int x) {
        this.x -= x;
    }

    /**
     * Subtracts from the y coordinate.
     * 
     * @param y the value to subtract from y
     */
    public void subtractY(int y) {
        this.y -= y;
    }

    public void negate() {
        this.x = -this.x;
        this.y = -this.y;
    }

    public void negateX() {
        this.x = -this.x;
    }

    public void negateY() {
        this.y = -this.y;
    }

}
