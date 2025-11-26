package en.tresz.spaceinvaders.game.objects.projectiles;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.util.Vector2D;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * A projectile fired by the player moving upwards.
 */
public class PlayerProjectile extends GameObject {

    BufferedImage projectileImage = en.tresz.spaceinvaders.util.ImageLoader
            .loadBufferedImage("/images/spaceship-projectile.png");
    
    private static final int VELOCITY_Y = 8;

    /**
     * Constructs a PlayerProjectile at the given position.
     * 
     * @param position the starting position
     */
    public PlayerProjectile(Vector2D position) {
        super(new Vector2D(position.getX(), position.getY()), new Vector2D(0, VELOCITY_Y));
        width = 9;
        height = 20;
    }

    /**
     * Updates the projectile position moving upwards.
     * 
     * @param gamePanel the game panel (not used)
     */
    public void update(GamePanel gamePanel) {
        position.setY(position.getY() - velocity.getY());
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
