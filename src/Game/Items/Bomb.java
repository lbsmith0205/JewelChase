package Game.Items;

import Game.Board.Board;
import Game.Characters.FloorFollowingThief;
import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Board.Tile;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

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

    public void activate(int accumulatorValue) {
        this.bombActivated = true;
    }

    public void explode() {
        for (Tile tile : BLAST_ZONE) {
            tile.explode();
        }
    }

    private void tickDown() {
        if(bombTimer > 0 && this.bombActivated) {
            this.bombTimer--;
            this.changeBombState();
        }
    }

    private void changeBombState() {
        if(!this.bombActivated) {
            this.bombState = BOMB_SPRITE_DEFAULT_PATH;
        } else {
            if(this.bombTimer == 3) {
                this.bombState = BOMB_COUNTDOWN_PATH_3;
            } else if(this.bombTimer == 2) {
                this.bombState = BOMB_COUNTDOWN_PATH_2;
            } else if(this.bombTimer == 1) {
                this.bombState = BOMB_COUNTDOWN_PATH_1;
            } else {
                this.bombState = null;
            }
        }
    }

    public void deactivateBomb() {
        this.bombActivated = false;
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(BOMB_SPRITE_DEFAULT_PATH);
    }

}
