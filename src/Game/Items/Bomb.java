package Game.Items;

import Game.Board.Board;

import Game.Board.Tile;
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
    private boolean isActive = false;
    private int detonationTime;
    private int activationTime;
    private String bombSpritePath;

    /**
     * Create an instance of the Bomb.
     *
     * @param position Tile the Bomb will be put on.
     *
     * @param board the Board that Bomb will explode on.
     */
    public Bomb(Tile position, Board board) {
        super(position);
        this.board = board;
        this.bombSpritePath = BOMB_SPRITE_DEFAULT_PATH;
        int bombXPosition = position.getXPosition();
        int bombYPosition = position.getYPosition();
        for (int x = 0; x < board.getWidth(); x++) {
            BLAST_ZONE.add(board.getTile(x, bombYPosition));
        }
        for (int y = 0; y < board.getHeight(); y++) {
            BLAST_ZONE.add(board.getTile(bombXPosition, y));
        }
        BLAST_ZONE.remove(position);
        refreshImage();

    }

    /**
     * Trigger the bomb and set it to tick down.
     *
     * @param time
     */
    public void activate(int time) {
        this.isActive = true;
        activationTime = time;
        detonationTime = activationTime - BOMB_DEFAULT_TIMER;
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
    public void updateBombState(int time) {
        int bombTimeRemaining = time - detonationTime;
        switch (bombTimeRemaining) {
            case 3 -> {
                System.out.println("Bomb 3");
                bombSpritePath = BOMB_COUNTDOWN_PATH_3;
                refreshImage();
            }
            case 2 -> {
                System.out.println("Bomb 2");
                bombSpritePath = BOMB_COUNTDOWN_PATH_2;
                refreshImage();
            }
            case 1 -> {
                System.out.println("Bomb 1");
                bombSpritePath = BOMB_COUNTDOWN_PATH_1;
                refreshImage();
            }
            case 0 -> explode();
        }
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(bombSpritePath);
    }

    public boolean getIsActive() {
        return isActive;
    }

    public ArrayList<Tile> getBlastZone() {
        return BLAST_ZONE;
    }

}
