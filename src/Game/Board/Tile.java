package Game.Board;

import javafx.scene.paint.Color;
import Game.Items.Loot;
import java.util.ArrayList;

public class Tile {

    private final Color[] tileColours;
    private final int yPosition;
    private final int xPosition;

    public static final int T_SIZE = 64;

    private String tileState;

    private ArrayList<Object> objectsOnTile;

    public Tile(int xPosition, int yPosition, Color[] tileColours) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.tileColours = tileColours;
        this.tileState = "Normal";
        this.objectsOnTile = new ArrayList<>();
    }

    public int getYPosition() {
        return yPosition;
    }

    public int getXPosition() {
        return xPosition;
    }

    public void setTileState(String type) {
        this.tileState = type;
    }

    public String getTileState() {return this.tileState;}

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

    public boolean hasLoot() {
        for (Object o : objectsOnTile) {
            if (o instanceof Loot) {
                return true;
            }
        }
        return false;
    }
}