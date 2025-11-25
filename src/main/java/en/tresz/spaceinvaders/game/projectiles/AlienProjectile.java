package en.tresz.spaceinvaders.game.projectiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import en.tresz.spaceinvaders.game.GameObject;
import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

public class AlienProjectile extends GameObject {
    private BufferedImage projectileImage = ImageLoader.loadBufferedImage("/images/alien-projectile.png");

    private static final int WIDTH = 10;
    private static final int HEIGHT = 30;

    public AlienProjectile(Vector2D position, Vector2D velocity) {
        super(position, velocity);
    }

    public void update(GamePanel gamePanel) {
        position.setY(position.getY() + velocity.getY());
    }

    protected void draw(Graphics g) {
        g.drawImage(projectileImage, position.centerX(WIDTH), position.centerY(HEIGHT), WIDTH, HEIGHT, null);
    }
}
