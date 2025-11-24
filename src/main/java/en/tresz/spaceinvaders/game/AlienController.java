package en.tresz.spaceinvaders.game;

import java.util.List;

import en.tresz.spaceinvaders.game.aliens.Alien;

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
}
