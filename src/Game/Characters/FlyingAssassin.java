package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * FlyingAssassin.java
 * Sub class of Character.
 * @author Oliver Rudge.
 */

public class FlyingAssassin extends Character {
    private String FLYING_ASSASSIN_PATH = "Sprites/Characters/Flying_Assassin_" + direction + ".png";

    int xPosition = position.getXPosition();
    int yPosition = position.getYPosition();

    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */

    public FlyingAssassin(Tile position, Direction direction) {
        super(position, direction);
        this.image = new Image(FLYING_ASSASSIN_PATH);
    }

    public void move(Board currentBoard) {
        int targetX = xPosition;
        int targetY = yPosition;

        switch (direction) {
            case UP -> targetY--;
            case LEFT -> targetX--;
            case DOWN -> targetY++;
            case RIGHT -> targetX++;
        }

        try {
            Tile target = currentBoard.getTile(targetX, targetY);
            position.removeObjectFromTile(this);
            this.position = target;
            position.addObjectToTile(this);
            this.direction = direction;
        } catch (ArrayIndexOutOfBoundsException e) {
            direction = direction.turnBack();
            move(currentBoard);
        }

    }


}
