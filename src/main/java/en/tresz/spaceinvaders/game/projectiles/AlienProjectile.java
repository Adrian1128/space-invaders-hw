package en.tresz.spaceinvaders.game.projectiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class AlienProjectile extends GameObject {
    private BufferedImage projectileImage = ImageLoader.loadBufferedImage("/images/alien-projectile.png");

    private static final int VELOCITY_Y = 6;
    
    public AlienProjectile(Vector2D position) {
        super(new Vector2D(position.getX(), position.getY()), new Vector2D(0, VELOCITY_Y));
        width = 10;
        height = 30;
    }

    public void update(GamePanel gamePanel) {
        position.setY(position.getY() + velocity.getY());
    }
    
    public void draw(Graphics g) {
        g.drawImage(projectileImage, position.centerX(width), position.centerY(height), width, height, null);
    }
}
