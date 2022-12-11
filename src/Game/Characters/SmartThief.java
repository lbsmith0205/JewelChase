package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.NavGraphNode;
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
    private String SMART_THIEF_PATH = "Sprites/Characters/ST/Smart_Thief_" + direction.name() + ".png";

    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public SmartThief(Tile position, Direction direction) {
        super(position, direction);
        this.image = new Image(SMART_THIEF_PATH);
    }

    @Override
    public void move(Board currentBoard) {
        NavGraph navigableRoutes = currentBoard.getNavGraph();
        navigableRoutes.unvisitAll();
        ArrayList<ArrayList<Tile>> seed = new ArrayList<>();
        ArrayList<Tile> thisTile = new ArrayList<>();
        thisTile.add(position);
        seed.add(thisTile);
        navigableRoutes.getNode(position).visit();
        ArrayList<ArrayList<Tile>> routes = availableRoutes(seed, navigableRoutes);
        Tile target = optimalMove(routes);
        direction = getNewDirection(position, target);
        position.removeObjectFromTile(this);
        this.position = target;
        position.addObjectToTile(this);
    }

    public ArrayList<ArrayList<Tile>> availableRoutes(ArrayList<ArrayList<Tile>> parentRoutes, NavGraph navigableRoutes) {
        ArrayList<ArrayList<Tile>> childRoutes = new ArrayList<>();
        for (ArrayList<Tile> route : parentRoutes) {
            int x = terminus(route).getXPosition();
            int y = terminus(route).getYPosition();
            for (NavGraphNode accessibleNode : navigableRoutes.getNode(x, y).getAccessibleNodes()) {
                if (!accessibleNode.getHasBeenVisited()) {
                    ArrayList<Tile> newRoute = new ArrayList<Tile>();
                    newRoute.addAll(route);
                    newRoute.add(accessibleNode.getTile());
                    childRoutes.add(newRoute);
                    accessibleNode.visit();
                }
            }
        }
        if (optimalMove(childRoutes) != null) {
            return childRoutes;
        } else {
            return availableRoutes(childRoutes, navigableRoutes);
        }
    }

    private Tile optimalMove(ArrayList<ArrayList<Tile>> availableRoutes) {
        for (ArrayList<Tile> route : availableRoutes) {
            if (terminus(route).hasLoot()) {
                return route.get(1);

            }
        }
        return null;
    }

    private Tile terminus(ArrayList<Tile> route) {
        return route.get(route.size() - 1);
    }

    private Direction getNewDirection(Tile source, Tile target) {
        int proposedXTravel = Math.round(Math.signum(target.getXPosition() - source.getXPosition()));
        int proposedYTravel = Math.round(Math.signum(target.getYPosition() - source.getYPosition()));
        Direction d = null;
        switch (proposedXTravel) {
            case 1 -> d = Direction.RIGHT;
            case -1 -> d = Direction.LEFT;
        }
        switch (proposedYTravel) {
            case 1 -> d = Direction.DOWN;
            case -1 -> d = Direction.UP;
        }
        return d;
    }
}
