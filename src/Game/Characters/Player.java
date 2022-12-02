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
            case KeyEvent.VK_UP:
                // W or Up key was pressed so move the player up by one tile
                break;

            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                // A or Left key was pressed so move the player left by one tile
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                // S or Down key was pressed so move the player down by one tile
                break;

            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                // D or Right key was pressed so move the player right by one tile
                break;
            default:
                break;
        }


    }


    @Override
    public void move() {


    }
}
