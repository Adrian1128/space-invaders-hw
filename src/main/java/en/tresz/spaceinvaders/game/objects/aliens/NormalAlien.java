package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

/**
 * A standard alien with normal movement and shooting patterns.
 */
public class NormalAlien extends Alien {

    public static final int NORMAL_ALIEN_WIDTH = 50;

    /**
     * Constructs a NormalAlien.
     * 
     * @param p        the initial position
     * @param v        the initial velocity
     * @param hitSpeed the speed multiplier
     */
    public NormalAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, NORMAL_ALIEN_WIDTH, hitSpeed);
        alienImage = ImageLoader.loadBufferedImage("/images/normal-alien.png");
    }

}
