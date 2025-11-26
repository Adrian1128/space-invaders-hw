package en.tresz.spaceinvaders.game.objects.aliens;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.projectiles.AlienProjectile;
import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

/**
 * A wide alien that shoots three projectiles simultaneously.
 */
public class WideAlien extends Alien {

    public static final int WIDE_ALIEN_WIDTH = 65;

    /**
     * Constructs a WideAlien.
     * 
     * @param p        the initial position
     * @param v        the initial velocity
     * @param hitSpeed the speed multiplier
     */
    public WideAlien(Vector2D p, Vector2D v, int hitSpeed) {
        super(p, v, WIDE_ALIEN_WIDTH, hitSpeed);
        alienImage = ImageLoader.loadBufferedImage("/images/wide-alien.png");
    }

    /**
     * Shoots three projectiles: center, left, and right.
     * 
     * @param gamePanel the game panel to add projectiles to
     */
    @Override
    public void shoot(GamePanel gamePanel) {
        if (shootingInterval > 0) {
            shootingInterval -= hitSpeed;
            return;
        }
        Vector2D projectile1Position = new Vector2D(position.getX(), position.getY() + getHalfHeight());
        Vector2D projectile2Position = new Vector2D(position.getX() + getHalfWidth(),
                position.getY() + getHalfHeight());
        Vector2D projectile3Position = new Vector2D(position.getX() - getHalfWidth(),
                position.getY() + getHalfHeight());
        AlienProjectile projectile1 = new AlienProjectile(projectile1Position);
        AlienProjectile projectile2 = new AlienProjectile(projectile2Position);
        AlienProjectile projectile3 = new AlienProjectile(projectile3Position);

        gamePanel.addGameObject(projectile1);
        gamePanel.addGameObject(projectile2);
        gamePanel.addGameObject(projectile3);
        shootingInterval = 500;
    }
}
