package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.aliens.Alien;

import en.tresz.spaceinvaders.game.projectiles.PlayerProjectile;

import java.util.List;

import java.util.ArrayList;

public class AlienController {
    public void moveDownAllAliens(GamePanel gamePanel) {
        List<Alien> aliens = getAllAliens(gamePanel);
        for (Alien alien : aliens) {
            alien.getPosition().addY(alien.getVelocity().getY());
            alien.setCollisionCount(0);
        }
    }

    public void handleAlienColision(Alien otherAlien, GamePanel gamePanel) {
        List<Alien> aliens = getAllAliens(gamePanel);
        for (Alien alien : aliens) {
            if (alien != otherAlien && alien.intersects(otherAlien)) {
                alien.getVelocity().negateX();
            }
        }
    }

    public void addAllAliens(List<GameObject> objects, List<Alien> aliens) {
        for (GameObject object : objects) {
            if (object instanceof Alien alien) {
                aliens.add(alien);
            }
        }
    }

    public List<Alien> getAllAliens(GamePanel gamePanel) {
        List<Alien> aliens = new ArrayList<>();
        for (GameObject object : gamePanel.getGameObjects()) {
            if (object instanceof Alien alien) {
                aliens.add(alien);
            }
        }
        return aliens;
    }

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

}
