package en.tresz.spaceinvaders.game.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class FastAlien extends Alien {

    /**
     * Constructor for the FastAlien class.
     * 
     * @param p                 the position vector
     * @param v                 the velocity vector
     * @param closingInTreshold the number of wall collisions before moving down
     */
    public FastAlien(Vector2D p, Vector2D v, int closingInTreshold) {
        super(p, v, 40, closingInTreshold * 2);
        velocity.setX(velocity.getX() * 2);
        alienImage = ImageLoader.loadBufferedImage("/images/fast-alien.png");
    }

}
