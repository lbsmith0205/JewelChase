package Game;

public class Board {
    private final int width;
    private final int height;
    private final Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }

    public Tile findTile(int x, int y) {
        return tiles[x][y];
    }

}
