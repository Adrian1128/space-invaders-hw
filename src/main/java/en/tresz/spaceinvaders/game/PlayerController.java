package en.tresz.spaceinvaders.game;

public class PlayerController {
    public void handlePlayerHit(Player player, GamePanel gamePanel) {
        int currentHealth = player.getHealth();
        currentHealth -= 1;
        player.setHealth(currentHealth);
        if (currentHealth <= 0) {
            gamePanel.removeGameObject(player);
            // TODO: Handle game over logic
        }
    }
    
}
