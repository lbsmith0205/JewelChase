package Game.Board;

import javafx.scene.paint.Color;

public class Board {
    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
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

    public Tile findTile(int x, int y) {
        return tiles[x][y];
    }

    public boolean hasTile(int x, int y) {
        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                if(tiles[i][j] == tiles[x][y]) {
                    return true;
                }
            }
        }
        return false;

        //return findTile(x,y) != null;
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
