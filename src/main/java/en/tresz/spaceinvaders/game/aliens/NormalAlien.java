package en.tresz.spaceinvaders.game.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;


public class NormalAlien extends Alien {
    
    /**
     * Constructor for the NormalAlien class, only changes the width.
     * 
     * @param p                 the position vector
     * @param v                 the velocity vector
     * @param closingInTreshold the number of wall collisions before moving down
     */
    public NormalAlien(Vector2D p, Vector2D v, int closingInTreshold, int hitSpeed) {
        super(p, v, 50, closingInTreshold, hitSpeed);
        alienImage = ImageLoader.loadBufferedImage("/images/normal-alien.png");
    }

}
