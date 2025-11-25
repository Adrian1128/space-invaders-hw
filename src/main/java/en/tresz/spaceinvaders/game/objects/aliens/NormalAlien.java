package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;


public class NormalAlien extends Alien {
    

    public NormalAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, 50, hitSpeed);
        alienImage = ImageLoader.loadBufferedImage("/images/normal-alien.png");
    }

}
