package Game.Items;


import Game.Board.Board;
import Game.Board.Tile;
import Game.Characters.Character;
import Game.Characters.Player;
import javafx.scene.image.Image;

/**
 * Loot class, used to create an instance of either a Cent, Dollar, Diamond, or a Ruby.
 *
 * @author Khoi Nguyen Cao
 */
public class Loot extends Item{
    private static final String CENT_SPRITE_PATH = "Sprites/Items/Cent.png";
    private static final String DOLLAR_SPRITE_PATH = "Sprites/Items/Dollar.png";
    private static final String DIAMOND_SPRITE_PATH = "Sprites/Items/Diamond.png";
    private static final String RUBY_SPRITE_PATH = "Sprites/Items/Ruby.png";
    private static final String LOOTABLE_DOOR_PATH = "Sprites/Items/LootableDoor.png";

    private static final String RED_LEVER_PATH = "Sprites/Items/Levers/LeverRed.png";
    private static final String GREEN_LEVER_PATH = "Sprites/Items/Levers/LeverGreen.png";
    private static final String BLUE_LEVER_PATH = "Sprites/Items/Levers/LeverBlue.png";
    private static final String CLOCK_PATH = "Sprites/Items/Clock.png";
    private static final int CENT_COIN_VALUE = 1;
    private static final int DOLLAR_COIN_VALUE = 2;
    private static final int RUBY_VALUE = 5;
    private static final int DIAMOND_VALUE = 10;
    private static final int CLOCK_REWARD = 10; // seconds

    private final int value;

    private String lootSprite;
    private String lootType;


    /**
     * Create an instance of a Loot.
     *
     * @param position Tile the Loot will be put on.
     * @param lootType type of the Loot will be.
     */
    public Loot (Tile position, String lootType) {
        super(position);
        this.lootType = lootType;
        switch (lootType) {
            case "¢" -> {
                this.value = CENT_COIN_VALUE;
                this.lootSprite = CENT_SPRITE_PATH;
            }
            case "$" -> {
                this.value = DOLLAR_COIN_VALUE;
                this.lootSprite = DOLLAR_SPRITE_PATH;
            }
            case "Ru" -> {
                this.value = RUBY_VALUE;
                this.lootSprite = RUBY_SPRITE_PATH;
            }
            case "Di" -> {
                this.value = DIAMOND_VALUE;
                this.lootSprite = DIAMOND_SPRITE_PATH;
            }
            case "LD" -> {
                this.value = 0;
                this.lootSprite = LOOTABLE_DOOR_PATH;
            }
            case "RL" -> {
                this.value = 0;
                this.lootSprite = RED_LEVER_PATH;
            }
            case "GL" -> {
                this.value = 0;
                this.lootSprite = GREEN_LEVER_PATH;
            }
            case "BL" -> {
                this.value = 0;
                this.lootSprite = BLUE_LEVER_PATH;
            }
            case "Cl" -> {
                this.value = 0;
                this.lootSprite = CLOCK_PATH;
            }
            default -> {
                this.lootType = null;
                this.value = 0;
                this.lootSprite = null;
            }
        }
        refreshImage();
    }

    /**
     * Get how much the Loot is worth.
     *
     * @return value of the Loot.
     */
    public int getValue() {
        return value;
    }

    public void pullLever(Board board) {
        switch (lootType) {
            case "RL" -> board.openGates("RGt");
            case "GL" -> board.openGates("GGt");
            case "BL" -> board.openGates("BGt");
        }
    }

    public int adjustTime(Character character) {
        if (!lootType.equals("Cl")) {
            return 0;
        }
        if (character instanceof Player) {
            return CLOCK_REWARD;
        } else {
            return -CLOCK_REWARD;
        }
    }

    /**
     * Get the type of the Loot.
     *
     * @return type of the Loot in String.
     */
    public String getLootType() {
        return lootType;
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(lootSprite);

    }


}
