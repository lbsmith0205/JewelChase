package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.NavGraphNode;
import Game.Board.Tile;
import Game.Direction;
import java.util.ArrayList;

/**
 * SmartThief.java
 * Sub class of Character.
 * @author Daniel Baxter
 */

public class SmartThief extends Thief {
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public SmartThief(Tile position, Direction direction) {
        super(position, direction);
    }

    public void move(Board currentBoard) {
        NavGraph navigableRoutes = currentBoard.getNavGraph();
        ArrayList<ArrayList<Tile>> seed = new ArrayList<>();
        ArrayList<Tile> thisTile = new ArrayList<>();
        thisTile.add(this.position);
        seed.add(thisTile);
        ArrayList<ArrayList<Tile>> routes = availableRoutes(seed, navigableRoutes);
        Tile target = optimalMove(routes);
        position.removeObjectFromTile(this);
        this.position = target;
        position.addObjectToTile(this);
        //this.direction = direction;

    }

    public ArrayList<ArrayList<Tile>> availableRoutes(ArrayList<ArrayList<Tile>> parentRoutes, NavGraph navigableRoutes) {
        ArrayList<ArrayList<Tile>> childRoutes = new ArrayList<>();
        for (ArrayList<Tile> route : parentRoutes) {
            int x = terminus(route).getXPosition();
            int y = terminus(route).getYPosition();
            for (NavGraphNode accessibleNode : navigableRoutes.getNode(x, y).getAccessibleNodes()) {
                ArrayList<Tile> newRoute = route;
                newRoute.add(accessibleNode.getTile());
                childRoutes.add(newRoute);
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
                return route.get(0);
            }
        }
        return null;
    }

    private Tile terminus(ArrayList<Tile> route) {
        return route.get(route.size() - 1);
    }




}
