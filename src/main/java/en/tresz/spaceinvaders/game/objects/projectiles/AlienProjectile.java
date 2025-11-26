package en.tresz.spaceinvaders.game.objects.projectiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.util.ImageLoader;
import en.tresz.spaceinvaders.util.Vector2D;

/**
 * A projectile fired by aliens moving downwards.
 */
public class AlienProjectile extends GameObject {
    private BufferedImage projectileImage = ImageLoader.loadBufferedImage("/images/alien-projectile.png");

    private static final int VELOCITY_Y = 6;

    /**
     * Constructs an AlienProjectile at the given position.
     * 
     * @param position the starting position
     */
    public AlienProjectile(Vector2D position) {
        super(new Vector2D(position.getX(), position.getY()), new Vector2D(0, VELOCITY_Y));
        width = 10;
        height = 30;
    }

    /**
     * Updates the projectile position moving downwards.
     * 
     * @param gamePanel the game panel (not used)
     */
    public void update(GamePanel gamePanel) {
        position.setY(position.getY() + velocity.getY());
    }

    /**
     * Draws the projectile.
     * 
     * @param g the graphics context
     */
    public void draw(Graphics g) {
        g.drawImage(projectileImage, position.centerX(width), position.centerY(height), width, height, null);
    }
}
