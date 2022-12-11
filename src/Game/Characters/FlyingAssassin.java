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


    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    private String pathToImage = "Sprites/Characters/FA/Flying_Assassin_" + direction.name() + ".png";
    public FlyingAssassin(Tile position, Direction direction) {
        super(position, direction);
        refreshImage();
    }

    @Override
    public void move(Board currentBoard) {
        int targetX = position.getXPosition();
        int targetY = position.getYPosition();

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
        } catch (ArrayIndexOutOfBoundsException e) {
            direction = direction.turnBack();
            refreshImage();
            move(currentBoard);
        }


    }

    @Override
    protected void refreshImage() {
        pathToImage = "Sprites/Characters/FA/Flying_Assassin_" + direction.name() + ".png";
        this.image = new Image(pathToImage);
    }


}
