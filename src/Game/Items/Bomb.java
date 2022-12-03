package Game.Items;

import Game.Tile;

public class Bomb extends Item{
    private static final int BOMB_DEFAULT_TIMER = 3;

    private final Tile[] activationTiles;
    private boolean bombActivated = false;
    private int bombTimer;


    public Bomb(Tile position) {
        super(position);
        this.bombTimer = BOMB_DEFAULT_TIMER;
        this.activationTiles = new Tile[8];

        int bombX = position.getXPosition();
        int bombY = position.getYPosition();

        for(int i = 0; i < activationTiles.length; i++) {
            for(int x = bombX - 1; x < bombX + 2; x++) {
                for(int y = bombY - 1; y < bombY + 2; y++) {
                    if(y != bombY && x != bombX) {
                        activationTiles[i] = ;
                    }
                }
            }
        }
    }

    public void activateBomb(int xPos, int yPos) {
        for(int i = 0; i < activationTiles.length; i++) {
            if(xPos == activationTiles[i].getXPosition() && yPos == activationTiles[i].getYPosition()) {
                this.bombActivated = true;
            }
        }
    }

    public void explode() {
        if(!bombActivated) {
            return;
        }


    }

    public void deactivateBomb() {
        this.bombActivated = false;
    }

    @Override
    public void interact() {

    }
}
