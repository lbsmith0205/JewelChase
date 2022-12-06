package Game.Items;

import Game.Board.Board;
import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Board.Tile;
import Game.Characters.Character;

public class Door extends Item {
    private final Board boardIn;
    private boolean isOpen;

    public Door(Tile position, Board board) {
        super(position);
        this.boardIn = board;
        this.isOpen = false;
    }

    public void openDoor() {
        this.isOpen = true;
    }

    public void closeDoor() {
        this.isOpen = false;
    }

    @Override
    public void interact(Character c) {
        if(this.isOpen) {
            if (c instanceof SmartThief) {
                //Lose
            } else if (c instanceof Player) {
                //WIN
            }
        }
    }
}
