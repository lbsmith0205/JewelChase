package Game.Items;


import Game.Board.Tile;
import Game.Characters.*;

import Game.Characters.Character;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Loot class, used to create an instance of either a Cent, Dollar, Diamond, or a Ruby
 * @author Khoi Nguyen Cao
 */
public class Loot extends Item{
    private static final String CENT_SPRITE_PATH = "Sprites/Items/Cent.png";
    private static final String DOLLAR_SPRITE_PATH = "Sprites/Items/Dollar.png";
    private static final String DIAMOND_SPRITE_PATH = "Sprites/Items/Diamond.png";
    private static final String RUBY_SPRITE_PATH = "Sprites/Items/Ruby.png";

    private static final int CENT_COIN_VALUE = 1;
    private static final int DOLLAR_COIN_VALUE = 2;
    private static final int RUBY_VALUE = 5;
    private static final int DIAMOND_VALUE = 10;

    private final int value;

    private String lootSprite;
    private String lootType;
    private Image lootLook;


    public Loot (Tile position, String lootType) {
        super(position);
        switch (lootType){
            case "¢":
                this.lootType = lootType;
                this.value = CENT_COIN_VALUE;
                this.lootSprite = CENT_SPRITE_PATH;
                break;
            case "$":
                this.lootType = lootType;
                this.value = DOLLAR_COIN_VALUE;
                this.lootSprite = DOLLAR_SPRITE_PATH;
                break;
            case "Ru":
                this.lootType = lootType;
                this.value = RUBY_VALUE;
                this.lootSprite = RUBY_SPRITE_PATH;
                break;
            case "Di":
                this.lootType = lootType;
                this.value = DIAMOND_VALUE;
                this.lootSprite = DIAMOND_SPRITE_PATH;
                break;
            default:
                this.lootType = null;
                this.value = 0;
                this.lootSprite = null;
                break;
        }

        this.lootLook = new Image(lootSprite);

    }

    public int getValue() {
        return value;
    }

    public String getLootType() {
        return lootType;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.lootLook, this.position.getXPosition() * IMAGE_SIZE, this.position.getYPosition() * IMAGE_SIZE);
    }


}
