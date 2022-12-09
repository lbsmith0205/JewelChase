package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * SmartThief.java
 * Sub class of Character.
 * @author Daniel Baxter
 */

public class SmartThief extends Thief {
    private static final String SMART_THIEF_PATH = "Sprites/Characters/SmartThief.png";

    private Image smartThiefImage;
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public SmartThief(Tile position, Direction direction) {
        super(position, direction);
        this.smartThiefImage = new Image(SMART_THIEF_PATH);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.smartThiefImage, this.position.getXPosition() * IMAGE_SIZE,
                this.position.getYPosition() * IMAGE_SIZE);
    }

    public void move(Board currentBoard) {
        NavGraph navigableRoutes = currentBoard.getNavGraph();


    }

    private Tile optimalMove(ArrayList<ArrayList<Tile>> availableRoutes) {
        for (ArrayList<Tile> route : availableRoutes) {
            if (terminus(route).hasLoot()) {
                return route.get(0);
            }
        }
        return null;
    }

    private Tile terminus(ArrayList<Tile> route) {
        return route.get(route.size() - 1);
    }




}
