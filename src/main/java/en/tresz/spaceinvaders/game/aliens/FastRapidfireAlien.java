package en.tresz.spaceinvaders.game.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class FastRapidfireAlien extends Alien {

    /**
     * Constructor for the FastAlien class.
     * 
     * @param p                 the position vector
     * @param v                 the velocity vector
     * @param closingInTreshold the number of wall collisions before moving down
     */
    public FastRapidfireAlien(Vector2D p, Vector2D v, int closingInTreshold, int hitSpeed) {
        super(p, v, 40, closingInTreshold * 2, hitSpeed);
        velocity.setX(velocity.getX() * 2);
        alienImage = ImageLoader.loadBufferedImage("/images/fast-rapidfire-alien.png");
    }

}
