package Game.Board;

import Game.Characters.Character;
import Game.Items.Bomb;
import Game.Items.Item;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import Game.Items.Gate;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class Board {
    private static final int TILE_SIDE = 64;
    private static final int NO_OF_SUB_TILES = 4;
    private static final int SUB_TILE_SIDE = TILE_SIDE / 2;
    private static final int OFFSETS_X[] = {0, SUB_TILE_SIDE, 0, SUB_TILE_SIDE};
    private static final int OFFSETS_Y[] = {0, 0, SUB_TILE_SIDE, SUB_TILE_SIDE};
    private final int width;
    private final int widthPixels;
    private final int height;
    private final int heightPixels;
    private final Tile[][] tiles;
    private NavGraph navigableRoutes;

    public Board(int width, int height, Tile[][] tiles) {
        this.width = width;
        this.widthPixels = width * TILE_SIDE;
        this.height = height;
        this.heightPixels = height * TILE_SIDE;
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

    public ArrayList<Character> getAllCharacters() {
        ArrayList<Character> allCharacters = new ArrayList<>();
        for (Tile[] rowOfTiles : tiles) {
            for (Tile tile: rowOfTiles) {
                for (Object object : tile.getObjectsOnTile()) {
                    if (object instanceof Character) {
                        allCharacters.add((Character) object);
                    }
                }
            }
        }
        return allCharacters;
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> allItems = new ArrayList<>();
        for (Tile[] rowOfTiles : tiles) {
            for (Tile tile: rowOfTiles) {
                for (Object object : tile.getObjectsOnTile()) {
                    if (object instanceof Item) {
                        allItems.add((Item) object);
                    }
                }
            }
        }
        return allItems;
    }


    public void refreshNavGraph() {
        this.navigableRoutes = new NavGraph(this);
    }

    public NavGraph getNavGraph() {
        return this.navigableRoutes;
    }

    public void drawBoard(GraphicsContext gc) {

        StackPane gameBoard = new StackPane();
        Canvas tileLayer = new Canvas(widthPixels, heightPixels);
        gameBoard.getChildren().add(tileLayer);

        for (Tile[] rowOfTiles : tiles) {
            for (Tile tile : rowOfTiles) {
                for (int i = 0; i < NO_OF_SUB_TILES; i++) {
                    gc.setFill(tile.getTileColours()[i]);
                    int rectX = tile.getXPosition() * TILE_SIDE + OFFSETS_X[i];
                    int rectY = tile.getYPosition() * TILE_SIDE + OFFSETS_Y[i];
                    gc.fillRect(rectX, rectY, SUB_TILE_SIDE, SUB_TILE_SIDE);
                }
                gc.setStroke(Color.BLACK);
                int outlineX = tile.getXPosition() * TILE_SIDE;
                int outlineY = tile.getYPosition() * TILE_SIDE;
                gc.strokeRect(outlineX, outlineY, TILE_SIDE, TILE_SIDE);
            }
        }

        Canvas itemCharacterLayer = new Canvas(widthPixels, heightPixels);
        gameBoard.getChildren().add(itemCharacterLayer);
        ArrayList<Item> itemsToDraw = getAllItems();
        ArrayList<Character> charactersToDraw = getAllCharacters();

        for(Item i : itemsToDraw) {
            i.draw(gc);
        }

        for(Character c : charactersToDraw) {
            c.draw(gc);
        }

    }




}


