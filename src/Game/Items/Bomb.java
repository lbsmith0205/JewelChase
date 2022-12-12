package Game.Items;

import Game.Board.Board;
import Game.Board.Level;
import Game.Characters.FloorFollowingThief;
import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Board.Tile;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * A Bomb class can be used to create explosion on the Board and delete other items.
 *
 * @author Khoi Nguyen Cao, Luke Smith, Daniel Baxter
 */
public class Bomb extends Item{
    private static final String BOMB_SPRITE_DEFAULT_PATH = "Sprites/Items/Bombs/Bomb.png";
    private static final String BOMB_COUNTDOWN_PATH_3 = "Sprites/Items/Bombs/Bomb3.png";
    private static final String BOMB_COUNTDOWN_PATH_2 = "Sprites/Items/Bombs/Bomb2.png";
    private static final String BOMB_COUNTDOWN_PATH_1 = "Sprites/Items/Bombs/Bomb1.png";

    private static final int BOMB_DEFAULT_TIMER = 4;

    private final ArrayList<Tile> BLAST_ZONE = new ArrayList<>();
    private final Board board;
    private boolean bombActivated = false;
    private int bombTimer;
    private int activationTime;
    private String bombState;

    /**
     * Create an instance of the Bomb.
     *
     * @param position Tile the Bomb will be put on.
     *
     * @param board the Board that Bomb will explode on.
     */
    public Bomb(Tile position, Board board) {
        super(position);
        this.bombTimer = BOMB_DEFAULT_TIMER;
        this.board = board;
        this.bombState = BOMB_SPRITE_DEFAULT_PATH;
        int bombXPosition = position.getXPosition();
        int bombYPosition = position.getYPosition();
        for (int x = 0; x < board.getWidth(); x++) {
            BLAST_ZONE.add(board.getTile(x, bombYPosition));
        }
        for (int y = 0; y < board.getHeight(); y++) {
            BLAST_ZONE.add(board.getTile(bombXPosition, y));
        }
        refreshImage();

    }

    /**
     * Trigger the bomb and set it to tick down.
     *
     * @param accumulatorValue
     */
    public void activate(int accumulatorValue) {
        this.bombActivated = true;
    }

    /**
     * Explode the Tiles horizontally and vertically respectively to the Bomb
     */
    public void explode() {
        for (Tile tile : BLAST_ZONE) {
            tile.explode();
        }
    }

    /**
     * Change the Bomb's image according to its timer
     */
/*
    private void changeBombState() {
        int explosionTime = level.getTime() - BOMB_DEFAULT_TIMER;
        int bombTimeRemaining = level.getTime() - explosionTime;
        switch (bombTimeRemaining) {
            case 3 -> bombState = BOMB_COUNTDOWN_PATH_3;
            case 2 -> bombState = BOMB_COUNTDOWN_PATH_2;
            case 1 -> bombState = BOMB_COUNTDOWN_PATH_1;
            case 0 -> explode();
        }
    }*/

    @Override
    protected void refreshImage() {
        this.image = new Image(bombState);
    }

}
