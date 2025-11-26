package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.objects.aliens.Alien;
import en.tresz.spaceinvaders.game.objects.projectiles.PlayerProjectile;

import java.util.List;

import java.util.ArrayList;

/**
 * Manages alien collective behavior including movement, collisions, and
 * destruction.
 */
public class AlienController {
    /**
     * Moves all aliens down by one row height.
     * 
     * @param gamePanel the game panel containing aliens
     */
    public void moveDownAllAliens(GamePanel gamePanel) {
        List<Alien> aliens = getAllAliens(gamePanel);
        for (Alien alien : aliens) {
            alien.getPosition().addY(alien.getVelocity().getY());
            alien.setCollisionCount(0);
        }
    }

    /**
     * Handles collisions between all aliens, reversing velocities and separating
     * overlapping aliens.
     * 
     * @param gamePanel the game panel containing aliens
     */
    public void handleAllAlienCollisions(GamePanel gamePanel) {
        List<Alien> aliens = getAllAliens(gamePanel);

        for (int i = 0; i < aliens.size(); i++) {
            for (int j = i + 1; j < aliens.size(); j++) {

                Alien a = aliens.get(i);
                Alien b = aliens.get(j);

                if (a.intersects(b)) {
                    int dx = a.getPosition().getX() - b.getPosition().getX();

                    a.getVelocity().setX(-a.getVelocity().getX());
                    b.getVelocity().setX(-b.getVelocity().getX());

                    int overlap = (a.getHalfWidth() + b.getHalfWidth()) - Math.abs(dx);
                    int separation = overlap / 2 + 1;

                    if (dx > 0) {
                        a.getPosition().setX(a.getPosition().getX() + separation);
                        b.getPosition().setX(b.getPosition().getX() - separation);
                    } else {
                        a.getPosition().setX(a.getPosition().getX() - separation);
                        b.getPosition().setX(b.getPosition().getX() + separation);
                    }
                }
            }
        }
    }

    /**
     * Populates the aliens list with all alien objects from the game objects list.
     * 
     * @param objects the list of all game objects
     * @param aliens  the list to populate with aliens
     */
    public void addAllAliens(List<GameObject> objects, List<Alien> aliens) {
        for (GameObject object : objects) {
            if (object instanceof Alien alien) {
                aliens.add(alien);
            }
        }
    }

    /**
     * Gets all alien objects from the game panel.
     * 
     * @param gamePanel the game panel
     * @return a list of all aliens
     */
    public List<Alien> getAllAliens(GamePanel gamePanel) {
        List<Alien> aliens = new ArrayList<>();
        for (GameObject object : gamePanel.getGameObjects()) {
            if (object instanceof Alien alien) {
                aliens.add(alien);
            }
        }
        return aliens;
    }

    /**
     * Checks for and handles collisions between a player projectile and aliens.
     * 
     * @param projectile the player projectile
     * @param gamePanel  the game panel
     */
    public void handleAlienHit(PlayerProjectile projectile, GamePanel gamePanel) {
        List<Alien> aliens = getAllAliens(gamePanel);
        for (Alien alien : aliens) {
            if (alien.intersects(projectile)) {
                aliens.remove(alien);
                gamePanel.removeGameObject(alien);
                gamePanel.removeGameObject(projectile);
                break;
            }
        }
    }

    /**
     * Checks if any alien has reached the player's vertical position.
     * 
     * @param gamePanel the game panel
     * @param player    the player
     * @return true if any alien reached the bottom
     */
    public boolean hasAlienHitBottom(GamePanel gamePanel, Player player) {
        List<Alien> aliens = getAllAliens(gamePanel);
        for (Alien alien : aliens) {
            if (alien.reachedBottom(player)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if all aliens have been destroyed.
     * 
     * @param gamePanel the game panel
     * @return true if no aliens remain
     */
    public boolean areAllAliensDestroyed(GamePanel gamePanel) {
        List<Alien> aliens = getAllAliens(gamePanel);
        return aliens.isEmpty();
    }
}
