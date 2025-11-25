package en.tresz.spaceinvaders.game.projectiles;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PlayerProjectile extends GameObject {

    BufferedImage projectileImage = en.tresz.spaceinvaders.util.ImageLoader
            .loadBufferedImage("/images/spaceship-projectile.png");

    private static final int VELOCITY_Y = 8;

    public PlayerProjectile(Vector2D position) {
        super(new Vector2D(position.getX(), position.getY()), new Vector2D(0, VELOCITY_Y));
        width = 9;
        height = 20;
    }

    public void update(GamePanel gamePanel) {
        position.setY(position.getY() - velocity.getY());
    }

    public void draw(Graphics g) {
        g.drawImage(projectileImage, position.centerX(width), position.centerY(height), width, height, null);
    }
}
