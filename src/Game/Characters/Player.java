package Game.Characters;

import Game.Tile;

import java.awt.event.KeyEvent;

/**
 * Player.java
 * Sub class of Character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class Player extends Character {
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public Player(Tile position) {
        super(position);
    }

    // will eventually relate this tile positions
    public void processKeyEvent(KeyEvent event) {
        switch(event.getKeyCode()) {
            case KeyEvent.VK_W:
                // W key was pressed so move the player up by one tile
                break;
            case KeyEvent.VK_S:
                // S key was pressed so move the player down by one tile
                break;
            case KeyEvent.VK_A:
                // A key was pressed so move the player left by one tile
                break;
            case KeyEvent.VK_D:
                // D key was pressed so move the player right by one tile
                break;
            default:
                break;
        }

    }


    @Override
    public void move() {


    }
}
