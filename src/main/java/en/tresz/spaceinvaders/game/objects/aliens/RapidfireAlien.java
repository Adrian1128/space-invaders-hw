package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

/**
 * An alien that shoots three times faster than normal aliens.
 */
public class RapidfireAlien extends Alien {

    public static final int RAPIDFIRE_ALIEN_WIDTH = 60;

    /**
     * Constructs a RapidfireAlien with 3x shooting speed.
     * 
     * @param p        the initial position
     * @param v        the initial velocity
     * @param hitSpeed the base speed multiplier (tripled for shooting)
     */
    public RapidfireAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, RAPIDFIRE_ALIEN_WIDTH, hitSpeed * 3);
        alienImage = ImageLoader.loadBufferedImage("/images/rapidfire-alien.png");
    }

}
