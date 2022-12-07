package Game.Items;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Characters.Character;
import Game.Board.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Clock extends Item{
    private static final String CLOCK_SPRITE_PATH = "Sprites/Items/Clock.png";
    private static final int TIME_VALUE = 10;

    private Image clockLook;

    public Clock(Tile position) {
        super(position);
        this.clockLook = new Image(CLOCK_SPRITE_PATH);
    }

    @Override
    public void interact(Character c) {
        if(c instanceof SmartThief) {
            //Remove time
        } else if (c instanceof Player) {
            //Add time to timer
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.clockLook, this.position.getXPosition(), this.position.getYPosition());
    }
}
