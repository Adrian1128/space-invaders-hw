package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.aliens.Alien;

import java.util.List;

import javax.swing.JPanel;

import java.util.ArrayList;

public class AlienController {
    private List<Alien> aliens = new ArrayList<>();

    public AlienController(List<Alien> aliens) {
        this.aliens = aliens;
    }

    public void moveDownAllAliens() {
        for (Alien alien : aliens) {
            alien.getPosition().addY(alien.getVelocity().getY());
            alien.setCollisionCount(0);
        }
    }

    public void updateAllAliens(JPanel gamePanel) {
        for (Alien alien : aliens) {

            if (alien.isRequestGlobalMoveDown()) {
                moveDownAllAliens();
            } else {
                alien.update(gamePanel.getWidth(), gamePanel.getHeight());
            }
            handleAlienColision(alien);

            if (alien.reachedBottom(gamePanel)) {
                // TODO: Game over logic
            }
        
        }
    }

    public void handleAlienColision(Alien otherAlien) {
        for (Alien alien : aliens) {
            if (alien != otherAlien && alien.intersects(otherAlien)) {
                alien.getVelocity().negateX();
            }
        }
    }

}
