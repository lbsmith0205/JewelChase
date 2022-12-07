package Game.Items;

import Game.Board.Board;
import Game.Characters.FloorFollowingThief;
import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Board.Tile;
import Game.Characters.Character;

public class Bomb extends Item{
    private static final String BOMB_SPRITE_DEFAULT_PATH = "Sprites/Items/Bombs/Bomb.png";
    private static final String BOMB_SPRITE_TIME_3_PATH = "Sprites/Items/Bombs/Bomb3.png";
    private static final String BOMB_SPRITE_TIME_2_PATH = "Sprites/Items/Bombs/Bomb2.png";
    private static final String BOMB_SPRITE_TIME_1_PATH = "Sprites/Items/Bombs/Bomb1.png";


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
            for(int x = bombX - 1; x < bombX + 1; x++) {
                for(int y = bombY - 1; y < bombY + 1; y++) {
                    if(y != bombY && x != bombX) {
                        activationTiles[i] = boardIn.findTile(x,y);
                    }
                }
            }
        }
    }

    private void activateBomb(Tile position) {
        for(Tile t : activationTiles) {
            if(position == t) {
                this.bombActivated = true;
            }
        }
    }

    private void explode(Tile explodingTile) {
        if(!bombActivated || bombTimer > 0) {
            return;
        }

        int currentTileX = explodingTile.getXPosition();
        int currentTileY = explodingTile.getYPosition();

        Tile tileUp = this.boardIn.findTile(currentTileX, (currentTileY + 1));
        Tile tileDown = this.boardIn.findTile(currentTileX, (currentTileY - 1));
        Tile tileLeft = this.boardIn.findTile((currentTileX - 1), currentTileY);
        Tile tileRight = this.boardIn.findTile((currentTileX + 1), currentTileY);

        if(bombTimer == 0) {
            this.remove();
            explodingTile.setTileState("Explode");

            if(currentTileX == this.position.getXPosition()) {
                //Exploding up
                if (this.boardIn.hasTile(tileUp)) {
                    explode(this.boardIn.findTile(tileUp));
                }

                //Exploding down
                if (this.boardIn.hasTile(tileDown)) {
                    explode(this.boardIn.findTile(tileDown));
                }
            }

            if(currentTileY == this.position.getYPosition()) {
                //Exploding left
                if (this.boardIn.hasTile(tileLeft)) {
                    explode(this.boardIn.findTile(tileLeft));
                }

                //Exploding right
                if (this.boardIn.hasTile(tileRight)) {
                    explode(this.boardIn.findTile(tileRight));
                }
            }

        }

    }

    private void tickDown() {
        if(bombTimer > 0 && this.bombActivated) {
            this.bombTimer = bombTimer--;
        }
    }

    public void deactivateBomb() {
        this.bombActivated = false;
    }

    @Override
    public void interact(Character c) {
        if(!this.bombActivated) {
            if(c instanceof SmartThief || c instanceof FloorFollowingThief || c instanceof Player) {
                this.activateBomb(c.getPosition());
            }
        }

        if(this.bombTimer == 0 || this.position.getTileState().equalsIgnoreCase("Explode")) {
            this.explode(this.position);
        } else {
            this.tickDown();
        }

    }
}
