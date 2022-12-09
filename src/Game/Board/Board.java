package Game.Board;

import Game.Items.Bomb;
import javafx.scene.paint.Color;
import Game.Items.Gate;

public class Board {
    private final int width;
    private final int height;
    private final Tile[][] tiles;
    private NavGraph navigableRoutes = null;

    public Board(int width, int height, Tile[][] tiles) {
        this.width = width;
        this.height = height;
        this.tiles = tiles;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public boolean hasTile(Tile t) {
        int x = t.getXPosition();
        int y = t.getYPosition();
        for (Tile[] tile : tiles) {
            for (int i = 0; i < tiles.length; i++) {
                if (tile[i] == tiles[x][y]) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isLegalMove(Tile source, Tile target) {
        for (Object object : target.getObjectsOnTile()) {
            if (object instanceof Gate || object instanceof Bomb) {
                return false;
            }
        }
        for (Color currentColour : source.getTileColours()) {
            for (Color targetColour : target.getTileColours()) {
                if (targetColour == currentColour) {
                    return true;
                }
            }
        }
        return false;
    }


    public void refreshNavGraph() {
        this.navigableRoutes = new NavGraph(this);
    }

    public NavGraph getNavGraph() {
        return this.navigableRoutes;
    }




}
