package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

/**
 * A fast-moving alien that also shoots three times faster than normal.
 */
public class FastRapidfireAlien extends Alien {

    public static final int FAST_RAPIDFIRE_ALIEN_WIDTH = 40;
    
    /**
     * Constructs a FastRapidfireAlien with 3x movement speed and 3x shooting speed.
     * 
     * @param p        the initial position
     * @param v        the initial velocity (tripled horizontally)
     * @param hitSpeed the base speed multiplier (tripled for shooting, doubled
     *                 overall)
     */
    public FastRapidfireAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, FAST_RAPIDFIRE_ALIEN_WIDTH, hitSpeed * 3);
        velocity.setX(velocity.getX() * 3);
        hitSpeed *= 2;
        alienImage = ImageLoader.loadBufferedImage("/images/fast-rapidfire-alien.png");
    }

}
