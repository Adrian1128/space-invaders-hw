package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.objects.projectiles.AlienProjectile;

/**
 * Handles player-related game logic including collision detection with alien
 * projectiles.
 */
public class PlayerController {
    /**
     * Checks for and handles collisions between the player and alien projectiles.
     * 
     * @param player    the player to check
     * @param gamePanel the game panel containing game objects
     */
    public void handlePlayerHit(Player player, GamePanel gamePanel) {
        for (GameObject object : gamePanel.getGameObjects()) {
            if (object instanceof AlienProjectile projectile && player.intersects(projectile)) {
                gamePanel.setPlayerHit(true);
                player.setHealth(player.getHealth() - 1);
                gamePanel.removeGameObject(projectile);
                gamePanel.getHealthBar().takeDamage(1);
                break;
            }
        }
    }

}
