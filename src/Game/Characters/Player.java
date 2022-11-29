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

    public void onItemBeginDrag(KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.VK_UP) {

        }
        if (event.getKeyCode() == KeyEvent.VK_DOWN) {

        }
        if (event.getKeyCode() == KeyEvent.VK_LEFT) {

        }
        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {

        }


    }


    @Override
    public void move() {


    }
}
