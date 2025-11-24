package en.tresz.spaceinvaders.game.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class RapidfireAlien extends Alien {

    /**
     * Constructor for the NormalAlien class, only changes the width.
     * 
     * @param p                 the position vector
     * @param v                 the velocity vector
     * @param closingInTreshold the number of wall collisions before moving down
     */
    public RapidfireAlien(Vector2D p, Vector2D v, int closingInTreshold) {
        super(p, v, 60, closingInTreshold);
        alienImage = ImageLoader.loadBufferedImage("/images/rapidfire-alien.png");
    }

}
