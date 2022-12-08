package Game.Items;


import Game.Board.Tile;
import Game.Characters.Character;

import Game.Characters.FloorFollowingThief;
import Game.Characters.Player;
import Game.Characters.SmartThief;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

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
    private Image lootLook;

    //need to change the way to construct a Loot
    public Loot (Tile position, String lootType) {
        super(position);
        switch (lootType){
            case "¢":
                this.value = CENT_COIN_VALUE;
                this.lootSprite = CENT_SPRITE_PATH;
                break;
            case "$":
                this.value = DOLLAR_COIN_VALUE;
                this.lootSprite = DOLLAR_SPRITE_PATH;
                break;
            case "Ru":
                this.value = RUBY_VALUE;
                this.lootSprite = RUBY_SPRITE_PATH;
                break;
            case "Di":
                this.value = DIAMOND_VALUE;
                this.lootSprite = DIAMOND_SPRITE_PATH;
                break;
            default:
                this.value = 0;
                this.lootSprite = null;
                break;
        }

        this.lootLook = new Image(lootSprite);

    }

    public int getValue() {
        return value;
    }

    public int interact(Character c) {
        if(c instanceof SmartThief || c instanceof FloorFollowingThief) {
            this.remove();
            return 0;
        }

        if(c instanceof Player) {
            this.remove();
            return this.value;
        }

        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.lootLook, this.position.getXPosition(), this.position.getYPosition());
    }
}
