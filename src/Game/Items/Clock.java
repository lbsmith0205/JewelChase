package Game.Items;

import Game.Characters.Player;
import Game.Characters.Character;
import Game.Board.Tile;

import Game.Characters.Thief;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Clock extends Item{
    private static final String CLOCK_PATH = "Sprites/Items/Clock.png";
    private static final int TIME_VALUE = 10;

    private Image clockImage;

    public Clock(Tile position) {
        super(position);
        this.clockImage = new Image(CLOCK_PATH);
    }

    public int interact(Character c) {
        if (c instanceof Player) {
            this.remove();
            return TIME_VALUE;
        }
        this.remove();
        return -(TIME_VALUE);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.clockImage, this.position.getXPosition() * IMAGE_SIZE, this.position.getYPosition() * IMAGE_SIZE);
    }
}
