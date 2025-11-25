package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class FastAlien extends Alien {
    
    public FastAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, 40, hitSpeed);
        velocity.setX(velocity.getX() * 2);
        alienImage = ImageLoader.loadBufferedImage("/images/fast-alien.png");
    }

}
