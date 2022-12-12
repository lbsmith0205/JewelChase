package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.NavGraphNode;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.image.Image;
import java.util.ArrayList;

/**
 * A Smart Thief will seek out the closest Loot it can find and steals it.
 *
 * @author Daniel Baxter
 */

public class SmartThief extends Thief {
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */

    private String pathToImage = "Sprites/Characters/ST/Smart_Thief_" + direction.name() + ".png";

    public SmartThief(Tile position, Direction direction) {
        super(position, direction);
        refreshImage();
    }

    /**
     * Move the Smart Thief along the shortest path to the nearest Loot.
     *
     * @param board Board Smart Thief is on.
     */
    @Override
    public void move(Board board) {
        NavGraph navigableRoutes = board.getNavGraph();
        navigableRoutes.unvisitAll();
        ArrayList<ArrayList<Tile>> seed = new ArrayList<>();
        ArrayList<Tile> thisTile = new ArrayList<>();
        thisTile.add(position);
        seed.add(thisTile);
        navigableRoutes.getNode(position).visit();
        Tile target = null;
        try {
            ArrayList<ArrayList<Tile>> routes = availableRoutes(seed, navigableRoutes);
            target = optimalMove(routes);
            direction = getNewDirection(position, target);
        } catch (StackOverflowError e) {
            while (target == null) {
                target = randomValidTile(board);
            }
        }
        refreshImage();
        position.removeObjectFromTile(this);
        this.position = target;
        position.addObjectToTile(this);
    }

    /**
     * Recursive method which returns a list of the shortest routes from the Smart Thief's position to every other Tile,
     *  each recursion gets routes to tiles 1 tile further away.
     *
     * @param parentRoutes
     * @param navigableRoutes
     * @return
     */
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
        }
            return availableRoutes(childRoutes, navigableRoutes);
    }

    /**
     * Returns the first tile in a route to the nearest loot if one exists, returns null otherwise.
     * @param availableRoutes
     * @return
     */
    private Tile optimalMove(ArrayList<ArrayList<Tile>> availableRoutes) {
        for (ArrayList<Tile> route : availableRoutes) {
            if (terminus(route).hasLoot()) {
                return route.get(1);
            }
        }
        return null;
    }

    /**
     * Returns the tile at the end of a specified route.
     * @param route
     * @return
     */
    private Tile terminus(ArrayList<Tile> route) {
        return route.get(route.size() - 1);
    }

    private Tile randomValidTile(Board board) {
        direction = Direction.getRandomDirection();
        return board.findAccessibleTile(direction, position,1);
    }

    @Override
    protected void refreshImage() {
        pathToImage = "Sprites/Characters/ST/Smart_Thief_" + direction.name() + ".png";
        this.image = new Image(pathToImage);
    }
}
