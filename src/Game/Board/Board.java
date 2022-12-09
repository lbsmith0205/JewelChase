package Game.Board;

import Game.Items.Bomb;
import javafx.scene.paint.Color;
import Game.Items.Gate;

import java.util.Arrays;

public class Board {
    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Board(int width, int height, Tile[][] tile2DArray) {
        this.width = width;
        this.height = height;
        this.tiles = tile2DArray;
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
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                if(tiles[i][j] == tiles[x][y]) {
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

}
