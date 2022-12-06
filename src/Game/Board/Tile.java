package Game.Board;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Tile {

    private final Color[] tileColours;
    private final int yPosition;
    private final int xPosition;

    public static final int T_HEIGHT = 64;
    public static final int T_WIDTH = 64;

    private String tileType;

    private ArrayList<Object> objectsOnTile;

    public Tile(int xPosition, int yPosition, Color[] tileColours) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tileColours = tileColours;
        this.tileType = "Normal";
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setTileType(String type) {
        this.tileType = type;
    }

    public Color[] getTileColours() {
        return tileColours;
    }

    public ArrayList<Object> getObjectsOnTile() {
        return objectsOnTile;
    }

    public void addObjectToTile(Object newObject) {
        objectsOnTile.add(newObject);
    }

    public void removeObjectFromTile(Object targetObject) {
        for (Object object : objectsOnTile) {
            if (object == targetObject) {
                objectsOnTile.remove(object);
            }
        }
    }
}