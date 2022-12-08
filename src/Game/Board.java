package Game;

public class Board {
    private int width;
    private int height;
    private Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.tiles = new Tile[width][height];
    }


}
