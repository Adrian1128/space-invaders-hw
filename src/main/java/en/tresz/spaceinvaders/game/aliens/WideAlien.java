package en.tresz.spaceinvaders.game.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class WideAlien extends Alien {
    /**
     * Constructor for the NormalAlien class, only changes the width.
     * 
     * @param p                 the position vector
     * @param v                 the velocity vector
     * @param closingInTreshold the number of wall collisions before moving down
     */
    public WideAlien(Vector2D p, Vector2D v, int closingInTreshold) {
        super(p, v, 65, closingInTreshold);
        alienImage = ImageLoader.loadBufferedImage("/images/wide-alien.png");
    }
}
