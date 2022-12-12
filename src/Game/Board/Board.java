package Game.Board;

import Game.Characters.Character;
import Game.Characters.Player;
import Game.Characters.Thief;
import Game.Direction;
import Game.Items.*;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

/**
 *  A Board can be used to create the grid of Tiles for the Level.
 *
 * @author Daniel Baxter
 */
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

    /**
     * Create an instance of Board.
     *
     * @param width value for width of the Board.
     * @param height value for height of the Board.
     * @param tiles 2D array list of Tiles.
     */
    public Board(int width, int height, Tile[][] tiles) {
        this.width = width;
        this.widthPixels = width * TILE_SIDE;
        this.height = height;
        this.heightPixels = height * TILE_SIDE;
        this.tiles = tiles;
    }

    /**
     * Get the height of the Board.
     *
     * @return height of the Board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the Board.
     *
     * @return width of the Board.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the Tile on the Board with the given coordinates.
     *
     * @param x x position of the Tile.
     * @param y y position of the Tile.
     * @return Tile that match the x and y.
     */
    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    /**
     * Check if the Board has the Tile.
     *
     * @param t Tile to check.
     * @return true if the Tile exists, false if not.
     */
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

    /**
     * Check if the Tile can be moved onto from the current Tile.
     *
     * @param source Tile currently on.
     * @param target Tile need to move onto.
     * @return true if it meets the constraints, false if not.
     */
    public boolean isLegalMove(Tile source, Tile target, String characterMode) {
        for (Object object : target.getObjectsOnTile()) {
            switch (characterMode) {
                case "SmartThief" -> {
                    if (object instanceof Gate || object instanceof Bomb || object instanceof Thief) {
                        return false;
                    }
                }
                default -> {
                    if (object instanceof Gate || object instanceof Bomb) {
                        return false;
                    }
                }
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


    /**
     * Get an ArrayList of all the Characters on the Board.
     *
     * @return ArrayLisst of Characters on the Board.
     */
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

    /**
     * Get an ArrayList of all the Items on the Board.
     *
     * @return ArrayList of Items on the Board.
     */
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


    /**
     * Create a new navigation graph.
     */
    public void refreshNavGraph() {
        this.navigableRoutes = new NavGraph(this);
    }

    /**
     * Get the navigation graph.
     *
     * @return the navigation graph.
     */
    public NavGraph getNavGraph() {
        return this.navigableRoutes;
    }

    /**
     * Get the Player on the Board.
     *
     * @return Player on the Board.
     */
    public Player getPlayer() {
        for (Character character : getAllCharacters()) {
            if (character instanceof Player) {
                return (Player) character;
            }
        }
        return null;
    }

    /**
     * Draw the Tiles, Items and Characters on the Canvas.
     *
     * @param gc Graphic Context buffer draw on the Canvas.
     */
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

    /**
     * Find the Tile that is movable onto from the Board.
     *
     * @param d Direction moving towards to.
     * @param source Tile currently on.
     * @param targetDistance how far the Tile will be.
     * @return Tile that can be moved on.
     */
    public Tile findAccessibleTile(Direction d, Tile source, int targetDistance) {
        try {
            int x = source.getXPosition();
            int y = source.getYPosition();
            switch (d) {
                case UP -> y -= targetDistance;
                case LEFT -> x -= targetDistance;
                case DOWN -> y += targetDistance;
                case RIGHT -> x += targetDistance;
            }

            Tile target = tiles[x][y];
            if (this.isLegalMove(source, target, "SmartThief")) {
                return target;
            } else {
                if (target.hasBomb()) {
                    return null;
                }
                targetDistance++;
                return findAccessibleTile(d, source, targetDistance);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Find the next Tile that has the same Color as the current Tile.
     *
     * @param source Tile currently on.
     * @param d Direction looking at.
     * @param requiredColour Color needed to find.
     * @return the next Tile that match the requirements
     */
    public Tile getAdjacentTileOfRequiredColour(Tile source, Direction d, Color requiredColour) {
        try {
            int x = source.getXPosition();
            int y = source.getYPosition();
            Color[] dummyTileColours = {requiredColour, requiredColour, requiredColour, requiredColour};
            Tile dummyTile = new Tile(x, y, dummyTileColours);
            switch (d) {
                case UP -> y--;
                case LEFT -> x--;
                case DOWN -> y++;
                case RIGHT -> x++;
            }
            Tile target = tiles[x][y];
            if (isLegalMove(dummyTile, target, "Default")) {
                return target;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
        return null;
    }

    /**
     * Find and get all the Bombs that exists on the Board.
     *
     * @return ArrayList of all the Bombs found.
     */
    public ArrayList<Bomb> getAllBombs() {
        ArrayList<Bomb> listOfBombs = new ArrayList<>();
        for (Object item : getAllItems()) {
            if (item instanceof Bomb) {
                listOfBombs.add((Bomb) item);
            }
        }
        return listOfBombs;
    }

    public void openGates(String type) {
        ArrayList<Gate> gates = new ArrayList<>();
        for (Item item : getAllItems()) {
            if (item instanceof Gate) {
                gates.add((Gate) item);
            }
        }
        for (Gate gate : gates) {
            if (gate.getType().equals(type)) {
                gate.getPosition().removeObjectFromTile(gate);
            }
        }
    }

    public void placeLootableDoor() {
        if (!hasLoot()) {
            Door door = getDoor();
            Tile doorTile = door.getPosition();
            Loot lootableDoor = new Loot (doorTile, "LD");
            doorTile.addObjectToTile(lootableDoor);
        }
    }

    private boolean hasLoot() {
        for (Item item : getAllItems()) {
            if (item instanceof Loot) {
                return true;
            }
        }
        return false;
    }

    private Door getDoor() {
        for (Item item : getAllItems()) {
            if (item instanceof Door) {
                return (Door) item;
            }
        }
        return null;
    }





}


