package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class WideAlien extends Alien {

    public WideAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, 65, hitSpeed);
        alienImage = ImageLoader.loadBufferedImage("/images/wide-alien.png");
    }
}
