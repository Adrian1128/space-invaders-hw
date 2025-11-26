package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

/**
 * A fast-moving alien that moves three times faster than normal aliens.
 */
public class FastAlien extends Alien {

    public static final int FAST_ALIEN_WIDTH = 40;

    /**
     * Constructs a FastAlien with 3x horizontal velocity.
     * 
     * @param p        the initial position
     * @param v        the initial velocity (tripled horizontally)
     * @param hitSpeed the speed multiplier
     */
    public FastAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, FAST_ALIEN_WIDTH, hitSpeed);
        velocity.setX(velocity.getX() * 3);
        alienImage = ImageLoader.loadBufferedImage("/images/fast-alien.png");
    }

}
