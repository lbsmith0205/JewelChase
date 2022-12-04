package Game.Items;

import Game.Board;
import Game.Tile;

public class Bomb extends Item{
    private static final int BOMB_DEFAULT_TIMER = 3;

    private final Board boardIn;
    private final Tile[] activationTiles;
    private boolean bombActivated = false;
    private int bombTimer;


    public Bomb(Tile position, Board board) {
        super(position);
        this.bombTimer = BOMB_DEFAULT_TIMER;
        this.activationTiles = new Tile[8];
        this.boardIn = board;

        int bombX = position.getXPosition();
        int bombY = position.getYPosition();

        for(int i = 0; i < activationTiles.length; i++) {
            for(int x = bombX - 1; x < bombX + 2; x++) {
                for(int y = bombY - 1; y < bombY + 2; y++) {
                    if(y != bombY && x != bombX) {
                        activationTiles[i] = boardIn.findTile(x,y);
                    }
                }
            }
        }
    }

    public void activateBomb(Tile position) {
        for(int i = 0; i < activationTiles.length; i++) {
            if(position == activationTiles[i]) {
                this.bombActivated = true;
            }
        }
    }

    private void explode() {
        if(!bombActivated || bombTimer > 0) {
            return;
        }


    }

    public void tickDown() {
        this.bombTimer = bombTimer - 1;
    }

    public void deactivateBomb() {
        this.bombActivated = false;
    }

    @Override
    public void interact() {

    }
}
