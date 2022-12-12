package Game.Items;

import Game.Board.Tile;

import javafx.scene.image.Image;

public class Explosion extends Item {
    private static final String EXPLOSION_SPRITE_PATH = "Sprites/Items/Explosion.png";

    /**
     * Create an instance of an Explosion.
     *
     * @param position Tile the Explosion will be put on.
     */
    public Explosion(Tile position) {
        super(position);
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(EXPLOSION_SPRITE_PATH);

    }
}
