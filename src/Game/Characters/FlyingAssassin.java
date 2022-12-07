package Game.Characters;

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

    private Direction direction;
    protected Image image;
    int xPosition = position.getXPosition();
    int yPosition = position.getYPosition();

    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */

    public FlyingAssassin(Tile position) {
        super(position);
    }

    @Override
    //0 to be replaced with boundary exception
    public void move() {

        if(this.direction == Direction.RIGHT){
            if(this.xPosition + 1 == 0){
                this.direction = Direction.LEFT;
            }
            else {
                setPosition(board[this.xPosition + 1][this.yPosition]);
            }
        }

        if(this.direction == Direction.LEFT){
            if(this.xPosition - 1 == 0){
                this.direction = Direction.RIGHT;
            }
            else {
                setPosition(board[this.xPosition -1][this.yPosition]);
            }
        }

        if(this.direction == Direction.FORWARD){
            if(this.yPosition - 1 == 0){
                this.direction = Direction.BACKWARD;
            }
            else {
                setPosition(board[this.xPosition][this.yPosition - 1]);
            }
        }

        if(this.direction == Direction.BACKWARD) {
            if (this.yPosition + 1 == 0) {
                this.direction = Direction.FORWARD;
            }
            else {
                setPosition(board[this.xPosition][this.yPosition +1]);
            }
        }


    }
}
