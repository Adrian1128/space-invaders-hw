package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.game.aliens.Alien;

import java.util.List;

import java.util.ArrayList;

public class AlienController {
    public static List<Alien> aliens = new ArrayList<>();

    public static void moveDownAllAliens() {
        for (Alien alien : aliens) {
            alien.getPosition().addY(alien.getVelocity().getY());
            alien.setCollisionCount(0);
        }
    }

    public static void handleAlienColision(Alien otherAlien) {
        for (Alien alien : aliens) {
            if (alien != otherAlien && alien.intersects(otherAlien)) {
                alien.getVelocity().negateX();
            }
        }
    }

    public static void addAllAliens(ArrayList<GameObject> objects) {
        for (GameObject object : objects) {
            if (object instanceof Alien alien) {
                aliens.add(alien);
            }
        }
    }

}
