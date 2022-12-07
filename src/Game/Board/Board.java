package Game.Board;

public class Board {
    private final int width;
    private final int height;

    private final int size;
    private final Tile[][] tiles;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.size = width * height;
        this.tiles = new Tile[width][height];
    }

    public Tile findTile(int x, int y) {
        return tiles[x][y];
    }

    public Tile findTile(Tile t) {
        int x = t.getXPosition();
        int y = t.getYPosition();

        return tiles[x][y];
    }

    public  boolean hasTile(int x, int y) {
        /*boolean tileExist = false;

        for(int i = 0; i < tiles.length; i++) {
            for(int j = 0; j < tiles.length; j++) {
                if(tiles[i][j] == tiles[x][y]) {
                    tileExist = true;
                    break;
                }
            }
        }
        return tileExist;*/

        return findTile(x,y) != null;
    }

    public boolean hasTile(Tile t) {
        int x = t.getXPosition();
        int y = t.getYPosition();

        return findTile(x,y) != null;
    }

    /**
     * Returns the number of tiles in the board.
     *
     * @return int, number of tiles in instance of board.
     */
    public int getSize() {
        return size;
    }

}
