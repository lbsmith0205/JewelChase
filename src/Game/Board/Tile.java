package Game.Board;

import Game.Items.Bomb;
import Game.Items.Door;
import Game.Items.Gate;
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
        objectsOnTile.remove(targetObject);
    }

    public boolean hasLoot() {
        for (Object o : objectsOnTile) {
            if (o instanceof Loot) {
                return true;
            }
        }
        return false;
    }

    public String getTileInString() {
        String colour = "";
        for(int i = 0; i < tileColours.length; i++) {
            if(tileColours[i] == Color.INDIANRED) {
                colour += "R";
            } else if(tileColours[i] == Color.SPRINGGREEN) {
                colour += "G";
            } else if(tileColours[i] == Color.ROYALBLUE) {
                colour += "B";
            } else if(tileColours[i] == Color.KHAKI) {
                colour += "Y";
            } else if(tileColours[i] == Color.CYAN) {
                colour += "C";
            } else if(tileColours[i] == Color.MEDIUMPURPLE) {
                colour += "M";
            }
        }

        return colour;
    }

    public boolean hasBomb() {
        for (Object object : objectsOnTile) {
            if (object instanceof Bomb) {
                return true;
            }
        }
        return false;
    }

    public void explode() {
        ArrayList<Object> remnants = new ArrayList<>();
        for (Object object : objectsOnTile) {
            if (object instanceof Bomb) {
                ((Bomb) object).explode();
            }

            // Note: consult group on effects of bomb on characters.
            if (object instanceof Door || object instanceof Gate) {
                remnants.add(object);
            }
            objectsOnTile = remnants;
            if (objectsOnTile.isEmpty()) {
                //objectsOnTile.add(new Fire fire); implement fire sprite.
            }

        }
    }
}