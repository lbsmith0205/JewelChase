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
        refreshImage();
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

    @Override
    protected void refreshImage() {
        pathToImage = "Sprites/Characters/ST/Smart_Thief_" + direction.name() + ".png";
        this.image = new Image(pathToImage);
    }
}
