package Game.Items;

import Game.Characters.SmartThief;
import Game.Characters.Character;
import Game.Tile;

public class Clock extends Item{
    private static final int TIME_VALUE = 20;

    public Clock(Tile position) {
        super(position);
    }

    @Override
    public void interact(Character c) {
        if(c instanceof SmartThief) {

        }
    }
}
