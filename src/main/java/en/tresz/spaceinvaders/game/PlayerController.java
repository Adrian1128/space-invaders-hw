package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.projectiles.AlienProjectile;

public class PlayerController {
    public void handlePlayerHit(Player player, GamePanel gamePanel) {
        for (GameObject object : gamePanel.getGameObjects()) {
            if (object instanceof AlienProjectile projectile && player.intersects(projectile)) {
                player.setHealth(player.getHealth() - 1);
                gamePanel.removeGameObject(projectile);
                gamePanel.getHealthBar().takeDamage(1);
                break;
            }
        }
    }

}
