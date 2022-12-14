package Game.Items;

import Game.Board.Tile;

import javafx.scene.image.Image;

public class Explosion extends Item {
    private static final String EXPLOSION_SPRITE_PATH = "Sprites/Items/Explosion.png";
    private int fadeTimer = 3;
    private boolean faded = false;

    /**
     * Create an instance of an Explosion.
     *
     * @param position Tile the Explosion will be put on.
     */
    public Explosion(Tile position) {
        super(position);
        refreshImage();
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(EXPLOSION_SPRITE_PATH);

    }

    public void fade() {
        if (fadeTimer > 0) {
            fadeTimer -= 1;
            System.out.println(fadeTimer);
        } else {
            faded = true;
        }
    }
    public boolean hasFaded() {
        return faded;
    }


}
