package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class RapidfireAlien extends Alien {
    
    public RapidfireAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, 60, hitSpeed);
        alienImage = ImageLoader.loadBufferedImage("/images/rapidfire-alien.png");
    }

}
